package com.tgame.advfluxtools.multiblocks.energy;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyStorage;
import com.tgame.mods.libs.multiblocks.grid.AbstractMultiblockNode;
import com.tgame.mods.libs.multiblocks.grid.GridController;
import com.tgame.mods.libs.multiblocks.simpleimpl.SimpleGridController;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * @author tgame14
 * @since 24/05/14
 */
public class RFGridController extends SimpleGridController implements IEnergyStorage
{
	protected EnergyStorage energy;

	public RFGridController(World world)
	{
		super(world);

		this.energy = new EnergyStorage(0);
	}

	@Override
	protected void onMachineAssembled()
	{
		super.onMachineAssembled();

		this.energy.setCapacity(this.getNumConnectedBlocks() * 2000);
		this.energy.setMaxTransfer(10000);
		System.out.println("ASSEMBLED");
	}

	@Override
	protected void onMachineDisassembled()
	{
		super.onMachineDisassembled();
		this.energy.setMaxTransfer(0);
	}

	@Override
	public void onAttachedPartWithMultiblockData(AbstractMultiblockNode part, NBTTagCompound data)
	{
		super.onAttachedPartWithMultiblockData(part, data);
		if (data.hasKey("Energy"))
			this.energy.setEnergyStored(this.getEnergyStored() + data.getInteger("Energy"));
	}

	@Override
	protected void onAssimilate(GridController assimilated)
	{
		super.onAssimilate(assimilated);

		NBTTagCompound nbt = new NBTTagCompound();
		assimilated.writeToNBT(nbt);
		if (nbt.hasKey("Energy"))
			this.energy.setEnergyStored(getEnergyStored() + nbt.getInteger("Energy"));
	}

	@Override
	protected int getMinimumNumberOfBlocksForAssembledMachine()
	{
		return 1;
	}

	@Override
	protected int getMaximumXSize()
	{
		return Integer.MAX_VALUE;
	}

	@Override
	protected int getMaximumZSize()
	{
		return Integer.MAX_VALUE;
	}

	@Override
	protected int getMaximumYSize()
	{
		return Integer.MAX_VALUE;
	}

	@Override
	protected boolean updateServer()
	{
		return false;
	}

	@Override
	protected void onMachinePaused()
	{
		super.onMachinePaused();
	}

	@Override
	public int receiveEnergy(int maxReceive, boolean simulate)
	{
		return this.energy.receiveEnergy(maxReceive, simulate);
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate)
	{
		return this.energy.extractEnergy(maxExtract, simulate);
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

	@Override
	public void writeToNBT(NBTTagCompound data)
	{
		this.energy.writeToNBT(data);
	}

	@Override
	public void readFromNBT(NBTTagCompound data)
	{
		this.energy.readFromNBT(data);
	}

	@Override
	public void formatDescriptionPacket(NBTTagCompound data)
	{
		this.writeToNBT(data);
	}

	@Override
	public void decodeDescriptionPacket(NBTTagCompound data)
	{
		this.readFromNBT(data);
	}
}
