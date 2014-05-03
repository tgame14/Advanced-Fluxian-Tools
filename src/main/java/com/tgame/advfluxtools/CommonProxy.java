package com.tgame.advfluxtools;

import com.tgame.advfluxtools.libs.erogenousbeef.multiblock.MultiblockServerTickHandler;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

/**
 * @author tgame14
 * @since 30/04/14
 */
public class CommonProxy
{
	public void registerRenderers()
	{

	}

	public void preInit()
	{

	}

	public void init()
	{
		registerTickHandlers();
	}

	public void postInit()
	{

	}

	public void registerTickHandlers()
	{
		TickRegistry.registerTickHandler(new MultiblockServerTickHandler(), Side.SERVER);
	}
}
