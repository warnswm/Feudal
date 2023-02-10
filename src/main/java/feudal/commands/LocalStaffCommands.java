package feudal.commands;

import feudal.data.builder.FeudalKingdom;
import feudal.data.builder.FeudalPlayer;
import feudal.data.cache.CacheKingdomsMap;
import feudal.data.cache.CachePlayersMap;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class LocalStaffCommands implements CommandExecutor {
    private static boolean isLs(CommandSender sender, String[] args) {
        return !(sender instanceof Player) && !sender.hasPermission("feudal.ls") && !args[0].equals("ls");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (isLs(sender, args)) return false;

        assert sender instanceof Player;
        Player player = (Player) sender;

        FeudalPlayer feudalPlayer;
        FeudalKingdom feudalKingdom;


        switch (args[0].toLowerCase()) {

            case "changegameclass":

                feudalPlayer = CachePlayersMap.getFeudalPlayer(Bukkit.getPlayer(args[1]));
                feudalPlayer.setaClassID(Integer.parseInt(args[2]));

                break;

            case "addchunk":

                feudalKingdom = CacheKingdomsMap.getKingdomInfo().get(args[1]);
                feudalKingdom.addTerritory(player.getLocation().getChunk());

                break;

            case "test":

                ItemStack item = new ItemStack(Material.DIAMOND_SWORD);

                net.minecraft.server.v1_12_R1.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);

                NBTTagCompound tag = nmsItem.getTag() != null ? nmsItem.getTag() : new NBTTagCompound();
                tag.setBoolean(args[1], true);
                tag.setInt(args[2], 10);

                nmsItem.setTag(tag);
                player.getInventory().addItem(CraftItemStack.asBukkitCopy(nmsItem));

                break;

        }

        return false;
    }
}
