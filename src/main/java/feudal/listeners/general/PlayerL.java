package feudal.listeners.general;

import feudal.Feudal;
import feudal.data.FeudalPlayer;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.data.cache.CacheFeudalValues;
import feudal.utils.enums.professionEnums.ProfessionIDE;
import feudal.utils.wrappers.PlacedBlockWrapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
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
import java.util.concurrent.ThreadLocalRandom;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PlayerL implements Listener {

    @Getter
    private final static List<PlacedBlockWrapper> placedBlocks = new ArrayList<>();
    List<Integer> sleepingPlayers = new ArrayList<>();

    @EventHandler
    public final void playerTeleport(@NotNull PlayerTeleportEvent event) {

        if (event.getCause() == PlayerTeleportEvent.TeleportCause.ENDER_PEARL)
            event.getPlayer().setCooldown(Material.ENDER_PEARL, 220);

    }

    @EventHandler
    public final void entityDeath(@NotNull EntityDeathEvent event) {

        if (event.getEntity().getKiller() == null) return;

        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(event.getEntity().getKiller());

        if (event.getEntityType() != EntityType.PLAYER) {

            feudalPlayer.addBalance(MoneyForMobsE.getMoneyByEntity(event.getEntityType()));
            return;

        }

        feudalPlayer.addKills(1);

        FeudalPlayer feudalPlayerDeath = CacheFeudalPlayers.getFeudalPlayer((Player) event.getEntity());
        int balance = feudalPlayerDeath.getBalance() / 100 * ThreadLocalRandom.current().nextInt(3, 6);

        feudalPlayer.addBalance(balance);
        feudalPlayerDeath.takeBalance(balance + ThreadLocalRandom.current().nextInt(1, 4));
        feudalPlayerDeath.addDeaths(1);

    }

    @EventHandler
    public final void playerBedEnter(@NotNull PlayerBedEnterEvent event) {

        Player player = event.getPlayer();

        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 400, 0, true, true));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 240, 1, true, true));

        if (!sleepingPlayers.contains(player.getUniqueId().hashCode()))
            sleepingPlayers.add(player.getUniqueId().hashCode());

        if (sleepingPlayers.size() / Bukkit.getOnlinePlayers().size() > 2) return;

        World world = player.getWorld();

        world.setTime(24000);
        world.setStorm(false);
        world.setThundering(false);

    }

    @EventHandler
    public void playerBedLeave(@NotNull PlayerBedLeaveEvent event) {
        sleepingPlayers.remove(event.getPlayer().getUniqueId().hashCode());
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
                ProfessionIDE.getNameByID(feudalPlayer.getProfessionID()) +
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

        event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 600, Objects.requireNonNull(CraftItemStack.asNMSCopy(event.getItem()).getTag()).getByte("chefLvl") == 100 ? 1 : 0, true, true));

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

        if (entity instanceof Player)
            event.setDamage(defaultDamage - defaultDamage / 100 * (CacheFeudalPlayers.getFeudalPlayer((Player) entity).getStaminaLvl() * 0.2));

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public final void playerBlockBreak(@NotNull BlockBreakEvent event) {

        ItemStack item = event.getPlayer().getInventory().getItemInMainHand();

        if (item.getDurability() != 0 &&
                ThreadLocalRandom.current().nextInt(1, 26) == 25)
            item.setDurability((short) (item.getDurability() + ThreadLocalRandom.current().nextInt(2, 6)));


        Block block = event.getBlock();

        if (BlockToSaveE.checkBlockMaterial(block.getType()))
            placedBlocks.remove(new PlacedBlockWrapper(block.getWorld().getName(), block.getX(), block.getY(), block.getZ()));

    }

    @EventHandler
    public final void playerBlockPlaced(@NotNull BlockPlaceEvent event) {

        Block block = event.getBlock();

        if (!BlockToSaveE.checkBlockMaterial(block.getType())) return;

        block.setMetadata("PLACED", new FixedMetadataValue(Feudal.getPlugin(), "true"));
        placedBlocks.add(new PlacedBlockWrapper(block.getWorld().getName(), block.getX(), block.getY(), block.getZ()));

    }

}

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
enum BlockToSaveE {

    COAL_ORE(Material.COAL_ORE), IRON_ORE(Material.IRON_ORE), GOLD_ORE(Material.GOLD_ORE),
    DIAMOND_ORE(Material.DIAMOND_ORE), EMERALD_ORE(Material.EMERALD_ORE), LAPIS_ORE(Material.LAPIS_ORE),
    REDSTONE_ORE(Material.REDSTONE_ORE), LOG(Material.LOG);

    Material blockMaterial;

