package com.tgame.advfluxtools.entities;

import com.tgame.advfluxtools.Settings;
import net.minecraft.client.renderer.entity.RenderEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import java.io.File;

/**
 * @author tgame14
 * @since 30/04/14
 */
public class RenderLaserProjectile extends RenderEntity
{
	private static final ResourceLocation resource = new ResourceLocation(Settings.RESOURCE_LOCATION + "textures" + File.separator + "entities" + File.separator + EntityLaserProjectile.class.getSimpleName());

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		return resource;
	}
}
