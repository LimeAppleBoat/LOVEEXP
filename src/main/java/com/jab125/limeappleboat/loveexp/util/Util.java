package com.jab125.limeappleboat.loveexp.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import net.minecraft.entity.EntityType;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Util {
    static HashMap<UUID, Integer> LV = new HashMap<>();
    static HashMap<UUID, Integer> EXP = new HashMap<>();
    public static HashMap<String, LoveExpFormat> REGISTERED_MOBS = new HashMap<>();
    public static HashMap<String, LoveExpForceLVFormat> REGISTERED_MOBS_AUTO_LV = new HashMap<>();
    public static HashMap<String, LoveExpForceExpFormat> REGISTERED_MOBS_AUTO_EXP = new HashMap<>();

    public static int loveToHP(int love) {
        return switch (love) {
            case 0, 1 -> 20;
            case 2 -> 24;
            case 3 -> 28;
            case 4 -> 32;
            case 5 -> 36;
            case 6 -> 40;
            case 7 -> 44;
            case 8 -> 48;
            case 9 -> 52;
            case 10 -> 56;
            case 11 -> 60;
            case 12 -> 64;
            case 13 -> 68;
            case 14 -> 72;
            case 15 -> 76;
            case 16 -> 80;
            case 17 -> 84;
            case 18 -> 88;
            case 19 -> 92;
            case 20 -> 99;
            default -> love > 20 ? 99 : ((love - 1) * 4) + 20;
        };
    }
    public static int toNext(int exp) {
        int i = expToLove(exp) + 1;
        return loveToExp(i) == -1 ? -1 : loveToExp(i) - exp;
    }
    public static int expToLove(int exp) {
        if (exp < 10) return 1;
        if (exp < 30) return 2;
        if (exp < 70) return 3;
        if (exp < 120) return 4;
        if (exp < 200) return 5;
        if (exp < 300) return 6;
        if (exp < 500) return 7;
        if (exp < 800) return 8;
        if (exp < 1200) return 9;
        if (exp < 1700) return 10;
        if (exp < 2500) return 11;
        if (exp < 3500) return 12;
        if (exp < 5000) return 13;
        if (exp < 7000) return 14;
        if (exp < 10000) return 15;
        if (exp < 15000) return 16;
        if (exp < 25000) return 17;
        if (exp < 50000) return 18;
        if (exp < 99999) return 19;
        return 20;
    }
    public static int loveToExp(int love) {
        return switch (love) {
            case 1 -> 0;
            case 2 -> 10;
            case 3 -> 30;
            case 4 -> 70;
            case 5 -> 120;
            case 6 -> 200;
            case 7 -> 300;
            case 8 -> 500;
            case 9 -> 800;
            case 10 -> 1200;
            case 11 -> 1700;
            case 12 -> 2500;
            case 13 -> 3500;
            case 14 -> 5000;
            case 15 -> 7000;
            case 16 -> 10000;
            case 17 -> 15000;
            case 18 -> 25000;
            case 19 -> 50000;
            case 20 -> 99999;
            default -> -1;
        };
    }

    public static void setEXP(int EXP, UUID uuid) {
        if (Util.EXP.containsKey(uuid)) {
            Util.EXP.replace(uuid, EXP);
        } else {
            Util.EXP.put(uuid, EXP);
        }
    }

    public static void setLV(int LV, UUID uuid) {
        if (Util.LV.containsKey(uuid)) {
            Util.LV.replace(uuid, LV);
        } else {
            Util.LV.put(uuid, LV);
        }
    }

    public static int getEXP(UUID uuid) {
        if (Util.EXP.containsKey(uuid)) return Util.EXP.get(uuid);
        return -5;
    }

    public static int getLV(UUID uuid) {
        if (Util.LV.containsKey(uuid)) return Util.LV.get(uuid);
        return -5;
    }

    public static int loveToDF(int love) {
        return switch (love) {
            case 1, 2, 3, 4 -> 0;
            case 5, 6, 7, 8 -> 1;
            case 9, 10, 11, 12 -> 2;
            case 13, 14, 15, 16 -> 3;
            case 17, 18, 19, 20 -> 4;
            default -> 0;
        };
    }

    public static int loveToAT(int love) {
        return switch (love) {
            case 1 -> 0;
            case 2 -> 2;
            case 3 -> 4;
            case 4 -> 6;
            case 5 -> 8;
            case 6 -> 10;
            case 7 -> 12;
            case 8 -> 14;
            case 9 -> 16;
            case 10 -> 18;
            case 11 -> 20;
            case 12 -> 22;
            case 13 -> 24;
            case 14 -> 26;
            case 15 -> 28;
            case 16 -> 30;
            case 17 -> 32;
            case 18 -> 34;
            case 19 -> 36;
            case 20 -> 38;
            default -> 0;
        };
    }

    /**
     * @deprecated use a hashmap
     */
    @Deprecated
    public static class UUIDInt {
        int anInt;
        UUID uuid;
        public UUIDInt(int i, UUID uuid) {
            this.anInt = i;
            this.uuid = uuid;
        }

        public int getInt() {
            return anInt;
        }

        public UUID getUuid() {
            return uuid;
        }

        public void setInt(int anInt) {
            this.anInt = anInt;
        }

        public void setUuid(UUID uuid) {
            this.uuid = uuid;
        }
    }
}
