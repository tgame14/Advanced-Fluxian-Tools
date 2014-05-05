package com.tgame.advfluxtools.libs.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

/**
 * @author tgame14
 * @since 05/05/14
 */
public class ExternalInventory implements IExternalInventory
{
	private ItemStack[] items;
	private int slotCount;
	private TileEntity host;

	public ExternalInventory (TileEntity host, int slots)
	{
		this(slots);
		this.host = host;
	}


	public ExternalInventory (int slots)
	{
		this.items = new ItemStack[slots];
		this.slotCount = slots;
	}

	@Override
	public int getSizeInventory()
	{
		return items.length;
	}

	@Override
	public ItemStack getStackInSlot(int i)
	{
		return items[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j)
	{
		if (items[i] == null)
		{
			return null;
		}
		items[i].stackSize -= j;
		return items[i];
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i)
	{
		return getStackInSlot(i);
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack)
	{
		items[i] = itemstack;
	}

	@Override
	public String getInvName()
	{
		return "aft.container.inv";
	}

	@Override
	public boolean isInvNameLocalized()
	{
		return false;
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public void onInventoryChanged()
	{
		if (this.host != null)
		{
			host.onInventoryChanged();
		}

	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer)
	{
		if (this.host != null)
		{
			return this.host.worldObj.getBlockTileEntity(this.host.xCoord, this.host.yCoord, this.host.zCoord) != this.host ? false : entityplayer.getDistanceSq(this.host.xCoord + 0.5D, this.host.yCoord + 0.5D, this.host.zCoord + 0.5D) <= 64.0D;
		}
		return true;
	}

	@Override
	public void openChest()
	{

	}

	@Override
	public void closeChest()
	{

	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack)
	{
		if (i >= this.getSizeInventory())
		{
			return false;
		}
		return true;
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		NBTTagList nbtList = new NBTTagList();

		for (int i = 0; i < this.getSizeInventory(); ++i)
		{
			if (this.getStackInSlot(i) != null)
			{
				NBTTagCompound var4 = new NBTTagCompound();
				var4.setByte("Slot", (byte) i);
				this.getStackInSlot(i).writeToNBT(var4);
				nbtList.appendTag(var4);
			}
		}

		nbt.setTag("Items", nbtList);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		this.clearInventory();

		NBTTagList nbtList = nbt.getTagList("Items");

		for (int i = 0; i < nbtList.tagCount(); ++i)
		{
			NBTTagCompound stackTag = (NBTTagCompound) nbtList.tagAt(i);
			byte id = stackTag.getByte("Slot");

			if (id >= 0 && id < this.getSizeInventory())
			{
				this.setInventorySlotContents(id, ItemStack.loadItemStackFromNBT(stackTag));
			}
		}

		nbt.setTag("Items", nbtList);
	}

	public void clearInventory()
	{
		this.setItems(null);
		this.getItems();
	}


	@Override
	public ItemStack[] getItems()
	{
		if (this.items == null)
		{
			this.items = new ItemStack[this.slotCount];
		}
		return this.items;
	}

	public void setItems(ItemStack[] items)
	{
		this.items = items;
	}
}
