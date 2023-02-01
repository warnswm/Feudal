package feudal.listeners.gameClassesListeners;

import feudal.databaseAndCache.CachePlayers;
import feudal.databaseAndCache.PlayerInfo;
import feudal.utils.enums.MoneyForMobsEnum;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static feudal.utils.MathUtils.getRandInt;

public class GeneralListener implements Listener {

    @EventHandler
    public void playerTeleport(@NotNull PlayerTeleportEvent event) {

        if (event.getCause() != PlayerTeleportEvent.TeleportCause.ENDER_PEARL) return;

        event.getPlayer().setCooldown(Material.ENDER_PEARL, 220);

    }

    @EventHandler
    public void entityDeath(@NotNull EntityDeathEvent event) {

        if (event.getEntity().getKiller() == null) return;

        Player player = event.getEntity().getKiller();
        PlayerInfo playerInfo = CachePlayers.getPlayerInfo().get(player);

        if (event.getEntityType() != EntityType.PLAYER) {

            playerInfo.addBalance(MoneyForMobsEnum.getByEntity(event.getEntityType()));
            return;

        }

        PlayerInfo playerDeathInfo = CachePlayers.getPlayerInfo().get((Player) event.getEntity());
        int temp = playerDeathInfo.getBalance() / 100 * getRandInt(3, 5);

        playerInfo.addBalance(temp);
        playerDeathInfo.takeBalance(temp + getRandInt(1, 3));

    }

    @EventHandler
    public void playerResting(@NotNull PlayerBedEnterEvent event) {

        event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 400,  0, true, true));
        event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 240,  1, true, true));

    }

    @EventHandler
    public void playerEats(@NotNull PlayerItemConsumeEvent event) {

        if (!Objects.requireNonNull(CraftItemStack.asNMSCopy(event.getItem()).getTag()).getBoolean("cookedByChef")) return;

        Player player = event.getPlayer();

        if (CraftItemStack.asNMSCopy(event.getItem()).getTag().getByte("chefLvl") == 100) {

            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 600,  1, true, true));
            return;

        }

        if (CraftItemStack.asNMSCopy(event.getItem()).getTag().getByte("chefLvl") >= 25)
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 600,  0, true, true));

    }
}
