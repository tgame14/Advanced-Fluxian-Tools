package com.tgame.advfluxtools.multiblocks.furnace;

import com.tgame.advfluxtools.multiblocks.energy.RFGridController;
import com.tgame.mods.libs.inventory.IInventoryHandler;
import com.tgame.mods.libs.machinery.Furnace;
import com.tgame.mods.libs.multiblocks.MultiblockValidationException;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;

/**
 * @author tgame14
 * @since 04/06/14
 */
public class RFFurnaceGridController extends RFGridController implements IInventoryHandler
{
	protected Furnace furnace;

	public RFFurnaceGridController(World world)
	{
		super(world);

		this.furnace = new Furnace(0, 1, 1);
	}

	public int calcInternalSpace()
	{
		int maxX = this.getMaximumCoord().x();
		int maxY = this.getMaximumCoord().y();
		int maxZ = this.getMaximumCoord().z();

		int minX = this.getMaximumCoord().x();
		int minY = this.getMaximumCoord().y();
		int minZ = this.getMaximumCoord().z();

		return (maxX - minX - 2) * (maxZ - minZ - 2) * (maxY - minY - 2);

	}

	@Override
	protected boolean updateServer()
	{
		if (this.isAssembled())
		{
			return furnace.updateFurnace();
		}
		return false;
	}

	@Override
	protected void isBlockGoodForInterior(World world, int x, int y, int z) throws MultiblockValidationException
	{
		if (!world.getBlock(x, y, z).isAir(world, x, y, z))
		{
			throw new MultiblockValidationException(StatCollector.translateToLocal("multiblock.empty_interior.info"));
		}
	}

	@Override
	protected void onMachineAssembled()
	{
		super.onMachineAssembled();
		this.furnace.setActive(true);
		this.furnace.setBurnTicksPerItem(5 / this.calcInternalSpace());
	}

	@Override
	protected void onMachineDisassembled()
	{
		super.onMachineDisassembled();
		this.furnace.setActive(false);
	}

	@Override
	protected int getMinimumNumberOfBlocksForAssembledMachine()
	{
		return 26;
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
	public boolean canConnectInventory(ForgeDirection from)
	{
		return this.isAssembled();
	}

	@Override
	public ItemStack insertItem(ForgeDirection from, ItemStack item, boolean simulate)
	{
		return this.furnace.getInputInv().insertItem(item, simulate);
	}

	@Override
	public ItemStack extractItem(ForgeDirection from, ItemStack item, boolean simulate)
	{
		return this.furnace.getOutputInv().extractItem(item, simulate);
	}

	@Override
	public ItemStack extractItem(ForgeDirection from, int maxExtract, boolean simulate)
	{
		return this.furnace.getOutputInv().extractItem(maxExtract, simulate);
	}

	@Override
	public List<ItemStack> getInventoryContents(ForgeDirection from)
	{
		return this.furnace.getOutputInv().getInventoryContents();
	}

	@Override
	public int getSizeInventory(ForgeDirection from)
	{
		return this.furnace.getOutputInv().getSizeInventory();
	}

	@Override
	public boolean isEmpty(ForgeDirection from)
	{
		return this.furnace.getInputInv().isEmpty();
	}

	@Override
	public boolean isFull(ForgeDirection from)
	{
		return this.furnace.getInputInv().isFull();
	}

	@Override
	public void writeToNBT(NBTTagCompound data)
	{
		super.writeToNBT(data);

		NBTTagCompound furnaceTag = new NBTTagCompound();
		this.furnace.writeToNBT(furnaceTag);

		data.setTag("furnaceTag", furnaceTag);
	}

	@Override
	public void readFromNBT(NBTTagCompound data)
	{
		super.readFromNBT(data);

		this.furnace.readFromNBT(data.getCompoundTag("furnaceTag"));
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate)
	{
		if (this.getEnergyStored(ForgeDirection.UNKNOWN) == 0 && !simulate && maxReceive > 0)
		{
			this.furnace.setHasFuel(true);
		}
		return super.receiveEnergy(from, maxReceive, simulate);
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate)
	{

		if (this.getEnergyStored(ForgeDirection.UNKNOWN) > 0 && !simulate && maxExtract >= this.getEnergyStored(ForgeDirection.UNKNOWN) )
		{
			this.furnace.setHasFuel(false);
		}
		return super.extractEnergy(from, maxExtract, simulate);
	}
}
