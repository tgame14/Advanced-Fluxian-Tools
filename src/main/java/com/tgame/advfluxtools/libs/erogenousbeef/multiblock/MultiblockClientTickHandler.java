package com.tgame.advfluxtools.libs.erogenousbeef.multiblock;

import cpw.mods.fml.common.IScheduledTickHandler;
import cpw.mods.fml.common.TickType;
import net.minecraft.client.Minecraft;

import java.util.EnumSet;

public class MultiblockClientTickHandler implements IScheduledTickHandler
{

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData)
	{
		if (type.contains(TickType.CLIENT))
		{
			MultiblockRegistry.tickStart(Minecraft.getMinecraft().theWorld);
		}
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData)
	{
		if (type.contains(TickType.CLIENT))
		{
			MultiblockRegistry.tickEnd(Minecraft.getMinecraft().theWorld);
		}
	}

	@Override
	public EnumSet<TickType> ticks()
	{
		return EnumSet.of(TickType.CLIENT);
	}

	@Override
	public String getLabel()
	{
		return "AFT:MultiblockClientTickHandler";
	}

	@Override
	public int nextTickSpacing()
	{
		return 1;
	}

}
