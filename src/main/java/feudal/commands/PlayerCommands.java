package feudal.commands;

import feudal.data.Auction;
import feudal.data.FeudalKingdom;
import feudal.data.FeudalPlayer;
import feudal.data.cache.CacheFeudalKingdoms;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.data.database.KingdomDBHandler;
import feudal.utils.RTPUtils;
import feudal.utils.enums.professionEnums.ProfessionIDE;
import feudal.utils.wrappers.ChunkWrapper;
import feudal.utils.wrappers.ItemStackWrapper;
import feudal.visual.Menus;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerCommands implements CommandExecutor {

    private boolean confirmDeletion;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        switch (args[0]) {

            case "create":

                if (args.length < 2) {

                    player.sendMessage("Пожалуйста укажите желаемое имя королевства!");
                    break;

                }

                createKingdomCommand(player, args[1]);
                break;

            case "prof":

                if (CacheFeudalPlayers.getFeudalPlayer(player).getProfessionID() != 0) {

                    player.sendMessage("У вас уже есть профессия!");
                    break;

                }

                new Menus(player).professionSelectionMenu();
                break;

            case "withdraw":

                if (args.length < 2) {

                    player.sendMessage("Пожалуйста укажите сумму!");
                    break;

                } else if (args[1].length() > 10) {

                    player.sendMessage("Слишком большая сумма для снятия");
                    break;

                }

                withdrawMoneyFromTreasury(KingdomDBHandler.getPlayerKingdom(player), player, Integer.parseInt(args[1].replaceAll("[^0-9]", "")));

                break;

            case "replenish":

                if (args.length < 2) {

                    player.sendMessage("Пожалуйста укажите сумму!");
                    break;

                } else if (args[1].length() > 10) {

                    player.sendMessage("Слишком большая сумма для пополнения");
                    break;

                }

                replenishMoneyFromTreasury(KingdomDBHandler.getPlayerKingdom(player), player, Integer.parseInt(args[1].replaceAll("[^0-9]", "")));

                break;


            case "invite":

                invitePlayer(CacheFeudalPlayers.getFeudalPlayer(player).getKingdomName(), player, args[1]);

                break;

            case "kick":

                kickPlayer(CacheFeudalPlayers.getFeudalPlayer(player).getKingdomName(), player, args[1]);

                break;

            case "reject":

                reject(player, args[1]);

                break;

            case "disband":

                disband(player);

                break;

            case "leave":

                leave(player);

                break;

            case "addbaron":

                addBaron(CacheFeudalPlayers.getFeudalPlayer(player).getKingdomName(), player, args[1]);

                break;

            case "removebaron":

                removeBaron(CacheFeudalPlayers.getFeudalPlayer(player).getKingdomName(), player, args[1]);

                break;

            case "mail":

                new Menus(player).mailMenu();

                break;

            case "claim":

                claim(CacheFeudalPlayers.getFeudalPlayer(player).getKingdomName(), player);

                break;

            case "up":

                up(player);

                break;

            case "rtp":

                player.teleport(RTPUtils.rtpCalc(player, 10, 1000, 10, 1000));

                break;

            case "ah":

                ah(player);

                break;

            case "sell":

                if (player.getInventory().getItemInMainHand() == null) {

                    player.sendMessage("Возьмите предмет в основную руку!");
                    break;

                } else if (args.length < 2) {

                    player.sendMessage("Укажите сумму!");
                    break;

                }

                sell(player.getInventory().getItemInMainHand(), Integer.parseInt(args[1].replaceAll("[^0-9]", "")));

                break;

            default:
                player.sendMessage("Неизвестная команда! Введите /f help, чтобы посмотреть доступные команды");

        }

        return false;
    }

    private void createKingdomCommand(@NotNull Player player, String kingdomName) {

        if (!checkKingdomName(kingdomName)) {

            player.sendMessage("Нельзя создать королевство с таким именем!");
            return;

        }

        List<String> membersUUID = new ArrayList<>();
        membersUUID.add(player.getUniqueId().toString());

        createKingdom(kingdomName, player, membersUUID);

    }

    private void up(@NotNull Player player) {
        new Menus(player).upgradeProfessionMenu();
    }

    private boolean checkKingdomName(@NotNull String kingdomName) {

        kingdomName.replaceAll("[^A-Za-zА-Яа-я0-9]", "");

        return kingdomName.length() <= 16 &&
                kingdomName.length() > 3 &&
                CacheFeudalKingdoms.getKingdomInfo().get(kingdomName) == null;

    }

    private void createKingdom(@NotNull String kingdomName, @NotNull Player player, List<String> membersUUID) {

        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);

        if (!feudalPlayer.getKingdomName().equals("")) {

            player.sendMessage("Вы уже состоите в королевстве!");
            return;

        }

        KingdomDBHandler.createNewKingdom(kingdomName, player, membersUUID, new ArrayList<>(), new ArrayList<>());

        FeudalKingdom feudalKingdom = new FeudalKingdom(kingdomName);
        feudalKingdom.setKingdomName(kingdomName)
                .setKing(player.getUniqueId())
                .setBalance(10000)
                .setReputation(1000)
                .setMembers(membersUUID)
                .setMaxNumberMembers(5)
                .setBarons(new ArrayList<>())
                .setTerritory(new ArrayList<>());

        CacheFeudalKingdoms.getKingdomInfo().put(kingdomName, feudalKingdom);

        feudalPlayer.setKingdomName(kingdomName);
        player.sendMessage("Вы успешно создали королевство: " + kingdomName);

    }

    private void withdrawMoneyFromTreasury(@NotNull String kingdomName, @NotNull Player player, int colum) {

        FeudalKingdom feudalKingdom = CacheFeudalKingdoms.getKingdomInfo().get(kingdomName);

        if (kingdomName.equals("")) {

            player.sendMessage("Вы не состоите в королевстве!");
            return;

        } else if (!feudalKingdom.getBaronsUUID().contains(player.getUniqueId().toString()) &&
                !feudalKingdom.getKingUUID().equals(player.getUniqueId())) {

            player.sendMessage("Недостаточно прав для снятия золота с казны!");
            return;

        }

        if (feudalKingdom.getBalance() < colum) {

            player.sendMessage("В казне недостаточно залота");
            return;

        }

        feudalKingdom.takeBalance(colum);
        CacheFeudalPlayers.getFeudalPlayer(player).takeBalance(colum - colum / 100 * 5);

    }

    private void replenishMoneyFromTreasury(@NotNull String kingdomName, @NotNull Player player, int colum) {

        FeudalKingdom feudalKingdom = CacheFeudalKingdoms.getKingdomInfo().get(kingdomName);
        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);

        if (kingdomName.equals("")) {

            player.sendMessage("Вы не состоите в королевстве!");
            return;

        } else if (!feudalKingdom.getBaronsUUID().contains(player.getUniqueId().toString()) &&
                !feudalKingdom.getKingUUID().equals(player.getUniqueId())) {

            player.sendMessage("Недостаточно прав для пополнения казны!");
            return;

        }

        if (feudalPlayer.getBalance() < colum) {

            player.sendMessage("Недостаточно средств!");
            return;

        }

        feudalPlayer.takeBalance(colum);
        feudalKingdom.addBalance(colum - colum / 100 * 5);

    }

    private void invitePlayer(@NotNull String kingdomName, @NotNull Player playerInviting, String nick) {

        Player invitedPlayer = Bukkit.getPlayerExact(nick);
        FeudalKingdom feudalKingdom = CacheFeudalKingdoms.getKingdomInfo().get(kingdomName);

        if (kingdomName.equals("") || feudalKingdom == null) {

            playerInviting.sendMessage("Вы не состоите в королевстве!");
            return;

        } else if (invitedPlayer == null || !invitedPlayer.isOnline() || invitedPlayer.getGameMode().equals(GameMode.SPECTATOR)) {

            playerInviting.sendMessage("Игрок не найден на сервере!");
            return;

        } else if (invitedPlayer == playerInviting) {

            playerInviting.sendMessage("Вы не можете пригласить самого себя!");
            return;

        } else if (!feudalKingdom.getKingUUID().equals(playerInviting.getUniqueId())) {

            playerInviting.sendMessage("Вы не лидер королевства!");
            return;

        } else if (feudalKingdom.getMaxNumberMembers() <= (feudalKingdom.getMembersUUID().size() + 1)) {

            playerInviting.sendMessage("В ваше королевство нельзя пригласить больше участников!");
            return;

        } else if (feudalKingdom.getInvitationUUID().contains(invitedPlayer.getUniqueId())) {

            playerInviting.sendMessage("Приглашение уже отправлено этому игроку!");
            return;

        }

        FeudalPlayer feudalInvitedPlayer = CacheFeudalPlayers.getFeudalPlayer(invitedPlayer);

        if (!feudalInvitedPlayer.getKingdomName().equals("")) {

            playerInviting.sendMessage("Игрок уже состоит в королевстве!");
            return;

        }

        feudalKingdom.addInvitation(invitedPlayer);
        feudalInvitedPlayer.addInvitations(kingdomName);

        feudalInvitedPlayer.addLetter(playerInviting.getName(), "Игрок " +
                playerInviting.getName() +
                " приглашает вас вступить в королевство - " +
                kingdomName);

        playerInviting.sendMessage("Приглашение отправлено!");

    }

    private void kickPlayer(@NotNull String kingdomName, @NotNull Player playerInviting, String nick) {

        Player invitedPlayer = Bukkit.getPlayerExact(nick);
        FeudalKingdom feudalKingdom = CacheFeudalKingdoms.getKingdomInfo().get(kingdomName);

        if (kingdomName.equals("") || feudalKingdom == null) {

            playerInviting.sendMessage("Вы не состоите в королевстве!");
            return;

        } else if (invitedPlayer == playerInviting) {

            playerInviting.sendMessage("Вы не можете кикнуть самого себя!");
            return;

        } else if (!feudalKingdom.getKingUUID().equals(playerInviting.getUniqueId())) {

            playerInviting.sendMessage("Вы не лидер королевства!");
            return;

        } else if (!feudalKingdom.getMembersUUID().contains(invitedPlayer.getUniqueId().toString())) {

            playerInviting.sendMessage("Игрок не состоит в вашем королевстве!");
            return;

        }

        feudalKingdom.removeMember(playerInviting);

        feudalKingdom.getMembersUUID().forEach(member -> {

            if (Bukkit.getPlayer(UUID.fromString(member)) != null)
                Bukkit.getPlayer(member).sendMessage("Игрок " + invitedPlayer.getName() + " был кикнут из вашего королевства!");

        });

    }

    private void sell(@NotNull ItemStack itemStack, int price) {

        Auction.getProducts().add(new ItemStackWrapper(itemStack.getType(), itemStack.getDurability(), itemStack.getAmount(), itemStack.getItemMeta().getDisplayName(), itemStack.getItemMeta().getLore(), itemStack.getEnchantments(), price));

    }

    private void reject(@NotNull Player player, String kingdomName) {

        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);

        if (!feudalPlayer.getInvitations().contains(kingdomName)) {

            player.sendMessage("Вы не были приглашены этим королевством! Или время приглашения истекло.");
            return;

        }

        FeudalKingdom feudalKingdom = CacheFeudalKingdoms.getKingdomInfo().get(kingdomName);

        feudalKingdom.deleteInvitation(player);
        feudalPlayer.deleteInvitations(kingdomName);

        Bukkit.getPlayer(feudalKingdom.getKingUUID()).sendMessage("Игрок " +
                player.getDisplayName() +
                ", отклонил ваше приглашение.");
        player.sendMessage("Вы отклонили приграшение в королевство: " +
                kingdomName);

    }

    private void disband(@NotNull Player player) {

        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);
        FeudalKingdom feudalKingdom = CacheFeudalKingdoms.getKingdomInfo().get(feudalPlayer.getKingdomName());

        if (feudalPlayer.getKingdomName().equals("") || !feudalKingdom.getKingUUID().equals(player.getUniqueId())) {

            player.sendMessage("Вы не лидер королевства!");
            return;

        }

        confirmDeletion = !confirmDeletion;
        if (confirmDeletion) {

            player.sendMessage("Напишите ещё раз, чтобы подтвердить расформирование королевства");
            return;

        }

        KingdomDBHandler.deleteKingdom(feudalPlayer.getKingdomName());

        feudalKingdom.getMembersUUID().forEach(member -> {

            FeudalPlayer feudalMember = CacheFeudalPlayers.getFeudalPlayer(Bukkit.getPlayer(member));
            feudalMember.setKingdomName("");

            if (Bukkit.getPlayer(UUID.fromString(member)) != null)
                Bukkit.getPlayer(member).sendMessage("Ваше королевство расформировано!");

        });

        CacheFeudalKingdoms.getKingdomInfo().remove(feudalPlayer.getKingdomName());

    }

    private void leave(@NotNull Player player) {

        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);

        String kingdomName = feudalPlayer.getKingdomName();
        FeudalKingdom feudalKingdom = CacheFeudalKingdoms.getKingdomInfo().get(kingdomName);

        if (kingdomName.equals("")) {

            player.sendMessage("Вы не состоите в королевстве!");
            return;

        }

        feudalKingdom.getMembersUUID().remove(player.getUniqueId().toString());
        feudalPlayer.setKingdomName("");

        feudalKingdom.getMembersUUID().forEach(member -> {

            if (Bukkit.getPlayer(UUID.fromString(member)) != null)
                Bukkit.getPlayer(member).sendMessage("Игрок " + player.getName() + " покинул ваше королевство!");

        });
        player.sendMessage("Вы покинули королевство " + kingdomName);

    }

    private void ah(@NotNull Player player) {

        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);
        if (feudalPlayer.getProfessionID() != ProfessionIDE.TRADER.getId()) {

            player.sendMessage("Вы не торговец!");
            return;

        }

        new Menus(player).auctionMenu(1);

    }

    private void claim(String kingdomName, @NotNull Player player) {

        FeudalKingdom feudalKingdom = CacheFeudalKingdoms.getKingdomInfo().get(kingdomName);

        if (kingdomName.equals("")) {

            player.sendMessage("Вы не состоите в королевстве!");
            return;

        } else if (!feudalKingdom.getKingUUID().equals(player.getUniqueId())) {

            player.sendMessage("Вы не лидер королевства!");
            return;

        } else if (feudalKingdom.getTerritory().size() >= feudalKingdom.getMembersUUID().stream().map(member ->
                CacheFeudalPlayers.getFeudalPlayer(Bukkit.getPlayer(UUID.fromString(member)))).mapToInt(feudalPlayer ->
                ProfessionIDE.getContainsLandBYID(feudalPlayer.getProfessionID())).sum()) {

            player.sendMessage("Максимальное количество земель в королевстве!");
            return;

        }


        World world = Bukkit.getWorld(player.getWorld().getUID());
        Chunk playerChunk = world.getChunkAt(player.getLocation());

        if (feudalKingdom.getTerritory().contains(new ChunkWrapper(world.getName(), playerChunk.getX(), playerChunk.getZ()).hashCode())) {

            player.sendMessage("Этот чанк уже есть в вашем королевстве!");
            return;

        }
        boolean canCapture = feudalKingdom.getTerritory().isEmpty();

        Location firstLocation = new Location(world, playerChunk.getX() - 1, 64, playerChunk.getZ() - 1);
        Location secondLocation = new Location(world, playerChunk.getX() + 1, 64, playerChunk.getZ() + 1);

        for (int x = firstLocation.getBlockX(); x <= secondLocation.getBlockX(); x++)
            for (int z = firstLocation.getBlockZ(); z <= secondLocation.getBlockZ(); z++) {

                if (feudalKingdom.getTerritory().contains(new ChunkWrapper(world.getName(), x, z).hashCode()))
                    canCapture = true;

            }

        if (!canCapture) {

            player.sendMessage("Этот чанк нельзя захватить!");
            return;

        }

        feudalKingdom.addTerritory(world.getChunkAt(player.getLocation()));
        feudalKingdom.getMembersUUID().forEach(member -> {

            if (Bukkit.getPlayer(UUID.fromString(member)) != null)
                Bukkit.getPlayer(UUID.fromString(member)).sendMessage("Ваш король захватил новые земли!");

        });

    }

    private void addBaron(@NotNull String kingdomName, @NotNull Player playerInviting, String nick) {

        Player baron = Bukkit.getPlayerExact(nick);
        FeudalKingdom feudalKingdom = CacheFeudalKingdoms.getKingdomInfo().get(kingdomName);

        if (kingdomName.equals("") || feudalKingdom == null) {

            playerInviting.sendMessage("Вы не состоите в королевстве!");
            return;

        } else if (baron == null || !baron.isOnline()) {

            playerInviting.sendMessage("Игрок не найден на сервере!");
            return;

        } else if (baron == playerInviting) {

            playerInviting.sendMessage("Вы не можете назначить самого себя!");
            return;

        } else if (!feudalKingdom.getKingUUID().equals(playerInviting.getUniqueId())) {

            playerInviting.sendMessage("Вы не лидер королевства!");
            return;

        } else if (feudalKingdom.getBaronsUUID().size() == 1) {

            playerInviting.sendMessage("В королевстве максимальное количество баронов!");
            return;

        } else if (feudalKingdom.getBaronsUUID().contains(baron.getUniqueId().toString())) {

            playerInviting.sendMessage("Игрок уже назначен бароном!");
            return;

        }

        feudalKingdom.addBaron(baron);
        playerInviting.sendMessage("Игрок " + baron.getName() + " назначен бароном!");

    }

    private void removeBaron(@NotNull String kingdomName, @NotNull Player playerInviting, String nick) {

        Player baron = Bukkit.getPlayerExact(nick);
        FeudalKingdom feudalKingdom = CacheFeudalKingdoms.getKingdomInfo().get(kingdomName);

        if (kingdomName.equals("") || feudalKingdom == null) {

            playerInviting.sendMessage("Вы не состоите в королевстве!");
            return;

        } else if (baron == null || !baron.isOnline()) {

            playerInviting.sendMessage("Игрок не найден на сервере!");
            return;

        } else if (baron == playerInviting) {

            playerInviting.sendMessage("Вы не можете убрать барона с самого себя!");
            return;

        } else if (!feudalKingdom.getKingUUID().equals(playerInviting.getUniqueId())) {

            playerInviting.sendMessage("Вы не лидер королевства!");
            return;

        } else if (!feudalKingdom.getBaronsUUID().contains(baron.getUniqueId().toString())) {

            playerInviting.sendMessage("Игрок не назначен бароном!");
            return;

        }

        feudalKingdom.removeBaron(baron);
        playerInviting.sendMessage("Игрок " + baron.getName() + " снят с поста барона!");

    }

    private void help(CommandSender sender, String[] args) {

        Player player = (Player) sender;

        player.sendMessage("/f claim - захватить чанк\n"
                + "/f create - создать королевство\n" +
                "/f help - меню команд\n" +
                "/f invite - добавить игрока в королевство\n" +
                "/f kick - удалить игрока из королевства\n" +
                "/f ah - открыть аукцион\n");

    }
}
