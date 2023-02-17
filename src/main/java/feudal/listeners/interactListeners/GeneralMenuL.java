package feudal.listeners.interactListeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;

public class GeneralMenuL implements Listener {

    public static boolean isaBoolean(@NotNull InventoryClickEvent event) {
        return !event.getView().getTitle().equals("Почта") &&
                !event.getView().getTitle().equals("Взаимодействие с приглашением") &&
                !event.getView().getTitle().equals("Прокачка класса") &&
                !event.getView().getTitle().equals("Выберите класс") &&
                !event.getView().getTitle().equals("Выберите зачарование для снятия") &&
                !event.getView().getTitle().equals("Аукцион") &&
                !event.getView().getTitle().equals("Подтверждение покупки") &&
                !event.getView().getTitle().equals("Прокачка атрибутов") &&
                !event.getView().getTitle().equals("Смена класса") ||
                event.getCurrentItem() == null ||
                event.getCurrentItem().getItemMeta() == null;
    }

    @EventHandler
    public final void interactInventory(@NotNull InventoryClickEvent event) {

        if (GeneralMenuL.isaBoolean(event)) return;

        event.setCancelled(true);

        Player player = (Player) event.getView().getPlayer();

        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Закрыть меню"))
            player.closeInventory();

    }
}
