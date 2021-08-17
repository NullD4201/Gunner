package nulld.gunner.events;

import nulld.gunner.Main;
import nulld.gunner.inventories.CustomInv;
import nulld.gunner.utils.Items;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class SetByChat implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        String message  = event.getMessage();
        Inventory toOpen = CustomInv.gunCreateInv();
        ItemStack toSetItemStack = (ItemStack) Main.settings.get(player.getUniqueId().toString() + ".gunCreateModelOf.currentModel");

        if ((Boolean) Main.settings.get(player.getUniqueId().toString() + ".gunCreateNameOf.setNameByChat")){
            Main.settings.put(player.getUniqueId().toString() + ".gunCreateNameOf.setName", message);
            Main.settings.put(player.getUniqueId().toString() + ".gunCreateNameOf.setNameByChat", false);
            event.setCancelled(true);

            if (toSetItemStack != null && !(toSetItemStack.equals(Items.air))){
                ItemMeta toSetItemMeta = toSetItemStack.getItemMeta();
                toSetItemMeta.setDisplayName(Main.coloredText("&f" + message));
                toSetItemStack.setItemMeta(toSetItemMeta);

                toOpen.setItem(4, toSetItemStack);
            }
            player.openInventory(toOpen);
        }
        if ((Boolean) Main.settings.get(player.getUniqueId().toString() + ".gunCreateDamageOf.setDamageByChat")){
            String[] messages = message.split(" ");
            event.setCancelled(true);

            if ((messages.length > 2) || (Double.parseDouble(messages[0]) > Double.parseDouble(messages[1]))) player.sendMessage(Main.coloredText(Main.prefix + "&e최소데미지 최대데미지&7(ex: 10 20)"));
            else {
                Main.settings.put(player.getUniqueId().toString() + ".gunCreateDamageOf.minDamage", Double.parseDouble(messages[0]));
                Main.settings.put(player.getUniqueId().toString() + ".gunCreateDamageOf.maxDamage", Double.parseDouble(messages[1]));
                Main.settings.put(player.getUniqueId().toString() + ".gunCreateDamageOf.setDamageByChat", false);

                if (toSetItemStack != null && !(toSetItemStack.equals(Items.air))){
                    ItemMeta toSetItemMeta = toSetItemStack.getItemMeta();
                    List<String> lore = Arrays.asList(Main.coloredText("&a최소 데미지 : &f" + Main.settings.get(player.getUniqueId().toString() + ".gunCreateDamageOf.minDamage")), Main.coloredText("&a최대 데미지 : &f" + Main.settings.get(player.getUniqueId().toString() + ".gunCreateDamageOf.maxDamage")));
                    toSetItemMeta.setLore(lore);
                    toSetItemStack.setItemMeta(toSetItemMeta);

                    ItemStack thisItem = (ItemStack) Main.settings.get(player.getUniqueId().toString() + ".gunCreateDamageOf.thisItem");
                    ItemMeta thisItemMeta = thisItem.getItemMeta();
                    thisItemMeta.setLore(lore);
                    thisItem.setItemMeta(thisItemMeta);

                    toOpen.setItem(4, toSetItemStack);
                    toOpen.setItem(21, thisItem);
                }
                player.openInventory(toOpen);
            }
        }
    }
}
