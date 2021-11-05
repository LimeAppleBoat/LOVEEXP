package com.jab125.limeappleboat.loveexp.api;

import com.jab125.limeappleboat.loveexp.util.Util;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;

public class LoveExpApiRegistry {
    /**
     * @param entity The Entity
     * @param exp Amount of EXP to drop on death
     */
    public static void registerMob(EntityType entity, int exp) {
        Util.REGISTERED_MOBS.put(entity, new Util.DualInt(exp));
    }

    /**
     * @param entity The Entity
     * @param exp Amount of EXP to drop on death
     * @param gold not used but is recommended to as functionality will be added in a future update
     */
    public static void registerMob(EntityType entity, int exp, int gold) {
        Util.REGISTERED_MOBS.put(entity, new Util.DualInt(exp, gold));
    }

    /**
     * @param entity The Entity
     * @param lv The Level to set on death
     */
    public static void registerMobAutoLV(EntityType entity, int lv) {
        Util.REGISTERED_MOBS_AUTO_LV.put(entity, new Util.DualInt(lv, 0));
    }

    /**
     * @param entity The Entity
     * @param lv The Level to set on death
     * @param gold not used but is recommended to as functionality will be added in a future update
     */
    public static void registerMobAutoLV(EntityType entity, int lv, int gold) {
        Util.REGISTERED_MOBS_AUTO_LV.put(entity, new Util.DualInt(lv, gold));
    }

    /**
     * @param entity The Entity
     * @param exp The EXP to set on death
     * @param gold not used but is recommended to as functionality will be added in a future update
     */
    public static void registerMobAutoEXP(EntityType entity, int exp, int gold) {
        Util.REGISTERED_MOBS_AUTO_EXP.put(entity, new Util.DualInt(exp, gold));
    }

    /**
     * @param entity The Entity
     * @param exp The Level to set on death
     */
    public static void registerMobAutoEXP(EntityType entity, int exp) {
        Util.REGISTERED_MOBS_AUTO_EXP.put(entity, new Util.DualInt(exp, 0));
    }
}
