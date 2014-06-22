package com.tgame.advfluxtools.prefabs;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import com.tgame.mods.prefabs.tiles.TGTileBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author tgame14
 * @since 22/06/2014
 */
public class TGTileEnergyHandler extends TGTileBase implements IEnergyHandler
	{
		protected EnergyStorage storage;

		public TGTileEnergyHandler()
		{
			this.storage = new EnergyStorage(32000);
		}

		public TGTileEnergyHandler(int size)
		{
			this.storage = new EnergyStorage(size);
		}

		@Override
		public void readFromNBT(NBTTagCompound nbt) {

		super.readFromNBT(nbt);
		storage.readFromNBT(nbt);
	}

		@Override
		public void writeToNBT(NBTTagCompound nbt) {

		super.writeToNBT(nbt);
		storage.writeToNBT(nbt);
	}

	/* IEnergyHandler */
		@Override
		public boolean canConnectEnergy(ForgeDirection from) {

		return true;
	}

		@Override
		public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {

		return storage.receiveEnergy(maxReceive, simulate);
	}

		@Override
		public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {

		return storage.extractEnergy(maxExtract, simulate);
	}

		@Override
		public int getEnergyStored(ForgeDirection from) {

		return storage.getEnergyStored();
	}

		@Override
		public int getMaxEnergyStored(ForgeDirection from) {

		return storage.getMaxEnergyStored();
	}
}
