package com.tgame.advfluxtools.items;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

/**
 * @author tgame14
 * @since 30/04/14
 */
public enum EnumLaserMode
{
	EXPLOSION(4000)
			{
				@Override
				public void onImpact(World world, Entity projectile)
				{
					world.createExplosion(projectile, projectile.posX, projectile.posY, projectile.posZ, 4, true);
					projectile.setDead();
				}
			};

	public final int powerusage;

	private EnumLaserMode(int powerusage)
	{
		this.powerusage = powerusage;
	}

	public abstract void onImpact(World world, Entity projectile);
}
