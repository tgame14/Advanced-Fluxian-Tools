package com.tgame.advfluxtools;

import com.tgame.advfluxtools.entities.EntityLaserProjectile;
import com.tgame.advfluxtools.entities.RenderLaserProjectile;
import com.tgame.advfluxtools.libs.erogenousbeef.multiblock.MultiblockClientTickHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

/**
 * @author tgame14
 * @since 30/04/14
 */
public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRenderers()
	{
		super.registerRenderers();
		RenderingRegistry.registerEntityRenderingHandler(EntityLaserProjectile.class, new RenderLaserProjectile());
	}

	@Override
	public void init()
	{
		TickRegistry.registerScheduledTickHandler(new MultiblockClientTickHandler(), Side.CLIENT);
	}
}
