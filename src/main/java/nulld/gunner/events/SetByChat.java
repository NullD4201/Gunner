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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
            if (isNumeric(messages[0]) && isNumeric(messages[1])) {
                if ((messages.length > 2) || (Double.parseDouble(messages[0]) > Double.parseDouble(messages[1]))) player.sendMessage(Main.coloredText(Main.prefix + "&e최소데미지 최대데미지&7(ex: 10 20)"));
                else {
                    Main.settings.put(player.getUniqueId().toString() + ".gunCreateDamageOf.minDamage", Double.parseDouble(messages[0]));
                    Main.settings.put(player.getUniqueId().toString() + ".gunCreateDamageOf.maxDamage", Double.parseDouble(messages[1]));
                    Main.settings.put(player.getUniqueId().toString() + ".gunCreateDamageOf.setDamageByChat", false);

                    if (toSetItemStack != null && !(toSetItemStack.equals(Items.air))){
                        ItemMeta toSetItemMeta = toSetItemStack.getItemMeta();
                        List<Data> list = new ArrayList<>();
                        list.add(new Data("총기 데미지", Main.settings.get(player.getUniqueId().toString() + ".gunCreateDamageOf.minDamage") + " ~ " + Main.settings.get(player.getUniqueId().toString() + ".gunCreateDamageOf.maxDamage")));
                        list.sort(new DataSorter());
                        List<String> lore = list.stream().map(Data::toString).collect(Collectors.toCollection(ArrayList::new));
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
            } else player.sendMessage(Main.prefix + "&c정수 또는 실수만 입력해 주세요.");
        }
        if ((Boolean) Main.settings.get(player.getUniqueId().toString() + ".gunCreateScatterOf.setScatterByChat")){
            event.setCancelled(true);
            if (isNumeric(message)){
                Main.settings.put(player.getUniqueId().toString() + ".gunCreateScatterOf.scatter", Double.parseDouble(message));
                Main.settings.put(player.getUniqueId().toString() + ".gunCreateScatterOf.setScatterByChat", false);

                if (toSetItemStack != null && !(toSetItemStack.equals(Items.air))){
                    ItemMeta toSetItemMeta = toSetItemStack.getItemMeta();
                    List<Data> list = new ArrayList<>();
                    list.add(new Data("총기 탄 퍼짐", Main.settings.get(player.getUniqueId().toString() + ".gunCreateScatterOf.scatter")));
                    list.sort(new DataSorter());
                    List<String> lore = list.stream().map(Data::toString).collect(Collectors.toCollection(ArrayList::new));
                    toSetItemMeta.setLore(lore);
                    toSetItemStack.setItemMeta(toSetItemMeta);

                    toOpen.setItem(4, toSetItemStack);
                }
                player.openInventory(toOpen);
            } else player.sendMessage(Main.prefix + "&c정수 또는 실수만 입력해 주세요.");
        }
        if ((Boolean) Main.settings.get(player.getUniqueId().toString() + ".gunCreateSpeedOf.setSpeedByChat")){
            event.setCancelled(true);
            if (isNumeric(message)){
                Main.settings.put(player.getUniqueId().toString() + ".gunCreateSpeedOf.shotSpeed", Double.parseDouble(message));
                Main.settings.put(player.getUniqueId().toString() + ".gunCreateSpeedOf.setSpeedByChat", false);

                if (toSetItemStack != null && !(toSetItemStack.equals(Items.air))){
                    ItemMeta toSetItemMeta = toSetItemStack.getItemMeta();
                    List<Data> list = new ArrayList<>();
                    list.add(new Data("총기 탄속", Main.settings.get(player.getUniqueId().toString() + ".gunCreateSpeedOf.shotSpeed")));
                    list.sort(new DataSorter());
                    List<String> lore = list.stream().map(Data::toString).collect(Collectors.toCollection(ArrayList::new));
                    toSetItemMeta.setLore(lore);
                    toSetItemStack.setItemMeta(toSetItemMeta);

                    toOpen.setItem(4, toSetItemStack);
                }
                player.openInventory(toOpen);
            } else player.sendMessage(Main.prefix + "&c정수 또는 실수만 입력해 주세요.");
        }
    }

    public static boolean isNumeric(String string){
        try {
            Double.parseDouble(string);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }
}
