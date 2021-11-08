package com.jab125.limeappleboat.loveexp.mixin;

import com.google.common.collect.Multimap;
import com.jab125.limeappleboat.loveexp.client.ClientExpValues;
import com.jab125.limeappleboat.loveexp.client.LoveExpClient;
import com.jab125.limeappleboat.loveexp.util.Util;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class HUDMixin {
    private int LV = 1;
    private int EXP = 0;
    private String NEXT = "N/A";
    @Final
    @Shadow
    private MinecraftClient client;

    @Shadow
    private int scaledWidth;

    @Shadow
    private int scaledHeight;

    @Final
    @Shadow private static Text DEMO_EXPIRED_MESSAGE;

    @Shadow
    public abstract TextRenderer getTextRenderer();


    @Shadow protected abstract PlayerEntity getCameraPlayer();

    @Inject(at = @At("RETURN"), method = "renderStatusBars")
    private void injectToStatusBars(MatrixStack matrices, CallbackInfo ci) {
        renderStats(matrices, this.getCameraPlayer());
        renderHealthBar2(matrices);
    }

    public void renderStats(MatrixStack matrices, PlayerEntity playerEntity) {
        LV = Util.expToLove(ClientExpValues.CLIENT_EXP);
        EXP = ClientExpValues.CLIENT_EXP;
        this.NEXT = Util.toNext(EXP) != -1 ? Integer.toString(Util.toNext(EXP)) : "N/A";

        this.client.getProfiler().push("stats");

//        for (ItemStack armorPiece : playerEntity.getArmorItems()) {
//            armorPiece.
//        }

        double ATModifiers = 0;
        for (EntityAttributeModifier modifier : playerEntity.getMainHandStack().getAttributeModifiers(EquipmentSlot.MAINHAND).get(EntityAttributes.GENERIC_ATTACK_DAMAGE)) {
            ATModifiers += modifier.getValue();
        }

        double DFModifiers = 0;

        for (EntityAttributeModifier modifier : playerEntity.getMainHandStack().getAttributeModifiers(EquipmentSlot.HEAD).get(EntityAttributes.GENERIC_ARMOR_TOUGHNESS)) {
            DFModifiers += modifier.getValue();
        }
        for (EntityAttributeModifier modifier : playerEntity.getMainHandStack().getAttributeModifiers(EquipmentSlot.CHEST).get(EntityAttributes.GENERIC_ARMOR_TOUGHNESS)) {
            DFModifiers += modifier.getValue();
        }
        for (EntityAttributeModifier modifier : playerEntity.getMainHandStack().getAttributeModifiers(EquipmentSlot.LEGS).get(EntityAttributes.GENERIC_ARMOR_TOUGHNESS)) {
            DFModifiers += modifier.getValue();
        }
        for (EntityAttributeModifier modifier : playerEntity.getMainHandStack().getAttributeModifiers(EquipmentSlot.FEET).get(EntityAttributes.GENERIC_ARMOR_TOUGHNESS)) {
            DFModifiers += modifier.getValue();
        }
            //DFModifiers += modifier.getValue();

        Object text2 = new LiteralText("LV: ").append(LV + "");
        Object text3 = new LiteralText("AT: ").append(String.valueOf((int) ClientExpValues.CLIENT_AT)).append(" (" + ((int)ClientExpValues.CLIENT_AT_MODIFIER)+ ")");
        Object text4 = new LiteralText("DF: ").append(new LiteralText("?").formatted(Formatting.RED));//.append(String.valueOf((int) playerEntity.getAttributeValue(EntityAttributes.GENERIC_ARMOR_TOUGHNESS))).append(" ("+(int) DFModifiers +" ARMR) (? ARMR TN)");
        Object text5 = new LiteralText("EXP: ").append(EXP + "");
        Object text6 = new LiteralText("NEXT: ").append(NEXT);

        int i = 5;
        this.getTextRenderer().drawWithShadow(matrices, (Text)text2, i, 5.0F, 16777215);
        this.getTextRenderer().drawWithShadow(matrices, (Text)text3, i, 5.0F + this.getTextRenderer().fontHeight, 16777215);
        this.getTextRenderer().drawWithShadow(matrices, (Text)text4, i, 5.0F + this.getTextRenderer().fontHeight * 2, 16777215);
        this.getTextRenderer().drawWithShadow(matrices, (Text)text5, i, 5.0F + this.getTextRenderer().fontHeight * 3, 16777215);
        this.getTextRenderer().drawWithShadow(matrices, (Text)text6, i, 5.0F + this.getTextRenderer().fontHeight * 4, 16777215);
        this.client.getProfiler().pop();
    }

    private Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot equipmentSlot) {
        return ItemStack.EMPTY.getAttributeModifiers(equipmentSlot);
    }


    private void renderHealthBar2(MatrixStack matrices) {
        String string = (MathHelper.ceil(this.getCameraPlayer().getHealth() + this.getCameraPlayer().getAbsorptionAmount())) + " / " + MathHelper.ceil(this.getCameraPlayer().getMaxHealth());
        int m;
        int n;
        m = (this.scaledWidth - this.getTextRenderer().getWidth(string)) / 2;
        n = this.scaledHeight - 31 - 4 - 30;
        this.getTextRenderer().draw(matrices, string, (float)(m + 1), (float)n, 0);
        this.getTextRenderer().draw(matrices, string, (float)(m - 1), (float)n, 0);
        this.getTextRenderer().draw(matrices, string, (float)m, (float)(n + 1), 0);
        this.getTextRenderer().draw(matrices, string, (float)m, (float)(n - 1), 0);
        this.getTextRenderer().draw(matrices, string, (float)m, (float)n, 8453920);
    }
    private static <T> int requireNonNullOr0(T obj) {
        if (obj == null)
            return 0;
        return (int) obj;
    }
}
