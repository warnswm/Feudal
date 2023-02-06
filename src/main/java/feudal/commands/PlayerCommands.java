package feudal.commands;

import feudal.data.builder.FeudalKingdom;
import feudal.data.cache.CacheKingdomsMap;
import feudal.data.cache.CachePlayersMap;
import feudal.data.database.KingdomDBInfo;
import feudal.view.generalMenu.GameClassUpMenu;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerCommands implements CommandExecutor {

    final FileConfiguration config = Bukkit.getPluginManager().getPlugin("Feudal").getConfig();
    final KingdomDBInfo kingdomDBInfo = new KingdomDBInfo(config.get("MongoClientName").toString(), config.get("MongoDataBaseName").toString(), config.get("MongoCollectionName").toString());

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

                withdrawMoneyFromTheTreasury(kingdomDBInfo.getPlayerKingdom(player), player, Integer.parseInt(args[1]));
                break;
            case "gameclassupmenu":

                GameClassUpMenu gameClassUpMenu = new GameClassUpMenu(player);
                gameClassUpMenu.upgradeGameClass();
                break;

        }

        return false;
    }

    private void helpCommand(@NotNull Player player) {
        player.sendMessage("/f claim - захватить чанк\n" + "/f create - создать королевство\n" + "/f help - меню коамнд\n" + "/f invite - добавить игрока в королевство\n" + "/f kick - удалить игрока из королевства\n" + "/f m - меню королевства\n" + "/f map - soon\n" + "/f shield - soon\n" + "/f location - указать локацию королевства\n" + "/f ah - открыть аукцион\n");
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
        return kingdomName.length() <= 16 && kingdomName.length() > 3 && !kingdomName.equalsIgnoreCase("notInTheKingdom");
    }

    private void createKingdom(@NotNull String kingdomName, @NotNull Player player, List<Player> members) {

        kingdomDBInfo.createNewKingdom(kingdomName, player, members, Collections.EMPTY_LIST, Collections.EMPTY_LIST, Collections.EMPTY_LIST);

        FeudalKingdom feudalKingdom = new FeudalKingdom(kingdomName);

        feudalKingdom.setKingdomName(kingdomName)
                .setKing((Player) kingdomDBInfo.getField(kingdomName, "king"))
                .setBalance(10000)
                .setReputation(1000)
                .setMembers((List<Player>) kingdomDBInfo.getField(kingdomName, "members"))
                .setBarons((List<Player>) kingdomDBInfo.getField(kingdomName, "barons"))
                .setTerritory((List<Chunk>) kingdomDBInfo.getField(kingdomName, "territory"))
                .setPrivateTerritory((List<Chunk>) kingdomDBInfo.getField(kingdomName, "privateTerritory"));

        CacheKingdomsMap.getKingdomInfo().put(kingdomName, feudalKingdom);

    }

    private void withdrawMoneyFromTheTreasury(@NotNull String kingdomName, @NotNull Player player, int colum) {

        if (kingdomName.equalsIgnoreCase("notInTheKingdom")) {

            player.sendMessage("Вы не состоите в королевстве");
            return;

        }

        FeudalKingdom feudalKingdom = CacheKingdomsMap.getKingdomInfo().get(kingdomName);

        if (feudalKingdom.getBalance() < colum) {

            player.sendMessage("В казне недостаточно средств");
            return;

        }

        feudalKingdom.takeBalance(colum);
        CachePlayersMap.getPlayerInfo().get(player).addBalance(colum - colum / 100 * 5);

    }
}
