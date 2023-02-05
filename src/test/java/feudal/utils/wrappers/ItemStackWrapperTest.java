package feudal.utils.wrappers;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ItemStackWrapperTest {
    /**
     * Method under test: {@link ItemStackWrapper#itemStackToItemStackWrapper(ItemStack, int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testItemStackToItemStackWrapper() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at org.bukkit.Bukkit.getItemFactory(Bukkit.java:1019)
        //       at org.bukkit.inventory.ItemStack.getItemMeta(ItemStack.java:568)
        //       at feudal.utils.wrappers.ItemStackWrapper.itemStackToItemStackWrapper(ItemStackWrapper.java:33)
        //   See https://diff.blue/R013 to resolve this issue.

        ItemStackWrapper.itemStackToItemStackWrapper(new ItemStack(1), 1);
    }

    /**
     * Method under test: {@link ItemStackWrapper#itemStackToItemStackWrapper(ItemStack, int)}
     */
    @Test
    void testItemStackToItemStackWrapper2() {
        assertThrows(IllegalArgumentException.class, () -> ItemStackWrapper.itemStackToItemStackWrapper(null, 1));
    }

    /**
     * Method under test: {@link ItemStackWrapper#itemStackToItemStackWrapper(ItemStack, int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testItemStackToItemStackWrapper3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at org.bukkit.Bukkit.getItemFactory(Bukkit.java:1019)
        //       at org.bukkit.inventory.ItemStack.getItemMeta(ItemStack.java:568)
        //       at feudal.utils.wrappers.ItemStackWrapper.itemStackToItemStackWrapper(ItemStackWrapper.java:33)
        //   See https://diff.blue/R013 to resolve this issue.

        ItemStackWrapper.itemStackToItemStackWrapper(new ItemStack(-1), 1);
    }

    /**
     * Method under test: {@link ItemStackWrapper#itemStackWrapperToItemStack(ItemStackWrapper)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testItemStackWrapperToItemStack() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at org.bukkit.Bukkit.getItemFactory(Bukkit.java:1019)
        //       at org.bukkit.inventory.ItemStack.getItemMeta(ItemStack.java:568)
        //       at feudal.utils.wrappers.ItemStackWrapper.itemStackToItemStackWrapper(ItemStackWrapper.java:33)
        //   See https://diff.blue/R013 to resolve this issue.

        ItemStackWrapper.itemStackWrapperToItemStack(ItemStackWrapper.itemStackToItemStackWrapper(new ItemStack(1), 1));
    }

    /**
     * Method under test: {@link ItemStackWrapper#itemStackWrapperToItemStack(ItemStackWrapper)}
     */
    @Test
    void testItemStackWrapperToItemStack2() {
        assertThrows(IllegalArgumentException.class, () -> ItemStackWrapper.itemStackWrapperToItemStack(null));
    }

    /**
     * Method under test: {@link ItemStackWrapper#itemStackWrapperToItemStack(ItemStackWrapper)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testItemStackWrapperToItemStack3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at org.bukkit.Bukkit.getItemFactory(Bukkit.java:1019)
        //       at org.bukkit.inventory.ItemStack.getItemMeta(ItemStack.java:568)
        //       at feudal.utils.wrappers.ItemStackWrapper.itemStackWrapperToItemStack(ItemStackWrapper.java:44)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<String> lore = new ArrayList<>();
        ItemStackWrapper.itemStackWrapperToItemStack(
                new ItemStackWrapper(Material.AIR, (short) 1, 10, "Name", lore, new HashMap<>(), 1));
    }

    /**
     * Method under test: {@link ItemStackWrapper#itemStackWrapperToItemStack(ItemStackWrapper)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testItemStackWrapperToItemStack4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at org.bukkit.inventory.ItemStack.<init>(ItemStack.java:68)
        //       at feudal.utils.wrappers.ItemStackWrapper.itemStackWrapperToItemStack(ItemStackWrapper.java:41)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<String> lore = new ArrayList<>();
        ItemStackWrapper
                .itemStackWrapperToItemStack(new ItemStackWrapper(null, (short) 1, 10, "Name", lore, new HashMap<>(), 1));
    }
}

