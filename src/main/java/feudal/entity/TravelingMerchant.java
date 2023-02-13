package feudal.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TravelingMerchant {

    Location location;
    Map<Integer, ItemStack> products = new HashMap<>();
    Map<Integer, ItemStack> purchase = new HashMap<>();

    public void spawnTraveling() {

        Villager travelingMerchant = (Villager) location.getWorld().spawnEntity(location, EntityType.VILLAGER);

        travelingMerchant.setNoDamageTicks(36000);

        travelingMerchant.setCustomNameVisible(true);
        travelingMerchant.setCustomName("§aСтранствующий торговец");

        travelingMerchant.setCollidable(false);
        travelingMerchant.resetMaxHealth();

//        travelingMerchant.set

    }
}
