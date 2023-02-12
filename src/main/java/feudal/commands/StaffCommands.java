package feudal.commands;

import feudal.visual.tab.Tab;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class StaffCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player) &&
                !sender.hasPermission("feudal.staff") &&
                !args[0].equals("s")) return false;

        assert sender instanceof Player;
        Player player = (Player) sender;

        if (args[0].equals("spy")) {

            if (player.getGameMode().equals(GameMode.SPECTATOR)) {

                Tab.showPlayer(player);

                if (player.hasPotionEffect(PotionEffectType.NIGHT_VISION))
                    player.removePotionEffect(PotionEffectType.NIGHT_VISION);

                return false;

            }

            Tab.hidePlayer(player);
            player.setGameMode(GameMode.SPECTATOR);

            if (!player.hasPotionEffect(PotionEffectType.NIGHT_VISION))
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 100000, 0, true, true));

        }

        return false;
    }
}
