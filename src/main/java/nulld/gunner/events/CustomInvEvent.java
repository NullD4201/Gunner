package nulld.gunner.events;

import nulld.gunner.Main;
import nulld.gunner.inventories.CustomInv;
import nulld.gunner.utils.Items;
import nulld.gunner.utils.ModelType;
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

import java.util.*;
import java.util.stream.Collectors;

public class CustomInvEvent implements Listener {
    @EventHandler
    public void onInvOpen(InventoryOpenEvent event){
        Player player = (Player) event.getPlayer();
        String title = event.getView().getTitle();
        if (title.equalsIgnoreCase("총기 제작")){
            if (Main.settings.get(player.getUniqueId().toString() + ".gunCreateNameOf.setName") != null){
                ItemStack nameItem = event.getInventory().getItem(19);
                ItemMeta nameItemMeta = nameItem.getItemMeta();
                List<String> list = Collections.singletonList(Main.coloredText("&f" + Main.settings.get(player.getUniqueId().toString() + ".gunCreateNameOf.setName")));
                nameItemMeta.setLore(list);
                nameItem.setItemMeta(nameItemMeta);
            }
            if (Main.settings.get(player.getUniqueId().toString() + ".gunCreateDamageOf.minDamage") != null && Main.settings.get(player.getUniqueId().toString() + ".gunCreateDamageOf.maxDamage") != null){
                ItemStack damageItem = event.getInventory().getItem(21);
                ItemMeta damageItemMeta = damageItem.getItemMeta();
                List<String> list = Collections.singletonList(new Data("총기 데미지", Main.settings.get(player.getUniqueId().toString() + ".gunCreateDamageOf.minDamage") + " ~ " + Main.settings.get(player.getUniqueId().toString() + ".gunCreateDamageOf.maxDamage")).toString());
                damageItemMeta.setLore(list);
                damageItem.setItemMeta(damageItemMeta);
            }
            if (Main.settings.get(player.getUniqueId().toString() + ".gunCreateScatterOf.scatter") != null){
                ItemStack nameItem = event.getInventory().getItem(22);
                ItemMeta nameItemMeta = nameItem.getItemMeta();
                List<String> list = Collections.singletonList(new Data("총기 탄 퍼짐", Main.settings.get(player.getUniqueId().toString() + ".gunCreateScatterOf.scatter")).toString());
                nameItemMeta.setLore(list);
                nameItem.setItemMeta(nameItemMeta);
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
                if (Arrays.asList(19, 20, 21, 22, 23, 24, 29, 30, 31, 32, 33, 34).contains(slot)) {
                    if (eventInv.getItem(4) != null && !eventInv.getItem(4).equals(Items.air)) Main.settings.put(player.getUniqueId().toString() + ".gunCreateModelOf.currentModel", eventInv.getItem(4));
                    else Main.settings.put(player.getUniqueId().toString() + ".gunCreateModelOf.currentModel", Items.air);
                    if (slot == 19){ // 이름
                        Main.settings.put(player.getUniqueId().toString() + ".gunCreateNameOf.setNameByChat", true);
                        player.sendMessage(Main.coloredText(Main.prefix + "&a총기의 이름을 입력해 주세요."));
                        player.closeInventory();
                    } else if (slot == 20){ // 모델
                        Main.settings.put(player.getUniqueId().toString() + ".setModelInventory.BukkitTaskCancel", false);
                        player.openInventory(CustomInv.setModelInv(ModelType.GUNS));
                    } else if (slot == 21){ // 데미지
                        Main.settings.put(player.getUniqueId().toString() + ".gunCreateDamageOf.setDamageByChat", true);
                        player.sendMessage(Main.coloredText(Main.prefix + "&a총기의 데미지를 입력해 주세요."));
                        player.sendMessage(Main.coloredText(Main.prefix + "&e최소데미지 최대데미지&7(ex: 10 20)"));
                        player.closeInventory();
                    } else if (slot == 22){ // 탄 퍼짐
                        Main.settings.put(player.getUniqueId().toString() + ".gunCreateScatterOf.setScatterByChat", true);
                        player.sendMessage(Main.coloredText(Main.prefix + "&a총기의 탄 퍼짐 값을 입력해 주세요."));
                        player.sendMessage(Main.coloredText(Main.prefix + "&e0.0 ~ 100.0"));
                        player.closeInventory();
                    } else if (slot == 23){ // 반동
                        Main.settings.put(player.getUniqueId().toString() + ".gunCreateKnockBackOf.setKnockBackByChat", true);
                        Main.settings.put(player.getUniqueId().toString() + ".gunCreateKnockBackOf.thisItem", clickedItem);
                        player.sendMessage(Main.coloredText(Main.prefix + "&e0.0 ~ 100.0"));
                        player.closeInventory();
                    } else if (slot == 24){ // 탄속
                        Main.settings.put(player.getUniqueId().toString() + ".gunCreateSpeedOf.setSpeedByChat", true);
                        player.sendMessage(Main.coloredText(Main.prefix + "&a1초당 탄의 개수를 입력해 주세요."));
                        player.closeInventory();
                    } else if (slot == 29){ // 장전 속도
                        Main.settings.put(player.getUniqueId().toString() + ".gunCreateReloadSpeedOf.setReloadSpeedByChat", true);
                        player.sendMessage(Main.coloredText(Main.prefix + "&a장전하는데 걸리는 시간을 입력해 주세요."));
                        player.closeInventory();
                    } else if (slot == 30){ // 무게
                        Main.settings.put(player.getUniqueId().toString() + ".gunCreateWeightOf.setWeightByChat", true);
                        player.sendMessage(Main.coloredText(Main.prefix + "&a총기의 무게를 입력해 주세요."));
                        player.closeInventory();
                    } else if (slot == 31){ // 거리
                        Main.settings.put(player.getUniqueId().toString() + ".gunCreateDistanceOf.setDistanceByChat", true);
                        player.sendMessage(Main.coloredText(Main.prefix + "&a총기의 사거리를 입력해 주세요."));
                        player.closeInventory();
                    } else if (slot == 32){ // 종류
                        player.openInventory(CustomInv.gunTypeInv((ItemStack) Main.settings.get(player.getUniqueId().toString() + ".gunCreateModelOf.currentModel")));
                    } else if (slot == 33){ // 블록 파괴
                        player.openInventory(CustomInv.blockBreakInv((ItemStack) Main.settings.get(player.getUniqueId().toString() + ".gunCreateModelOf.currentModel")));
                    } else if (slot == 34){ // 피격 효과
                        player.openInventory(CustomInv.effectInv((ItemStack) Main.settings.get(player.getUniqueId().toString() + ".gunCreateModelOf.currentModel")));
                    }
                } else if (slot == 25){ // 탄창
                    player.openInventory(CustomInv.shotContainerSelectInv(1));
                } else if (slot == 28) { // 총선 색상
                    player.openInventory(CustomInv.shotColorInv());
                } else if (slot == 37) { // 강화 재료
                    player.openInventory(CustomInv.ingredientInv());
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
                                ItemStack toSet;
                                if (itemStack != null){
                                    toSet = itemStack.clone();
                                    ItemMeta itemMeta = toSet.getItemMeta();
                                    List<Data> list = new ArrayList<>();
                                    itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                                    itemMeta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
                                    itemMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
                                    itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                                    itemMeta.addItemFlags(ItemFlag.HIDE_DESTROYS);
                                    itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                    if (Main.settings.get(player.getUniqueId().toString() + ".gunCreateNameOf.setName") != null) itemMeta.setDisplayName(Main.coloredText("&f" + Main.settings.get(player.getUniqueId().toString() + ".gunCreateNameOf.setName")));
                                    if (Main.settings.get(player.getUniqueId().toString() + ".gunCreateDamageOf.minDamage") != null) list.add(new Data("총기 데미지", Main.settings.get(player.getUniqueId().toString() + ".gunCreateDamageOf.minDamage") + " ~ " + Main.settings.get(player.getUniqueId().toString() + ".gunCreateDamageOf.maxDamage")));
                                    if (Main.settings.get(player.getUniqueId().toString() + ".gunCreateScatterOf.scatter") != null) list.add(new Data("총기 탄 퍼짐", Main.settings.get(player.getUniqueId().toString() + ".gunCreateScatterOf.scatter")));
                                    if (Main.settings.get(player.getUniqueId().toString() + ".gunCreateKnockBackOf.knockBack") != null) list.add(new Data("총기 반동", Main.settings.get(player.getUniqueId().toString() + ".gunCreateKnockBackOf.knockBack")));
                                    if (Main.settings.get(player.getUniqueId().toString() + ".gunCreateSpeedOf.shotSpeed") != null) list.add(new Data("총기 탄속", Main.settings.get(player.getUniqueId().toString() + ".gunCreateSpeedOf.shotSpeed")));
                                    if (Main.settings.get(player.getUniqueId().toString() + ".gunCreateReloadSpeedOf.reloadSpeed") != null) list.add(new Data("총기 장전 속도", Main.settings.get(player.getUniqueId().toString() + ".gunCreateReloadSpeedOf.reloadSpeed")));
                                    if (Main.settings.get(player.getUniqueId().toString() + ".gunCreateWightOf.weight") != null) list.add(new Data("총기 무게", Main.settings.get(player.getUniqueId().toString() + ".gunCreateWeightOf.weight")));
                                    if (Main.settings.get(player.getUniqueId().toString() + ".gunCreateDistanceOf.distance") != null) list.add(new Data("총기 거리", Main.settings.get(player.getUniqueId().toString() + ".gunCreateDistanceOf.distance")));
                                    if (Main.settings.get(player.getUniqueId().toString() + ".gunCreateKindOf.kind") != null) list.add(new Data("총기 종류", Main.settings.get(player.getUniqueId().toString() + ".gunCreateKindOf.kind")));
                                    if (Main.settings.get(player.getUniqueId().toString() + ".gunCreateBlockBreakOf.blockBreak") != null) list.add(new Data("총기 블록 파괴", Main.settings.get(player.getUniqueId().toString() + ".gunCreateBlockBreakOf.blockBreak")));
                                    if (Main.settings.get(player.getUniqueId().toString() + ".gunCreateEffectOf.effect") != null) list.add(new Data("총기 피격 효과", Main.settings.get(player.getUniqueId().toString() + ".gunCreateEffectOf.effect")));
                                    list.sort(new DataSorter());
                                    List<String> lore = list.stream().map(Data::toString).collect(Collectors.toCollection(ArrayList::new));
                                    itemMeta.setLore(lore);
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

class DataSorter implements Comparator<Data>{
    @Override
    public int compare(Data d1, Data d2){
        if (Main.gunItemLoreSeq.indexOf(d1.dataType) < Main.gunItemLoreSeq.indexOf(d2.dataType)) return 1;
        else if (Main.gunItemLoreSeq.indexOf(d1.dataType) > Main.gunItemLoreSeq.indexOf(d2.dataType)) return -1;
        return 0;
    }
}

class Data{
    String dataType;
    Object dataObj;

    public Data(String dataType, Object dataObj){
        this.dataType = dataType;
        this.dataObj = dataObj;
    }

    @Override
    public String toString(){
        return Main.coloredText("&f" + this.dataType + " : &e" + this.dataObj);
    }
}