package com.jab125.limeappleboat.loveexp.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import java.util.HashMap;
import java.util.UUID;

@Environment(EnvType.CLIENT)
public class ClientExpValues {
    public static HashMap<UUID, Integer> CLIENT_EXP_MAP;
    public static int CLIENT_EXP;
    public static double CLIENT_AT;
    public static double CLIENT_AT_MODIFIER;
    public static double CLIENT_DF;
    public static double CLIENT_KILLS;
}
