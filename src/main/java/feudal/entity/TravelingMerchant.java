package feudal.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@UtilityClass
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TravelingMerchant {

    final List<Material> possibleProductSale = new ArrayList<>();
    final List<Material> possibleProductPurchase = new ArrayList<>();
    @Getter
    @Setter
    Map<Integer, ItemStack> sale;
    @Getter
    @Setter
    Map<Integer, ItemStack> purchase;

    static {

        possibleProductSale.add(Material.END_CRYSTAL);
        possibleProductSale.add(Material.GOLDEN_APPLE);
        possibleProductSale.add(Material.BEACON);
        possibleProductSale.add(Material.BEACON);
        possibleProductSale.add(Material.MOB_SPAWNER);

//        possibleProductSale.add(Material.RAW_FISH);
//        possibleProductPurchase.add(Material.RAW_FISH);

        possibleProductPurchase.add(Material.TOTEM);
        possibleProductPurchase.add(Material.NETHER_STAR);
        possibleProductPurchase.add(Material.ELYTRA);
        possibleProductPurchase.add(Material.ENCHANTMENT_TABLE);

    }

    public void spawnTraveling() {

        World world = Bukkit.getWorld("world");

        int xCord = ThreadLocalRandom.current().nextInt(-25000, 25000);
        int zCord = ThreadLocalRandom.current().nextInt(-25000, 25000);
        int yCord = 0;

        for (int y = 255; y > 0; y--) {

            Block block = world.getBlockAt(xCord, y, zCord);
            Material material = block.getType();

            if (material.isTransparent() ||
                    material.equals(Material.STATIONARY_LAVA) ||
                    material.equals(Material.AIR))
                continue;

            yCord = block.getY() + 1;

        }

        Location location = new Location(world, xCord, yCord, zCord);


        Villager travelingMerchant = (Villager) location.getWorld().spawnEntity(location, EntityType.VILLAGER);

        travelingMerchant.setNoDamageTicks(36000);

        travelingMerchant.setCustomNameVisible(true);
        travelingMerchant.setCustomName("§aСтранствующий торговец");

        travelingMerchant.setCollidable(false);
        travelingMerchant.resetMaxHealth();

    }
}
