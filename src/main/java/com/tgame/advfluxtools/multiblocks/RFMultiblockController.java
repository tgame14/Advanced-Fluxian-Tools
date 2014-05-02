package com.tgame.advfluxtools.multiblocks;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyStorage;
import com.tgame.advfluxtools.Settings;
import com.tgame.advfluxtools.libs.erogenousbeef.multiblock.IMultiblockPart;
import com.tgame.advfluxtools.libs.erogenousbeef.multiblock.MultiblockControllerBase;
import com.tgame.advfluxtools.libs.erogenousbeef.multiblock.rectangular.RectangularMultiblockControllerBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * @author tgame14
 * @since 01/05/14
 */
public abstract class RFMultiblockController extends RectangularMultiblockControllerBase implements IEnergyStorage
{
	/** default external energy storage object */
	protected EnergyStorage energy;

	protected RFMultiblockController(World world)
	{
		super(world);
		//TODO: Handle per block in creation and addition
		this.energy = new EnergyStorage(16000, 80);
	}

	@Override
	public void onAttachedPartWithMultiblockData(IMultiblockPart part, NBTTagCompound data)
	{

	}

	@Override
	protected void onBlockAdded(IMultiblockPart newPart)
	{
		this.energy.setCapacity(2000 * this.getNumConnectedBlocks());
	}

	@Override
	protected void onBlockRemoved(IMultiblockPart oldPart)
	{

	}

	@Override
	protected void onMachineAssembled()
	{
		Settings.LOGGER.info("Provided multiblock assembled");
	}

	@Override
	protected void onMachineRestored()
	{

	}

	@Override
	protected void onMachinePaused()
	{

	}

	@Override
	protected void onMachineDisassembled()
	{

	}

	@Override
	protected int getMinimumNumberOfBlocksForAssembledMachine()
	{
		return 0;
	}

	@Override
	protected int getMaximumXSize()
	{
		return 0;
	}

	@Override
	protected int getMaximumZSize()
	{
		return 0;
	}

	@Override
	protected int getMaximumYSize()
	{
		return 0;
	}

	@Override
	protected void onAssimilate(MultiblockControllerBase assimilated)
	{

	}

	@Override
	protected void onAssimilated(MultiblockControllerBase assimilator)
	{

	}

	@Override
	protected boolean updateServer()
	{
		return false;
	}

	@Override
	protected void updateClient()
	{

	}

	@Override
	public void writeToNBT(NBTTagCompound data)
	{

	}

	@Override
	public void readFromNBT(NBTTagCompound data)
	{

	}

	@Override
	public void formatDescriptionPacket(NBTTagCompound data)
	{

	}

	@Override
	public void decodeDescriptionPacket(NBTTagCompound data)
	{

	}
}
