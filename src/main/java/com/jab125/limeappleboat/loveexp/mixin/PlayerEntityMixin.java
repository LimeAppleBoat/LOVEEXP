package com.jab125.limeappleboat.loveexp.mixin;

import com.jab125.limeappleboat.loveexp.util.PacketHandler;
import com.jab125.limeappleboat.loveexp.util.Util;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralText;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

import static com.jab125.limeappleboat.loveexp.util.Util.*;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

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
        if (!this.playerEntity.world.isClient) {
            Objects.requireNonNull(this.playerEntity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)).setBaseValue(loveToHP(LV));
            Objects.requireNonNull(this.playerEntity.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE)).setBaseValue(loveToAT(LV));
            Objects.requireNonNull(this.playerEntity.getAttributeInstance(EntityAttributes.GENERIC_ARMOR_TOUGHNESS)).setBaseValue(loveToDF(DISLV));
            Util.setLV(LV, this.playerEntity.getUuid());
            Util.setEXP(EXP, this.playerEntity.getUuid());
            PacketHandler.sendEXP(playerEntity);
            PacketHandler.sendAT(playerEntity);
            PacketHandler.sendATModifiers(playerEntity);
        }
        //Util.init();
        //System.out.println(Util.expJSON);
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
        int expOnDeath = 0;
        if (!world.isClient()) {
            if (!(other.getType().equals(EntityType.PLAYER) && !world.getServer().isHardcore())) {
                if (other.getType().equals(EntityType.PLAYER)) {
                    Entity lightNing = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
                    lightNing.setPos(other.getPos().getX(), other.getPos().getY(), other.getPos().getZ());
                    world.spawnEntity(lightNing);
                }
                if (Util.REGISTERED_MOBS_AUTO_LV.containsKey(EntityType.getId(other.getType()).toString())) {
                    if (!(this.EXP > loveToExp(Util.REGISTERED_MOBS_AUTO_LV.get(EntityType.getId(other.getType()).toString()).getLv()))) {
                        expOnDeath = loveToExp(Util.REGISTERED_MOBS_AUTO_LV.get(EntityType.getId(other.getType()).toString()).getLv()) - EXP;
                        this.EXP = loveToExp(Util.REGISTERED_MOBS_AUTO_LV.get(EntityType.getId(other.getType()).toString()).getLv());
                    }
                } else if (Util.REGISTERED_MOBS_AUTO_EXP.containsKey(EntityType.getId(other.getType()).toString())) {
                    if (!(EXP > Util.REGISTERED_MOBS_AUTO_EXP.get(EntityType.getId(other.getType()).toString()).getExp())) {
                        expOnDeath = Util.REGISTERED_MOBS_AUTO_EXP.get(EntityType.getId(other.getType()).toString()).getExp() - EXP;
                        this.EXP = Util.REGISTERED_MOBS_AUTO_EXP.get(EntityType.getId(other.getType()).toString()).getExp();
                    }
                } else if (Util.REGISTERED_MOBS.containsKey(EntityType.getId(other.getType()).toString()))
                    expOnDeath = Util.REGISTERED_MOBS.get(EntityType.getId(other.getType()).toString()).getExp();
                else expOnDeath = other.getXpToDrop(this.playerEntity);
                if (!(Util.REGISTERED_MOBS_AUTO_LV.containsKey(EntityType.getId(other.getType()).toString()) || Util.REGISTERED_MOBS_AUTO_EXP.containsKey(EntityType.getId(other.getType()).toString()))) {
                    this.EXP += expOnDeath;
                }
            } else {
                expOnDeath = 0;
            }
        }
            this.EXP = MathHelper.clamp(this.EXP, 0, 99999);
            this.playerEntity.sendMessage(new LiteralText("YOU WON!"), false);
            this.playerEntity.sendMessage(new LiteralText("You earned " + expOnDeath + " XP and " + 0 + " gold."), false);
//        if (expToLove(this.EXP) > this.LV) {
//            playSound(SoundEvents.BLOCK_GLASS_BREAK, 10, .1F);
//        }

    }
    @Inject(at = @At("HEAD"), method = "wakeUp(ZZ)V")
    private void injectToWakeUp(boolean bl, boolean updateSleepingPlayers, CallbackInfo ci) {
        if (!this.playerEntity.world.isClient) {
            if (this.LV < 6 && this.getAbsorptionAmount() < 10) {
                this.setAbsorptionAmount(10);
            }
            this.playerEntity.setHealth(this.playerEntity.getMaxHealth());
        }
    }

    public int getLV() {
        return LV;
    }

    public int getEXP() {
        return EXP;
    }

    @Shadow public abstract void setAbsorptionAmount(float absorptionAmount);
}
