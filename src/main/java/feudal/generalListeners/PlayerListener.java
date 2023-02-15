package feudal.generalListeners;

import feudal.Feudal;
import feudal.data.builder.FeudalPlayer;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.utils.MathUtils;
import feudal.utils.enums.BlockToSaveEnum;
import feudal.utils.enums.MoneyForMobsEnum;
import feudal.utils.enums.gameClassesEnums.GameClassesIDEnum;
import feudal.utils.wrappers.PlacedBlockWrapper;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import net.minecraft.server.v1_12_R1.ChatMessageType;
import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PlayerListener implements Listener {

    public static List<PlacedBlockWrapper> placedBlocks = new ArrayList<>();
    List<String> sleepingPlayers = new ArrayList<>();

    @EventHandler
    public final void playerTeleport(@NotNull PlayerTeleportEvent event) {

        if (event.getCause() != PlayerTeleportEvent.TeleportCause.ENDER_PEARL) return;

        event.getPlayer().setCooldown(Material.ENDER_PEARL, 220);

    }

    @EventHandler
    public final void entityDeath(@NotNull EntityDeathEvent event) {

        if (event.getEntity().getKiller() == null) return;

        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(event.getEntity().getKiller());

        if (event.getEntityType() != EntityType.PLAYER) {

            feudalPlayer.addBalance(MoneyForMobsEnum.getMoneyByEntity(event.getEntityType()));
            return;

        }

        feudalPlayer.addKills(1);

        FeudalPlayer feudalPlayerDeath = CacheFeudalPlayers.getFeudalPlayer((Player) event.getEntity());
        int balance = feudalPlayerDeath.getBalance() / 100 * MathUtils.getRandomInt(3, 6);

        feudalPlayer.addBalance(balance);
        feudalPlayerDeath.takeBalance(balance + MathUtils.getRandomInt(1, 4));
        feudalPlayerDeath.addDeaths(1);

    }

    @EventHandler
    public final void playerBedEnter(@NotNull PlayerBedEnterEvent event) {

        Player player = event.getPlayer();

        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 400, 0, true, true));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 240, 1, true, true));

        if (!sleepingPlayers.contains(player.getUniqueId().toString()))
            sleepingPlayers.add(player.getUniqueId().toString());

        if (sleepingPlayers.size() / Bukkit.getOnlinePlayers().size() > 2) return;

        World world = player.getWorld();

        world.setTime(24000);
        world.setStorm(false);
        world.setThundering(false);

    }

    @EventHandler
    public void playerBedLeave(@NotNull PlayerBedLeaveEvent event) {
        sleepingPlayers.remove(event.getPlayer().getUniqueId().toString());
    }

    @EventHandler
    public final void playerInteractWithPlayer(@NotNull PlayerInteractAtEntityEvent event) {

        if (!(event.getRightClicked() instanceof Player)) return;

        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer((Player) event.getRightClicked());
        String kingdomName = feudalPlayer.getKingdomName().equals("") ? "Не состоит" : feudalPlayer.getKingdomName();

        CraftPlayer player = (CraftPlayer) event.getPlayer();
        player.getHandle().playerConnection.sendPacket(new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\":\"" +
                "-=[Короролевство: " +
                kingdomName +
                ", класс: " +
                GameClassesIDEnum.getNameByID(feudalPlayer.getAClassID()) +
                ", убийств: " + feudalPlayer.getKills() +
                ", смертей: " + feudalPlayer.getDeaths() +
                "]=-" +
                "\"}"), ChatMessageType.GAME_INFO));

    }

    @EventHandler
    public final void playerEats(@NotNull PlayerItemConsumeEvent event) {

        if (event.getItem().getType().equals(Material.GOLDEN_APPLE)) {

            event.getPlayer().setCooldown(Material.GOLDEN_APPLE, 220);
            return;

        }

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

    @EventHandler(priority = EventPriority.HIGHEST)
    public final void playerOnFoodChange(@NotNull EntityRegainHealthEvent event) {

        if (!(event.getEntity() instanceof Player)) return;

        Player player = (Player) event.getEntity();
        float staminaLvl = CacheFeudalPlayers.getFeudalPlayer(player).getStaminaLvl();
        double defaultAmount = event.getAmount();

        event.setAmount(defaultAmount * (staminaLvl / 100) + defaultAmount);

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public final void playerRegenerationEvent(@NotNull EntityRegainHealthEvent event) {

        if (!(event.getEntity() instanceof Player)) return;

        Player player = (Player) event.getEntity();
        float survivabilityLvl = CacheFeudalPlayers.getFeudalPlayer(player).getSurvivabilityLvl();

        event.setAmount(1 * (survivabilityLvl / 200) + 1);

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public final void playerAttack(@NotNull EntityDamageByEntityEvent event) {

        if (!(event.getDamager() instanceof Player)) return;

        Player player = (Player) event.getDamager();
        Entity entity = event.getEntity();
        float strengthLvl = CacheFeudalPlayers.getFeudalPlayer(player).getStrengthLvl();
        double defaultDamage = event.getDamage();

        event.setDamage(defaultDamage * (strengthLvl / 200) + defaultDamage);

        if (!(entity instanceof Player)) return;

        event.setDamage(defaultDamage - defaultDamage / 100 * (CacheFeudalPlayers.getFeudalPlayer((Player) entity).getStaminaLvl() * 0.2));

    }

    @EventHandler
    public final void playerBlockPlaced(@NotNull BlockPlaceEvent event) {

        Block block = event.getBlock();

        if (!BlockToSaveEnum.checkBlockMaterial(block.getType())) return;

        block.setMetadata("PLACED", new FixedMetadataValue(Feudal.getPlugin(), "true"));
        placedBlocks.add(PlacedBlockWrapper.blockToPlacedBlockWrapper(block));

    }

    @EventHandler
    public final void playerBreakBlock(@NotNull BlockBreakEvent event) {

        Block block = event.getBlock();

        if (!BlockToSaveEnum.checkBlockMaterial(block.getType()) ||
                !placedBlocks.contains(PlacedBlockWrapper.blockToPlacedBlockWrapper(block))) return;

        placedBlocks.remove(PlacedBlockWrapper.blockToPlacedBlockWrapper(block));

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public final void playerBlockBreak(@NotNull BlockBreakEvent event) {

        ItemStack item = event.getPlayer().getInventory().getItemInMainHand();
        if (item.getDurability() == 0 || MathUtils.getRandomInt(1, 26) != 25) return;

        item.setDurability((short) (item.getDurability() + MathUtils.getRandomInt(2, 6)));

    }
}
