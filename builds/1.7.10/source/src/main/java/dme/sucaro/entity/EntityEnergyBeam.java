// 
// Decompiled by Procyon v0.5.36
// 


package dme.sucaro.entity;

import net.minecraft.item.ItemStack;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import java.util.List;
import net.minecraft.util.AxisAlignedBB;
import java.util.Random;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.block.material.Material;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.MathHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;

public class EntityEnergyBeam extends Entity
{
    private int field_145791_d;
    private int field_145792_e;
    private int field_145789_f;
    private Block field_145790_g;
    private int inData;
    private boolean inGround;
    public int canBePickedUp;
    public int arrowShake;
    public Entity shootingEntity;
    private int ticksInGround;
    private int ticksInAir;
    private double damage;
    private int knockbackStrength;
    private static final String __OBFID = "CL_00001715";
    private double ExplodeSize;
    private boolean Explodes;
    private boolean Gravity;
    
    public EntityEnergyBeam(final World p_i1753_1_) {
        super(p_i1753_1_);
        this.field_145791_d = -1;
        this.field_145792_e = -1;
        this.field_145789_f = -1;
        this.damage = 2.0;
        this.ExplodeSize = 1.0;
        this.Explodes = true;
        this.Gravity = true;
        this.renderDistanceWeight = 10; //this.field_70155_l = 10.0;
        this.setSize(0.5f, 0.5f); //this.func_70105_a(0.5f, 0.5f);
    }
    
    public EntityEnergyBeam(final World w, final double p_i1754_2_, final double p_i1754_4_, final double p_i1754_6_) {
        super(w);
        this.field_145791_d = -1;
        this.field_145792_e = -1;
        this.field_145789_f = -1;
        this.damage = 2.0;
        this.ExplodeSize = 1.0;
        this.Explodes = true;
        this.Gravity = true;
        this.field_70155_l = 10.0;
        this.func_70105_a(0.5f, 0.5f);
        this.func_70107_b(p_i1754_2_, p_i1754_4_, p_i1754_6_);
        this.field_70129_M = 0.0f;
    }
    
    public EntityEnergyBeam(final World w, final EntityLivingBase source, final EntityLivingBase target, final float p_i1755_4_, final float p_i1755_5_) {
        super(w);
        this.field_145791_d = -1;
        this.field_145792_e = -1;
        this.field_145789_f = -1;
        this.damage = 2.0;
        this.ExplodeSize = 1.0;
        this.Explodes = true;
        this.Gravity = true;
        this.field_70155_l = 10.0;
        this.shootingEntity = (Entity)source;
        if (source instanceof EntityPlayer) {
            this.canBePickedUp = 1;
        }
        this.field_70163_u = source.field_70163_u + source.func_70047_e() - 0.10000000149011612;
        final double d0 = target.field_70165_t - source.field_70165_t;
        final double d2 = target.field_70121_D.field_72338_b + target.field_70131_O / 3.0f - this.field_70163_u;
        final double d3 = target.field_70161_v - source.field_70161_v;
        final double d4 = MathHelper.func_76133_a(d0 * d0 + d3 * d3);
        if (d4 >= 1.0E-7) {
            final float f2 = (float)(Math.atan2(d3, d0) * 180.0 / 3.141592653589793) - 90.0f;
            final float f3 = (float)(-(Math.atan2(d2, d4) * 180.0 / 3.141592653589793));
            final double d5 = d0 / d4;
            final double d6 = d3 / d4;
            this.func_70012_b(source.field_70165_t + d5, this.field_70163_u, source.field_70161_v + d6, f2, f3);
            this.field_70129_M = 0.0f;
            final float f4 = (float)d4 * 0.2f;
            this.setThrowableHeading(d0, d2 + f4, d3, p_i1755_4_, p_i1755_5_);
        }
    }
    
