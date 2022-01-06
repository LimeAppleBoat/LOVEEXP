package com.jab125.limeappleboat.loveexp.mixin;

import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(InGameHud.class)
public interface InGameHudAccessor {
    @Accessor
    static Text getSAVING_LEVEL_TEXT() {
        throw new UnsupportedOperationException();
    }

    @Mutable
    @Accessor
    static void setSAVING_LEVEL_TEXT(Text SAVING_LEVEL_TEXT) {
        throw new UnsupportedOperationException();
    }
}
