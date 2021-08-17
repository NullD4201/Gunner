package nulld.gunner.inventories;

import nulld.gunner.Main;
import nulld.gunner.utils.Items;
import nulld.gunner.utils.ModelType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class CustomInv {

    public static Inventory gunCreateInv(){
        Inventory craftInventory = Bukkit.createInventory(null, 54, Main.coloredText("총기 제작"));

        for (int i = 0; i < 54; i++) craftInventory.setItem(i, Items.outUI);
        craftInventory.setItem(4, Items.air);
        craftInventory.setItem(19, Items.gunCreateNameOf);
        craftInventory.setItem(20, Items.gunCreateItemOf);
        craftInventory.setItem(21, Items.gunCreateDamageOf);
        craftInventory.setItem(22, Items.gunCreateScatterOf);
        craftInventory.setItem(23, Items.gunCreateKnockbackOf);
        craftInventory.setItem(24, Items.gunCreateShotSpeedOf);
        craftInventory.setItem(25, Items.gunCreateShotContainerOf);
        craftInventory.setItem(28, Items.gunCreateShotColorOf);
        craftInventory.setItem(29, Items.gunCreateReloadSpeedOf);
        craftInventory.setItem(30, Items.gunCreateWeightOf);
        craftInventory.setItem(31, Items.gunCreateDistanceOf);
        craftInventory.setItem(32, Items.gunCreateKindOf);
        craftInventory.setItem(33, Items.gunCreateBlockBreakOf);
        craftInventory.setItem(34, Items.gunCreateEffectOf);
        craftInventory.setItem(37, Items.gunCreateIngredientOf);
        craftInventory.setItem(47, Items.itemCreateConfirm);
        craftInventory.setItem(49, Items.itemCreateSave);
        craftInventory.setItem(51, Items.itemCreateDelete);

        return craftInventory;
    }
    public static void updateGunCreateInv(Player player, Inventory toSetInventory, ItemStack newModel){
        Main.settings.put(player.getUniqueId().toString() + ".setModelInventory.BukkitTaskCancel", true);
        toSetInventory.setItem(4, newModel);
        if (Main.settings.get(player.getUniqueId().toString() + ".gunCreateNameOf.setName") != null){
            ItemStack nameItem = toSetInventory.getItem(19);
            ItemMeta nameItemMeta = nameItem.getItemMeta();
            List<String> list = Arrays.asList(Main.coloredText("&e" + Main.settings.get(player.getUniqueId().toString() + ".gunCreateNameOf.setName")));
            nameItemMeta.setLore(list);
            nameItem.setItemMeta(nameItemMeta);
        }
    }

    public static Inventory setModelInv(ModelType modelType){
        String type = "";
        List<Integer> settingInt = Arrays.asList(10, 11, 12, 19, 21, 28, 29, 30);
        if (modelType.equals(ModelType.GUNS)) type = "총기";
        else if (modelType.equals(ModelType.CONTAINER)) type = "탄창";
        else if (modelType.equals(ModelType.PARTS)) type = "파츠";
        else if (modelType.equals(ModelType.SHOTS)) type = "총알";
        else if (modelType.equals(ModelType.PAPER)) type = "강화서";

        Inventory setModelInventory = Bukkit.createInventory(null, 54, Main.coloredText(type + " 모델 설정"));

        for (int i = 0; i < 54; i++) setModelInventory.setItem(i, Items.outUI);
        for (int i = 0; i < settingInt.size(); i++) {
            setModelInventory.setItem(settingInt.get(i), Items.setModelResult);
            setModelInventory.setItem(settingInt.get(i)+4, Items.setModelCurrent);
        }
        setModelInventory.setItem(20, Items.air);
        setModelInventory.setItem(24, Items.air);
        setModelInventory.setItem(48, Items.setModelConfirm);
        setModelInventory.setItem(50, Items.setModelCancel);

        return setModelInventory;
    }
}
