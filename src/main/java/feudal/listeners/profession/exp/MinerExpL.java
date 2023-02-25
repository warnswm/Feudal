package feudal.listeners.profession.exp;

import feudal.data.FeudalPlayer;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.utils.enums.professionEnums.ProfessionIDE;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.jetbrains.annotations.NotNull;

public class MinerExpL implements Listener {

    @EventHandler
    public final void playerBlockBreak(@NotNull BlockBreakEvent event) {

        Block block = event.getBlock();
        if (block.hasMetadata("PLACED")) return;

        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(event.getPlayer());

        if (feudalPlayer.getProfessionID() != ProfessionIDE.MINER.getId()) return;

        int exp = BlocksForMinerE.getAttributeExpByMaterial(block.getType());

        feudalPlayer.addExperience(exp);
        feudalPlayer.addProfessionExperience(4 * exp);

    }
}

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
enum BlocksForMinerE {
    COAL_ORE(Material.COAL_ORE, 3), IRON_ORE(Material.IRON_ORE, 5), GOLD_ORE(Material.GOLD_ORE, 8),
    DIAMOND_ORE(Material.DIAMOND_ORE, 15), EMERALD_ORE(Material.EMERALD_ORE, 25), LAPIS_ORE(Material.LAPIS_ORE, 10),
    REDSTONE_ORE(Material.REDSTONE_ORE, 4);

    Material material;
    int attributeExp;

    public static int getAttributeExpByMaterial(Material material) {

        if (material == null) return 0;

        for (BlocksForMinerE blocksForMiner : values())
            if (blocksForMiner.getMaterial() == material)
                return blocksForMiner.getAttributeExp();

        return 0;

    }
}
