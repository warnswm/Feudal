package feudal.commands;

import feudal.Feudal;
import feudal.data.builder.FeudalKingdom;
import feudal.data.builder.FeudalPlayer;
import feudal.data.cache.CacheFeudalKingdoms;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.data.database.KingdomDBHandler;
import feudal.utils.PlannedActivitiesUtils;
import feudal.visual.menus.MailMenu;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static feudal.utils.enums.ArmorEnum.getByItemMaterial;

public class PlayerCommands implements CommandExecutor {

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

                withdrawMoneyFromTheTreasury(KingdomDBHandler.getPlayerKingdom(player), player, Integer.parseInt(args[1]));

                break;

            case "invite":

                invitePlayer(CacheFeudalPlayers.getFeudalPlayer(player).getKingdomName(), player, args[1]);

                break;

            case "accept":

                accept(player, args[1]);

                break;

            case "reject":

                reject(player, args[1]);

                break;

            case "mail":

                MailMenu.openMailMenu(player);

                break;

            case "map":

                map(player);

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

        KingdomDBHandler.createNewKingdom(kingdomName, player, membersUUID, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        FeudalKingdom feudalKingdom = new FeudalKingdom(kingdomName);
        feudalKingdom.setKingdomName(kingdomName)
                .setKing(player)
                .setBalance(10000)
                .setReputation(1000)
                .setMembers(membersUUID)
                .setMaxNumberMembers(5)
                .setBarons(new ArrayList<>())
                .setTerritory(new ArrayList<>())
                .setPrivateTerritory(new ArrayList<>());

        CacheFeudalKingdoms.getKingdomInfo().put(kingdomName, feudalKingdom);

        feudalPlayer.setKingdomName(kingdomName);

        player.sendMessage("Вы успешно создали королевство: " + kingdomName);

    }

    private void withdrawMoneyFromTheTreasury(@NotNull String kingdomName, @NotNull Player player, int colum) {

        if (kingdomName.equals("")) {

            player.sendMessage("Вы не состоите в королевстве!");
            return;

        }

        FeudalKingdom feudalKingdom = CacheFeudalKingdoms.getKingdomInfo().get(kingdomName);

        if (feudalKingdom.getBalance() < colum) {

            player.sendMessage("В казне недостаточно средств");
            return;

        }

        feudalKingdom.takeBalance(colum);
        CacheFeudalPlayers.getFeudalPlayer(player).addBalance(colum - colum / 100 * 5);

    }

    private void invitePlayer(@NotNull String kingdomName, @NotNull Player playerInviting, String nick) {

        Player invitedPlayer = Bukkit.getPlayerExact(nick);
        FeudalKingdom feudalKingdom = CacheFeudalKingdoms.getKingdomInfo().get(kingdomName);

        if (kingdomName.equals("") || feudalKingdom == null) {

            playerInviting.sendMessage("Вы не состоите в королевстве!");
            return;

        } else if (invitedPlayer == playerInviting) {

            playerInviting.sendMessage("Вы не можете пригласить самого себя!");
            return;

        } else if (invitedPlayer == null || !invitedPlayer.isOnline() || invitedPlayer.getGameMode().equals(GameMode.SPECTATOR)) {

            playerInviting.sendMessage("Игрок не найден на сервере!");
            return;

        } else if (!feudalKingdom.getKingUUID().equals(playerInviting.getUniqueId().toString())) {

            playerInviting.sendMessage("Вы не лидер королевства!");
            return;

        } else if (feudalKingdom.getMaxNumberMembers() <= (feudalKingdom.getMembersUUID().size() + 1)) {

            playerInviting.sendMessage("В ваше королевство нельзя пригласить больше участников!");
            return;

        } else if (feudalKingdom.getInvitationUUID().contains(invitedPlayer.getUniqueId().toString())) {

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

        invitedPlayer.sendMessage("Игрок " +
                playerInviting.getName() +
                " приглашает вас вступить в его королевство - " +
                kingdomName +
                ". Введите /f accept [имя королевства], чтобы принять приглашение!");
        playerInviting.sendMessage("Приглашение отправлено!");


        PlannedActivitiesUtils.scheduleRepeatAtTime(Feudal.getPlugin(), () -> Bukkit.getScheduler().runTaskLater(Feudal.getPlugin(), () -> {

            feudalInvitedPlayer.deleteInvitations(kingdomName);
            feudalKingdom.deleteInvitation(invitedPlayer);

            playerInviting.sendMessage("Игрок " + invitedPlayer.getName() + " не принял ваше приглашение!");

        }, 0L), 2400);

    }

    private void accept(@NotNull Player player, String kingdomName) {

        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);

        if (!feudalPlayer.getInvitations().contains(kingdomName)) {

            player.sendMessage("Вы не были приглашены этим королевством!");
            return;

        }

        FeudalKingdom feudalKingdom = CacheFeudalKingdoms.getKingdomInfo().get(kingdomName);

        feudalKingdom.addMember(player);

        feudalPlayer.setKingdomName(kingdomName);
        feudalPlayer.clearInvitations();

        Bukkit.getPlayerExact(feudalKingdom.getKingUUID()).sendMessage("Игрок " +
                player.getDisplayName() +
                " принял ваше приглашение!");
        player.sendMessage("Вы вступили в королевство: " +
                kingdomName);

    }

    private void reject(@NotNull Player player, String kingdomName) {

        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);

        if (!feudalPlayer.getInvitations().contains(kingdomName)) {

            player.sendMessage("Вы не были приглашены этим королевством!");
            return;

        }

        FeudalKingdom feudalKingdom = CacheFeudalKingdoms.getKingdomInfo().get(kingdomName);

        feudalKingdom.deleteInvitation(player);
        feudalPlayer.deleteInvitations(kingdomName);

        Bukkit.getPlayerExact(feudalKingdom.getKingUUID()).sendMessage("Игрок " +
                player.getDisplayName() +
                ", отклонил ваше приглашение.");
        player.sendMessage("Вы отклонили приграшение в королевство: " +
                kingdomName);

    }

    private void map(@NotNull Player player) {

        if (player.getGameMode().equals(GameMode.SPECTATOR)) {

            player.setGameMode(GameMode.SURVIVAL);
            player.teleport(player.getLocation().add(0, -50, 0));

            PlayerInventory playerInv = player.getInventory();

            int attribute = playerInv.getHelmet() == null ? 0 : getByItemMaterial(playerInv.getHelmet().getType());
            attribute += playerInv.getChestplate() == null ? 0 : getByItemMaterial(playerInv.getChestplate().getType());
            attribute += playerInv.getLeggings() == null ? 0 : getByItemMaterial(playerInv.getLeggings().getType());
            attribute += playerInv.getBoots() == null ? 0 : getByItemMaterial(playerInv.getBoots().getType());


            FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);
            float speed = attribute == 0 ? 0.2f * feudalPlayer.getSpeedLvl() / 130 + 0.2f : (0.2f * feudalPlayer.getSpeedLvl() / 130 + 0.2f) - 0.2f - (0.2f / 100 * attribute);

            player.setWalkSpeed(speed);

            return;

        }

        player.setGameMode(GameMode.SPECTATOR);
        player.teleport(player.getLocation().add(0, 50, 0));
        player.setWalkSpeed(0);

    }

}