    public static boolean checkBlockMaterial(Material blockMaterial) {

        if (blockMaterial == null) return false;

        for (BlockToSaveE blockToSaveE : values())
            if (blockToSaveE.getBlockMaterial() == blockMaterial)
                return true;

        return false;

    }
}

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
enum MoneyForMobsE {
    BAT(EntityType.BAT, get(EntityType.BAT)),
    CHICKEN(EntityType.CHICKEN, get(EntityType.CHICKEN)),
    COW(EntityType.COW, get(EntityType.COW)),
    MUSHROOM_COW(EntityType.MUSHROOM_COW, get(EntityType.MUSHROOM_COW)),
    HORSE(EntityType.HORSE, get(EntityType.HORSE)),
    OCELOT(EntityType.OCELOT, get(EntityType.OCELOT)),
    PARROT(EntityType.PARROT, get(EntityType.PARROT)),
    POLAR_BEAR(EntityType.POLAR_BEAR, get(EntityType.POLAR_BEAR)),
    PIG(EntityType.PIG, get(EntityType.PIG)),
    RABBIT(EntityType.RABBIT, get(EntityType.RABBIT)),
    SHEEP(EntityType.SHEEP, get(EntityType.SHEEP)),
    SNOW_MAN(EntityType.SNOWMAN, get(EntityType.SNOWMAN)),
    SQUID(EntityType.SQUID, get(EntityType.SQUID)),
    VILLAGER(EntityType.VILLAGER, get(EntityType.VILLAGER)),
    CAVE_SPIDER(EntityType.CAVE_SPIDER, get(EntityType.CAVE_SPIDER)),
    ENDERMAN(EntityType.ENDERMAN, get(EntityType.ENDERMAN)),
    IRON_GOLEM(EntityType.IRON_GOLEM, get(EntityType.IRON_GOLEM)),
    LLAMA(EntityType.LLAMA, get(EntityType.LLAMA)),
    SPIDER(EntityType.SPIDER, get(EntityType.SPIDER)),
    WOLF(EntityType.WOLF, get(EntityType.WOLF)),
    BLAZE(EntityType.BLAZE, get(EntityType.BLAZE)),
    CREEPER(EntityType.CREEPER, get(EntityType.CREEPER)),
    ELDER_GUARDIAN(EntityType.ELDER_GUARDIAN, get(EntityType.ELDER_GUARDIAN)),
    ENDERMITE(EntityType.ENDERMITE, get(EntityType.ENDERMITE)),
    EVOKER(EntityType.EVOKER, get(EntityType.EVOKER)),
    GHAST(EntityType.GHAST, get(EntityType.GHAST)),
    GUARDIAN(EntityType.GUARDIAN, get(EntityType.GUARDIAN)),
    HUSK(EntityType.HUSK, get(EntityType.HUSK)),
    MAGMA_CUBE(EntityType.MAGMA_CUBE, get(EntityType.MAGMA_CUBE)),
    SHULKER(EntityType.SHULKER, get(EntityType.SHULKER)),
    SILVERFISH(EntityType.SILVERFISH, get(EntityType.SILVERFISH)),
    SKELETON(EntityType.SKELETON, get(EntityType.SKELETON)),
    SLIME(EntityType.SLIME, get(EntityType.SLIME)),
    STRAY(EntityType.STRAY, get(EntityType.STRAY)),
    VEX(EntityType.VEX, get(EntityType.VEX)),
    VINDICATOR(EntityType.VINDICATOR, get(EntityType.VINDICATOR)),
    WITCH(EntityType.WITCH, get(EntityType.WITCH)),
    WITHER_SKELETON(EntityType.WITHER_SKELETON, get(EntityType.WITHER_SKELETON)),
    ZOMBIE(EntityType.ZOMBIE, get(EntityType.ZOMBIE)),
    ZOMBIE_VILLAGER(EntityType.ZOMBIE_VILLAGER, get(EntityType.ZOMBIE_VILLAGER)),
    ENDER_DRAGON(EntityType.ENDER_DRAGON, get(EntityType.ENDER_DRAGON)),
    WITHER(EntityType.WITHER, get(EntityType.WITHER));

    EntityType entity;
    int money;

    public static int getMoneyByEntity(EntityType entity) {

        if (entity == null) return 0;

        for (MoneyForMobsE moneyForMobsE : values())
            if (moneyForMobsE.getEntity() == entity)
                return moneyForMobsE.getMoney();

        return 0;

    }

    private static int get(EntityType entity) {

        if (entity == null) return 0;

        if (CacheFeudalValues.getMoneyForMobs().containsKey(entity))
            return CacheFeudalValues.getMoneyForMobs().get(entity);

        return 0;

    }
}
