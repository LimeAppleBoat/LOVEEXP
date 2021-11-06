package com.jab125.limeappleboat.loveexp.util;

import com.jab125.limeappleboat.loveexp.api.LoveExpApi;
import com.jab125.limeappleboat.loveexp.api.LoveExpApiRegistry;
import net.minecraft.entity.EntityType;

public class LoveExpIntegration implements LoveExpApi {
    /**
     * This is used for this mod just so you don't get {@code ClassNotFoundException} when loading up your mod without this mod
     * <p>
     * Use {@link com.jab125.limeappleboat.loveexp.api.LoveExpApiRegistry#registerMob(EntityType, int)} or {@link com.jab125.limeappleboat.loveexp.api.LoveExpApiRegistry#registerMob(EntityType, int, int)} to register exp and love value for mobs
     */
    @Override
    public void register() {
        // Passive Mobs
        LoveExpApiRegistry.registerMob(EntityType.AXOLOTL, 10, 0);
        LoveExpApiRegistry.registerMob(EntityType.BAT, 3, 0);
        LoveExpApiRegistry.registerMob(EntityType.CAT, 7, 0);
        LoveExpApiRegistry.registerMob(EntityType.CHICKEN, 5, 0);
        LoveExpApiRegistry.registerMob(EntityType.COD, 5, 0);
        LoveExpApiRegistry.registerMob(EntityType.COW, 5, 0);
        LoveExpApiRegistry.registerMob(EntityType.DONKEY, 10, 0);
        LoveExpApiRegistry.registerMob(EntityType.FOX, 7, 0);
        LoveExpApiRegistry.registerMob(EntityType.GLOW_SQUID, 12, 0);
        LoveExpApiRegistry.registerMob(EntityType.HORSE, 13, 0);
        LoveExpApiRegistry.registerMob(EntityType.MOOSHROOM, 6, 0);
        LoveExpApiRegistry.registerMob(EntityType.MULE, 10, 0);
        LoveExpApiRegistry.registerMob(EntityType.OCELOT, 7, 0);
        LoveExpApiRegistry.registerMob(EntityType.PARROT, 3, 0);
        LoveExpApiRegistry.registerMob(EntityType.PIG, 5, 0);
        LoveExpApiRegistry.registerMob(EntityType.PUFFERFISH, 8, 0);
        LoveExpApiRegistry.registerMob(EntityType.RABBIT, 7, 0);
        LoveExpApiRegistry.registerMob(EntityType.SALMON, 5, 0);
        LoveExpApiRegistry.registerMob(EntityType.SHEEP, 5, 0);
        LoveExpApiRegistry.registerMob(EntityType.SKELETON_HORSE, 27, 0);
        LoveExpApiRegistry.registerMob(EntityType.SNOW_GOLEM, 16, 0);
        LoveExpApiRegistry.registerMob(EntityType.SQUID, 4, 0);
        LoveExpApiRegistry.registerMob(EntityType.STRIDER, 27, 0);
        LoveExpApiRegistry.registerMob(EntityType.TROPICAL_FISH, 5, 0);
        LoveExpApiRegistry.registerMob(EntityType.TURTLE, 5, 0);
        LoveExpApiRegistry.registerMob(EntityType.VILLAGER, 60, 3);
        LoveExpApiRegistry.registerMob(EntityType.WANDERING_TRADER, 60, 3);

        // Neutral Mobs
        LoveExpApiRegistry.registerMob(EntityType.BEE, 7, 0);
        LoveExpApiRegistry.registerMob(EntityType.CAVE_SPIDER, 12, 0);
        LoveExpApiRegistry.registerMob(EntityType.DOLPHIN, 15, 0);
        LoveExpApiRegistry.registerMob(EntityType.ENDERMAN, 70, 0);
        LoveExpApiRegistry.registerMob(EntityType.GOAT, 25, 0);
        LoveExpApiRegistry.registerMob(EntityType.IRON_GOLEM, 80, 0);
        LoveExpApiRegistry.registerMob(EntityType.LLAMA, 11, 0);
        LoveExpApiRegistry.registerMob(EntityType.PANDA, 10, 0);
        LoveExpApiRegistry.registerMob(EntityType.PIGLIN, 27, 0);
        LoveExpApiRegistry.registerMob(EntityType.POLAR_BEAR, 30, 0);
        LoveExpApiRegistry.registerMob(EntityType.SPIDER, 20, 0);
        LoveExpApiRegistry.registerMob(EntityType.TRADER_LLAMA, 15, 0);
        LoveExpApiRegistry.registerMob(EntityType.WOLF, 15, 0);
        LoveExpApiRegistry.registerMob(EntityType.ZOMBIFIED_PIGLIN, 35, 0);

        // Hostile Mobs
        LoveExpApiRegistry.registerMob(EntityType.BLAZE, 45, 0);
        LoveExpApiRegistry.registerMob(EntityType.CREEPER, 45, 0);
        LoveExpApiRegistry.registerMob(EntityType.DROWNED, 35, 0);
        LoveExpApiRegistry.registerMob(EntityType.ELDER_GUARDIAN, 75, 0);
        LoveExpApiRegistry.registerMob(EntityType.ENDERMITE, 17, 0);
        LoveExpApiRegistry.registerMob(EntityType.EVOKER, 75, 0);
        LoveExpApiRegistry.registerMob(EntityType.GHAST, 55, 0);
        LoveExpApiRegistry.registerMob(EntityType.GUARDIAN, 70, 0);
        LoveExpApiRegistry.registerMob(EntityType.HOGLIN, 65, 0);
        LoveExpApiRegistry.registerMob(EntityType.HUSK, 35, 0);
        LoveExpApiRegistry.registerMob(EntityType.MAGMA_CUBE, 45, 0);
        LoveExpApiRegistry.registerMob(EntityType.PHANTOM, 45, 0);
        LoveExpApiRegistry.registerMob(EntityType.PIGLIN_BRUTE, 95, 0);
        LoveExpApiRegistry.registerMob(EntityType.PILLAGER, 75, 0);
        LoveExpApiRegistry.registerMob(EntityType.RAVAGER, 125, 0);
        LoveExpApiRegistry.registerMob(EntityType.SHULKER, 125, 0);
        LoveExpApiRegistry.registerMob(EntityType.SILVERFISH, 45, 0);
        LoveExpApiRegistry.registerMob(EntityType.SKELETON, 45, 0);
        LoveExpApiRegistry.registerMob(EntityType.SLIME, 35, 0);
        LoveExpApiRegistry.registerMob(EntityType.STRAY, 45, 0);
        LoveExpApiRegistry.registerMob(EntityType.VEX, 115, 0);
        LoveExpApiRegistry.registerMob(EntityType.VINDICATOR, 75, 0);
        LoveExpApiRegistry.registerMob(EntityType.WITCH, 75, 0);
        LoveExpApiRegistry.registerMob(EntityType.WITHER_SKELETON, 85, 0);
        LoveExpApiRegistry.registerMob(EntityType.ZOGLIN, 95, 0);
        LoveExpApiRegistry.registerMob(EntityType.ZOMBIE, 35, 0);
        LoveExpApiRegistry.registerMob(EntityType.ZOMBIE_VILLAGER, 35, 3);

        // Boss Mobs
        //LoveExpApiRegistry.registerMob(EntityType.WITHER, 665, 0);
        LoveExpApiRegistry.registerMobAutoLV(EntityType.WITHER, 15, 99);
        LoveExpApiRegistry.registerMob(EntityType.ENDER_DRAGON, 1031, 0);

        // The Unused
        LoveExpApiRegistry.registerMob(EntityType.GIANT, 0, 0);
        LoveExpApiRegistry.registerMob(EntityType.ZOMBIE_HORSE, 0, 0);
        LoveExpApiRegistry.registerMob(EntityType.ILLUSIONER, 0, 0);

        // Misc
        LoveExpApiRegistry.registerMob(EntityType.PLAYER, 80, 0);
    }
}
