package com.jab125.limeappleboat.loveexp.api;

import net.minecraft.entity.EntityType;

@FunctionalInterface
public interface LoveExpApi {
    /**
     * This is used for this mod just so you don't get {@code ClassNotFoundException} when loading up your mod without this mod
     * <p>
     * Use {@link com.jab125.limeappleboat.loveexp.api.LoveExpApiRegistry#registerMob(EntityType, int)} or {@link com.jab125.limeappleboat.loveexp.api.LoveExpApiRegistry#registerMob(EntityType, int, int)} to register exp and love value for mobs
     */
    void register();
}
