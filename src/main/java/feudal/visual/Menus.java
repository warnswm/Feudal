package feudal.visual;

import feudal.data.Auction;
import feudal.data.FeudalPlayer;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.utils.CreateItemUtils;
import feudal.utils.enums.professionEnums.AttributeForProfessionE;
import feudal.utils.enums.professionEnums.ProfessionIDE;
import feudal.utils.wrappers.ItemStackWrapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Menus {

    Player player;

    public void travelingMerchantMenu() {

        Inventory travelingMerchantMenu = Bukkit.createInventory(player, 54, "Странствующий торговец");
        byte numberPurchaseSlots = 0, numberSaleSlots = 0;

        for (int i = 10; i <= 39; i++) {

            travelingMerchantMenu.setItem(i, CreateItemUtils.createItem(Material.CLAY_BALL, 1, "Покупка"));

            numberPurchaseSlots++;
            if (numberPurchaseSlots == 3) {

                i += 6;
                numberPurchaseSlots = 0;

            }

        }

        for (int j = 14; j <= 43; j++) {

            travelingMerchantMenu.setItem(j, CreateItemUtils.createItem(Material.CLAY_BALL, 1, "Продажа"));

            numberSaleSlots++;

            if (numberSaleSlots == 3) {

                j += 6;
                numberSaleSlots = 0;

            }

        }

        player.openInventory(travelingMerchantMenu);

    }

    public void switchingProfessionOneStageMenu() {

        Inventory switchingProfession = Bukkit.createInventory(player, 9, "Смена профессии");

        int i = 0;

        for (ProfessionIDE value : ProfessionIDE.values()) {

            if (value.getId() == CacheFeudalPlayers.getFeudalPlayer(player).getProfessionID() ||
                    value.getId() > 9) continue;

            switchingProfession.setItem(i, CreateItemUtils.createItem(Material.CLAY_BALL, 1, value.getProfessionName()));
            i++;

        }

        player.openInventory(switchingProfession);

    }

    public void auctionMenu(int page) {

        Inventory auction = Bukkit.createInventory(player, 54, "Аукцион");

        int minItemIndex = 28 * page + 1;

        for (int i = 10; i <= 37; i++)
            if (Auction.getProducts().size() >= minItemIndex)
                auction.setItem(i, ItemStackWrapper.itemStackWrapperToItemStack(Auction.getProducts().get(minItemIndex)));

        player.openInventory(auction);

    }


    public void upgradeProfessionMenu() {

        Inventory upgradeProfessionInv = Bukkit.createInventory(player, 9, "Прокачка профессии");
        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);

        if (feudalPlayer.getProfessionLvl() >= 50)
            upgradeProfessionInv.setItem(4, CreateItemUtils.createItem(Material.CLAY_BALL, 1, "Смена класса"));

        upgradeProfessionInv.setItem(2, CreateItemUtils.createItem(Material.CLAY_BALL, 1, AttributeForProfessionE.getOneAttributeNameByID(feudalPlayer.getProfessionID())));
        upgradeProfessionInv.setItem(6, CreateItemUtils.createItem(Material.CLAY_BALL, 1, AttributeForProfessionE.getSecondAttributeNameByID(feudalPlayer.getProfessionID())));

        player.openInventory(upgradeProfessionInv);

    }

    public void professionSelectionMenu() {

        Inventory professionSelection = Bukkit.createInventory(player, 54, "Выбор профессии");

        professionSelection.setItem(10, CreateItemUtils.createItem(Material.CLAY_BALL, 1, "Строитель"));
        professionSelection.setItem(12, CreateItemUtils.createItem(Material.CLAY_BALL, 1, "Повар"));
        professionSelection.setItem(14, CreateItemUtils.createItem(Material.CLAY_BALL, 1, "Фермер"));
        professionSelection.setItem(16, CreateItemUtils.createItem(Material.CLAY_BALL, 1, "Рыболов"));
        professionSelection.setItem(28, CreateItemUtils.createItem(Material.CLAY_BALL, 1, "Охотник"));
        professionSelection.setItem(30, CreateItemUtils.createItem(Material.CLAY_BALL, 1, "Шахтёр"));
        professionSelection.setItem(32, CreateItemUtils.createItem(Material.CLAY_BALL, 1, "Пастух"));
        professionSelection.setItem(34, CreateItemUtils.createItem(Material.CLAY_BALL, 1, "Торговец"));
        professionSelection.setItem(49, CreateItemUtils.createItem(Material.CLAY_BALL, 1, "Дровосек"));

        player.openInventory(professionSelection);

    }

    public void mailMenu() {

        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);
        Inventory mailMenu = Bukkit.createInventory(player, 54, "Почта");

        int iterations = Math.min(feudalPlayer.getLetters().size(), 54);

        for (int i = 0; i < iterations; i++) {

            String text = feudalPlayer.getLetters().get(i);
            String title = text.length() < 10 ? text : feudalPlayer.getLetters().get(i).substring(0, 10);

            mailMenu.setItem(i, CreateItemUtils.createItem(Material.PAPER, 1, title + "...", feudalPlayer.getLetters().get(i)));

        }

        player.openInventory(mailMenu);

    }

    public void clerkMenu() {

        ItemStack item = player.getInventory().getItemInMainHand();

        if (item.getEnchantments().isEmpty()) {

            player.sendMessage("Предмет в основной руке не имеет зачарования!");
            return;

        }

        Inventory clerkMenu = Bukkit.createInventory(player, 54, "Выберите зачарование для снятия");

        int i = 10;

        for (Enchantment enchantment : item.getEnchantments().keySet()) {

            clerkMenu.setItem(i, CreateItemUtils.createItem(Material.ENCHANTED_BOOK, enchantment, item.getEnchantments().get(enchantment), 1));
            i++;

        }


        player.openInventory(clerkMenu);

    }

    public void attributesUpMenu(int attributeLvl, String attributeName, double percent) {

        Inventory attributesPumpingMenuInv = Bukkit.createInventory(player, 9, "Прокачка атрибутов");
        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);

        double percentAtt = Math.pow(1 + (percent / 100), attributeLvl) * 100;

        if (attributeLvl == 100) {

            attributesPumpingMenuInv.setItem(4, CreateItemUtils.createItem(Material.GRAY_SHULKER_BOX, 1, "Атрибут " + attributeName + " имеет максимальный уровень!"));
            return;

        }

        if (feudalPlayer.getExperience() >= percentAtt)
            attributesPumpingMenuInv.setItem(4, CreateItemUtils.createItem(Material.GREEN_SHULKER_BOX, 1, "Прокачать уровень " + attributeName));

        else
            attributesPumpingMenuInv.setItem(4, CreateItemUtils.createItem(Material.GRAY_SHULKER_BOX, 1, "Не достаточно опыта, нужно ещё " + (int) (percentAtt - feudalPlayer.getExperience())));


        player.openInventory(attributesPumpingMenuInv);

    }

}
