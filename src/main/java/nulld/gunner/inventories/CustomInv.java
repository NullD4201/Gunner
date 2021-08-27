package nulld.gunner.inventories;

import de.tr7zw.nbtapi.NBTItem;

import net.minecraft.server.v1_12_R1.NBTBase;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import net.minecraft.server.v1_12_R1.NBTTagString;
import nulld.gunner.Main;
import nulld.gunner.utils.AES256;
import nulld.gunner.utils.Items;
import nulld.gunner.utils.ModelType;
import nulld.gunner.utils.ParticleItems;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;

import java.io.File;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
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
        Inventory shotContainerSelect = Bukkit.createInventory(null, 54, "총기 탄창 선택");
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
        Inventory shotColorInv = Bukkit.createInventory(null, 54, "총선 색상 설정");

        List<ItemStack> particleItems = (List<ItemStack>) Main.settings.get("particleGUIItemsList");
        Field[] fields = ParticleItems.class.getFields();
        for (int i = 0; i < particleItems.size(); i++) {
            net.minecraft.server.v1_12_R1.ItemStack nmsCopy = CraftItemStack.asNMSCopy(particleItems.get(i));
            NBTTagCompound compound = new NBTTagCompound();
            compound.set("internal", new NBTTagString(fields[i].getName()));
            nmsCopy.setTag(compound);
        }
        for (int i = 0; i < 54; i++) {
            if (i >= particleItems.size()) shotColorInv.setItem(i, Items.outUI);
            else shotColorInv.setItem(i, particleItems.get(i));
        }

        return shotColorInv;
    }

    public static Inventory gunTypeInv(Player player, ItemStack itemStack){
        Inventory gunTypeInv = Bukkit.createInventory(null, 45, "총기 종류");

        for (int i = 0; i < 45; i++) gunTypeInv.setItem(i, Items.outUI);
        gunTypeInv.setItem(4, itemStack);
        ItemStack re = Items.gunTypeRevolver;
        ItemStack ri = Items.gunTypeRifle;
        ItemStack ma = Items.gunTypeMachineGun;
        ItemStack sn = Items.gunTypeSniper;
        if (Main.settings.get(player.getUniqueId().toString() + ".gunCreateTypeOf.type") != null){
            re.removeEnchantment(Enchantment.BINDING_CURSE);
            ri.removeEnchantment(Enchantment.BINDING_CURSE);
            ma.removeEnchantment(Enchantment.BINDING_CURSE);
            sn.removeEnchantment(Enchantment.BINDING_CURSE);
            if (Main.settings.get(player.getUniqueId().toString() + ".gunCreateTypeOf.type").equals("권총")) re.addUnsafeEnchantment(Enchantment.BINDING_CURSE, 1);
            if (Main.settings.get(player.getUniqueId().toString() + ".gunCreateTypeOf.type").equals("라이플")) ri.addUnsafeEnchantment(Enchantment.BINDING_CURSE, 1);
            if (Main.settings.get(player.getUniqueId().toString() + ".gunCreateTypeOf.type").equals("기관총")) ma.addUnsafeEnchantment(Enchantment.BINDING_CURSE, 1);
            if (Main.settings.get(player.getUniqueId().toString() + ".gunCreateTypeOf.type").equals("스나이퍼")) sn.addUnsafeEnchantment(Enchantment.BINDING_CURSE, 1);
        }
        gunTypeInv.setItem(19, re);
        gunTypeInv.setItem(21, ri);
        gunTypeInv.setItem(23, ma);
        gunTypeInv.setItem(25, sn);
        gunTypeInv.setItem(40, Items.gunTypeComplete);

        return gunTypeInv;
    }

    public static Inventory blockBreakInv(Player player, ItemStack itemStack){
        Inventory blockBreakInv = Bukkit.createInventory(null, 27, "총기 블록 파괴");

        for (int i = 0; i < 27; i++) blockBreakInv.setItem(i, Items.outUI);
        blockBreakInv.setItem(11, Items.gunCreateBlockBreakOf);
        if (Main.settings.get(player.getUniqueId().toString() + ".gunCreateBlockBreakOf.blockBreak").equals("&2허용")) blockBreakInv.setItem(15, Items.blockBreakAllow);
        else if (Main.settings.get(player.getUniqueId().toString() + ".gunCreateBlockBreakOf.blockBreak").equals("&c불가")) blockBreakInv.setItem(15, Items.blockBreakDeny);
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

    public static Inventory potionInv(){
        Inventory potionInv = Bukkit.createInventory(null, 54, "이펙트 설정");

        for (int i = 0; i < 54; i++) potionInv.setItem(i, Items.outUI);
        potionInv.setItem(10, Items.item(new ItemStack(Material.POTION), "효과 없음"));
        potionInv.setItem(11, Items.potion(PotionEffectType.ABSORPTION, "흡수"));
        potionInv.setItem(12, Items.potion(PotionEffectType.BLINDNESS, "실명"));
        potionInv.setItem(13, Items.potion(PotionEffectType.FIRE_RESISTANCE, "화염 저항"));
        potionInv.setItem(14, Items.potion(PotionEffectType.GLOWING, "발광"));
        potionInv.setItem(15, Items.potion(PotionEffectType.FAST_DIGGING, "성급함"));
        potionInv.setItem(16, Items.potion(PotionEffectType.HEALTH_BOOST, "생명력 강화"));
        potionInv.setItem(19, Items.potion(PotionEffectType.HUNGER, "허기"));
        potionInv.setItem(20, Items.potion(PotionEffectType.HARM, "즉시 피해"));
        potionInv.setItem(21, Items.potion(PotionEffectType.HEAL, "즉시 치유"));
        potionInv.setItem(22, Items.potion(PotionEffectType.INVISIBILITY, "투명"));
        potionInv.setItem(23, Items.potion(PotionEffectType.JUMP, "점프 강화"));
        potionInv.setItem(24, Items.potion(PotionEffectType.LEVITATION, "공중 부양"));
        potionInv.setItem(25, Items.potion(PotionEffectType.LUCK, "행운"));
        potionInv.setItem(28, Items.potion(PotionEffectType.SLOW_DIGGING, "피로"));
        potionInv.setItem(29, Items.potion(PotionEffectType.CONFUSION, "멀미"));
        potionInv.setItem(30, Items.potion(PotionEffectType.NIGHT_VISION, "야간 투시"));
        potionInv.setItem(31, Items.potion(PotionEffectType.POISON, "독"));
        potionInv.setItem(32, Items.potion(PotionEffectType.REGENERATION, "재생"));
        potionInv.setItem(33, Items.potion(PotionEffectType.DAMAGE_RESISTANCE, "저항"));
        potionInv.setItem(34, Items.potion(PotionEffectType.SATURATION, "포화"));
        potionInv.setItem(37, Items.potion(PotionEffectType.SLOW, "구속"));
        potionInv.setItem(38, Items.potion(PotionEffectType.SPEED, "신속"));
        potionInv.setItem(39, Items.potion(PotionEffectType.INCREASE_DAMAGE, "힘"));
        potionInv.setItem(40, Items.potion(PotionEffectType.UNLUCK, "불운"));
        potionInv.setItem(41, Items.potion(PotionEffectType.WATER_BREATHING, "수중 호흡"));
        potionInv.setItem(42, Items.potion(PotionEffectType.WEAKNESS, "나약함"));
        potionInv.setItem(43, Items.potion(PotionEffectType.WITHER, "위더"));

        potionInv.setItem(1, Items.item(new ItemStack(Material.ARROW), "&f1 감소"));
        potionInv.setItem(2, Items.item(new ItemStack(Material.PAPER), "&f레벨 : 1"));
        potionInv.setItem(3, Items.item(new ItemStack(Material.ARROW), "&f1 증가"));
        potionInv.setItem(5, Items.sec("D"));
        potionInv.setItem(6, Items.item(new ItemStack(Material.PAPER), "&f00:00"));
        potionInv.setItem(7, Items.sec("A"));

        return potionInv;
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


    public static void updateThisItemInv(Player player, ItemStack itemStack){
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta != null){
            itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
            itemMeta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
            itemMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            itemMeta.addItemFlags(ItemFlag.HIDE_DESTROYS);
            itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            List<String> lore = new ArrayList<>();
            if (Main.settings.get(player.getUniqueId().toString() + ".gunCreateNameOf.name") != null)
                itemMeta.setDisplayName(Main.coloredText("&f" + Main.settings.get(player.getUniqueId().toString() + ".gunCreateNameOf.name")));
            if (Main.settings.get(player.getUniqueId().toString() + ".gunCreateDamageOf.minDamage") != null)
                lore.add(Main.coloredText("&f총기 데미지 : &e" + Main.settings.get(player.getUniqueId().toString() + ".gunCreateDamageOf.minDamage") + " ~ " + Main.settings.get(player.getUniqueId().toString() + ".gunCreateDamageOf.maxDamage")));
            if (Main.settings.get(player.getUniqueId().toString() + ".gunCreateScatterOf.scatter") != null)
                lore.add(Main.coloredText("&f총기 탄 퍼짐 : &e" + Main.settings.get(player.getUniqueId().toString() + ".gunCreateScatterOf.scatter")));
            if (Main.settings.get(player.getUniqueId().toString() + ".gunCreateKnockBackOf.knockBack") != null)
                lore.add(Main.coloredText("&f총기 반동 : &e" + Main.settings.get(player.getUniqueId().toString() + ".gunCreateKnockBackOf.knockback")));
            if (Main.settings.get(player.getUniqueId().toString() + ".gunCreateSpeedOf.shotSpeed") != null)
                lore.add(Main.coloredText("&f총기 탄속 : &e" + Main.settings.get(player.getUniqueId().toString() + ".gunCreateSpeedOf.shotSpeed")));
            if (Main.settings.get(player.getUniqueId().toString() + ".gunCreateReloadSpeedOf.reloadSpeed") != null)
                lore.add(Main.coloredText("&f총기 장전 속도 : &e" + Main.settings.get(player.getUniqueId().toString() + ".gunCreateReloadSpeedOf.reloadSpeed")));
            if (Main.settings.get(player.getUniqueId().toString() + ".gunCreateWeightOf.weight") != null)
                lore.add(Main.coloredText("&f총기 무게 : &e" + Main.settings.get(player.getUniqueId().toString() + ".gunCreateWeightOf.weight")));
            if (Main.settings.get(player.getUniqueId().toString() + ".gunCreateDistanceOf.distance") != null)
                lore.add(Main.coloredText("&f총기 거리 : &e" + Main.settings.get(player.getUniqueId().toString() + ".gunCreateDistanceOf.distance")));
            if (Main.settings.get(player.getUniqueId().toString() + ".gunCreateTypeOf.type") != null)
                lore.add(Main.coloredText("&f총기 종류 : &e" + Main.settings.get(player.getUniqueId().toString() + ".gunCreateTypeOf.type")));
            if (Main.settings.get(player.getUniqueId().toString() + ".gunCreateBlockBreakOf.blockBreak") != null)
                lore.add(Main.coloredText("&f총기 블록 파괴 : &e" + Main.settings.get(player.getUniqueId().toString() + ".gunCreateBlockBreakOf.blockBreak")));
            if (Main.settings.get(player.getUniqueId().toString() + ".gunCreateEffectOf.effect") != null)
                lore.add(Main.coloredText("&f총기 피격 효과 : &e" + Main.settings.get(player.getUniqueId().toString() + ".gunCreateEffectOf.effect")));

            itemMeta.setLore(lore);

            itemStack.setItemMeta(itemMeta);
        }
    }
}
