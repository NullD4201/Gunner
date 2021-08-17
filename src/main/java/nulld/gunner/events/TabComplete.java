package nulld.gunner.events;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.Listener;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TabComplete implements Listener, TabCompleter {
    public static List<String> Guns = Arrays.asList("제작", "목록", "강화");

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args){
        if (label.equalsIgnoreCase("총기")){
            if (sender.isOp()) return StringUtil.copyPartialMatches(args[0], Guns, new ArrayList<>());
            else return StringUtil.copyPartialMatches(args[0], Arrays.asList("강화"), new ArrayList<>());
        } else if (label.equalsIgnoreCase("총")) return StringUtil.copyPartialMatches(args[0], Arrays.asList("파츠"), new ArrayList<>());
        else return StringUtil.copyPartialMatches(args[0], new ArrayList<>(), new ArrayList<>());
    }
}
