package feudal.commands;

import feudal.data.builder.FeudalKingdom;
import feudal.data.builder.FeudalPlayer;
import feudal.data.cache.CacheFeudalKingdoms;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.data.database.KingdomDBHandler;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

            default:
                player.sendMessage("Не известная команда! Введите /f help, чтобы посмотреть доступные команды");

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

            player.sendMessage("Невозможно создать королевство с таким именем");
            return;

        }

        List<Player> members = new ArrayList<>();
        members.add(player);

        createKingdom(kingdomName, player, members);

    }

    private boolean checkKingdomName(@NotNull String kingdomName) {
        return kingdomName.length() <= 16 && kingdomName.length() > 3 && CacheFeudalKingdoms.getKingdomInfo().get(kingdomName) == null;
    }

    private void createKingdom(@NotNull String kingdomName, @NotNull Player player, List<Player> members) {

        KingdomDBHandler.createNewKingdom(kingdomName, player, members, Collections.EMPTY_LIST, Collections.EMPTY_LIST, Collections.EMPTY_LIST);

        FeudalKingdom feudalKingdom = new FeudalKingdom(kingdomName);

        feudalKingdom.setKingdomName(kingdomName)
                .setKing(player)
                .setBalance(10000)
                .setReputation(1000)
                .setMembers((List<Player>) KingdomDBHandler.getField(kingdomName, "members"))
                .setBarons((List<Player>) KingdomDBHandler.getField(kingdomName, "barons"))
                .setTerritory((List<Chunk>) KingdomDBHandler.getField(kingdomName, "territory"))
                .setPrivateTerritory((List<Chunk>) KingdomDBHandler.getField(kingdomName, "privateTerritory"));

        CacheFeudalKingdoms.getKingdomInfo().put(kingdomName, feudalKingdom);

        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);
        feudalPlayer.setKingdomName(kingdomName);

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

        Player invitedPlayer = Bukkit.getPlayer(nick);
        FeudalKingdom feudalKingdom = CacheFeudalKingdoms.getKingdomInfo().get(kingdomName);

        if (kingdomName.equals("")) {

            playerInviting.sendMessage("Вы не находитесь в королевстве!");
            return;

        } else if (invitedPlayer == null || !invitedPlayer.isOnline()) {

            playerInviting.sendMessage("Игрок не найден на сервере!");
            return;

        } else if (feudalKingdom.getKing() != playerInviting) {

            playerInviting.sendMessage("Вы не лидер королевства!");
            return;

        }

        FeudalPlayer feudalInvitedPlayer = CacheFeudalPlayers.getFeudalPlayer(invitedPlayer);

        if (!feudalInvitedPlayer.getKingdomName().equals("")) {

            playerInviting.sendMessage("Игрок уже состоит в другом королевстве!");
            return;

        }

        feudalKingdom.addInvitation(invitedPlayer);
        feudalInvitedPlayer.addInvitations(kingdomName);

        invitedPlayer.sendMessage("Игрок: " + playerInviting.getDisplayName() + " приглашает вас вступить в его королевство, " + kingdomName + ". Введите /f accept [имя королевства], чтобы принять приглашение!");
        playerInviting.sendMessage("Приглашение отправлено!");

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

        feudalKingdom.getKing().sendMessage("Игрок: " + player.getDisplayName() + " принял ваше приглашение!");
        player.sendMessage("Вы вступили в королевство: " + kingdomName);

    }

}
