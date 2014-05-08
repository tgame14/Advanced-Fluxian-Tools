package com.tgame.advfluxtools.multiblocks.furnace;

import cofh.api.energy.EnergyStorage;
import com.tgame.advfluxtools.Settings;
import com.tgame.advfluxtools.libs.erogenousbeef.common.CoordTriplet;
import com.tgame.advfluxtools.libs.erogenousbeef.multiblock.IMultiblockPart;
import com.tgame.advfluxtools.libs.erogenousbeef.multiblock.MultiblockControllerBase;
import com.tgame.advfluxtools.libs.erogenousbeef.multiblock.MultiblockValidationException;
import com.tgame.advfluxtools.libs.inventory.item.IInventoryStorage;
import com.tgame.advfluxtools.multiblocks.RFMultiblockController;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;

/**
 * @author tgame14
 * @since 01/05/14
 */
public class RFFurnaceController extends RFMultiblockController implements IFluidTank//, IInventoryStorage
{
	protected RFFurnaceController(World world, TileEntity tile)
	{
		super(world, tile);
		this.tank = new FluidTank(32000);
	}

	@Override
	public void onAttachedPartWithMultiblockData(IMultiblockPart part, NBTTagCompound data)
	{
		super.onAttachedPartWithMultiblockData(part, data);
	}

	@Override
	protected void onBlockAdded(IMultiblockPart newPart)
	{
		super.onBlockAdded(newPart);
	}

	@Override
	protected void onBlockRemoved(IMultiblockPart oldPart)
	{
		super.onBlockRemoved(oldPart);
	}

	@Override
	protected void onMachineAssembled()
	{
		this.energy = new EnergyStorage(2000 * this.getNumConnectedBlocks());
		this.tank = new FluidTank(2000 * this.getNumConnectedBlocks());
		super.onMachineAssembled();
	}

	@Override
	protected void onMachineRestored()
	{
		super.onMachineRestored();
	}

	@Override
	protected void onMachinePaused()
	{
		super.onMachinePaused();
	}

	@Override
	protected void onMachineDisassembled()
	{
		super.onMachineDisassembled();
	}

	@Override
	protected int getMinimumNumberOfBlocksForAssembledMachine()
	{
		return super.getMinimumNumberOfBlocksForAssembledMachine();
	}

	@Override
	protected int getMaximumXSize()
	{
		return 24;
	}

	@Override
	protected int getMaximumZSize()
	{
		return 24;
	}

	@Override
	protected int getMaximumYSize()
	{
		return 24;
	}

	@Override
	protected void onAssimilate(MultiblockControllerBase assimilated)
	{
		super.onAssimilate(assimilated);
	}

	@Override
	protected void onAssimilated(MultiblockControllerBase assimilator)
	{
		super.onAssimilated(assimilator);
	}

	@Override
	protected boolean updateServer()
	{
		return super.updateServer();
	}

	@Override
	protected void updateClient()
	{
		super.updateClient();
	}

	@Override
	public void writeToNBT(NBTTagCompound data)
	{
		NBTTagCompound tag = new NBTTagCompound("Energy");
		this.energy.writeToNBT(tag);
		data.setCompoundTag("Energy", tag);
		super.writeToNBT(data);
	}

	@Override
	public void readFromNBT(NBTTagCompound data)
	{
		this.energy.readFromNBT(data.getCompoundTag("Energy"));
		super.readFromNBT(data);
	}

	@Override
	public void attachBlock(IMultiblockPart part)
	{
		super.attachBlock(part);
	}

	@Override
	public void detachBlock(IMultiblockPart part, boolean chunkUnloading)
	{
		super.detachBlock(part, chunkUnloading);
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
	public void assimilate(MultiblockControllerBase other)
	{
		super.assimilate(other);
	}

	@Override
	protected void isBlockGoodForFrame(World world, int x, int y, int z) throws MultiblockValidationException
	{
		super.isBlockGoodForFrame(world, x, y, z);
	}

	@Override
	protected void isBlockGoodForTop(World world, int x, int y, int z) throws MultiblockValidationException
	{
		super.isBlockGoodForTop(world, x, y, z);
	}

	@Override
	protected void isBlockGoodForBottom(World world, int x, int y, int z) throws MultiblockValidationException
	{
		super.isBlockGoodForBottom(world, x, y, z);
	}

	@Override
	protected void isBlockGoodForSides(World world, int x, int y, int z) throws MultiblockValidationException
	{
		super.isBlockGoodForSides(world, x, y, z);
	}

	@Override
	protected void isBlockGoodForInterior(World world, int x, int y, int z) throws MultiblockValidationException
	{
		super.isBlockGoodForInterior(world, x, y, z);
	}

	@Override
	public CoordTriplet getReferenceCoord()
	{
		return super.getReferenceCoord();
	}

	@Override
	public CoordTriplet getMinimumCoord()
	{
		return super.getMinimumCoord();
	}

	@Override
	public CoordTriplet getMaximumCoord()
	{
		return super.getMaximumCoord();
	}

	// * * * IFLUIDHANDLER * * * //

	protected FluidTank tank;

	@Override
	public FluidStack getFluid()
	{
		return tank.getFluid();
	}

	@Override
	public int getFluidAmount()
	{
		return tank.getFluidAmount();
	}

	@Override
	public int getCapacity()
	{
		return tank.getCapacity();
	}

	@Override
	public FluidTankInfo getInfo()
	{
		return tank.getInfo();
	}

	@Override
	public int fill(FluidStack resource, boolean doFill)
	{
		return tank.fill(resource, doFill);
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain)
	{
		return tank.drain(maxDrain, doDrain);
	}

	/// * * * IENERGYSTORAGE * * * ///

	@Override
	public int receiveEnergy(int i, boolean b)
	{
		return this.energy.receiveEnergy(i, b);
	}

	@Override
	public int extractEnergy(int i, boolean b)
	{
		return this.energy.extractEnergy(i, b);
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
}
