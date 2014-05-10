package com.tgame.advfluxtools.multiblocks;

import cofh.api.energy.IEnergyHandler;
import com.tgame.advfluxtools.libs.erogenousbeef.multiblock.MultiblockControllerBase;
import com.tgame.advfluxtools.libs.erogenousbeef.multiblock.MultiblockValidationException;
import com.tgame.advfluxtools.libs.erogenousbeef.multiblock.rectangular.RectangularMultiblockTileEntityBase;
import com.tgame.advfluxtools.multiblocks.furnace.RFFurnaceController;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * All multiblock tiles that want to be under this System, need to extend this class
 * idea with this Tile, is that it passes all energy calculations and information to the controller
 * And by that this is merely a "ghost" to act as a proxy.
 *
 * @author tgame14
 * @since 01/05/14
 */
public abstract class RFTileMultiblock extends RectangularMultiblockTileEntityBase implements IEnergyHandler//, IInventory
{

	@Override
	public void isGoodForInterior() throws MultiblockValidationException
	{
		throw new MultiblockValidationException("Block cannot be be interior! Place reserved for fluids");
	}

	@Override
	public boolean canInterface(ForgeDirection forgeDirection)
	{
		return getMultiblockController() != null && getMultiblockController().getLastValidationException() == null;
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
	public int getEnergyStored(ForgeDirection forgeDirection)
	{
		return getRFController().getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection forgeDirection)
	{
		return getRFController().getMaxEnergyStored();
	}


	public RFFurnaceController getRFController()
	{
		return (RFFurnaceController) this.getMultiblockController();
	}

	@Override
	public void onMachineBroken()
	{
		super.onMachineBroken();
		onInventoryChanged();
	}

	@Override
	public void onMachineAssembled(MultiblockControllerBase controller)
	{
		super.onMachineAssembled(controller);
		onInventoryChanged();
	}

	@Override
	public String toString()
	{
		return this.getClass().getSimpleName() + " { " + this.getWorldLocation().x + ", " + this.getWorldLocation().y + ", " + this.getWorldLocation().z + " } ";
	}

	/// * * * IINVENTORY * * * ///

//	@Override
//	public int getSizeInventory()
//	{
//		return getRFController().getSizeInventory();
//	}
//
//	@Override
//	public ItemStack getStackInSlot(int i)
//	{
//		return getRFController().getStackInSlot(i);
//	}
//
//	@Override
//	public ItemStack decrStackSize(int i, int j)
//	{
//		return getRFController().decrStackSize(i, j);
//	}
//
//	@Override
//	public ItemStack getStackInSlotOnClosing(int i)
//	{
//		return getRFController().getStackInSlotOnClosing(i);
//	}
//
//	@Override
//	public void setInventorySlotContents(int i, ItemStack itemstack)
//	{
//		getRFController().setInventorySlotContents(i, itemstack);
//	}
//
//	@Override
//	public String getInvName()
//	{
//		return getRFController().getInvName();
//	}
//
//	@Override
//	public boolean isInvNameLocalized()
//	{
//		return getRFController().isInvNameLocalized();
//	}
//
//	@Override
//	public int getInventoryStackLimit()
//	{
//		return getRFController().getInventoryStackLimit();
//	}
//
//	@Override
//	public boolean isUseableByPlayer(EntityPlayer entityplayer)
//	{
//		return getRFController().isUseableByPlayer(entityplayer);
//	}
//
//	@Override
//	public void openChest()
//	{
//		getRFController().openChest();
//	}
//
//	@Override
//	public void closeChest()
//	{
//		getRFController().closeChest();
//	}
//
//	@Override
//	public boolean isItemValidForSlot(int i, ItemStack itemstack)
//	{
//		return getRFController().isItemValidForSlot(i, itemstack);
//	}
}
