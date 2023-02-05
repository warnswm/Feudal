package feudal.utils;

import feudal.utils.wrappers.BannerWrapper;
import feudal.utils.wrappers.ChunkWrapper;
import feudal.utils.wrappers.ItemStackWrapper;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GsonUtilsTest {
    /**
     * Method under test: {@link GsonUtils#chunkToJson(ChunkWrapper)}
     */
    @Test
    void testChunkToJson() {
        assertEquals("{\"worldName\":\"World Name\",\"x\":2,\"z\":1}",
                GsonUtils.chunkToJson(new ChunkWrapper("World Name", 2, 1)));
        assertThrows(IllegalArgumentException.class, () -> GsonUtils.chunkToJson(null));
        assertEquals("{\"x\":2,\"z\":1}", GsonUtils.chunkToJson(new ChunkWrapper(null, 2, 1)));
        assertEquals("{\"worldName\":\"\",\"x\":2,\"z\":1}", GsonUtils.chunkToJson(new ChunkWrapper("", 2, 1)));
        assertEquals(
                "{\"worldName\":\"Argument for @NotNull parameter \\u0027%s\\u0027 of %s.%s must not be null\",\"x\":2,\"z\":3}",
                GsonUtils
                        .chunkToJson(new ChunkWrapper("Argument for @NotNull parameter '%s' of %s.%s must not be null", 2, 3)));
    }

    /**
     * Method under test: {@link GsonUtils#bannerToJson(BannerWrapper)}
     */
    @Test
    void testBannerToJson() {
        assertEquals("{}", GsonUtils.bannerToJson(new BannerWrapper()));
        assertThrows(IllegalArgumentException.class, () -> GsonUtils.bannerToJson(null));
    }

    /**
     * Method under test: {@link GsonUtils#itemStackWrapperToJson(List)}
     */
    @Test
    void testItemStackWrapperToJson() {
        assertEquals("[]", GsonUtils.itemStackWrapperToJson(new ArrayList<>()));
        assertThrows(IllegalArgumentException.class, () -> GsonUtils.itemStackWrapperToJson(null));
    }

    /**
     * Method under test: {@link GsonUtils#itemStackWrapperToJson(List)}
     */
    @Test
    void testItemStackWrapperToJson2() {
        ArrayList<ItemStackWrapper> itemStackWrapperList = new ArrayList<>();
        itemStackWrapperList.add(null);
        assertEquals("[null]", GsonUtils.itemStackWrapperToJson(itemStackWrapperList));
    }

    /**
     * Method under test: {@link GsonUtils#itemStackWrapperToJson(List)}
     */
    @Test
    void testItemStackWrapperToJson3() {
        ArrayList<ItemStackWrapper> itemStackWrapperList = new ArrayList<>();
        ArrayList<String> lore = new ArrayList<>();
        itemStackWrapperList.add(new ItemStackWrapper(Material.AIR, (short) 1, 10, "Name", lore, new HashMap<>(), 1));
        assertEquals(
                "[{\"type\":\"AIR\",\"durability\":1,\"amount\":10,\"name\":\"Name\",\"lore\":[],\"enchants\":{},\"price\":1}]",
                GsonUtils.itemStackWrapperToJson(itemStackWrapperList));
    }

    /**
     * Method under test: {@link GsonUtils#itemStackWrapperToJson(List)}
     */
    @Test
    void testItemStackWrapperToJson4() {
        ArrayList<ItemStackWrapper> itemStackWrapperList = new ArrayList<>();
        ArrayList<String> lore = new ArrayList<>();
        itemStackWrapperList.add(new ItemStackWrapper(null, (short) 1, 10, "Name", lore, new HashMap<>(), 1));
        assertEquals("[{\"durability\":1,\"amount\":10,\"name\":\"Name\",\"lore\":[],\"enchants\":{},\"price\":1}]",
                GsonUtils.itemStackWrapperToJson(itemStackWrapperList));
    }

    /**
     * Method under test: {@link GsonUtils#itemStackWrapperToJson(List)}
     */
    @Test
    void testItemStackWrapperToJson5() {
        ArrayList<ItemStackWrapper> itemStackWrapperList = new ArrayList<>();
        ArrayList<String> lore = new ArrayList<>();
        itemStackWrapperList.add(new ItemStackWrapper(Material.AIR, (short) 1, 10, "", lore, new HashMap<>(), 1));
        assertEquals(
                "[{\"type\":\"AIR\",\"durability\":1,\"amount\":10,\"name\":\"\",\"lore\":[],\"enchants\":{},\"price\":1}]",
                GsonUtils.itemStackWrapperToJson(itemStackWrapperList));
    }

    /**
     * Method under test: {@link GsonUtils#itemStackWrapperToJson(List)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testItemStackWrapperToJson6() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at org.bukkit.enchantments.EnchantmentWrapper.getName(EnchantmentWrapper.java:44)
        //       at org.bukkit.enchantments.Enchantment.toString(Enchantment.java:272)
        //       at java.lang.String.valueOf(String.java:2994)
        //       at com.google.gson.internal.bind.MapTypeAdapterFactory$Adapter.write(MapTypeAdapterFactory.java:207)
        //       at com.google.gson.internal.bind.MapTypeAdapterFactory$Adapter.write(MapTypeAdapterFactory.java:145)
        //       at com.google.gson.internal.bind.TypeAdapterRuntimeTypeWrapper.write(TypeAdapterRuntimeTypeWrapper.java:69)
        //       at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$1.write(ReflectiveTypeAdapterFactory.java:125)
        //       at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$Adapter.write(ReflectiveTypeAdapterFactory.java:243)
        //       at com.google.gson.internal.bind.ObjectTypeAdapter.write(ObjectTypeAdapter.java:107)
        //       at com.google.gson.internal.bind.TypeAdapterRuntimeTypeWrapper.write(TypeAdapterRuntimeTypeWrapper.java:69)
        //       at com.google.gson.internal.bind.CollectionTypeAdapterFactory$Adapter.write(CollectionTypeAdapterFactory.java:97)
        //       at com.google.gson.internal.bind.CollectionTypeAdapterFactory$Adapter.write(CollectionTypeAdapterFactory.java:61)
        //       at com.google.gson.Gson.toJson(Gson.java:669)
        //       at com.google.gson.Gson.toJson(Gson.java:648)
        //       at com.google.gson.Gson.toJson(Gson.java:603)
        //       at com.google.gson.Gson.toJson(Gson.java:583)
        //       at feudal.utils.GsonUtils.itemStackWrapperToJson(GsonUtils.java:20)
        //   See https://diff.blue/R013 to resolve this issue.

        HashMap<Enchantment, Integer> enchantmentIntegerMap = new HashMap<>();
        enchantmentIntegerMap.put(new EnchantmentWrapper(1), 1);
        ItemStackWrapper e = new ItemStackWrapper(Material.AIR, (short) 1, 10, "Name", new ArrayList<>(),
                enchantmentIntegerMap, 1);

        ArrayList<ItemStackWrapper> itemStackWrapperList = new ArrayList<>();
        itemStackWrapperList.add(e);
        GsonUtils.itemStackWrapperToJson(itemStackWrapperList);
    }

    /**
     * Method under test: {@link GsonUtils#itemStackWrapperToJson(List)}
     */
    @Test
    void testItemStackWrapperToJson7() {
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("foo");
        stringList.add("foo");
        ItemStackWrapper e = new ItemStackWrapper(Material.AIR, (short) 1, 10, "Name", stringList, new HashMap<>(), 1);

        ArrayList<ItemStackWrapper> itemStackWrapperList = new ArrayList<>();
        itemStackWrapperList.add(e);
        assertEquals(
                "[{\"type\":\"AIR\",\"durability\":1,\"amount\":10,\"name\":\"Name\",\"lore\":[\"foo\",\"foo\"],\"enchants\":{},"
                        + "\"price\":1}]",
                GsonUtils.itemStackWrapperToJson(itemStackWrapperList));
    }
}

