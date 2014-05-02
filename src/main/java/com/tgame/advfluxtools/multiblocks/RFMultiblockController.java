package com.tgame.advfluxtools.multiblocks;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyStorage;
import com.tgame.advfluxtools.Settings;
import com.tgame.advfluxtools.libs.erogenousbeef.multiblock.IMultiblockPart;
import com.tgame.advfluxtools.libs.erogenousbeef.multiblock.MultiblockControllerBase;
import com.tgame.advfluxtools.libs.erogenousbeef.multiblock.MultiblockValidationException;
import com.tgame.advfluxtools.libs.erogenousbeef.multiblock.rectangular.RectangularMultiblockControllerBase;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fluids.IFluidBlock;

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
		this.energy.setCapacity(2000 * this.getNumConnectedBlocks());
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
		Block block = Block.blocksList[world.getBlockId(x, y, z)];
		if (block != null && block.isAirBlock(world, x, y, z))
			throw new MultiblockValidationException("Internal blocks must be EMPTY!");

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
