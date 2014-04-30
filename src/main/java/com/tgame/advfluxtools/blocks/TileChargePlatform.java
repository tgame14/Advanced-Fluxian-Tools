package com.tgame.advfluxtools.blocks;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.TileEnergyHandler;

/**
 * @author tgame14
 * @since 30/04/14
 */
public class TileChargePlatform extends TileEnergyHandler
{
	public TileChargePlatform()
	{
		super();
		this.storage = new EnergyStorage(32000, 10000);
	}
}
