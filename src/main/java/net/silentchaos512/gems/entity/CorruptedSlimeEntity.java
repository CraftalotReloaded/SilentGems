package net.silentchaos512.gems.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class CorruptedSlimeEntity extends SlimeEntity {
    public CorruptedSlimeEntity(EntityType<? extends CorruptedSlimeEntity> typeIn, World worldIn) {
        super(typeIn, worldIn);
    }

    @Override
    public boolean canSpawn(IWorld worldIn, SpawnReason spawnReasonIn) {
        return worldIn.getDifficulty() != Difficulty.PEACEFUL;
    }

    @Nonnull
    @Override
    protected IParticleData getSquishParticle() {
        return super.getSquishParticle();
    }

    @Override
    protected int getAttackStrength() {
        return super.getAttackStrength();
    }
}
