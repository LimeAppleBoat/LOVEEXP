package com.jab125.limeappleboat.loveexp.mixin;

import com.jab125.limeappleboat.loveexp.util.Util;
import net.minecraft.command.DataCommandObject;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.command.DataCommand;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

import static com.jab125.limeappleboat.loveexp.util.Util.expToLove;
import static com.jab125.limeappleboat.loveexp.util.Util.loveToHP;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {
    @Shadow public abstract void playSound(SoundEvent sound, float volume, float pitch);

    @Shadow public abstract float getAbsorptionAmount();


    PlayerEntity playerEntity;
    private int LV;
    private int EXP;
    private int DISLV = 1;
    private int DISEXP = 0;



    @Inject(at = @At("HEAD"), method = "tick")
    private void injectToTick(CallbackInfo ci) {
        this.playerEntity = (PlayerEntity) (Object) this;
        this.LV = expToLove(this.EXP);
        if (Util.getLV(playerEntity.getUuid()) != -5) DISLV = Util.getLV(playerEntity.getUuid());
        if (Util.getLV(playerEntity.getUuid()) != -5) DISEXP = Util.getEXP(playerEntity.getUuid());
        Objects.requireNonNull(this.playerEntity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)).setBaseValue(loveToHP(DISLV));
        if (!this.playerEntity.world.isClient) {
            Util.setLV(LV, this.playerEntity.getUuid());
            Util.setEXP(EXP, this.playerEntity.getUuid());
        }
    }
    @Inject(at = @At("HEAD"), method = "readCustomDataFromNbt")
    private void injectToReadNbt(NbtCompound nbt, CallbackInfo ci) {
        if (nbt.contains("LV", 3)) {
            this.LV = nbt.getInt("LV");
        }
        if (nbt.contains("EXP", 3)) {
            this.EXP = nbt.getInt("EXP");
        }
    }
    @Inject(at = @At("HEAD"), method = "writeCustomDataToNbt")
    private void injectToWriteNbt(NbtCompound nbt, CallbackInfo ci) {
        nbt.putInt("LV", this.LV);
        nbt.putInt("EXP", this.EXP);
    }

    @Inject(at = @At("HEAD"), method = "onKilledOther")
    private void injectToOnKilled(ServerWorld world, LivingEntity other, CallbackInfo ci) {
        this.EXP += other.getXpToDrop(this.playerEntity);
        if (expToLove(this.EXP) > this.LV) {
            playSound(SoundEvents.BLOCK_GLASS_BREAK, 10, .1F);
        }
    }
    @Inject(at = @At("HEAD"), method = "wakeUp(ZZ)V")
    private void injectToWakeUp(boolean bl, boolean updateSleepingPlayers, CallbackInfo ci) {
        if (this.LV < 6 && this.getAbsorptionAmount() < 10) {
            this.setAbsorptionAmount(this.getAbsorptionAmount() + 10);
        }
        this.playerEntity.setHealth(this.playerEntity.getMaxHealth());
    }

    public int getLV() {
        return LV;
    }

    public int getEXP() {
        return EXP;
    }

    @Shadow public abstract void setAbsorptionAmount(float absorptionAmount);
}