    public EntityEnergyBeam(final World w, final EntityLivingBase elb, final float p_i1756_3_) {
        super(w);
        this.field_145791_d = -1;
        this.field_145792_e = -1;
        this.field_145789_f = -1;
        this.damage = 2.0;
        this.ExplodeSize = 1.0;
        this.Explodes = true;
        this.Gravity = true;
        this.field_70155_l = 10.0;
        this.shootingEntity = (Entity)elb;
        if (elb instanceof EntityPlayer) {
            this.canBePickedUp = 1;
        }
        this.func_70105_a(0.5f, 0.5f);
        this.func_70012_b(elb.field_70165_t, elb.field_70163_u + elb.func_70047_e(), elb.field_70161_v, elb.field_70177_z, elb.field_70125_A);
        this.field_70165_t -= MathHelper.func_76134_b(this.field_70177_z / 180.0f * 3.1415927f) * 0.16f;
        this.field_70163_u -= 0.10000000149011612;
        this.field_70161_v -= MathHelper.func_76126_a(this.field_70177_z / 180.0f * 3.1415927f) * 0.16f;
        this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
        this.field_70129_M = 0.0f;
        this.field_70159_w = -MathHelper.func_76126_a(this.field_70177_z / 180.0f * 3.1415927f) * MathHelper.func_76134_b(this.field_70125_A / 180.0f * 3.1415927f);
        this.field_70179_y = MathHelper.func_76134_b(this.field_70177_z / 180.0f * 3.1415927f) * MathHelper.func_76134_b(this.field_70125_A / 180.0f * 3.1415927f);
        this.field_70181_x = -MathHelper.func_76126_a(this.field_70125_A / 180.0f * 3.1415927f);
        this.setThrowableHeading(this.field_70159_w, this.field_70181_x, this.field_70179_y, p_i1756_3_ * 1.5f, 1.0f);
    }
    
    protected void func_70088_a() {
        this.field_70180_af.func_75682_a(16, (Object)0);
    }
    
    public void setThrowableHeading(double p_70186_1_, double p_70186_3_, double p_70186_5_, final float p_70186_7_, final float p_70186_8_) {
        final float f2 = MathHelper.func_76133_a(p_70186_1_ * p_70186_1_ + p_70186_3_ * p_70186_3_ + p_70186_5_ * p_70186_5_);
        p_70186_1_ /= f2;
        p_70186_3_ /= f2;
        p_70186_5_ /= f2;
        p_70186_1_ += this.field_70146_Z.nextGaussian() * (this.field_70146_Z.nextBoolean() ? -1 : 1) * 0.007499999832361937 * p_70186_8_;
        p_70186_3_ += this.field_70146_Z.nextGaussian() * (this.field_70146_Z.nextBoolean() ? -1 : 1) * 0.007499999832361937 * p_70186_8_;
        p_70186_5_ += this.field_70146_Z.nextGaussian() * (this.field_70146_Z.nextBoolean() ? -1 : 1) * 0.007499999832361937 * p_70186_8_;
        p_70186_1_ *= p_70186_7_;
        p_70186_3_ *= p_70186_7_;
        p_70186_5_ *= p_70186_7_;
        this.field_70159_w = p_70186_1_;
        this.field_70181_x = p_70186_3_;
        this.field_70179_y = p_70186_5_;
        final float f3 = MathHelper.func_76133_a(p_70186_1_ * p_70186_1_ + p_70186_5_ * p_70186_5_);
        final float n = (float)(Math.atan2(p_70186_1_, p_70186_5_) * 180.0 / 3.141592653589793);
        this.field_70177_z = n;
        this.field_70126_B = n;
        final float n2 = (float)(Math.atan2(p_70186_3_, f3) * 180.0 / 3.141592653589793);
        this.field_70125_A = n2;
        this.field_70127_C = n2;
        this.ticksInGround = 0;
    }
    
    @SideOnly(Side.CLIENT)
    public void func_70056_a(final double p_70056_1_, final double p_70056_3_, final double p_70056_5_, final float p_70056_7_, final float p_70056_8_, final int p_70056_9_) {
        this.func_70107_b(p_70056_1_, p_70056_3_, p_70056_5_);
        this.func_70101_b(p_70056_7_, p_70056_8_);
    }
    
