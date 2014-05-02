package com.tgame.advfluxtools.multiblocks.furnace;

import com.tgame.advfluxtools.libs.erogenousbeef.multiblock.MultiblockControllerBase;
import com.tgame.advfluxtools.libs.erogenousbeef.multiblock.MultiblockValidationException;
import com.tgame.advfluxtools.multiblocks.RFTileMultiblock;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

/**
 * @author tgame14
 * @since 01/05/14
 */
public class TileRFFurnace extends RFTileMultiblock implements IFluidHandler
{
	@Override
	public void onMachineActivated()
	{

	}

	@Override
	public void onMachineDeactivated()
	{

	}

	@Override
	public MultiblockControllerBase createNewMultiblock()
	{
		return new RFFurnaceController(getWorldObj());
	}

	@Override
	public Class<? extends MultiblockControllerBase> getMultiblockControllerType()
	{
		return RFFurnaceController.class;
	}

	@Override
	public int receiveEnergy(ForgeDirection forgeDirection, int i, boolean b)
	{
		return getRFController().receiveEnergy(i, b);
	}

	@Override
	public int extractEnergy(ForgeDirection forgeDirection, int i, boolean b)
	{
		return getRFController().extractEnergy(i, b);
	}

	@Override
	public boolean canInterface(ForgeDirection forgeDirection)
	{
		return super.canInterface(forgeDirection);
	}

	@Override
	public int getEnergyStored(ForgeDirection forgeDirection)
	{
		return getRFController().getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection forgeDirection)
	{
		return getRFController().getMaxEnergyStored();
	}

	@Override
	public void isGoodForFrame() throws MultiblockValidationException
	{

	}

	@Override
	public void isGoodForSides() throws MultiblockValidationException
	{

	}

	@Override
	public void isGoodForTop() throws MultiblockValidationException
	{

	}

	@Override
	public void isGoodForBottom() throws MultiblockValidationException
	{

	}

	@Override
	public void isGoodForInterior() throws MultiblockValidationException
	{
		super.isGoodForInterior();
	}

	public RFFurnaceController getRFController()
	{
		return (RFFurnaceController) this.getMultiblockController();
	}

	@Override
	public void onMachineAssembled(MultiblockControllerBase controller)
	{
		super.onMachineAssembled(controller);
		worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 1, 3);
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord); //a safety, not required yet does not harm too much.
	}

	@Override
	public void onMachineBroken()
	{
		super.onMachineBroken();
		worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 3);
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}

	/// * * * IFLUIDHANDLER * * * ///

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
	{
		return getRFController().fill(resource, doFill);
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain)
	{
		if (getRFController().getFluid().isFluidEqual(resource));
		return getRFController().drain(resource.amount, doDrain);
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
	{
		return this.drain(from, new FluidStack(getRFController().getFluid(), maxDrain), doDrain);
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid)
	{
		return getRFController().isEmpty() || getRFController().getFluid().equals(fluid);
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid)
	{
		return !getRFController().isEmpty() || getRFController().getFluid().equals(fluid);
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from)
	{
		return new FluidTankInfo[] { getRFController().getInfo() };
	}

	@Override
	public String toString()
	{
		return this.getClass().getSimpleName() + " { " + this.getWorldLocation().x + ", " + this.getWorldLocation().y + ", " + this.getWorldLocation().z + " } ";
	}
}
