package com.tgame.advfluxtools.multiblocks;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyStorage;
import com.tgame.advfluxtools.libs.multiblocks.grid.Grid;
import com.tgame.advfluxtools.libs.multiblocks.grid.IGridNode;

/**
 * @author tgame14
 * @since 01/05/14
 */
public abstract class MultiblockTE3 extends Grid
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
}
