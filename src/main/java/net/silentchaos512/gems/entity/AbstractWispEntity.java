package net.silentchaos512.gems.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.PacketDistributor;
import net.silentchaos512.gems.entity.projectile.AbstractWispShotEntity;
import net.silentchaos512.gems.lib.WispTypes;
import net.silentchaos512.gems.network.Network;
import net.silentchaos512.gems.network.SpawnEntityPacket;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.function.Supplier;

public abstract class AbstractWispEntity extends MonsterEntity {
    private float heightOffset = 0.5F;
    private int heightOffsetUpdateTime;

    public AbstractWispEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public abstract WispTypes getWispType();

    protected abstract AbstractWispShotEntity getProjectile(AbstractWispEntity wisp, double accelX, double accelY, double accelZ);

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(4, new WispAttackGoal(this));
        this.goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D, 0.0F));
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double)0.25F);
        this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(48.0D);
    }

    @Override
    protected void registerData() {
        super.registerData();
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return super.getAmbientSound();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return super.getHurtSound(damageSourceIn);
    }

    @Override
    protected SoundEvent getDeathSound() {
        return super.getDeathSound();
    }

    @Override
    public int getBrightnessForRender() {
        return 15728880;
    }

    @Override
    public float getBrightness() {
        return 1f;
    }

    @Override
    public void livingTick() {
        if (!this.onGround && this.getMotion().y < 0.0D) {
            this.setMotion(this.getMotion().mul(1.0D, 0.6D, 1.0D));
        }

//        if (this.world.isRemote) {
//            if (this.rand.nextInt(24) == 0 && !this.isSilent()) {
//                this.world.playSound(this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D, SoundEvents.ENTITY_BLAZE_BURN, this.getSoundCategory(), 1.0F + this.rand.nextFloat(), this.rand.nextFloat() * 0.7F + 0.3F, false);
//            }
//
//            for(int i = 0; i < 2; ++i) {
//                this.world.addParticle(ParticleTypes.LARGE_SMOKE, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.getWidth(), this.posY + this.rand.nextDouble() * (double)this.getHeight(), this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.getWidth(), 0.0D, 0.0D, 0.0D);
//            }
//        }

        super.livingTick();
    }

    @Override
    protected void updateAITasks() {
        --this.heightOffsetUpdateTime;
        if (this.heightOffsetUpdateTime <= 0) {
            this.heightOffsetUpdateTime = 100;
            this.heightOffset = 0.5F + (float)this.rand.nextGaussian() * 3.0F;
        }

        LivingEntity target = this.getAttackTarget();
        if (target != null && target.posY + (double)target.getEyeHeight() > this.posY + (double)this.getEyeHeight() + (double)this.heightOffset && this.canAttack(target)) {
            Vec3d vec3d = this.getMotion();
            this.setMotion(this.getMotion().add(0.0D, ((double)0.3F - vec3d.y) * (double)0.3F, 0.0D));
            this.isAirBorne = true;
        }

        super.updateAITasks();
    }

    @SuppressWarnings("NoopMethodInAbstractClass")
    @Override
    public void fall(float distance, float damageMultiplier) {
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    static class WispAttackGoal extends Goal {
        private final AbstractWispEntity wisp;
        private int attackStep;
        private int attackTime;
        private int field_223527_d;

        public WispAttackGoal(AbstractWispEntity wispIn) {
            this.wisp = wispIn;
            this.setMutexFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        @Override
        public boolean shouldExecute() {
            LivingEntity target = this.wisp.getAttackTarget();
            return target != null && target.isAlive() && this.wisp.canAttack(target);
        }

        @Override
        public void startExecuting() {
            this.attackStep = 0;
        }

        @Override
        public void resetTask() {
//            this.wisp.setOnFire(false);
            this.field_223527_d = 0;
        }

        @Override
        public void tick() {
            --this.attackTime;
            LivingEntity target = this.wisp.getAttackTarget();
            if (target != null) {
                boolean canSeeTarget = this.wisp.getEntitySenses().canSee(target);
                if (canSeeTarget) {
                    this.field_223527_d = 0;
                } else {
                    ++this.field_223527_d;
                }

                double distanceSq = this.wisp.getDistanceSq(target);
                if (distanceSq < 4.0D) {
                    if (!canSeeTarget) {
                        return;
                    }

                    if (this.attackTime <= 0) {
                        this.attackTime = 20;
                        this.wisp.attackEntityAsMob(target);
                    }

                    this.wisp.getMoveHelper().setMoveTo(target.posX, target.posY, target.posZ, 1.0D);
                } else if (distanceSq < this.getFollowDistance() * this.getFollowDistance() && canSeeTarget) {
                    double dx = target.posX - this.wisp.posX;
                    double dy = target.getBoundingBox().minY + (double)(target.getHeight() / 2.0F) - (this.wisp.posY + (double)(this.wisp.getHeight() / 2.0F));
                    double dz = target.posZ - this.wisp.posZ;
                    if (this.attackTime <= 0) {
                        ++this.attackStep;
                        if (this.attackStep == 1) {
                            this.attackTime = 60;
//                            this.wisp.setOnFire(true);
                        } else if (this.attackStep <= 4) {
                            this.attackTime = 6;
                        } else {
                            this.attackTime = 100;
                            this.attackStep = 0;
//                            this.wisp.setOnFire(false);
                        }

                        if (this.attackStep > 1) {
                            float f = MathHelper.sqrt(MathHelper.sqrt(distanceSq)) * 0.5F;
                            this.wisp.world.playEvent(null, 1018, new BlockPos(this.wisp), 0);

                            for(int i = 0; i < 1; ++i) {
                                AbstractWispShotEntity shot = this.wisp.getProjectile(this.wisp, dx + this.wisp.getRNG().nextGaussian() * (double)f, dy, dz + this.wisp.getRNG().nextGaussian() * (double)f);
                                shot.setColor(this.wisp.getWispType().getColor().getColor());
                                shot.posY = this.wisp.posY + this.wisp.getHeight() / 2 + 0.5;
                                this.wisp.world.addEntity(shot);

                                // Need to manually spawn this on the client... because vanilla
                                Supplier<PacketDistributor.TargetPoint> p = PacketDistributor.TargetPoint.p(this.wisp.posX, this.wisp.posY, this.wisp.posZ, 4096, this.wisp.dimension);
                                Network.channel.send(PacketDistributor.NEAR.with(p), new SpawnEntityPacket(shot));
                            }
                        }
                    }

                    this.wisp.getLookController().setLookPositionWithEntity(target, 10.0F, 10.0F);
                } else if (this.field_223527_d < 5) {
                    this.wisp.getMoveHelper().setMoveTo(target.posX, target.posY, target.posZ, 1.0D);
                }

                super.tick();
            }
        }

        private double getFollowDistance() {
            return this.wisp.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).getValue();
        }
    }

    @Override
    protected ResourceLocation getLootTable() {
        return super.getLootTable();
    }
}
