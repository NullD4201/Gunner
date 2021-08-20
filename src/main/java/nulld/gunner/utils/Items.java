package nulld.gunner.utils;

import nulld.gunner.Main;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.List;

public class Items {
    public static ItemStack air = new ItemStack(Material.AIR);
    public static ItemStack outUI = item(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7), " ");
    public static ItemStack defaultGun = item(new ItemStack(Material.GOLD_HOE, 1, (short) 7), " ");
    public static ItemStack defaultShotContainer = item(new ItemStack(Material.GOLD_RECORD, 1, (short) 7), " ");
    public static ItemStack defaultShots = item(new ItemStack(Material.SEEDS, 1, (short) 7), " ");

    public static ItemStack listPrev = item(new ItemStack(Material.ARROW, 1), "&f이전 페이지");
    public static ItemStack listNext = item(new ItemStack(Material.ARROW, 1), "&f다음 페이지");
    public static ItemStack listSetPage(int page, int total){
        ItemStack itemStack = item(new ItemStack(Material.COMPASS, 1), "&f페이지 이동");
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lore = Collections.singletonList(Main.coloredText("&6" + page + " / " + total));
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack itemCreateConfirm = item(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 4), "&e아이템 생성"); // 노란색
    public static ItemStack itemCreateSave = item(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5), "&2아이템 저장"); // 연두색
    public static ItemStack itemCreateDelete = item(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14), "&c아이템 삭제"); // 빨간색

    public static ItemStack setModelResult = item(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 4), "&f모델 적용 후"); // 노란색
    public static ItemStack setModelCurrent = item(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 10), "&f모델 적용 전"); // 보라색
    public static ItemStack setModelConfirm = item(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5), "&2모델 저장");
    public static ItemStack setModelCancel = item(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14), "&c모델 수정 취소");

    public static ItemStack gunCreateNameOf = item(new ItemStack(Material.NAME_TAG, 1), "&a총기 이름");
    public static ItemStack gunCreateItemOf = item(new ItemStack(Material.IRON_NUGGET, 1), "&a총기 아이템");
    public static ItemStack gunCreateDamageOf = item(new ItemStack(Material.DIAMOND_SWORD, 1), "&a총기 데미지");
    public static ItemStack gunCreateScatterOf = item(new ItemStack(Material.SEEDS, 1), "&a총기 탄 퍼짐");
    public static ItemStack gunCreateKnockbackOf = item(new ItemStack(Material.LEASH, 1), "&a총기 반동");
    public static ItemStack gunCreateShotSpeedOf = item(new ItemStack(Material.FEATHER, 1), "&a총기 탄속");
    public static ItemStack gunCreateShotContainerOf = item(new ItemStack(Material.GOLD_RECORD, 1), "&a총기 탄창");
    public static ItemStack gunCreateShotColorOf = item(new ItemStack(Material.INK_SACK, 1, (short) 15), "&a총기 총선 색상");
    public static ItemStack gunCreateReloadSpeedOf = item(new ItemStack(Material.STRING, 1), "&a총기 장전 속도");
    public static ItemStack gunCreateWeightOf = item(new ItemStack(Material.CAULDRON_ITEM, 1), "&a총기 무게");
    public static ItemStack gunCreateDistanceOf = item(new ItemStack(Material.BOW, 1), "&a총기 거리");
    public static ItemStack gunCreateKindOf = item(new ItemStack(Material.LEVER, 1), "&a총기 종류");
    public static ItemStack gunCreateBlockBreakOf = item(new ItemStack(Material.GRASS, 1), "&a총기 블록 파괴");
    public static ItemStack gunCreateEffectOf = item(new ItemStack(Material.BREWING_STAND_ITEM, 1), "&a총기 상대 피격 시 걸리는 효과");
    public static ItemStack gunCreateIngredientOf = item(new ItemStack(Material.SUGAR_CANE, 1), "&a총기 강화 재료");

    public static ItemStack particleYes = item(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5), "&2저장"); // 연두색
    public static ItemStack particleNo = item(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14), "&c취소"); // 빨간색

    public static ItemStack gunTypeRevolver = item(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 0), "&f권총"); // 흰색
    public static ItemStack gunTypeRifle = item(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 12), "&f라이플"); // 갈색
    public static ItemStack gunTypeMachineGun = item(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15), "&f기관총"); // 검정색
    public static ItemStack gunTypeSniper = item(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 11), "&f스나이퍼"); // 파란색
    public static ItemStack gunTypeComplete = item(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5), "&2설정 완료"); // 연두색

    public static ItemStack blockBreakAllow = item(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5), "&2허용 &7(클릭하여 불가로 변경)"); // 연두색
    public static ItemStack blockBreakDeny = item(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14), "&c불가 &7(클릭하여 허용으로 변경)"); // 빨간색

    public static ItemStack effectType = item(new ItemStack(Material.DRAGONS_BREATH, 1), "&a<[ &2이펙트 &f설정 &a]>");
    public static ItemStack effectTime = item(new ItemStack(Material.WATCH, 1), "&a<[ &6시간 &f설정 &a]>");
    public static ItemStack effectPercent = item(new ItemStack(Material.END_CRYSTAL, 1), "&a<[ &e확률 &f설정 &a]>");
    public static ItemStack effectDamage = item(new ItemStack(Material.DIAMOND_SWORD, 1), "&a<[ &c데미지 &f설정 &a]>");
    public static ItemStack effectConfirm = item(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5), "&2피격효과 저장");
    public static ItemStack effectCancel = item(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14), "&c피격효과 삭제");

    public static ItemStack ingredientOneToTen = item(new ItemStack(Material.SIGN, 1), "&a<[ &e1 ~ 10강 &f까지 필요한 &7재료 &a]>");
    public static ItemStack ingredientOverEleven = item(new ItemStack(Material.SIGN, 1), "&a<[ &c11강 &f이상 필요한 &7재료 &a]>");
    public static ItemStack ingredientConfirm = item(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5), "&2재료목록 저장");
    public static ItemStack ingredientCancel = item(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14), "&c재료목록 수정 취소");

    public static ItemStack enhanceGunIcon = item(new ItemStack(Material.GOLD_HOE, 1), "&a<[ &c강화&f할 &6무기를 &7아래 &f올려주세요 &a]>");
    public static ItemStack enhanceGunIngredientNeed = item(new ItemStack(Material.SIGN, 1), "&a<[ &c강화&f하는데 &6필요한 &f재료 &a]>");
    public static ItemStack enhanceGunIngredientHave = item(new ItemStack(Material.SIGN, 1), "&a<[ &c강화 &6재료를 &7아래 &f올려주세요 &a]>");
    public static ItemStack enhanceGunPaper = item(new ItemStack(Material.PAPER, 1), "&a<[ &6강화서&f를 &7아래 &f올려주세요 &a]>");
    public static ItemStack enhanceGunStart = item(new ItemStack(Material.ANVIL, 1), "&2강화 시작");

    public static ItemStack shotContainerCreateNameOf = item(new ItemStack(Material.NAME_TAG, 1), "&5탄창 이름");
    public static ItemStack shotContainerCreateItemOf = item(new ItemStack(Material.IRON_NUGGET, 1), "&5탄창 아이템");
    public static ItemStack shotContainerCreateShotOf = item(new ItemStack(Material.SEEDS, 1), "&5탄창 장전 가능 총알");
    public static ItemStack shotContainerCreateReloadSpeedOf = item(new ItemStack(Material.FEATHER, 1), "&5탄창 장전 속도");
    public static ItemStack shotContainerCreateShotCountOf = item(new ItemStack(Material.LEASH, 1), "&5탄창 총알 수");
    public static ItemStack shotContainerCreateKindOf = item(new ItemStack(Material.PUMPKIN_SEEDS, 1), "&5탄창 종류");

    public static ItemStack shotsCreateNameOf = item(new ItemStack(Material.NAME_TAG, 1), "&d총알 이름");
    public static ItemStack shotsCreateItemOf = item(new ItemStack(Material.IRON_NUGGET, 1), "&d총알 아이템");

    public static ItemStack partsCreateNameOf = item(new ItemStack(Material.NAME_TAG, 1), "&b파츠 이름");
    public static ItemStack partsCreateItemOf = item(new ItemStack(Material.IRON_NUGGET, 1), "&b파츠 아이템");
    public static ItemStack partsCreateMinDamageOf = item(new ItemStack(Material.IRON_SWORD, 1), "&b파츠 추가 최소 데미지");
    public static ItemStack partsCreateMaxDamageOf = item(new ItemStack(Material.DIAMOND_SWORD, 1), "&b파츠 추가 최대 데미지");
    public static ItemStack partsCreateScatterOf = item(new ItemStack(Material.SEEDS, 1), "&b파츠 탄 퍼짐");
    public static ItemStack partsCreateKnockBackOf = item(new ItemStack(Material.LEASH, 1), "&b파츠 반동제어");
    public static ItemStack partsCreateShotSpeedOf = item(new ItemStack(Material.FEATHER, 1), "&b파츠 탄속 증가");
    public static ItemStack partsCreateReloadSpeedOf = item(new ItemStack(Material.WEB, 1), "&b파츠 장전속도");
    public static ItemStack partsCreateDistanceOf = item(new ItemStack(Material.BOW, 1), "&b파츠 거리 증가");
    public static ItemStack partsCreateRemoveSoundOf = item(new ItemStack(Material.GOLD_RECORD, 1), "&b파츠 소음제어");
    public static ItemStack partsCreateIngredientOf = item(new ItemStack(Material.SUGAR_CANE, 1), "&b파츠 강화재료");
    public static ItemStack partsCreateKindOf = item(new ItemStack(Material.PUMPKIN_SEEDS, 1), "&b파츠 종류");

    public static ItemStack partsRemoveSoundIcon = item(new ItemStack(Material.NOTE_BLOCK, 1), "&b파츠 소음제어");

    public static ItemStack partsRemoveSoundAllow = item(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5), "&2허용"); // 연두색
    public static ItemStack partsRemoveSoundDeny = item(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14), "&c불가"); // 빨간색

    public static ItemStack enhanceGunPaperCreateNameOf = item(new ItemStack(Material.NAME_TAG, 1), "&6강화서 이름");
    public static ItemStack enhanceGunPaperCreateItemOf = item(new ItemStack(Material.IRON_NUGGET, 1), "&6강화서 아이템");
    public static ItemStack enhanceGunPaperCreateMinDamageOf = item(new ItemStack(Material.IRON_SWORD, 1), "&6최소 데미지 강화");
    public static ItemStack enhanceGunPaperCreateMaxDamageOf = item(new ItemStack(Material.DIAMOND_SWORD, 1), "&6최대 데미지 강화");
    public static ItemStack enhanceGunPaperCreateScatterOf = item(new ItemStack(Material.SEEDS, 1), "&6탄 퍼짐 강화");
    public static ItemStack enhanceGunPaperCreateKnockBackOf = item(new ItemStack(Material.LEASH, 1), "&6반동 제어 강화");
    public static ItemStack enhanceGunPaperCreateShotSpeedOf = item(new ItemStack(Material.FEATHER, 1), "&6탄속 증가 강화");
    public static ItemStack enhanceGunPaperCreateReloadSpeedOf = item(new ItemStack(Material.WEB, 1), "&6장전속도 강화");
    public static ItemStack enhanceGunPaperCreateDistanceOf = item(new ItemStack(Material.BOW, 1), "&6거리 증가 강화");
    public static ItemStack enhanceGunPaperCreateEffectOf = item(new ItemStack(Material.REDSTONE, 1), "&6타격 효과 강화");
    public static ItemStack enhanceGunPaperCreateKindOf = item(new ItemStack(Material.PUMPKIN_SEEDS, 1), "&6강화서 종류");

    public static ItemStack item(ItemStack itemStack, String name){
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(Main.coloredText(name));
        itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        itemMeta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
        itemMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemMeta.addItemFlags(ItemFlag.HIDE_DESTROYS);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }
}
