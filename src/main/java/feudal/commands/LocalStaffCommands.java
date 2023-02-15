package feudal.commands;

import feudal.data.builder.FeudalKingdom;
import feudal.data.builder.FeudalPlayer;
import feudal.data.cache.CacheFeudalKingdoms;
import feudal.data.cache.CacheFeudalPlayers;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LocalStaffCommands implements CommandExecutor {
    private static boolean isLs(CommandSender sender, String[] args) {
        return !(sender instanceof Player) && !sender.hasPermission("feudal.ls") && !args[0].equals("ls");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (isLs(sender, args)) return false;

        assert sender instanceof Player;
        Player player = (Player) sender;

        FeudalPlayer feudalPlayer;
        FeudalKingdom feudalKingdom;

        if (args[1] == null) {

            player.sendMessage("Недостаточно аргументов!");
            return false;

        }


        switch (args[0].toLowerCase()) {

            case "changegc":

                feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(Bukkit.getPlayerExact(args[1]));
                feudalPlayer.setaClassID(Integer.parseInt(args[2]));

                break;

            case "addchunk":

                feudalKingdom = CacheFeudalKingdoms.getKingdomInfo().get(args[1]);
                feudalKingdom.addTerritory(player.getLocation().getChunk());

                break;

            case "sendletter":

                if (Bukkit.getPlayerExact(args[1]) == null) {

                    player.sendMessage("Игрок не найден!");
                    break;

                }

                String[] text = new String[args.length - 2];
                System.arraycopy(args, 2, text, 0, args.length - 2);

                sendLetter(Bukkit.getPlayerExact(args[1]), text);

                break;

        }

        return false;

    }

    private void sendLetter(Player player, String[] text) {
        CacheFeudalPlayers.getFeudalPlayer(player).addLetter(player, String.join(" ", text));
    }
}
