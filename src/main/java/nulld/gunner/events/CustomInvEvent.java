package nulld.gunner.events;

import de.tr7zw.nbtapi.NBTItem;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import nulld.gunner.Main;
import nulld.gunner.inventories.CustomInv;
import nulld.gunner.utils.Items;
import nulld.gunner.utils.ModelType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CustomInvEvent implements Listener {
    @EventHandler
    public void onInvOpen(InventoryOpenEvent event) {
        Player player = (Player) event.getPlayer();
        String title = event.getView().getTitle();
        if (title.equalsIgnoreCase("총기 제작")) {
            openGunCreate(player, event, 19, "gunCreateNameOf.name", "이름", null);
            openGunCreate(player, event, 21, "gunCreateDamageOf.minDamage", "총기 데미지", null);
            openGunCreate(player, event, 22, "gunCreateScatterOf.scatter", "총기 탄 퍼짐", null);
            openGunCreate(player, event, 23, "gunCreateKnockBackOf.knockback", "총기 반동", null);
            openGunCreate(player, event, 24, "gunCreateSpeedOf.shotSpeed", "총기 탄속", null);
            openGunCreate(player, event, 25, "gunCreateShotContainerOf.list", "총기 탄창", (List<String>) Main.settings.get(player.getUniqueId().toString() + ".gunCreateShotContainerOf.list"));
            openGunCreate(player, event, 29, "gunCreateReloadSpeedOf.reloadSpeed", "총기 장전 속도", null);
            openGunCreate(player, event, 30, "gunCreateWeightOf.weight", "총기 무게", null);
            openGunCreate(player, event, 31, "gunCreateDistanceOf.distance", "총기 거리", null);
            openGunCreate(player, event, 32, "gunCreateTypeOf.type", "총기 종류", null);
            openGunCreate(player, event, 33, "gunCreateBlockBreakOf.blockBreak", "총기 블록 파괴", null);
            openGunCreate(player, event, 34, "gunCreateEffectOf.effect", "총기 피격 효과", null);
            openGunCreate(player, event, 28, "gunCreateColorOf.particleName", "총기 총선 색상", null);

            ItemStack itemStack = (ItemStack) Main.settings.get(player.getUniqueId().toString() + ".gunCreateModelOf.currentModel");
            if (itemStack != null) CustomInv.updateThisItemInv(player, itemStack);
            else itemStack = Items.air;

            event.getView().setItem(4, itemStack);
        }
    }

    public static void openGunCreate(Player player, InventoryOpenEvent event, int index, String booleanType, String dataType, @Nullable List<String> cList) {
        if (Main.settings.get(player.getUniqueId().toString() + "." + booleanType) != null) {
            ItemStack itemStack = event.getInventory().getItem(index);
            ItemMeta itemMeta = itemStack.getItemMeta();
            List<String> list = Collections.singletonList(new Data(dataType, Main.settings.get(player.getUniqueId().toString() + "." + booleanType)).toString());
            if (booleanType.contains("Damage"))
                list = Collections.singletonList(new Data(dataType, Main.settings.get(player.getUniqueId().toString() + "." + booleanType) + " ~ " + Main.settings.get(player.getUniqueId().toString() + ".gunCreateDamageOf.maxDamage")).toString());
            if (cList != null) list = cList;
            itemMeta.setLore(list);
            itemStack.setItemMeta(itemMeta);
        }
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory eventInv = event.getClickedInventory();
        Inventory playerInv = player.getInventory();
        String title = ChatColor.stripColor(event.getView().getTitle());
        int slot = event.getSlot();
        ItemStack clickedItem = event.getCurrentItem();

        if (clickedItem != null) {
            if (title.equalsIgnoreCase("총기 제작")) {
                event.setCancelled(true);
                if (Arrays.asList(19, 20, 21, 22, 23, 24, 29, 30, 31, 32, 33, 34).contains(slot)) {
                    if (eventInv.getItem(4) != null && !eventInv.getItem(4).equals(Items.air))
                        Main.settings.put(player.getUniqueId().toString() + ".gunCreateModelOf.currentModel", eventInv.getItem(4));
                    else
                        Main.settings.put(player.getUniqueId().toString() + ".gunCreateModelOf.currentModel", Items.air);
                    if (slot == 19) { // 이름
                        Main.settings.put(player.getUniqueId().toString() + ".gunCreateNameOf.setNameByChat", true);
                        player.sendMessage(Main.coloredText(Main.prefix + "&a총기의 이름을 입력해 주세요."));
                        player.closeInventory();
                    } else if (slot == 20) { // 모델
                        Main.settings.put(player.getUniqueId().toString() + ".setModelInventory.BukkitTaskCancel", false);
                        player.openInventory(CustomInv.setModelInv(ModelType.GUNS));
                    } else if (slot == 21) { // 데미지
                        Main.settings.put(player.getUniqueId().toString() + ".gunCreateDamageOf.setDamageByChat", true);
                        Main.settings.put(player.getUniqueId().toString() + ".gunCreateDamageOf.thisItem", clickedItem);
                        player.sendMessage(Main.coloredText(Main.prefix + "&a총기의 데미지를 입력해 주세요."));
                        player.sendMessage(Main.coloredText(Main.prefix + "&e최소데미지 최대데미지&7(ex: 10 20)"));
                        player.closeInventory();
                    } else if (slot == 22) { // 탄 퍼짐
                        Main.settings.put(player.getUniqueId().toString() + ".gunCreateScatterOf.setScatterByChat", true);
                        Main.settings.put(player.getUniqueId().toString() + ".gunCreateScatterOf.thisItem", clickedItem);
                        player.sendMessage(Main.coloredText(Main.prefix + "&a총기의 탄 퍼짐 값을 입력해 주세요."));
                        player.sendMessage(Main.coloredText(Main.prefix + "&e0.0 ~ 100.0"));
                        player.closeInventory();
                    } else if (slot == 23) { // 반동
                        Main.settings.put(player.getUniqueId().toString() + ".gunCreateKnockBackOf.setKnockBackByChat", true);
                        Main.settings.put(player.getUniqueId().toString() + ".gunCreateKnockBackOf.thisItem", clickedItem);
                        player.sendMessage(Main.coloredText(Main.prefix + "&a총기의 반동을 입력해 주세요."));
                        player.sendMessage(Main.coloredText(Main.prefix + "&e0.0 ~ 100.0"));
                        player.closeInventory();
                    } else if (slot == 24) { // 탄속
                        Main.settings.put(player.getUniqueId().toString() + ".gunCreateSpeedOf.setSpeedByChat", true);
                        player.sendMessage(Main.coloredText(Main.prefix + "&a1초당 탄의 개수를 입력해 주세요."));
                        player.closeInventory();
                    } else if (slot == 29) { // 장전 속도
                        Main.settings.put(player.getUniqueId().toString() + ".gunCreateReloadSpeedOf.setReloadSpeedByChat", true);
                        player.sendMessage(Main.coloredText(Main.prefix + "&a장전하는데 걸리는 시간을 입력해 주세요."));
                        player.closeInventory();
                    } else if (slot == 30) { // 무게
                        Main.settings.put(player.getUniqueId().toString() + ".gunCreateWeightOf.setWeightByChat", true);
                        player.sendMessage(Main.coloredText(Main.prefix + "&a총기의 무게를 입력해 주세요."));
                        player.closeInventory();
                    } else if (slot == 31) { // 거리
                        Main.settings.put(player.getUniqueId().toString() + ".gunCreateDistanceOf.setDistanceByChat", true);
                        player.sendMessage(Main.coloredText(Main.prefix + "&a총기의 사거리를 입력해 주세요."));
                        player.closeInventory();
                    } else if (slot == 32) { // 종류
                        player.openInventory(CustomInv.gunTypeInv(player, (ItemStack) Main.settings.get(player.getUniqueId().toString() + ".gunCreateModelOf.currentModel")));
                    } else if (slot == 33) { // 블록 파괴
                        player.openInventory(CustomInv.blockBreakInv(player, (ItemStack) Main.settings.get(player.getUniqueId().toString() + ".gunCreateModelOf.currentModel")));
                    } else if (slot == 34) { // 피격 효과
                        player.openInventory(CustomInv.effectInv((ItemStack) Main.settings.get(player.getUniqueId().toString() + ".gunCreateModelOf.currentModel")));
                    }
                } else if (slot == 25) { // 탄창
                    Main.settings.computeIfAbsent(player.getUniqueId().toString() + ".gunCreateShotContainerOf.list", k -> new ArrayList<>());
                    if (eventInv.getItem(4) != null && !eventInv.getItem(4).equals(Items.air))
                        Main.settings.put(player.getUniqueId().toString() + ".gunCreateModelOf.currentModel", eventInv.getItem(4));
                    else
                        Main.settings.put(player.getUniqueId().toString() + ".gunCreateModelOf.currentModel", Items.air);
                    player.openInventory(CustomInv.shotContainerSelectInv(1));
                } else if (slot == 28) { // 총선 색상
                    if (eventInv.getItem(4) != null && !eventInv.getItem(4).equals(Items.air))
                        Main.settings.put(player.getUniqueId().toString() + ".gunCreateModelOf.currentModel", eventInv.getItem(4));
                    else
                        Main.settings.put(player.getUniqueId().toString() + ".gunCreateModelOf.currentModel", Items.air);
                    player.openInventory(CustomInv.shotColorInv());
                } else if (slot == 37) { // 강화 재료
                    if (eventInv.getItem(4) != null && !eventInv.getItem(4).equals(Items.air))
                        Main.settings.put(player.getUniqueId().toString() + ".gunCreateModelOf.currentModel", eventInv.getItem(4));
                    else
                        Main.settings.put(player.getUniqueId().toString() + ".gunCreateModelOf.currentModel", Items.air);
                    player.openInventory(CustomInv.ingredientInv());
                }
            } else if (title.contains("모델 설정")) {
                if (eventInv.equals(playerInv)) event.setCancelled(false);
                else {
                    if (slot != 24) event.setCancelled(true);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            if (Main.settings.get(player.getUniqueId().toString() + ".setModelInventory.BukkitTaskCancel") != null && (Boolean) Main.settings.get(player.getUniqueId().toString() + ".setModelInventory.BukkitTaskCancel"))
                                this.cancel();
                            else {
                                ItemStack itemStack = eventInv.getItem(24);
                                ItemStack toSet;
                                if (itemStack != null) {
                                    toSet = itemStack.clone();
                                    CustomInv.updateThisItemInv(player, toSet);
                                } else toSet = Items.air;

                                eventInv.setItem(20, toSet);
                            }
                        }
                    }.runTaskTimerAsynchronously(Main.getPlugin(Main.class), 0, 2);
                    if (title.contains("총기")) {
                        if (slot == 48) {
                            Inventory gunCreateInv = CustomInv.gunCreateInv();
                            CustomInv.updateGunCreateInv(player, gunCreateInv, eventInv.getItem(20));
                            Main.settings.put(player.getUniqueId().toString() + ".gunCreateModelOf.currentModel", eventInv.getItem(24));
                            player.openInventory(gunCreateInv);
                        }
                        if (slot == 50) {
                            Inventory gunCreateInv = CustomInv.gunCreateInv();
                            CustomInv.updateGunCreateInv(player, gunCreateInv, (ItemStack) Main.settings.get(player.getUniqueId().toString() + ".gunCreateModelOf.currentModel"));
                            player.openInventory(gunCreateInv);
                        }
                    }
                }
            } else if (title.equalsIgnoreCase("총기 탄창 선택")) {
                event.setCancelled(true);
                if (!eventInv.equals(playerInv)) {
                    int currentPage = Integer.parseInt(ChatColor.stripColor(eventInv.getItem(49).getItemMeta().getLore().get(0)).split(" / ")[0]);
                    int totalPage = Integer.parseInt(ChatColor.stripColor(eventInv.getItem(49).getItemMeta().getLore().get(0)).split(" / ")[1]);
                    if (slot == 45 && currentPage > 1)
                        player.openInventory(CustomInv.shotContainerSelectInv(currentPage - 1));
                    else if (slot == 53 && totalPage >= 2)
                        player.openInventory(CustomInv.shotContainerSelectInv(currentPage + 1));
                    else {
                        if (!clickedItem.equals(Items.outUI)) {
                            List<String> containerList = (List<String>) Main.settings.get(player.getUniqueId().toString() + ".gunCreateShotContainerOf.list");
                            if (clickedItem.getItemMeta().hasEnchant(Enchantment.BINDING_CURSE)) {
                                clickedItem.getItemMeta().removeEnchant(Enchantment.BINDING_CURSE);
                                containerList.remove(ChatColor.stripColor(clickedItem.getItemMeta().getDisplayName()));
                            } else {
                                clickedItem.addUnsafeEnchantment(Enchantment.BINDING_CURSE, 1);
                                containerList.add(ChatColor.stripColor(clickedItem.getItemMeta().getDisplayName()));
                            }
                            Main.settings.put(player.getUniqueId().toString() + ".gunCreateShotContainerOf.list", containerList);
                        }
                    }
                }
            } else if (title.equalsIgnoreCase("총기 종류")) {
                event.setCancelled(true);
                if (!eventInv.equals(playerInv)) {
                    if (slot >= 19 && slot < 26 && !clickedItem.equals(Items.outUI)) {
                        for (int i = 0; i < 4; i++)
                            eventInv.getItem(19 + 2 * i).removeEnchantment(Enchantment.BINDING_CURSE);
                        clickedItem.addUnsafeEnchantment(Enchantment.BINDING_CURSE, 1);

                        if (slot == 19 || slot == 21 || slot == 23 || slot == 25) Main.settings.put(player.getUniqueId().toString() + ".gunCreateTypeOf.type", clickedItem.getItemMeta().getDisplayName());
                    }
                    if (slot == 40) player.openInventory(CustomInv.gunCreateInv());
                }
            } else if (title.equalsIgnoreCase("총기 블록 파괴")) {
                event.setCancelled(true);
                if (slot == 15) {
                    if (clickedItem.equals(Items.blockBreakAllow)) {
                        eventInv.setItem(15, Items.blockBreakDeny);
                        Main.settings.put(player.getUniqueId().toString() + ".gunCreateBlockBreakOf.blockBreak", "&c불가");
                    } else if (clickedItem.equals(Items.blockBreakDeny)) {
                        eventInv.setItem(15, Items.blockBreakAllow);
                        Main.settings.put(player.getUniqueId().toString() + ".gunCreateBlockBreakOf.blockBreak", "&2허용");
                    }
                }
            } else if (title.equalsIgnoreCase("총선 색상 설정")) {
                event.setCancelled(true);

                if (slot < 48) {
                    net.minecraft.server.v1_12_R1.ItemStack nmsCopy = CraftItemStack.asNMSCopy(clickedItem);
                    NBTTagCompound compound = new NBTTagCompound();
                    Main.settings.put(player.getUniqueId().toString() + ".gunCreateColorOf.particle", compound.getString("internal"));
                    Main.settings.put(player.getUniqueId().toString() + ".gunCreateColorOf.particleName", clickedItem.getItemMeta().getDisplayName());
                    player.openInventory(CustomInv.gunCreateInv());
                }
            } else if (title.equalsIgnoreCase("총기 피격 효과")) {
                event.setCancelled(true);
                if (slot == 19) player.openInventory(CustomInv.potionInv());
                else if (slot == 21) {
                    Main.settings.put(player.getUniqueId().toString() + ".gunCreateEffectOf.timeSet", true);
                    player.sendMessage(Main.coloredText(Main.prefix + "&a시간을 초 단위로 입력해 주세요."));
                    player.closeInventory();
                } else if (slot == 23) {
                    Main.settings.put(player.getUniqueId().toString() + ".gunCreateEffectOf.ppSet", true);
                    player.sendMessage(Main.coloredText(Main.prefix + "&a확률을 입력해 주세요. (소수점 가능)"));
                    player.closeInventory();
                } else if (slot == 25) {
                    Main.settings.put(player.getUniqueId().toString() + ".gunCreateEffectOf.dmgSet", true);
                    player.sendMessage(Main.coloredText(Main.prefix + "&a데미지를 입력해 주세요. (소수점 가능)"));
                    player.closeInventory();
                }
            }
        }
    }

    @EventHandler
    public void onInvClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        String title = event.getView().getTitle();
        if (title.contains("모델 설정") && (Main.settings.get(player.getUniqueId().toString() + ".setModelInventory.BukkitTaskCancel") == null || !((Boolean) Main.settings.get(player.getUniqueId().toString() + ".setModelInventory.BukkitTaskCancel")))) {
            Inventory thisInv = event.getInventory();
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.openInventory(thisInv);
                }
            }.runTaskLater(Main.getPlugin(Main.class), 1);
        } else if (title.equalsIgnoreCase("총기 탄창 선택") || title.equalsIgnoreCase("총기 블록 파괴")) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.openInventory(CustomInv.gunCreateInv());
                }
            }.runTaskLater(Main.getPlugin(Main.class), 1);
        }
    }
}

class Data {
    String dataType;
    Object dataObj;

    public Data(String dataType, Object dataObj) {
        this.dataType = dataType;
        this.dataObj = dataObj;
    }

    @Override
    public String toString() {
        return Main.coloredText("&f" + this.dataType + " : &e" + this.dataObj);
    }
}