package com.jab125.limeappleboat.loveexp.client;

import com.jab125.limeappleboat.loveexp.util.PacketHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.s2c.play.BlockBreakingProgressS2CPacket;
import net.minecraft.util.math.BlockPos;

public class LoveExpClient implements ClientModInitializer {
    /**
     * Runs the mod initializer on the client environment.
     */
    @Override
    public void onInitializeClient() {
        ClientPlayNetworking.registerGlobalReceiver(PacketHandler.EXP_PACKET_ID, ((client, handler, buf, responseSender) -> {
            ClientExpValues.CLIENT_EXP = buf.readInt();
            client.execute(() -> {
                //System.out.println(ClientExpValues.CLIENT_EXP);
            });
        }));
        ClientPlayNetworking.registerGlobalReceiver(PacketHandler.AT_PACKET_ID, ((client, handler, buf, responseSender) -> {
            ClientExpValues.CLIENT_AT = buf.readDouble();
            client.execute(() -> {
                //System.out.println(ClientExpValues.CLIENT_AT);
            });
        }));
        ClientPlayNetworking.registerGlobalReceiver(PacketHandler.AT_MODIFIER_PACKET_ID, ((client, handler, buf, responseSender) -> {
            ClientExpValues.CLIENT_AT_MODIFIER = buf.readDouble();
            client.execute(() -> {
                //System.out.println(ClientExpValues.CLIENT_AT_MODIFIER);
            });
        }));
        ClientPlayNetworking.registerGlobalReceiver(PacketHandler.DF_PACKET_ID, ((client, handler, buf, responseSender) -> {
            ClientExpValues.CLIENT_DF = buf.readDouble();
            client.execute(() -> {
               // System.out.println(ClientExpValues.CLIENT_DF);
            });
        }));
        ClientPlayNetworking.registerGlobalReceiver(PacketHandler.CRASH_PACKET_ID, ((client, handler, buf, responseSender) -> {
            int crashStatus = buf.readInt();
            client.execute(() -> {
                System.exit(crashStatus);
            });
        }));
    }
}
