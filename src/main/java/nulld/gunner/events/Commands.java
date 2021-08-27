package nulld.gunner.events;

import nulld.gunner.Main;
import nulld.gunner.inventories.CustomInv;
import nulld.gunner.utils.ParticleItems;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

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
                    Main.settings.remove(player.getUniqueId().toString() + ".gunCreateNameOf.name");
                    Main.settings.remove(player.getUniqueId().toString() + ".gunCreateDamageOf.minDamage");
                    Main.settings.remove(player.getUniqueId().toString() + ".gunCreateDamageOf.maxDamage");
                    Main.settings.put(player.getUniqueId().toString() + ".gunCreateBlockBreakOf.blockBreak", "&c불가");
                    temp();
                    player.openInventory(CustomInv.gunCreateInv());
                }
            }
        }
        return false;
    }

    public void temp(){
        List<ItemStack> listParticles = new ArrayList<>();

        listParticles.add(ParticleItems.none);
        listParticles.add(ParticleItems.VILLAGER_ANGRY);
        listParticles.add(ParticleItems.BARRIER);
        listParticles.add(ParticleItems.BLOCK_CRACK);
        listParticles.add(ParticleItems.BLOCK_DUST);
        listParticles.add(ParticleItems.WATER_BUBBLE);
        listParticles.add(ParticleItems.CLOUD);
        listParticles.add(ParticleItems.CRIT);
        listParticles.add(ParticleItems.DAMAGE_INDICATOR);
        listParticles.add(ParticleItems.SUSPENDED_DEPTH);
        listParticles.add(ParticleItems.DRAGON_BREATH);
        listParticles.add(ParticleItems.DRIP_LAVA);
        listParticles.add(ParticleItems.DRIP_WATER);
        listParticles.add(ParticleItems.WATER_DROP);
        listParticles.add(ParticleItems.ENCHANTMENT_TABLE);
        listParticles.add(ParticleItems.END_ROD);
        listParticles.add(ParticleItems.EXPLOSION_NORMAL);
        listParticles.add(ParticleItems.FALLING_DUST);
        listParticles.add(ParticleItems.FIREWORKS_SPARK);
        listParticles.add(ParticleItems.FLAME);
        listParticles.add(ParticleItems.FOOTSTEP);
        listParticles.add(ParticleItems.VILLAGER_HAPPY);
        listParticles.add(ParticleItems.HEART);
        listParticles.add(ParticleItems.EXPLOSION_HUGE);
        listParticles.add(ParticleItems.ITEM_CRACK);
        listParticles.add(ParticleItems.SPELL_INSTANT);
        listParticles.add(ParticleItems.EXPLOSION_LARGE);
        listParticles.add(ParticleItems.SMOKE_LARGE);
        listParticles.add(ParticleItems.LAVA);
        listParticles.add(ParticleItems.CRIT_MAGIC);
        listParticles.add(ParticleItems.SPELL_MOB);
        listParticles.add(ParticleItems.SPELL_MOB_AMBIENT);
        listParticles.add(ParticleItems.MOB_APPEARANCE);
        listParticles.add(ParticleItems.NOTE);
        listParticles.add(ParticleItems.PORTAL);
        listParticles.add(ParticleItems.REDSTONE);
        listParticles.add(ParticleItems.SLIME);
        listParticles.add(ParticleItems.SMOKE_NORMAL);
        listParticles.add(ParticleItems.SNOWBALL);
        listParticles.add(ParticleItems.SNOW_SHOVEL);
        listParticles.add(ParticleItems.SPELL);
        listParticles.add(ParticleItems.SPIT);
        listParticles.add(ParticleItems.WATER_SPLASH);
        listParticles.add(ParticleItems.SUSPENDED);
        listParticles.add(ParticleItems.TOTEM);
        listParticles.add(ParticleItems.TOWN_AURA);
        listParticles.add(ParticleItems.WATER_WAKE);
        listParticles.add(ParticleItems.SPELL_WITCH);

        Main.settings.put("particleGUIItemsList", listParticles);
    }
}
