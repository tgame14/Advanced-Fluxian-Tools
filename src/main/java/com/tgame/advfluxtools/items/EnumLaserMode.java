package com.tgame.advfluxtools.items;

import com.tgame.advfluxtools.entities.EntityLaserProjectile;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumMovingObjectType;
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
@Deprecated
public enum EnumLaserMode
{
	EXPLOSION(4000)
			{
				@Override
				public void onImpact(World world, EntityLaserProjectile projectile, MovingObjectPosition hit)
				{
					if (!world.isRemote)
					{
						world.createExplosion(projectile, projectile.posX, projectile.posY, projectile.posZ, 4, true);
						projectile.setDead();
					}
				}
			},

	MINE(2000)
			{
				@Override
				public void onImpact(World world, EntityLaserProjectile projectile, MovingObjectPosition hit)
				{
					if (!world.isRemote)
					{
						if (hit.typeOfHit == EnumMovingObjectType.ENTITY)
						{
							if (hit.entityHit instanceof EntityLivingBase)
							{
								EntityLivingBase liveEntity = (EntityLivingBase) hit.entityHit;
								liveEntity.attackEntityFrom(new EntityDamageSource("entity.laser", projectile), 4F);
							}
						}
						else
						{
							world.destroyBlock(hit.blockX, hit.blockY, hit.blockZ, true);
							projectile.setBlocksHit(projectile.getBlocksHit() + 1);
						}
						if (projectile.getBlocksHit() > 5)
							projectile.setDead();
					}
				}
			};

	public final int powerusage;

	private EnumLaserMode(int powerusage)
	{
		this.powerusage = powerusage;
	}

	public abstract void onImpact(World world, EntityLaserProjectile projectile, MovingObjectPosition hit);
}
