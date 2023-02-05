package feudal.listeners.generalListeners;

import feudal.Feudal;
import feudal.data.cache.CachePlayersMap;
import feudal.data.database.PlayerInfo;
import feudal.utils.MathUtils;
import feudal.utils.enums.MoneyForMobsEnum;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static feudal.utils.MathUtils.getRandInt;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PlayerListener implements Listener {

    List<String> sleepingPlayers = new ArrayList<>();

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
        int temp = playerDeathInfo.getBalance() / 100 * getRandInt(2, 6);

        playerInfo.addBalance(temp);
        playerDeathInfo.takeBalance(temp + getRandInt(0, 4));

    }

    @EventHandler
    public void playerBedEnter(@NotNull PlayerBedEnterEvent event) {

        Player player = event.getPlayer();

        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 400, 0, true, true));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 240, 1, true, true));

        if (!sleepingPlayers.contains(player.getUniqueId().toString()))
            sleepingPlayers.add(player.getUniqueId().toString());

        if (sleepingPlayers.size() / Bukkit.getOnlinePlayers().size() < 2) {

            World world = player.getWorld();

            world.setTime(24000);
            world.setStorm(false);
            world.setThundering(false);

        }
    }

    @EventHandler
    public void playerBedLeave(@NotNull PlayerBedLeaveEvent event) {
        sleepingPlayers.remove(event.getPlayer().getUniqueId().toString());
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
    public void playerBlockPlaced(@NotNull BlockPlaceEvent event) {
        event.getBlock().setMetadata("PLACED", new FixedMetadataValue(Feudal.getPlugin(), "true"));
    }

    @EventHandler
    public void playerBlockBreak(@NotNull BlockBreakEvent event) {

        ItemStack item = event.getPlayer().getInventory().getItemInMainHand();

        if (item.getDurability() == 0 || MathUtils.getRandInt(0, 26) != 25) return;

        item.setDurability((short) (item.getDurability() + getRandInt(6, 11)));

    }
}
