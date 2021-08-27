package nulld.gunner.events;

import nulld.gunner.Main;
import nulld.gunner.inventories.CustomInv;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SetByChat implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();
        Inventory toOpen = CustomInv.gunCreateInv();
        ItemStack toSetItemStack = (ItemStack) Main.settings.get(player.getUniqueId().toString() + ".gunCreateModelOf.currentModel");

        if (Main.settings.get(player.getUniqueId().toString() + ".gunCreateNameOf.setNameByChat") != null && (Boolean) Main.settings.get(player.getUniqueId().toString() + ".gunCreateNameOf.setNameByChat")) {
            Main.settings.put(player.getUniqueId().toString() + ".gunCreateNameOf.name", message);
            Main.settings.put(player.getUniqueId().toString() + ".gunCreateNameOf.setNameByChat", false);
            event.setCancelled(true);
        }
        if (Main.settings.get(player.getUniqueId().toString() + ".gunCreateDamageOf.setDamageByChat") != null && (Boolean) Main.settings.get(player.getUniqueId().toString() + ".gunCreateDamageOf.setDamageByChat")) {
            String[] messages = message.split(" ");
            event.setCancelled(true);
            if (messages.length != 2) return;
            if (isNumeric(messages[0]) && isNumeric(messages[1])) {
                if (Double.parseDouble(messages[0]) > Double.parseDouble(messages[1]))
                    player.sendMessage(Main.coloredText(Main.prefix + "&e최소데미지 최대데미지&7(ex: 10 20)"));
                else {
                    Main.settings.put(player.getUniqueId().toString() + ".gunCreateDamageOf.minDamage", Double.parseDouble(messages[0]));
                    Main.settings.put(player.getUniqueId().toString() + ".gunCreateDamageOf.maxDamage", Double.parseDouble(messages[1]));
                    Main.settings.put(player.getUniqueId().toString() + ".gunCreateDamageOf.setDamageByChat", false);
                }
            } else player.sendMessage(Main.prefix + "&c정수 또는 실수만 입력해 주세요.");
        }
        if (Main.settings.get(player.getUniqueId().toString() + ".gunCreateScatterOf.setScatterByChat") != null && (Boolean) Main.settings.get(player.getUniqueId().toString() + ".gunCreateScatterOf.setScatterByChat")) {
            event.setCancelled(true);
            if (isNumeric(message)) {
                Main.settings.put(player.getUniqueId().toString() + ".gunCreateScatterOf.setScatterByChat", false);
                Main.settings.put(player.getUniqueId().toString() + ".gunCreateScatterOf.scatter", Double.parseDouble(message));
            }
        }
        if (Main.settings.get(player.getUniqueId().toString() + ".gunCreateKnockBackOf.setKnockBackByChat") != null && (Boolean) Main.settings.get(player.getUniqueId().toString() + ".gunCreateKnockBackOf.setKnockBackByChat")) {
            event.setCancelled(true);
            if (isNumeric(message)) {
                Main.settings.put(player.getUniqueId().toString() + ".gunCreateKnockBackOf.setKnockBackByChat", false);
                Main.settings.put(player.getUniqueId().toString() + ".gunCreateKnockBackOf.knockback", Double.parseDouble(message));
            }
        }
        if (Main.settings.get(player.getUniqueId().toString() + ".gunCreateSpeedOf.setSpeedByChat") != null && (Boolean) Main.settings.get(player.getUniqueId().toString() + ".gunCreateSpeedOf.setSpeedByChat")) {
            event.setCancelled(true);
            if (isNumeric(message)) {
                Main.settings.put(player.getUniqueId().toString() + ".gunCreateSpeedOf.setSpeedByChat", false);
                Main.settings.put(player.getUniqueId().toString() + ".gunCreateSpeedOf.shotSpeed", Double.parseDouble(message));
            }
        }
        if (Main.settings.get(player.getUniqueId().toString() + ".gunCreateReloadSpeedOf.setReloadSpeedByChat") != null && (Boolean) Main.settings.get(player.getUniqueId().toString() + ".gunCreateReloadSpeedOf.setReloadSpeedByChat")) {
            event.setCancelled(true);
            if (isNumeric(message)) {
                Main.settings.put(player.getUniqueId().toString() + ".gunCreateReloadSpeedOf.setReloadSpeedByChat", false);
                Main.settings.put(player.getUniqueId().toString() + ".gunCreateReloadSpeedOf.reloadSpeed", Double.parseDouble(message));
            }
        }
        if (Main.settings.get(player.getUniqueId().toString() + ".gunCreateWeightOf.setWeightByChat") != null && (Boolean) Main.settings.get(player.getUniqueId().toString() + ".gunCreateWeightOf.setWeightByChat")) {
            event.setCancelled(true);
            if (isNumeric(message)) {
                Main.settings.put(player.getUniqueId().toString() + ".gunCreateWeightOf.setWeightByChat", false);
                Main.settings.put(player.getUniqueId().toString() + ".gunCreateWeightOf.weight", Double.parseDouble(message));
            }
        }
        if (Main.settings.get(player.getUniqueId().toString() + ".gunCreateDistanceOf.setDistanceByChat") != null && (Boolean) Main.settings.get(player.getUniqueId().toString() + ".gunCreateDistanceOf.setDistanceByChat")) {
            event.setCancelled(true);
            if (isNumeric(message)) {
                Main.settings.put(player.getUniqueId().toString() + ".gunCreateDistanceOf.setDistanceByChat", false);
                Main.settings.put(player.getUniqueId().toString() + ".gunCreateDistanceOf.distance", Double.parseDouble(message));
            }
        }
        if (Main.settings.get(player.getUniqueId().toString() + ".gunCreateTypeOf.setTypeByChat") != null && (Boolean) Main.settings.get(player.getUniqueId().toString() + ".gunCreateTypeOf.setTypeByChat")) {
            event.setCancelled(true);
            if (isNumeric(message)) {
                Main.settings.put(player.getUniqueId().toString() + ".gunCreateTypeOf.setTypeByChat", false);
                Main.settings.put(player.getUniqueId().toString() + ".gunCreateTypeOf.type", Double.parseDouble(message));
            }
        }
        if (Main.settings.get(player.getUniqueId().toString() + ".gunCreateBlockBreakOf.setBlockBreakByChat") != null && (Boolean) Main.settings.get(player.getUniqueId().toString() + ".gunCreateBlockBreakOf.setBlockBreakByChat")) {
            event.setCancelled(true);
            if (isNumeric(message)) {
                Main.settings.put(player.getUniqueId().toString() + ".gunCreateBlockBreakOf.setBlockBreakByChat", false);
                Main.settings.put(player.getUniqueId().toString() + ".gunCreateBlockBreakOf.blockBreak", Double.parseDouble(message));
            }
        }
        if (Main.settings.get(player.getUniqueId().toString() + ".gunCreateEffectOf.setEffectByChat") != null && (Boolean) Main.settings.get(player.getUniqueId().toString() + ".gunCreateEffectOf.setEffectByChat")) {
            event.setCancelled(true);
            if (isNumeric(message)) {
                Main.settings.put(player.getUniqueId().toString() + ".gunCreateEffectOf.setEffectByChat", false);
                Main.settings.put(player.getUniqueId().toString() + ".gunCreateEffectOf.effect", Double.parseDouble(message));
            }
        }

        toOpen.setItem(4, toSetItemStack);
        player.openInventory(toOpen);
    }

    public static boolean isNumeric(String string) {
        try {
            Double.parseDouble(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
