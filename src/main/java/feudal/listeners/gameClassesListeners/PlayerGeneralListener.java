package feudal.listeners.gameClassesListeners;

import feudal.databaseAndCache.CachePlayers;
import feudal.databaseAndCache.PlayerInfo;
import feudal.utils.CreateItemUtil;
import feudal.utils.enums.ClassesIDEnum;
import feudal.utils.enums.GameClassesIDEnum;
import feudal.utils.enums.MoneyForMobsEnum;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;

import static feudal.utils.MathUtils.getRandInt;
import static feudal.utils.enums.EnchantmentEnum.getRandomEnc;

public class PlayerGeneralListener implements Listener {

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
    public void playerBreakBlock(@NotNull BlockBreakEvent event) {

        PlayerInfo playerInfo = CachePlayers.getPlayerInfo().get(event.getPlayer());
        Block block = event.getBlock();

        if (playerInfo.getAClassID() == GameClassesIDEnum.BUILDER.getId() &&
                block.getType().equals(Material.MOB_SPAWNER))
            System.out.println(block.getState());
//            block.getWorld().dropItemNaturally(block.getLocation(), block)

        if (block.hasMetadata("PLACED")) return;

        if (playerInfo.getAClassID() == GameClassesIDEnum.MINER.getId() &&
                block.getType().equals(Material.GOLD_ORE) ||
                block.getType().equals(Material.IRON_ORE))
            block.getWorld().dropItemNaturally(block.getLocation(), block.getType().equals(Material.GOLD_ORE) ? playerInfo.getGameClassLvl() >= 25 ? CreateItemUtil.createItem(Material.GOLD_INGOT, (int) (1 + Math.random() * 3)) : new ItemStack(Material.GOLD_INGOT) : playerInfo.getGameClassLvl() >= 25 ? CreateItemUtil.createItem(Material.IRON_INGOT, (int) (1 + Math.random() * 3)) : new ItemStack(Material.IRON_INGOT));

        else if (playerInfo.getAClassID() == GameClassesIDEnum.WOODCUTTER.getId())
            cutDownTree(block.getLocation(), event.getPlayer().getInventory().getItemInMainHand());


    }
    private void cutDownTree(@NotNull Location location, ItemStack handStack) {

        LinkedList<Block> blocks = new LinkedList<>();

        for (int i = location.getBlockY(); i < location.getWorld().getHighestBlockYAt(location.getBlockX(), location.getBlockZ());) {

            Location l = location.add(0, 1, 0);
            Block block = l.getBlock();

            if (block.getType().equals(Material.LOG)) {

                blocks.add(l.getBlock());

                i++;
                continue;
            }
            break;
        }

        for (Block block : blocks) {

            if (!block.breakNaturally(handStack)) return;

            handStack.setDurability((short)(handStack.getDurability() + 1));

            if (handStack.getType().getMaxDurability() == handStack.getDurability()) {
                handStack.setType(Material.AIR);
                return;
            }

        }
    }
    @EventHandler
    public void playerFishing(@NotNull PlayerFishEvent event) {

        PlayerInfo playerInfo = CachePlayers.getPlayerInfo().get(event.getPlayer());

        if (playerInfo.getAClassID() != ClassesIDEnum.FISHERMAN.getId() ||
                playerInfo.getGameClassLvl() < 25 ||
                event.getState() != PlayerFishEvent.State.CAUGHT_FISH) return;

        int item = getRandInt(1, 5), random = getRandInt(1, 3);

        if (random != 1 && random != 2) return;


        switch (item) {
            case 1:
                event.getPlayer().getInventory().addItem((new ItemStack(Material.BOW)));
                break;
            case 2:
                event.getPlayer().getInventory().addItem(CreateItemUtil.createItem(Material.ENCHANTED_BOOK, getRandomEnc(), 1, 1));
                break;
            case 3:
                event.getPlayer().getInventory().addItem((new ItemStack(Material.FISHING_ROD)));
                break;
            case 4:
                event.getPlayer().getInventory().addItem((new ItemStack(Material.NAME_TAG)));
                break;
            case 5:
                event.getPlayer().getInventory().addItem((new ItemStack(Material.SADDLE)));
                break;
        }

    }
    @EventHandler
    public void playerItemEnchant(@NotNull EnchantItemEvent event) {

        PlayerInfo playerInfo = CachePlayers.getPlayerInfo().get(event.getEnchanter());

        if (event.getItem().getType().equals(Material.BOOK) && playerInfo.getAClassID() != ClassesIDEnum.CLERK.getId())
            event.setCancelled(true);

        if (playerInfo.getGameClassLvl() < 75) return;

        event.setExpLevelCost((int) (event.getExpLevelCost() - event.getExpLevelCost() * 0.3));

        if (playerInfo.getGameClassLvl() != 100) return;

        event.getItem().addUnsafeEnchantment(getRandomEnc(), 1);
    }
//    @EventHandler
//    public void playerInteract(@NotNull PlayerInteractEvent event) {
//
//        Player player = event.getPlayer();
//
//        if (event.getClickedBlock() == null ||
//                !event.getClickedBlock().getType().equals(Material.ENCHANTMENT_TABLE) ||
//                !player.isSneaking() ||
//                !event.getAction().equals(Action.RIGHT_CLICK_BLOCK) ||
//                !event.getMaterial().equals(Material.ENCHANTED_BOOK)) return;
//
//        for (Enchantment e : player.getInventory().getItemInMainHand().getEnchantments().keySet())
//            player.getInventory().getItemInMainHand().removeEnchantment(e);
//
//    }
}
