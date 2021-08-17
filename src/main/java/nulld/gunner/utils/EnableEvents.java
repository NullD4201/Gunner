package nulld.gunner.utils;

import nulld.gunner.Main;
import nulld.gunner.events.Commands;
import nulld.gunner.events.CustomInvEvent;
import nulld.gunner.events.SetByChat;
import nulld.gunner.events.TabComplete;
import org.bukkit.Bukkit;

public class EnableEvents {
    public static void eventLoader(){
        Bukkit.getServer().getPluginManager().registerEvents(new TabComplete(), Main.getPlugin(Main.class));
        Bukkit.getServer().getPluginManager().registerEvents(new Commands(), Main.getPlugin(Main.class));
        Bukkit.getServer().getPluginManager().registerEvents(new SetByChat(), Main.getPlugin(Main.class));
        Bukkit.getServer().getPluginManager().registerEvents(new CustomInvEvent(), Main.getPlugin(Main.class));
        Bukkit.getPluginCommand("총").setExecutor(new Commands());
        Bukkit.getPluginCommand("총기").setExecutor(new Commands());
        Bukkit.getPluginCommand("총").setTabCompleter(new TabComplete());
        Bukkit.getPluginCommand("총기").setTabCompleter(new TabComplete());
    }
}
