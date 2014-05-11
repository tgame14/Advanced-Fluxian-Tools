package com.tgame.advfluxtools.multiblocks;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyStorage;
import com.tgame.advfluxtools.libs.erogenousbeef.multiblock.IMultiblockPart;
import com.tgame.advfluxtools.libs.erogenousbeef.multiblock.MultiblockControllerBase;
import com.tgame.advfluxtools.libs.erogenousbeef.multiblock.MultiblockValidationException;
import com.tgame.advfluxtools.libs.erogenousbeef.multiblock.rectangular.RectangularMultiblockControllerBase;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author tgame14
 * @since 01/05/14
 */
public abstract class RFMultiblockController extends RectangularMultiblockControllerBase implements IEnergyStorage
{
	/**
	 * default external energy storage object
	 */
	protected EnergyStorage energy;

	protected RFMultiblockController(World world, TileEntity tile)
	{
		super(world);
	}

	@Override
	public void onAttachedPartWithMultiblockData(IMultiblockPart part, NBTTagCompound data)
	{
		if (data.hasKey("Energy"))
		{
			this.energy.setEnergyStored(this.energy.getEnergyStored() + data.getInteger("Energy"));
		}
	}

	@Override
	protected void onBlockAdded(IMultiblockPart newPart)
	{
		this.energy.setCapacity(2000 * this.getNumConnectedBlocks());
	}

	@Override
	protected void onBlockRemoved(IMultiblockPart oldPart)
	{
		this.energy.setCapacity(2000 * this.getNumConnectedBlocks());
	}

	@Override
	protected void onMachineAssembled()
	{
		if (this.energy == null)
		{
			this.energy = new EnergyStorage(8000 * this.getNumConnectedBlocks(), 10000);
		}
		else
		{
			int stored = this.energy.getEnergyStored();
			this.energy = new EnergyStorage(8000 * this.getNumConnectedBlocks(), 10000);
			this.energy.setEnergyStored(stored);
		}
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
		return 26;
	}

	@Override
	protected int getMaximumXSize()
	{
		return 24;
	}


	@Override
	protected int getMaximumYSize()
	{
		return 24;
	}

	@Override
	protected int getMaximumZSize()
	{
		return 24;
	}

	@Override
	protected int getMinimumXSize()
	{
		return 3;
	}

	@Override
	protected int getMinimumYSize()
	{
		return 3;
	}

	@Override
	protected int getMinimumZSize()
	{
		return 3;
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
		//STUB only do rendering and such here.
	}


	@Override
	protected void isBlockGoodForInterior(World world, int x, int y, int z) throws MultiblockValidationException
	{
		Block block = world.getBlock(x, y, z);
		if (block != null && block.isAir(world, x, y, z))
			throw new MultiblockValidationException("Internal blocks must be EMPTY!");

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

	}

	@Override
	public void decodeDescriptionPacket(NBTTagCompound data)
	{

	}
}
