package com.tgame.advfluxtools.multiblocks.furnace;

import com.tgame.advfluxtools.multiblocks.energy.RFGridController;
import com.tgame.mods.config.Config;
import com.tgame.mods.libs.inventory.IInventoryHandler;
import com.tgame.mods.libs.inventory.simpleimpl.InventoryStorage;
import com.tgame.mods.libs.multiblocks.MultiblockValidationException;
import com.tgame.mods.libs.multiblocks.grid.AbstractMultiblockNode;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
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
	@Config
	public static int ENERGY_PER_ITEM_IN_FURNACE = 2000;

	protected int burnTimePerItem;
	protected int currentBurnTime;
	protected int costPerTick;
	protected boolean isBurning;

	protected InventoryStorage input;
	protected InventoryStorage output;

	public RFFurnaceGridController(World world)
	{
		super(world);

		this.burnTimePerItem = 0;
		this.currentBurnTime = 0;

		this.input = new InventoryStorage(0);
		this.output = new InventoryStorage(0);
		this.isBurning = false;

	}

	public int calcInternalSpace()
	{
		int maxX = this.getMaximumCoord().x();
		int maxY = this.getMaximumCoord().y();
		int maxZ = this.getMaximumCoord().z();

		int minX = this.getMinimumCoord().x();
		int minY = this.getMinimumCoord().y();
		int minZ = this.getMinimumCoord().z();

		return (maxX - minX - 1) * (maxZ - minZ - 1) * (maxY - minY - 1);

	}

	@Override
	protected boolean updateServer()
	{
		if (this.isAssembled())
		{
			if (this.getEnergyStored(ForgeDirection.UNKNOWN) >= this.costPerTick)
			{
				if (!isBurning)
				{
					for (AbstractMultiblockNode node : this.connectedParts)
					{
						node.onMachineActivated();
					}
					this.isBurning = true;

				}
				if (this.currentBurnTime > 0)
				{
					if (canSmelt())
					{
						this.extractEnergy(ForgeDirection.UNKNOWN, this.costPerTick, false);
						this.currentBurnTime--;
						return true;
					}

				}
				else
				{
					ItemStack smelted = null;
					for (int i = 0; i < this.input.getSizeInventory(); i++)
					{
						if (this.input.getInventoryContents().get(i) != null)
						{
							smelted = this.input.getInventoryContents().get(i);
						}
					}

					ItemStack out = FurnaceRecipes.smelting().getSmeltingResult(smelted);
					this.input.extractItem(smelted, false);
					this.output.insertItem(out, false);
					return true;
				}

			}
		}
		if (this.isBurning)
		{
			for (AbstractMultiblockNode node : this.connectedParts)
			{
				node.onMachineDeactivated();
			}
			this.isBurning = false;
		}

		return false;
	}

	private boolean canSmelt()
	{
		ItemStack candidate = this.input.getInventoryContents().get(0);
		if (candidate == null)
			return false;

		ItemStack stack = FurnaceRecipes.smelting().getSmeltingResult(candidate);
		if (stack == null)
		{
			return false;
		}
		ItemStack out = this.output.insertItem(stack.copy(), true);
		if (out.stackSize == stack.stackSize)
		{
			return false;
		}
		return true;

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

		int internalSpace = this.calcInternalSpace();

		this.input.setMaxSlots(internalSpace * 2);
		this.output.setMaxSlots(internalSpace * 2);

		this.burnTimePerItem = 175 / internalSpace;
		this.currentBurnTime = burnTimePerItem;
		this.costPerTick = ENERGY_PER_ITEM_IN_FURNACE / this.burnTimePerItem;
	}

	@Override
	protected void onMachineDisassembled()
	{
		super.onMachineDisassembled();
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
		return this.input.insertItem(item, simulate);
	}

	@Override
	public ItemStack extractItem(ForgeDirection from, ItemStack item, boolean simulate)
	{
		return this.output.extractItem(item, simulate);
	}

	@Override
	public ItemStack extractItem(ForgeDirection from, int maxExtract, boolean simulate)
	{
		return this.output.extractItem(maxExtract, simulate);
	}

	@Override
	public List<ItemStack> getInventoryContents(ForgeDirection from)
	{
		return this.output.getInventoryContents();
	}

	@Override
	public int getSizeInventory(ForgeDirection from)
	{
		return this.output.getSizeInventory();
	}

	@Override
	public boolean isEmpty(ForgeDirection from)
	{
		return this.input.isEmpty();
	}

	@Override
	public boolean isFull(ForgeDirection from)
	{
		return this.input.isFull();
	}

	@Override
	public void writeToNBT(NBTTagCompound data)
	{
		System.out.println("isBurning = " + isBurning);
		System.out.println("burnTimePerItem = " + burnTimePerItem);
		System.out.println("currentBurnTime = " + currentBurnTime);
		System.out.println("energy = " + energy);
		super.writeToNBT(data);

		NBTTagCompound inputTag = new NBTTagCompound();
		this.input.writeToNBT(inputTag);

		NBTTagCompound outputTag = new NBTTagCompound();
		this.output.writeToNBT(outputTag);

		data.setTag("inputTag", inputTag);
		data.setTag("outputTag", outputTag);
	}

	@Override
	public void readFromNBT(NBTTagCompound data)
	{
		super.readFromNBT(data);

		this.input.readFromNBT(data.getCompoundTag("inputTag"));
		this.output.readFromNBT(data.getCompoundTag("outputTag"));
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate)
	{
		return super.receiveEnergy(from, maxReceive, simulate);
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate)
	{
		return super.extractEnergy(from, maxExtract, simulate);
	}
}
