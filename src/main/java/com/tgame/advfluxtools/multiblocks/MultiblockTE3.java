package com.tgame.advfluxtools.multiblocks;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyStorage;
import cofh.api.energy.ItemEnergyContainer;
import com.tgame.advfluxtools.libs.multiblocks.grid.Grid;
import com.tgame.advfluxtools.libs.multiblocks.grid.IGridNode;

/**
 * @author tgame14
 * @since 01/05/14
 */
public class MultiblockTE3 extends Grid implements IEnergyStorage
{
	protected IEnergyStorage energy;

	public MultiblockTE3(IGridNode node)
	{
		super(node);
		this.energy = calcEnergyStorage();
	}

	private IEnergyStorage calcEnergyStorage()
	{
		return new EnergyStorage(this.nodeSet.size() * 2000, this.nodeSet.size() * 80);
	}

	@Override
	public void onClientTick()
	{
		//Only do client side REQUIRED here.
	}

	@Override
	public void onServerTick()
	{

	}

	@Override
	public int receiveEnergy(int i, boolean b)
	{
		return this.energy.receiveEnergy(i, b);
	}

	@Override
	public int extractEnergy(int i, boolean b)
	{
		return this.energy.extractEnergy(i, b);
	}

	@Override
	public int getEnergyStored()
	{
		return this.energy.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored()
	{
		return this.energy.getMaxEnergyStored();
	}
}
