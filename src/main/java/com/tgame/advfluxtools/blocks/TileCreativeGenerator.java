package com.tgame.advfluxtools.blocks;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.TileEnergyHandler;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @since 10/05/14
 * @author tgame14
 */
public class TileCreativeGenerator extends TileEnergyHandler
{
    private EnergyStorage storage = new EnergyStorage(Integer.MAX_VALUE);
    @Override
    public int receiveEnergy (ForgeDirection from, int maxReceive, boolean simulate)
    {
        return 0;
    }

    @Override
    public int extractEnergy (ForgeDirection from, int maxExtract, boolean simulate)
    {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean canConnectEnergy (ForgeDirection from)
    {
        return true;
    }

    @Override
    public int getEnergyStored (ForgeDirection from)
    {
        return Integer.MAX_VALUE;
    }

    @Override
    public int getMaxEnergyStored (ForgeDirection from)
    {
        return Integer.MAX_VALUE;
    }
}