    @SideOnly(Side.CLIENT)
    public void func_70016_h(final double p_70016_1_, final double p_70016_3_, final double p_70016_5_) {
        this.field_70159_w = p_70016_1_;
        this.field_70181_x = p_70016_3_;
        this.field_70179_y = p_70016_5_;
        if (this.field_70127_C == 0.0f && this.field_70126_B == 0.0f) {
            final float f = MathHelper.func_76133_a(p_70016_1_ * p_70016_1_ + p_70016_5_ * p_70016_5_);
            final float n = (float)(Math.atan2(p_70016_1_, p_70016_5_) * 180.0 / 3.141592653589793);
            this.field_70177_z = n;
            this.field_70126_B = n;
            final float n2 = (float)(Math.atan2(p_70016_3_, f) * 180.0 / 3.141592653589793);
            this.field_70125_A = n2;
            this.field_70127_C = n2;
            this.field_70127_C = this.field_70125_A;
            this.field_70126_B = this.field_70177_z;
            this.func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70177_z, this.field_70125_A);
            this.ticksInGround = 0;
        }
    }
    
    public void func_70071_h_() {
        if (!this.Gravity) {
            this.func_70024_g(0.0, 0.075, 0.0);
        }
        this.func_82142_c(true);
        this.func_70030_z();
        if (this.field_70127_C == 0.0f && this.field_70126_B == 0.0f) {
            final float f = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
            final float n = (float)(Math.atan2(this.field_70159_w, this.field_70179_y) * 180.0 / 3.141592653589793);
            this.field_70177_z = n;
            this.field_70126_B = n;
            final float n2 = (float)(Math.atan2(this.field_70181_x, f) * 180.0 / 3.141592653589793);
            this.field_70125_A = n2;
            this.field_70127_C = n2;
        }
        final Block block = this.field_70170_p.func_147439_a(this.field_145791_d, this.field_145792_e, this.field_145789_f);
        if (block.func_149688_o() != Material.field_151579_a) {
            block.func_149719_a((IBlockAccess)this.field_70170_p, this.field_145791_d, this.field_145792_e, this.field_145789_f);
            final AxisAlignedBB axisalignedbb = block.func_149668_a(this.field_70170_p, this.field_145791_d, this.field_145792_e, this.field_145789_f);
            if (axisalignedbb != null && axisalignedbb.func_72318_a(Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v))) {
                this.inGround = true;
            }
        }
        if (this.arrowShake > 0) {
            --this.arrowShake;
        }
        if (this.inGround) {
            final int j = this.field_70170_p.func_72805_g(this.field_145791_d, this.field_145792_e, this.field_145789_f);
            if (block == this.field_145790_g && j == this.inData) {
                if (this.Explodes) {
                    this.field_70170_p.func_72885_a((Entity)null, this.field_70165_t, this.field_70163_u, this.field_70161_v, (float)this.ExplodeSize, false, false);
                }
                this.func_70106_y();
            }
            else {
                this.inGround = false;
                this.field_70159_w *= this.field_70146_Z.nextFloat() * 0.2f;
                this.field_70181_x *= this.field_70146_Z.nextFloat() * 0.2f;
                this.field_70179_y *= this.field_70146_Z.nextFloat() * 0.2f;
                this.ticksInGround = 0;
                this.ticksInAir = 0;
            }
        }
        else {
            ++this.ticksInAir;
            Vec3 vec31 = Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
            Vec3 vec32 = Vec3.func_72443_a(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
            MovingObjectPosition movingobjectposition = this.field_70170_p.func_147447_a(vec31, vec32, false, true, false);
            vec31 = Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
            vec32 = Vec3.func_72443_a(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
            if (movingobjectposition != null) {
                vec32 = Vec3.func_72443_a(movingobjectposition.field_72307_f.field_72450_a, movingobjectposition.field_72307_f.field_72448_b, movingobjectposition.field_72307_f.field_72449_c);
            }
            Entity entity = null;
            final List list = this.field_70170_p.func_72839_b((Entity)this, this.field_70121_D.func_72321_a(this.field_70159_w, this.field_70181_x, this.field_70179_y).func_72314_b(1.0, 1.0, 1.0));
            double d0 = 0.0;
            for (int i = 0; i < list.size(); ++i) {
                final Entity entity2 = list.get(i);
                if (entity2.func_70067_L() && (entity2 != this.shootingEntity || this.ticksInAir >= 5)) {
                    final float f2 = 0.3f;
                    final AxisAlignedBB axisalignedbb2 = entity2.field_70121_D.func_72314_b((double)f2, (double)f2, (double)f2);
                    final MovingObjectPosition movingobjectposition2 = axisalignedbb2.func_72327_a(vec31, vec32);
                    if (movingobjectposition2 != null) {
                        final double d2 = vec31.func_72438_d(movingobjectposition2.field_72307_f);
                        if (d2 < d0 || d0 == 0.0) {
                            entity = entity2;
                            d0 = d2;
                        }
                    }
                }
            }
            if (entity != null) {
                movingobjectposition = new MovingObjectPosition(entity);
            }
            if (movingobjectposition != null && movingobjectposition.field_72308_g != null && movingobjectposition.field_72308_g instanceof EntityPlayer) {
                final EntityPlayer entityplayer = (EntityPlayer)movingobjectposition.field_72308_g;
                if (entityplayer.field_71075_bZ.field_75102_a || (this.shootingEntity instanceof EntityPlayer && !((EntityPlayer)this.shootingEntity).func_96122_a(entityplayer))) {
                    movingobjectposition = null;
                }
            }
            if (movingobjectposition != null) {
                if (movingobjectposition.field_72308_g != null) {
                    final float f3 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y);
                    int k = MathHelper.func_76143_f(f3 * this.damage);
                    if (this.getIsCritical()) {
                        k += this.field_70146_Z.nextInt(k / 2 + 2);
                    }
                    DamageSource damagesource = null;
                    if (this.shootingEntity == null) {
                        damagesource = DamageSource.func_76356_a((Entity)this, (Entity)this);
                    }
                    else {
                        damagesource = DamageSource.func_76356_a((Entity)this, this.shootingEntity);
                    }
                    if (this.func_70027_ad() && !(movingobjectposition.field_72308_g instanceof EntityEnderman)) {
                        movingobjectposition.field_72308_g.func_70015_d(5);
                    }
                    if (movingobjectposition.field_72308_g.func_70097_a(damagesource, (float)k)) {
                        if (movingobjectposition.field_72308_g instanceof EntityLivingBase) {
                            final EntityLivingBase entitylivingbase = (EntityLivingBase)movingobjectposition.field_72308_g;
                            if (!this.field_70170_p.field_72995_K) {
                                entitylivingbase.func_85034_r(entitylivingbase.func_85035_bI() + 1);
                            }
                            if (this.knockbackStrength > 0) {
                                final float f4 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
                                if (f4 > 0.0f) {
                                    movingobjectposition.field_72308_g.func_70024_g(this.field_70159_w * this.knockbackStrength * 0.6000000238418579 / f4, 0.1, this.field_70179_y * this.knockbackStrength * 0.6000000238418579 / f4);
                                }
                            }
                            if (this.shootingEntity != null && this.shootingEntity instanceof EntityLivingBase) {
                                EnchantmentHelper.func_151384_a(entitylivingbase, this.shootingEntity);
                                EnchantmentHelper.func_151385_b((EntityLivingBase)this.shootingEntity, (Entity)entitylivingbase);
                            }
                            if (this.shootingEntity != null && movingobjectposition.field_72308_g != this.shootingEntity && movingobjectposition.field_72308_g instanceof EntityPlayer && this.shootingEntity instanceof EntityPlayerMP) {
                                ((EntityPlayerMP)this.shootingEntity).field_71135_a.func_147359_a((Packet)new S2BPacketChangeGameState(6, 0.0f));
                            }
                        }
                        this.func_85030_a("random.bowhit", 1.0f, 1.2f / (this.field_70146_Z.nextFloat() * 0.2f + 0.9f));
                    }
                    else {
                        this.field_70159_w *= -0.10000000149011612;
                        this.field_70181_x *= -0.10000000149011612;
                        this.field_70179_y *= -0.10000000149011612;
                        this.field_70177_z += 180.0f;
                        this.field_70126_B += 180.0f;
                        this.ticksInAir = 0;
                    }
                }
                else {
                    this.field_145791_d = movingobjectposition.field_72311_b;
                    this.field_145792_e = movingobjectposition.field_72312_c;
                    this.field_145789_f = movingobjectposition.field_72309_d;
                    this.field_145790_g = this.field_70170_p.func_147439_a(this.field_145791_d, this.field_145792_e, this.field_145789_f);
                    this.inData = this.field_70170_p.func_72805_g(this.field_145791_d, this.field_145792_e, this.field_145789_f);
                    this.field_70159_w = (float)(movingobjectposition.field_72307_f.field_72450_a - this.field_70165_t);
                    this.field_70181_x = (float)(movingobjectposition.field_72307_f.field_72448_b - this.field_70163_u);
                    this.field_70179_y = (float)(movingobjectposition.field_72307_f.field_72449_c - this.field_70161_v);
                    final float f3 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y);
                    this.field_70165_t -= this.field_70159_w / f3 * 0.05000000074505806;
                    this.field_70163_u -= this.field_70181_x / f3 * 0.05000000074505806;
                    this.field_70161_v -= this.field_70179_y / f3 * 0.05000000074505806;
                    this.func_85030_a("random.bowhit", 1.0f, 1.2f / (this.field_70146_Z.nextFloat() * 0.2f + 0.9f));
                    this.inGround = true;
                    this.arrowShake = 7;
                    this.setIsCritical(false);
                    if (this.field_145790_g.func_149688_o() != Material.field_151579_a) {
                        this.field_145790_g.func_149670_a(this.field_70170_p, this.field_145791_d, this.field_145792_e, this.field_145789_f, (Entity)this);
                    }
                }
            }
            if (this.getIsCritical()) {
                for (int i = 0; i < 4; ++i) {
                    final int d3 = 5242980;
                    final double d2 = (d3 >> 16 & 0xFF) / 255.0;
                    final double d4 = (d3 >> 8 & 0xFF) / 255.0;
                    final double d5 = (d3 >> 0 & 0xFF) / 255.0;
                    this.field_70170_p.func_72869_a("mobSpell", this.field_70165_t, this.field_70163_u, this.field_70161_v, d2, d4, d5);
                    this.field_70170_p.func_72869_a("mobSpell", this.field_70165_t, this.field_70163_u, this.field_70161_v, d2, d4, d5);
                    this.field_70170_p.func_72869_a("mobSpell", this.field_70165_t, this.field_70163_u, this.field_70161_v, d2, d4, d5);
                    final Random rand = new Random();
                    this.field_70170_p.func_72869_a("mobSpell", this.field_70165_t + (rand.nextDouble() - 0.5) * 2.0, this.field_70163_u + (rand.nextDouble() - 0.5) * 2.0, this.field_70161_v + (rand.nextDouble() - 0.5) * 2.0, d2, d4, d5);
                }
            }
            this.field_70165_t += this.field_70159_w;
            this.field_70163_u += this.field_70181_x;
            this.field_70161_v += this.field_70179_y;
            final float f3 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
            this.field_70177_z = (float)(Math.atan2(this.field_70159_w, this.field_70179_y) * 180.0 / 3.141592653589793);
            this.field_70125_A = (float)(Math.atan2(this.field_70181_x, f3) * 180.0 / 3.141592653589793);
            while (this.field_70125_A - this.field_70127_C < -180.0f) {
                this.field_70127_C -= 360.0f;
            }
            while (this.field_70125_A - this.field_70127_C >= 180.0f) {
                this.field_70127_C += 360.0f;
            }
            while (this.field_70177_z - this.field_70126_B < -180.0f) {
                this.field_70126_B -= 360.0f;
            }
            while (this.field_70177_z - this.field_70126_B >= 180.0f) {
                this.field_70126_B += 360.0f;
            }
            this.field_70125_A = this.field_70127_C + (this.field_70125_A - this.field_70127_C) * 0.2f;
            this.field_70177_z = this.field_70126_B + (this.field_70177_z - this.field_70126_B) * 0.2f;
            final float f5 = 0.99f;
            final float f2 = 0.05f;
            if (this.func_70026_G()) {
                this.func_70066_B();
            }
            this.field_70159_w *= f5;
            this.field_70181_x *= f5;
            this.field_70179_y *= f5;
            this.field_70181_x -= f2;
            this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
            this.func_145775_I();
        }
    }
    
    public void func_70014_b(final NBTTagCompound stackData) {
        stackData.setShort("xTile", (short)this.field_145791_d);
        stackData.setShort("yTile", (short)this.field_145792_e);
        stackData.setShort("zTile", (short)this.field_145789_f);
        stackData.setShort("life", (short)this.ticksInGround);
        stackData.setByte("inTile", (byte)Block.getIdFromBlock(this.field_145790_g));
        stackData.setByte("inData", (byte)this.inData);
        stackData.setByte("shake", (byte)this.arrowShake);
        stackData.setByte("inGround", (byte)(byte)(this.inGround ? 1 : 0));
        stackData.setByte("pickup", (byte)this.canBePickedUp);
        stackData.setDouble("damage", this.damage);
    }
    
    public void func_70037_a(final NBTTagCompound p_70037_1_) {
        this.field_145791_d = p_70037_1_.func_74765_d("xTile");
        this.field_145792_e = p_70037_1_.func_74765_d("yTile");
        this.field_145789_f = p_70037_1_.func_74765_d("zTile");
        this.ticksInGround = p_70037_1_.func_74765_d("life");
        this.field_145790_g = Block.func_149729_e(p_70037_1_.func_74771_c("inTile") & 0xFF);
        this.inData = (p_70037_1_.func_74771_c("inData") & 0xFF);
        this.arrowShake = (p_70037_1_.func_74771_c("shake") & 0xFF);
        this.inGround = (p_70037_1_.func_74771_c("inGround") == 1);
        if (p_70037_1_.func_150297_b("damage", 99)) {
            this.damage = p_70037_1_.func_74769_h("damage");
        }
        if (p_70037_1_.func_150297_b("pickup", 99)) {
            this.canBePickedUp = p_70037_1_.func_74771_c("pickup");
        }
        else if (p_70037_1_.func_150297_b("player", 99)) {
            this.canBePickedUp = (p_70037_1_.func_74767_n("player") ? 1 : 0);
        }
    }
    
    public void func_70100_b_(final EntityPlayer p) {
        if (!this.worldObj.isRemote && this.inGround && this.arrowShake <= 0) { //!this.field_70170_p.field_72995_K
            boolean flag = this.canBePickedUp == 1 || (this.canBePickedUp == 2 && p.capabilities.isCreativeMode);
            if (this.canBePickedUp == 1 && !p.field_71071_by.func_70441_a(new ItemStack(Items.field_151032_g, 1))) {
                flag = false;
            }
            if (flag) {
                this.func_85030_a("random.pop", 0.2f, ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.7f + 1.0f) * 2.0f);
                p.dropItem((Entity)this, 1); //p.func_71001_a((Entity)this, 1);
                this.func_70106_y();
            }
        }
    }
    
    protected boolean func_70041_e_() {
        return false;
    }
    
    @SideOnly(Side.CLIENT)
    public float func_70053_R() {
        return 0.0f;
    }
    
    public void setDamage(final double d) {
        this.damage = d;
    }
    
    public double getDamage() {
        return this.damage;
    }
    
    public void setKnockbackStrength(final int s) {
        this.knockbackStrength = s;
    }
    
    public boolean func_70075_an() {
        return false;
    }
    
    public void setIsCritical(final boolean p_70243_1_) {
        final byte b0 = this.field_70180_af.func_75683_a(16);
        if (p_70243_1_) {
            this.field_70180_af.func_75692_b(16, (Object)(byte)(b0 | 0x1));
        }
        else {
            this.field_70180_af.func_75692_b(16, (Object)(byte)(b0 & 0xFFFFFFFE));
        }
    }
    
    public boolean getIsCritical() {
        final byte b0 = this.field_70180_af.func_75683_a(16);
        return (b0 & 0x1) != 0x0;
    }
    
    public EntityEnergyBeam(final World w, final EntityLivingBase elb, final float p_i1756_3_, final double size, final boolean Explodes, final boolean Gravity) {
        super(w);
        this.field_145791_d = -1;
        this.field_145792_e = -1;
        this.field_145789_f = -1;
        this.damage = 2.0;
        this.ExplodeSize = 1.0;
        this.Explodes = true;
        this.Gravity = true;
        this.Explodes = Explodes;
        this.ExplodeSize = size;
        this.Gravity = Gravity;
        this.field_70155_l = 10.0;
        this.shootingEntity = (Entity)elb;
        if (elb instanceof EntityPlayer) {
            this.canBePickedUp = 1;
        }
        this.func_70105_a(0.5f, 0.5f);
        this.func_70012_b(elb.field_70165_t, elb.field_70163_u + elb.func_70047_e(), elb.field_70161_v, elb.field_70177_z, elb.field_70125_A);
        this.field_70165_t -= MathHelper.func_76134_b(this.field_70177_z / 180.0f * 3.1415927f) * 0.16f;
        this.field_70163_u -= 0.10000000149011612;
        this.field_70161_v -= MathHelper.func_76126_a(this.field_70177_z / 180.0f * 3.1415927f) * 0.16f;
        this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
        this.field_70129_M = 0.0f;
        this.field_70159_w = -MathHelper.func_76126_a(this.field_70177_z / 180.0f * 3.1415927f) * MathHelper.func_76134_b(this.field_70125_A / 180.0f * 3.1415927f);
        this.field_70179_y = MathHelper.func_76134_b(this.field_70177_z / 180.0f * 3.1415927f) * MathHelper.func_76134_b(this.field_70125_A / 180.0f * 3.1415927f);
        this.field_70181_x = -MathHelper.func_76126_a(this.field_70125_A / 180.0f * 3.1415927f);
        this.setThrowableHeading(this.field_70159_w, this.field_70181_x, this.field_70179_y, p_i1756_3_ * 1.5f, 1.0f);
    }
}
