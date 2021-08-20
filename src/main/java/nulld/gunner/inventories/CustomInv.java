package nulld.gunner.inventories;

import nulld.gunner.Main;
import nulld.gunner.utils.AES256;
import nulld.gunner.utils.Items;
import nulld.gunner.utils.ModelType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Collections;
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
            List<String> list = Collections.singletonList(Main.coloredText("&e" + Main.settings.get(player.getUniqueId().toString() + ".gunCreateNameOf.setName")));
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
        for (Integer integer : settingInt) {
            setModelInventory.setItem(integer, Items.setModelResult);
            setModelInventory.setItem(integer + 4, Items.setModelCurrent);
        }
        setModelInventory.setItem(20, Items.air);
        setModelInventory.setItem(24, Items.air);
        setModelInventory.setItem(48, Items.setModelConfirm);
        setModelInventory.setItem(50, Items.setModelCancel);

        return setModelInventory;
    }

    public static Inventory shotContainerSelectInv(int page){
        Inventory shotContainerSelect = Bukkit.createInventory(null, 54, "탄창 선택");
        File[] containers = new File(Main.getPlugin(Main.class).getDataFolder() + "/Containers").listFiles();

        for (int i = 0; i < 54; i++) shotContainerSelect.setItem(i, Items.outUI);
        shotContainerSelect.setItem(45, Items.listPrev);
        shotContainerSelect.setItem(53, Items.listNext);
        shotContainerSelect.setItem(49, Items.listSetPage(page, containers.length / 36 + 1));

        if (containers.length != 0){
            for (int i = 0; i < 45; i++){
                File container = containers[(page-1)*36+i];
                try {
                    String name = AES256.decrypt(container.getName().replaceAll(".cinfo", ""));
                    File target = new File(Main.getPlugin(Main.class).getDataFolder() + "/Temp/Containers", name + ".yml");
                    Files.copy(container.getAbsoluteFile().toPath(), target.getAbsoluteFile().toPath(), StandardCopyOption.REPLACE_EXISTING);
                    FileConfiguration yml = YamlConfiguration.loadConfiguration(target);
                    shotContainerSelect.setItem(i, yml.getItemStack("item"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return shotContainerSelect;
    }

    public static Inventory shotColorInv(){
        Inventory shotColorInv = Bukkit.createInventory(null, 36, "총선 색상 설정");

        for (int i = 0; i < 36; i++) shotColorInv.setItem(i, Items.outUI);

        shotColorInv.setItem(13, Items.item(new ItemStack(Material.BARRIER, 1), "&f배리어"));
        shotColorInv.setItem(30, Items.particleYes);
        shotColorInv.setItem(32, Items.particleNo);

        return shotColorInv;
    }

    public static Inventory gunTypeInv(ItemStack itemStack){
        Inventory gunTypeInv = Bukkit.createInventory(null, 45, "총기 종류");

        for (int i = 0; i < 45; i++) gunTypeInv.setItem(i, Items.outUI);
        gunTypeInv.setItem(4, itemStack);
        gunTypeInv.setItem(19, Items.gunTypeRevolver);
        gunTypeInv.setItem(21, Items.gunTypeRifle);
        gunTypeInv.setItem(23, Items.gunTypeMachineGun);
        gunTypeInv.setItem(25, Items.gunTypeSniper);
        gunTypeInv.setItem(40, Items.gunTypeComplete);

        return gunTypeInv;
    }

    public static Inventory blockBreakInv(ItemStack itemStack){
        Inventory blockBreakInv = Bukkit.createInventory(null, 27, "총기 블록 파괴");

        for (int i = 0; i < 27; i++) blockBreakInv.setItem(i, Items.outUI);
        blockBreakInv.setItem(11, Items.gunCreateBlockBreakOf);
        blockBreakInv.setItem(15, Items.blockBreakDeny);
        blockBreakInv.setItem(4, itemStack);

        return blockBreakInv;
    }

    public static Inventory effectInv(ItemStack itemStack){
        Inventory effectInv = Bukkit.createInventory(null, 54, "총기 피격 효과");

        for (int i = 0; i < 54; i++) effectInv.setItem(i, Items.outUI);
        effectInv.setItem(4, itemStack);
        effectInv.setItem(19, Items.effectType);
        effectInv.setItem(21, Items.effectTime);
        effectInv.setItem(23, Items.effectPercent);
        effectInv.setItem(25, Items.effectDamage);
        effectInv.setItem(39, Items.effectConfirm);
        effectInv.setItem(41, Items.effectCancel);

        return effectInv;
    }

    public static Inventory ingredientInv(){
        Inventory ingredientInv = Bukkit.createInventory(null, 54, "총기 강화 재료");
        List<Integer> empty = Arrays.asList(10, 11, 12, 14, 15, 16, 19, 20, 21, 23, 24, 25, 28, 29, 30, 32, 33, 34);

        for (int i = 0; i < 54; i++) ingredientInv.setItem(i, Items.outUI);
        for (Integer i : empty) ingredientInv.setItem(i, Items.air);
        ingredientInv.setItem(38, Items.ingredientOneToTen);
        ingredientInv.setItem(42, Items.ingredientOverEleven);
        ingredientInv.setItem(48, Items.ingredientConfirm);
        ingredientInv.setItem(50, Items.ingredientCancel);

        return ingredientInv;
    }
}
