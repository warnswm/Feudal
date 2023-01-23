package feudal.view;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

public class ActionBar {
    public static void showActionBar(Player player) {

        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("ХУЙ"));

    }
}
