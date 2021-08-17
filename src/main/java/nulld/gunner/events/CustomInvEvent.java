package nulld.gunner.events;

import nulld.gunner.Main;
import nulld.gunner.inventories.CustomInv;
import nulld.gunner.utils.Items;
import nulld.gunner.utils.ModelType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.Arrays;
import java.util.List;

public class CustomInvEvent implements Listener {
    @EventHandler
    public void onInvOpen(InventoryOpenEvent event){
        Player player = (Player) event.getPlayer();
        String title = event.getView().getTitle();
        if (title.equalsIgnoreCase("총기 제작")){
            if (Main.settings.get(player.getUniqueId().toString() + ".gunCreateNameOf.setName") != null){
                ItemStack nameItem = event.getInventory().getItem(19);
                ItemMeta nameItemMeta = nameItem.getItemMeta();
                List<String> list = Arrays.asList(Main.coloredText("&e" + Main.settings.get(player.getUniqueId().toString() + ".gunCreateNameOf.setName")));
                nameItemMeta.setLore(list);
                nameItem.setItemMeta(nameItemMeta);
            }
            if (Main.settings.get(player.getUniqueId().toString() + ".gunCreateDamageOf.minDamage") != null && Main.settings.get(player.getUniqueId().toString() + ".gunCreateDamageOf.maxDamage") != null){
                ItemStack damageItem = event.getInventory().getItem(21);
                ItemMeta damageItemMeta = damageItem.getItemMeta();
                List<String> list = Arrays.asList(Main.coloredText("&a최소 데미지 : &f" + Main.settings.get(player.getUniqueId().toString() + ".gunCreateDamageOf.minDamage")), Main.coloredText("&a최대 데미지 : &f" + Main.settings.get(player.getUniqueId().toString() + ".gunCreateDamageOf.maxDamage")));
                damageItemMeta.setLore(list);
                damageItem.setItemMeta(damageItemMeta);
            }
        }
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        Inventory eventInv = event.getClickedInventory();
        Inventory playerInv = player.getInventory();
        String title = ChatColor.stripColor(event.getView().getTitle());
        int slot = event.getSlot();
        ItemStack clickedItem = event.getCurrentItem();

        if (clickedItem != null){
            if (title.equalsIgnoreCase("총기 제작")){
                event.setCancelled(true);
                if (slot == 19){
                    Main.settings.put(player.getUniqueId().toString() + ".gunCreateNameOf.setNameByChat", true);
                    if (eventInv.getItem(4) != null && !eventInv.getItem(4).equals(Items.air)) Main.settings.put(player.getUniqueId().toString() + ".gunCreateModelOf.currentModel", eventInv.getItem(4));
                    player.sendMessage(Main.coloredText(Main.prefix + "&a총기의 이름을 입력해 주세요."));
                    player.closeInventory();
                } else if (slot == 20){
                    if (eventInv.getItem(4) != null && !eventInv.getItem(4).equals(Items.air)) Main.settings.put(player.getUniqueId().toString() + ".gunCreateModelOf.currentModel", eventInv.getItem(4));
                    Main.settings.put(player.getUniqueId().toString() + ".setModelInventory.BukkitTaskCancel", false);
                    player.openInventory(CustomInv.setModelInv(ModelType.GUNS));
                } else if (slot == 21){
                    Main.settings.put(player.getUniqueId().toString() + ".gunCreateDamageOf.setDamageByChat", true);
                    Main.settings.put(player.getUniqueId().toString() + ".gunCreateDamageOf.thisItem", clickedItem);
                    if (eventInv.getItem(4) != null && !eventInv.getItem(4).equals(Items.air)) Main.settings.put(player.getUniqueId().toString() + ".gunCreateModelOf.currentModel", eventInv.getItem(4));
                    player.sendMessage(Main.coloredText(Main.prefix + "&a총기의 데미지를 입력해 주세요."));
                    player.sendMessage(Main.coloredText(Main.prefix + "&e최소데미지 최대데미지&7(ex: 10 20)"));
                    player.closeInventory();
                }
            } else if (title.contains("모델 설정")){
                if (eventInv.equals(playerInv)) event.setCancelled(false);
                else {
                    if (slot != 24) event.setCancelled(true);
                    new BukkitRunnable(){
                        @Override
                        public void run() {
                            if (Main.settings.get(player.getUniqueId().toString() + ".setModelInventory.BukkitTaskCancel") != null && (Boolean) Main.settings.get(player.getUniqueId().toString() + ".setModelInventory.BukkitTaskCancel")) this.cancel();
                            else {
                                ItemStack itemStack = eventInv.getItem(24);
                                ItemStack toSet = null;
                                if (itemStack != null){
                                    toSet = itemStack.clone();
                                    ItemMeta itemMeta = toSet.getItemMeta();
                                    itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                                    itemMeta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
                                    itemMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
                                    itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                                    itemMeta.addItemFlags(ItemFlag.HIDE_DESTROYS);
                                    itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                    if (Main.settings.get(player.getUniqueId().toString() + ".gunCreateNameOf.setName") != null) itemMeta.setDisplayName(Main.coloredText("&f" + Main.settings.get(player.getUniqueId().toString() + ".gunCreateNameOf.setName")));
                                    toSet.setItemMeta(itemMeta);
                                } else toSet = Items.air;

                                eventInv.setItem(20, toSet);
                            }
                        }
                    }.runTaskTimerAsynchronously(Main.getPlugin(Main.class), 0, 1);
                    if (title.contains("총기")){
                        if (slot == 48) {
                            Inventory gunCreateInv = CustomInv.gunCreateInv();
                            CustomInv.updateGunCreateInv(player, gunCreateInv, eventInv.getItem(20));
                            player.openInventory(gunCreateInv);
                        }
                        if (slot == 50){
                            Inventory gunCreateInv = CustomInv.gunCreateInv();
                            CustomInv.updateGunCreateInv(player, gunCreateInv, (ItemStack) Main.settings.get(player.getUniqueId().toString() + ".gunCreateModelOf.currentModel"));
                            player.openInventory(gunCreateInv);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onInvClose(InventoryCloseEvent event){
        Player player = (Player) event.getPlayer();
        String title = event.getView().getTitle();
        if (title.contains("모델 설정") && (Main.settings.get(player.getUniqueId().toString() + ".setModelInventory.BukkitTaskCancel") == null || !((Boolean) Main.settings.get(player.getUniqueId().toString() + ".setModelInventory.BukkitTaskCancel")))){
            Inventory thisInv = event.getInventory();
            new BukkitRunnable(){
                @Override
                public void run(){
                    player.openInventory(thisInv);
                }
            }.runTaskLater(Main.getPlugin(Main.class), 1);
        }
    }
}
