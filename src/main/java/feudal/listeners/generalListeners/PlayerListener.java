package feudal.listeners.generalListeners;

import feudal.Feudal;
import feudal.data.cache.CachePlayersMap;
import feudal.data.database.PlayerInfo;
import feudal.utils.enums.MoneyForMobsEnum;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static feudal.utils.MathUtils.getRandInt;

public class PlayerListener implements Listener {

    @EventHandler
    public void playerTeleport(@NotNull PlayerTeleportEvent event) {

        if (event.getCause() != PlayerTeleportEvent.TeleportCause.ENDER_PEARL) return;

        event.getPlayer().setCooldown(Material.ENDER_PEARL, 220);

    }

    @EventHandler
    public void entityDeath(@NotNull EntityDeathEvent event) {

        if (event.getEntity().getKiller() == null) return;

        PlayerInfo playerInfo = CachePlayersMap.getPlayerInfo().get(event.getEntity().getKiller());

        if (event.getEntityType() != EntityType.PLAYER) {

            playerInfo.addBalance(MoneyForMobsEnum.getByEntity(event.getEntityType()));
            return;

        }

        PlayerInfo playerDeathInfo = CachePlayersMap.getPlayerInfo().get((Player) event.getEntity());
        int temp = playerDeathInfo.getBalance() / 100 * getRandInt(3, 5);

        playerInfo.addBalance(temp);
        playerDeathInfo.takeBalance(temp + getRandInt(1, 3));

    }

    @EventHandler
    public void playerResting(@NotNull PlayerBedEnterEvent event) {

        event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 400, 0, true, true));
        event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 240, 1, true, true));

    }

    @EventHandler
    public void playerEats(@NotNull PlayerItemConsumeEvent event) {

        if (CraftItemStack.asNMSCopy(event.getItem()).getTag() == null ||
                !Objects.requireNonNull(CraftItemStack.asNMSCopy(event.getItem()).getTag()).getBoolean("cookedByChef") ||
                Objects.requireNonNull(CraftItemStack.asNMSCopy(event.getItem()).getTag()).getByte("chefLvl") < 25)
            return;

        Player player = event.getPlayer();

        if (Objects.requireNonNull(CraftItemStack.asNMSCopy(event.getItem()).getTag()).getByte("chefLvl") == 100) {

            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 600, 1, true, true));
            return;

        }

        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 600, 0, true, true));

    }

    @EventHandler
    public void playerOnFoodChange(@NotNull EntityRegainHealthEvent event) {

        if (!(event.getEntity() instanceof Player)) return;

        Player player = (Player) event.getEntity();
        float staminaLvl = CachePlayersMap.getPlayerInfo().get(player).getStaminaLvl();
        double defaultAmount = event.getAmount();

        event.setAmount(defaultAmount * (staminaLvl / 100) + defaultAmount);
    }

    @EventHandler
    public void playerRegenerationEvent(@NotNull EntityRegainHealthEvent event) {

        if (!(event.getEntity() instanceof Player)) return;

        Player player = (Player) event.getEntity();
        float survivabilityLvl = CachePlayersMap.getPlayerInfo().get(player).getSurvivabilityLvl();

        event.setAmount(1 * (survivabilityLvl / 200) + 1);

    }

    @EventHandler
    public void playerAttack(@NotNull EntityDamageByEntityEvent event) {

        if (!(event.getDamager() instanceof Player)) return;

        Player player = (Player) event.getDamager();
        Entity entity = event.getEntity();

        float strengthLvl = CachePlayersMap.getPlayerInfo().get(player).getStrengthLvl();
        double defaultDamage = event.getDamage();

        event.setDamage(defaultDamage * (strengthLvl / 200) + defaultDamage);

        if (!(entity instanceof Player)) return;

        event.setDamage(defaultDamage - defaultDamage / 100 * (CachePlayersMap.getPlayerInfo().get(entity).getStaminaLvl() * 0.2));
    }

    @EventHandler
    public void blockPlaced(@NotNull BlockPlaceEvent event) {

        event.getBlock().setMetadata("PLACED", new FixedMetadataValue(Feudal.getPlugin(), "true"));

    }

}
