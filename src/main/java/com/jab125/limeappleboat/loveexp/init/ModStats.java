package com.jab125.limeappleboat.loveexp.init;

import com.jab125.limeappleboat.loveexp.LoveExp;
import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModStats {
    public static final Identifier EXP = new Identifier(LoveExp.MODID,"exp");
    public void registerStats() {
        Registry.register(Registry.CUSTOM_STAT, "exp", EXP);
        Stats.CUSTOM.getOrCreateStat(EXP, StatFormatter.DEFAULT);
    }
}
