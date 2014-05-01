package com.tgame.advfluxtools.multiblocks;

import cofh.api.energy.TileEnergyHandler;
import com.tgame.advfluxtools.libs.multiblocks.WorldPos;
import com.tgame.advfluxtools.libs.multiblocks.grid.IGrid;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author tgame14
 * @since 01/05/14
 */
public abstract class TileRFMultiblock extends TileEnergyHandler implements IRFMultiblock
{
	protected boolean isDelegate;
	protected IGrid grid;

	public TileRFMultiblock()
	{

	}

	@Override
	public WorldPos getWorldPos()
	{
		return new WorldPos(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
	}

	@Override
	public IGrid getGrid()
	{
		return this.grid;
	}

	@Override
	public void setGrid(IGrid grid)
	{
		this.grid = grid;
	}

	@Override
	public Class<? extends IGrid> getNewGridClass()
	{
		return MultiblockTE3.class;
	}

	@Override
	public boolean canBeDelegate()
	{
		//Be sure to implement correctly
		return true;
	}

	@Override
	public boolean isDelegate()
	{
		return this.isDelegate;
	}

	@Override
	public void setDelegate(boolean isDelegate)
	{
		this.isDelegate = isDelegate;
	}

}
