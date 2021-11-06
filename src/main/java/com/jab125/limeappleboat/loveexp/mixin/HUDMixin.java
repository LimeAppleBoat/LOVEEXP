package com.jab125.limeappleboat.loveexp.mixin;

import com.jab125.limeappleboat.loveexp.util.Util;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.screen.DownloadingTerrainScreen;
import net.minecraft.client.gui.screen.Overlay;
import net.minecraft.client.option.HotbarStorage;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.StringVisitable;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ChatUtil;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
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
        if (Util.getLV(playerEntity.getUuid()) != -5) LV = Util.getLV(playerEntity.getUuid());
        if (Util.getLV(playerEntity.getUuid()) != -5) EXP = Util.getEXP(playerEntity.getUuid());
        this.NEXT = Util.toNext(EXP) != -1 ? Integer.toString(Util.toNext(EXP)) : "N/A";

        this.client.getProfiler().push("stats");
        Object text2 = new LiteralText("LV: ").append(LV + "");
        Object text3 = new LiteralText("EXP: ").append(EXP + "");
        Object text4 = new LiteralText("NEXT: ").append(NEXT);

        int i = 5;
        this.getTextRenderer().drawWithShadow(matrices, (Text)text2, i, 5.0F, 16777215);
        this.getTextRenderer().drawWithShadow(matrices, (Text)text3, i, 5.0F + this.getTextRenderer().fontHeight, 16777215);
        this.getTextRenderer().drawWithShadow(matrices, (Text)text4, i, 5.0F + this.getTextRenderer().fontHeight * 2, 16777215);
        this.client.getProfiler().pop();
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
}
