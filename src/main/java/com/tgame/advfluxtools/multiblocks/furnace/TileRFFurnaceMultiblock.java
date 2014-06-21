package com.tgame.advfluxtools.multiblocks.furnace;

import com.tgame.advfluxtools.multiblocks.energy.TileEnergyMultiblock;
import com.tgame.mods.libs.inventory.IInventoryHandler;
import com.tgame.mods.libs.multiblocks.MultiblockValidationException;
import com.tgame.mods.libs.multiblocks.grid.GridController;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;

/**
 * @author tgame14
 * @since 10/06/14
 */
public class TileRFFurnaceMultiblock extends TileEnergyMultiblock implements IInventoryHandler, IInventory
{
	@Override
	public void isGoodForFrame() throws MultiblockValidationException
	{
		throw new MultiblockValidationException("Must be on faces of multiblock, frame requires a casing");
	}

	@Override
	public Class<? extends GridController> getMultiblockControllerType()
	{
		return RFFurnaceGridController.class;
	}

	@Override
	public GridController createNewMultiblock()
	{
		return new RFFurnaceGridController(this.getWorldObj());
	}

	@Override
	public void onMachineAssembled(GridController controller)
	{
		super.onMachineAssembled(controller);
		this.getWorldObj().setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 1, 3);
	}

	@Override
	public void onMachineBroken()
	{
		super.onMachineBroken();
		this.getWorldObj().setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 3);
	}

	public IInventoryHandler getInventoryHandler()
	{
		return (IInventoryHandler) this.getMultiblockController();
	}

	@Override
	public boolean canConnectInventory(ForgeDirection from)
	{
		return this.getInventoryHandler().canConnectInventory(from);
	}

	@Override
	public ItemStack insertItem(ForgeDirection from, ItemStack item, boolean simulate)
	{
		return this.getInventoryHandler().insertItem(from, item, simulate);
	}

	@Override
	public ItemStack extractItem(ForgeDirection from, ItemStack item, boolean simulate)
	{
		return this.getInventoryHandler().extractItem(from, item, simulate);
	}

	@Override
	public ItemStack extractItem(ForgeDirection from, int maxExtract, boolean simulate)
	{
		return this.getInventoryHandler().extractItem(from, maxExtract, simulate);
	}

	@Override
	public List<ItemStack> getInventoryContents(ForgeDirection from)
	{
		return this.getInventoryHandler().getInventoryContents(from);
	}

	@Override
	public int getSizeInventory(ForgeDirection from)
	{
		return this.getInventoryHandler().getSizeInventory(from);
	}

	@Override
	public boolean isEmpty(ForgeDirection from)
	{
		return this.getInventoryHandler().isEmpty(from);
	}

	@Override
	public boolean isFull(ForgeDirection from)
	{
		return this.getInventoryHandler().isFull(from);
	}

	@Override
	public int getSizeInventory()
	{
		return this.getSizeInventory(ForgeDirection.UNKNOWN);
	}

	@Override
	public ItemStack getStackInSlot(int i)
	{
		return this.getInventoryContents(ForgeDirection.UNKNOWN).get(i);
	}

	@Override
	public ItemStack decrStackSize(int var1, int var2)
	{
		ItemStack candidate = this.getStackInSlot(var1);
		return this.extractItem(ForgeDirection.UNKNOWN, candidate, false);

	}

	@Override
	public ItemStack getStackInSlotOnClosing(int var1)
	{
		return this.getStackInSlot(var1);
	}

	@Override
	public void setInventorySlotContents(int var1, ItemStack var2)
	{
		this.insertItem(ForgeDirection.UNKNOWN, var2, false);
	}

	@Override
	public String getInventoryName()
	{
		return "container.rffurnace";
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return false;
	}

	@Override
	public int getInventoryStackLimit()
	{
		return Integer.MAX_VALUE;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer var1)
	{
		return false;
	}

	@Override
	public void openInventory()
	{

	}

	@Override
	public void closeInventory()
	{

	}

	@Override
	public boolean isItemValidForSlot(int var1, ItemStack var2)
	{
		return this.isFull(ForgeDirection.UNKNOWN);
	}

	public static class TileRFFurnaceCasing extends TileRFFurnaceMultiblock
	{
		@Override
		public void isGoodForFrame() throws MultiblockValidationException
		{

		}

		@Override
		public void isGoodForTop() throws MultiblockValidationException
		{
			throw new MultiblockValidationException("Casings are only applicable to frames");
		}

		@Override
		public void isGoodForSides() throws MultiblockValidationException
		{
			throw new MultiblockValidationException("Casings are only applicable to frames");
		}

		@Override
		public void isGoodForBottom() throws MultiblockValidationException
		{
			throw new MultiblockValidationException("Casings are only applicable to frames");
		}
	}
}
