package nulld.gunner.events;

import nulld.gunner.Main;
import nulld.gunner.inventories.CustomInv;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Commands implements Listener, CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        Player player = (Player) sender;

        if (label.equalsIgnoreCase("총")){
            if (args.length == 0){
                player.sendMessage(Main.coloredText("&a───────────────────<[  &6명령어  &a]>───────────────────"));
                player.sendMessage(Main.coloredText(" &d- /총 파츠 &f: 총 파츠를 장착할 수 있는 GUI를 오픈합니다"));
                player.sendMessage(Main.coloredText(" &d- /총기 강화 &f: 총을 강화할 수 있는 강화 GUI를 오픈합니다."));
                if (player.isOp()){
                    player.sendMessage(Main.coloredText(" &d- /총기 제작 &f: 총기를 제작할 수 있는 GUI를 오픈합니다"));
                    player.sendMessage(Main.coloredText(" &d- /총기 목록 &f: 총기 목록을 오픈합니다."));
                }
                player.sendMessage(Main.coloredText("&a────────────────────────────────────────────────"));
            }
        } else if (label.equalsIgnoreCase("총기")){
            if(args.length == 0){
                player.performCommand("총");
            } else if (args.length == 1){
                if (args[0].equalsIgnoreCase("제작")){
                    Main.settings.remove(player.getUniqueId().toString() + ".gunCreateNameOf.setName");
                    Main.settings.remove(player.getUniqueId().toString() + ".gunCreateDamageOf.minDamage");
                    Main.settings.remove(player.getUniqueId().toString() + ".gunCreateDamageOf.maxDamage");
                    player.openInventory(CustomInv.gunCreateInv());
                }
            }
        }
        return false;
    }
}
