package nulld.gunner;

import nulld.gunner.utils.EnableEvents;
import nulld.gunner.utils.ExtraStrings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Main extends JavaPlugin implements Listener {
    public static String coloredText(String string){ return ChatColor.translateAlternateColorCodes('&', string); }
    public static String prefix = "&8[ &e&lGunner&8 ] ";
    public static ConsoleCommandSender console = Bukkit.getConsoleSender();
    public static Map<String, Object> settings = new HashMap<>();

    public static List<String> gunItemLoreSeq = Arrays.asList("총기 데미지", "총기 탄 퍼짐", "총기 반동", "총기 탄속", "총기 장전 속도", "총기 무게", "총기 거리", "총기 종류", "총기 블록 파괴", "총기 피격 효과");

    @Override
    public void onEnable() {
        // Plugin startup logic
        EnableEvents.eventLoader();
        File gunsFolder = new File(getDataFolder() + "/Guns");
        if (!gunsFolder.exists()) gunsFolder.mkdirs();
        File containersFolder = new File(getDataFolder() + "/Containers");
        if (!containersFolder.exists()) containersFolder.mkdirs();
        File shotsFolder = new File(getDataFolder() + "/Shots");
        if (!shotsFolder.exists()) shotsFolder.mkdirs();
        File partsFolder = new File(getDataFolder() + "/Parts");
        if (!partsFolder.exists()) partsFolder.mkdirs();

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
