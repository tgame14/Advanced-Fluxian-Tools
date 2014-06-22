package com.tgame.advfluxtools.blocks;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import com.tgame.advfluxtools.prefabs.TGTileEnergyHandler;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @since 10/05/14
 * @author tgame14
 */
public class TileCreativeGenerator extends TGTileEnergyHandler
{

	public TileCreativeGenerator()
	{
		this.storage = new EnergyStorage(Integer.MAX_VALUE);
	}
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

    @Override
    public void updateEntity ()
    {
        super.updateEntity();
		for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS)
        {
            TileEntity tile = worldObj.getTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ);
            if (tile instanceof IEnergyHandler)
            {
                IEnergyHandler handler = (IEnergyHandler) tile;
                handler.receiveEnergy(dir.getOpposite(), this.extractEnergy(ForgeDirection.UNKNOWN, 0, true), false);
            }
        }
    }
}
