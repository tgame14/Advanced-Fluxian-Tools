package com.tgame.advfluxtools.items;

import com.tgame.advfluxtools.entities.EntityLaserProjectile;
import com.tgame.mods.config.Config;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author tgame14
 * @since 30/04/14
 */
//@Deprecated TODO: Deprecate but in the meanwhile the crossed out line bugs me
public enum EnumLaserMode
{
    EXPLOSION(8000, 0.8F)
            {
                @Override
                public void onImpact (World world, EntityLaserProjectile projectile, MovingObjectPosition hit)
                {
                    if (!world.isRemote)
                    {
                        world.createExplosion(projectile, projectile.posX, projectile.posY, projectile.posZ, LASER_EXPLOSION_SIZE, true);
                        projectile.setDead();
                    }
                }
            },

    MINE(2000, 1.2F)
            {
                @Override
                public void onImpact (World world, EntityLaserProjectile projectile, MovingObjectPosition hit)
                {
                    if (!world.isRemote)
                    {
                        if (hit.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY)
                        {
                            if (hit.entityHit instanceof EntityLivingBase)
                            {
                                EntityLivingBase liveEntity = (EntityLivingBase) hit.entityHit;
                                liveEntity.attackEntityFrom(new EntityDamageSource("entity.laser", projectile), 4F);
                            }
                        }
                        else
                        {
                            Block block = world.getBlock(hit.blockX, hit.blockY, hit.blockZ);
                            if (block.getBlockHardness(world, hit.blockX, hit.blockY, hit.blockZ) < 30F && block.getBlockHardness(world, hit.blockX, hit.blockY, hit.blockZ) != -1.0F)
                            {

                                world.func_147480_a(hit.blockX, hit.blockY, hit.blockZ, true);
                                projectile.setBlocksHit(projectile.getBlocksHit() + 1);
                            }
                            else
                            {
                                projectile.setDead();
                            }
                        }
                        if (projectile.getBlocksHit() > 5)
                            projectile.setDead();
                    }
                }
            };

    public final int powerusage;
    public final float speed;

    private EnumLaserMode (int powerusage, float speed)
    {
        this.powerusage = powerusage;
        this.speed = speed;
    }

    public abstract void onImpact (World world, EntityLaserProjectile projectile, MovingObjectPosition hit);

    @Config
    private static float LASER_EXPLOSION_SIZE = 3.5F;
}
