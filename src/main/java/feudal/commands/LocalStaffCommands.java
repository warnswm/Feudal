package feudal.commands;

import feudal.auction.Auction;
import feudal.data.builder.FeudalKingdom;
import feudal.data.builder.FeudalPlayer;
import feudal.data.cache.CacheKingdomsMap;
import feudal.data.cache.CachePlayersMap;
import feudal.utils.TabUtils;
import feudal.utils.wrappers.ItemStackWrapper;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class LocalStaffCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player) && !sender.hasPermission("feudal.ls") && !args[0].equals("ls")) return false;

        assert sender instanceof Player;
        Player player = (Player) sender;

        FeudalPlayer feudalPlayer;
        FeudalKingdom feudalKingdom;


        switch (args[0].toLowerCase()) {

            case "changegameclass":

                feudalPlayer = CachePlayersMap.getPlayerInfo().get(player);
                feudalPlayer.setaClassID(Integer.parseInt(args[2]));
                break;
            case "addchunk":

                feudalKingdom = CacheKingdomsMap.getKingdomInfo().get(args[1]);
                feudalKingdom.addTerritory(player.getLocation().getChunk());
                break;

            case "addah":

                if (player.getInventory().getItemInMainHand() == null ||
                        Integer.parseInt(args[1]) > 1_000_000_000) break;
                Auction.addProduct(ItemStackWrapper.itemStackToItemStackWrapper(player.getInventory().getItemInMainHand(), Integer.parseInt(args[1])));
                break;

            case "addnbtstr":

                if (player.getInventory().getItemInMainHand() == null) break;

                net.minecraft.server.v1_12_R1.ItemStack nmsStrItem = CraftItemStack.asNMSCopy(player.getInventory().getItemInMainHand());

                NBTTagCompound strTag = nmsStrItem.getTag() != null ? nmsStrItem.getTag() : new NBTTagCompound();
                strTag.setString(args[1], args[2]);

                nmsStrItem.setTag(strTag);

                player.getInventory().remove(player.getInventory().getItemInMainHand());
                player.getInventory().addItem(CraftItemStack.asBukkitCopy(nmsStrItem));

                break;

            case "addnbtbyte":

                if (player.getInventory().getItemInMainHand() == null) break;

                net.minecraft.server.v1_12_R1.ItemStack nmsByteItem = CraftItemStack.asNMSCopy(player.getInventory().getItemInMainHand());

                NBTTagCompound byteTag = nmsByteItem.getTag() != null ? nmsByteItem.getTag() : new NBTTagCompound();
                byteTag.setByte(args[1], Byte.parseByte(args[2]));

                nmsByteItem.setTag(byteTag);

                player.getInventory().remove(player.getInventory().getItemInMainHand());
                player.getInventory().addItem(CraftItemStack.asBukkitCopy(nmsByteItem));

                break;

            case "spy":

                if (player.getGameMode().equals(GameMode.SPECTATOR)) {

                    TabUtils.showPlayer(player);

                    if (player.hasPotionEffect(PotionEffectType.NIGHT_VISION))
                        player.removePotionEffect(PotionEffectType.NIGHT_VISION);

                    break;

                }

                TabUtils.hidePlayer(player);
                player.setGameMode(GameMode.SPECTATOR);

                if (!player.hasPotionEffect(PotionEffectType.NIGHT_VISION))
                    player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 100000, 0, true, true));

                break;

        }

        return false;
    }
}
