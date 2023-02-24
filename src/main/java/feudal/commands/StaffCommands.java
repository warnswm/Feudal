package feudal.commands;

import alterr.command.Command;
import feudal.data.SpyPlayer;
import feudal.data.cache.CacheSpyPlayers;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

public class StaffCommands {

    @Command(names = {"s spy"}, permission = "feudal.staff", playerOnly = true)
    public void spy(@NotNull Player player) {

        if (player.getGameMode().equals(GameMode.SPECTATOR)) {

            if (CacheSpyPlayers.getSpyPlayer(player) == null) return;

            CacheSpyPlayers.getSpyPlayer(player).show();

            if (player.hasPotionEffect(PotionEffectType.NIGHT_VISION))
                player.removePotionEffect(PotionEffectType.NIGHT_VISION);

            return;

        }

        new SpyPlayer(player.getUniqueId(), player.getGameMode(), player.getLocation()).hide();
        player.setGameMode(GameMode.SPECTATOR);

        if (!player.hasPotionEffect(PotionEffectType.NIGHT_VISION))
            player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 100000, 0, true, true));

    }

}
