package nulld.gunner;

import nulld.gunner.utils.EnableEvents;
import nulld.gunner.utils.ExtraStrings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class Main extends JavaPlugin implements Listener {
    public static String coloredText(String string){ return ChatColor.translateAlternateColorCodes('&', string); }
    public static String prefix = "&8[ &e&lGunner&8 ] ";
    public static ConsoleCommandSender console = Bukkit.getConsoleSender();
    public static Map<String, Object> settings = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        EnableEvents.eventLoader();

        console.sendMessage(ExtraStrings.enableLogo);
        console.sendMessage(coloredText(prefix + "&aPlugin Enabled."));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        for (Player player : Bukkit.getOnlinePlayers()) player.closeInventory();
        console.sendMessage(coloredText(prefix + "&cPlugin Disabled."));
    }
}
