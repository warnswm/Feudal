package feudal.commands;

import feudal.data.builder.FeudalKingdom;
import feudal.data.builder.FeudalPlayer;
import feudal.data.cache.CacheFeudalKingdoms;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.data.database.KingdomDBHandler;
import feudal.utils.wrappers.ChunkWrapper;
import feudal.visual.menus.MailMenu;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
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

            case "help":

                helpCommand(player);
                break;

            case "create":

                if (args[1] == null) {

                    player.sendMessage("Пожалуйста укажите желаемое имя королевства!");
                    break;

                }

                createKingdomCommand(player, args[1]);
                break;

            case "withdraw":

                if (args[1].length() > 10) {

                    player.sendMessage("Слишком большая сумма для снятия");
                    break;

                }

                withdrawMoneyFromTreasury(KingdomDBHandler.getPlayerKingdom(player), player, Integer.parseInt(args[1]));

                break;

            case "replenish":

                if (args[1].length() > 10) {

                    player.sendMessage("Слишком большая сумма для пополнения");
                    break;

                }

                replenishMoneyFromTreasury(KingdomDBHandler.getPlayerKingdom(player), player, Integer.parseInt(args[1]));

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

                MailMenu.openMailMenu(player);

                break;

            case "claim":

                claim(CacheFeudalPlayers.getFeudalPlayer(player).getKingdomName(), player);

                break;

            default:
                player.sendMessage("Неизвестная команда! Введите /f help, чтобы посмотреть доступные команды");

        }

        return false;
    }

    private void helpCommand(@NotNull Player player) {
        player.sendMessage("/f claim - захватить чанк\n"
                + "/f create - создать королевство\n" +
                "/f help - меню коамнд\n" +
                "/f invite - добавить игрока в королевство\n" +
                "/f kick - удалить игрока из королевства\n" +
                "/f m - меню королевства\n" + "/f map - soon\n" +
                "/f shield - soon\n" +
                "/f location - указать локацию королевства\n" +
                "/f ah - открыть аукцион\n");
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

        List<UUID> membersUUIDStr = new ArrayList<>();
        membersUUID.forEach(member -> membersUUIDStr.add(UUID.fromString(member)));

        KingdomDBHandler.createNewKingdom(kingdomName, player, membersUUID, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        FeudalKingdom feudalKingdom = new FeudalKingdom(kingdomName);
        feudalKingdom.setKingdomName(kingdomName)
                .setKing(player)
                .setBalance(10000)
                .setReputation(1000)
                .setMembers(membersUUIDStr)
                .setMaxNumberMembers(5)
                .setBarons(new ArrayList<>())
                .setTerritory(new ArrayList<>())
                .setPrivateTerritory(new ArrayList<>());

        CacheFeudalKingdoms.getKingdomInfo().put(kingdomName, feudalKingdom);

        feudalPlayer.setKingdomName(kingdomName);

        player.sendMessage("Вы успешно создали королевство: " + kingdomName);

    }

    private void withdrawMoneyFromTreasury(@NotNull String kingdomName, @NotNull Player player, int colum) {

        FeudalKingdom feudalKingdom = CacheFeudalKingdoms.getKingdomInfo().get(kingdomName);

        if (kingdomName.equals("")) {

            player.sendMessage("Вы не состоите в королевстве!");
            return;

        } else if (!feudalKingdom.getBaronsUUID().contains(player.getUniqueId()) &&
                !feudalKingdom.getKingUUID().equals(player.getUniqueId())) {

            player.sendMessage("Недостаточно прав для снятия денег с казны!");
            return;

        }

        if (feudalKingdom.getBalance() < colum) {

            player.sendMessage("В казне недостаточно средств");
            return;

        }

        feudalKingdom.takeBalance(colum);
        CacheFeudalPlayers.getFeudalPlayer(player).addBalance(colum - colum / 100 * 5);

    }

    private void replenishMoneyFromTreasury(@NotNull String kingdomName, @NotNull Player player, int colum) {

        FeudalKingdom feudalKingdom = CacheFeudalKingdoms.getKingdomInfo().get(kingdomName);
        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);

        if (kingdomName.equals("")) {

            player.sendMessage("Вы не состоите в королевстве!");
            return;

        } else if (!feudalKingdom.getBaronsUUID().contains(player.getUniqueId()) &&
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

        feudalInvitedPlayer.addLetter(playerInviting, "Игрок " +
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

        } else if (!feudalKingdom.getMembersUUID().contains(invitedPlayer.getUniqueId())) {

            playerInviting.sendMessage("Игрок не состоит в вашем королевстве!");
            return;

        }

        feudalKingdom.removeMember(playerInviting);

        feudalKingdom.getMembersUUID().forEach(member -> Bukkit.getPlayer(member).sendMessage("Игрок " + invitedPlayer.getName() + " был кикнут из вашего королевства!"));

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

        feudalKingdom.getMembersUUID().remove(player.getUniqueId());
        feudalPlayer.setKingdomName("");

        feudalKingdom.getMembersUUID().forEach(member -> Bukkit.getPlayer(member).sendMessage("Игрок " + player.getName() + " покинул ваше королевство!"));
        player.sendMessage("Вы покинули королевство " + kingdomName);

    }

    private void claim(String kingdomName, @NotNull Player player) {

        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);
        FeudalKingdom feudalKingdom = CacheFeudalKingdoms.getKingdomInfo().get(kingdomName);

        if (kingdomName.equals("")) {

            player.sendMessage("Вы не состоите в королевстве!");
            return;

        } else if (!feudalKingdom.getKingUUID().equals(player.getUniqueId())) {

            player.sendMessage("Вы не лидер королевства!");
            return;

        }

        World world = Bukkit.getWorld(player.getWorld().getUID());
        Chunk playerChunk = world.getChunkAt(player.getLocation());
        boolean canCapture = false;

        Location firstLocation = new Location(world, playerChunk.getX() + 1, 64, playerChunk.getZ() + 1);
        Location secondLocation = new Location(world, playerChunk.getX() - 1, 64, playerChunk.getZ() - 1);

        for (int x = (firstLocation.getBlockX() >> 4); x <= (secondLocation.getBlockX() >> 4); x++)
            for (int z = (firstLocation.getBlockZ() >> 4); z <= (secondLocation.getBlockZ() >> 4); z++) {

                if (feudalKingdom.getTerritory().contains(ChunkWrapper.chunkToChunkWrapper(world.getChunkAt(x, z)).hashCode()))
                    canCapture = true;

            }


        if (!canCapture) {

            player.sendMessage("Этот чанк нельзя захватить!");
            return;

        }

        feudalKingdom.addTerritory(world.getChunkAt(player.getLocation()));
        feudalKingdom.getMembersUUID().forEach(member -> Bukkit.getPlayer(member).sendMessage("Ваш король захватил новые земли!"));

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

        } else if (feudalKingdom.getBaronsUUID().contains(baron.getUniqueId())) {

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

        } else if (!feudalKingdom.getBaronsUUID().contains(baron.getUniqueId())) {

            playerInviting.sendMessage("Игрок не назначен бароном!");
            return;

        }

        feudalKingdom.removeBaron(baron);
        playerInviting.sendMessage("Игрок " + baron.getName() + " снят с поста барона!");

    }
}
