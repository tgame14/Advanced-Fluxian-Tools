package com.tgame.advfluxtools.multiblocks;

import cofh.api.energy.EnergyStorage;
import com.tgame.advfluxtools.libs.erogenousbeef.multiblock.IMultiblockPart;
import com.tgame.advfluxtools.libs.erogenousbeef.multiblock.MultiblockControllerBase;
import com.tgame.advfluxtools.libs.erogenousbeef.multiblock.rectangular.RectangularMultiblockControllerBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * @author tgame14
 * @since 01/05/14
 */
public abstract class RFMultiblockController extends RectangularMultiblockControllerBase
{
	/** default external energy storage object */
	protected EnergyStorage storage;

	protected RFMultiblockController(World world)
	{
		super(world);
		this.storage = new EnergyStorage(16000, 80);
	}

	@Override
	public void onAttachedPartWithMultiblockData(IMultiblockPart part, NBTTagCompound data)
	{

	}

	@Override
	protected void onBlockAdded(IMultiblockPart newPart)
	{

	}

	@Override
	protected void onBlockRemoved(IMultiblockPart oldPart)
	{

	}

	@Override
	protected void onMachineAssembled()
	{

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
