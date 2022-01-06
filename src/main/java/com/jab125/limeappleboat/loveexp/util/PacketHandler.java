package com.jab125.limeappleboat.loveexp.util;

import com.jab125.limeappleboat.loveexp.LoveExp;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.s2c.play.BlockBreakingProgressS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class PacketHandler {
    public static Identifier EXP_PACKET_ID = new Identifier(LoveExp.MODID, "exp_packet");
    public static Identifier AT_PACKET_ID = new Identifier(LoveExp.MODID, "at_packet");
    public static Identifier AT_MODIFIER_PACKET_ID = new Identifier(LoveExp.MODID, "at_modifier_packet");
    public static Identifier DF_PACKET_ID = new Identifier(LoveExp.MODID, "df_packet");
    public static Identifier CRASH_PACKET_ID = new Identifier(LoveExp.MODID, "crash_packet");
    public static Identifier KILL_PACKET_ID = new Identifier(LoveExp.MODID, "kill_packet");

    public static void sendKILLS(PlayerEntity playerToSend) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeInt(Util.getKILLS((ServerPlayerEntity) playerToSend));
        ServerPlayNetworking.send((ServerPlayerEntity) playerToSend, KILL_PACKET_ID, buf);
        //return TypedActionResult.success(Util.getEXP(playerToSend.getUuid()));
    }
    public static void sendEXP(PlayerEntity playerToSend) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeInt(Util.getEXP(playerToSend.getUuid()));
        ServerPlayNetworking.send((ServerPlayerEntity) playerToSend, EXP_PACKET_ID, buf);
        //return TypedActionResult.success(Util.getEXP(playerToSend.getUuid()));
    }
    public static void sendAT(PlayerEntity playerToSend) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeDouble(playerToSend.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE) - getDof(playerToSend));
        ServerPlayNetworking.send((ServerPlayerEntity) playerToSend, AT_PACKET_ID, buf);
    }
    public static void sendATModifiers(PlayerEntity playerToSend) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeDouble(getDof(playerToSend));
        ServerPlayNetworking.send((ServerPlayerEntity) playerToSend, AT_MODIFIER_PACKET_ID, buf);
    }

    public static void sendCrashPacket(PlayerEntity playerToSend, int status_code) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeInt(status_code);
        ServerPlayNetworking.send((ServerPlayerEntity) playerToSend, CRASH_PACKET_ID, buf);
    }



    private static double getDof(PlayerEntity playerEntity) {
        double ATModifiers = 0;
        for (EntityAttributeModifier modifier : playerEntity.getMainHandStack().getAttributeModifiers(EquipmentSlot.MAINHAND).get(EntityAttributes.GENERIC_ATTACK_DAMAGE)) {
            ATModifiers += modifier.getValue();
        }
        ATModifiers += EnchantmentHelper.getAttackDamage(playerEntity.getMainHandStack(), EntityGroup.DEFAULT);
        return ATModifiers;
    }
}
