package com.tgame.advfluxtools.multiblocks;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyStorage;
import com.tgame.advfluxtools.Settings;
import com.tgame.advfluxtools.libs.containers.ExternalInventory;
import com.tgame.advfluxtools.libs.containers.IExternalInventory;
import com.tgame.advfluxtools.libs.erogenousbeef.multiblock.IMultiblockPart;
import com.tgame.advfluxtools.libs.erogenousbeef.multiblock.MultiblockControllerBase;
import com.tgame.advfluxtools.libs.erogenousbeef.multiblock.MultiblockValidationException;
import com.tgame.advfluxtools.libs.erogenousbeef.multiblock.rectangular.RectangularMultiblockControllerBase;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fluids.IFluidBlock;

/**
 * @author tgame14
 * @since 01/05/14
 */
public abstract class RFMultiblockController extends RectangularMultiblockControllerBase implements IEnergyStorage, IExternalInventory
{
	/** default external energy storage object */
	protected EnergyStorage energy;

	protected RFMultiblockController(World world, TileEntity tile)
	{
		super(world);
		//TODO: Handle per block in creation and addition
		this.energy = new EnergyStorage(16000, 80);
		this.inv = new ExternalInventory(tile, 24 * 2 + 4 * 9);
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

	/// * * * IEXTERNALINVENTORY * * * ///

	protected ExternalInventory inv;

	@Override
	public int getSizeInventory()
	{
		return this.inv.getSizeInventory();
	}

	@Override
	public ItemStack getStackInSlot(int i)
	{
		return this.inv.getStackInSlot(i);
	}

	@Override
	public ItemStack[] getItems()
	{
		return this.inv.getItems();
	}

	@Override
	public ItemStack decrStackSize(int i, int j)
	{
		return this.inv.decrStackSize(i, j);
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i)
	{
		return this.inv.getStackInSlotOnClosing(i);
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack)
	{
		this.inv.setInventorySlotContents(i, itemstack);
	}

	@Override
	public String getInvName()
	{
		return this.inv.getInvName();
	}

	@Override
	public boolean isInvNameLocalized()
	{
		return this.inv.isInvNameLocalized();
	}

	@Override
	public int getInventoryStackLimit()
	{
		return this.inv.getInventoryStackLimit();
	}

	@Override
	public void onInventoryChanged()
	{
		this.inv.onInventoryChanged();
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer)
	{
		return this.inv.isUseableByPlayer(entityplayer);
	}

	@Override
	public void openChest()
	{
		this.inv.openChest();
	}

	@Override
	public void closeChest()
	{
		this.inv.closeChest();
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack)
	{
		return this.inv.isItemValidForSlot(i, itemstack);
	}
}
