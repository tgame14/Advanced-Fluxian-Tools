package com.tgame.advfluxtools.libs.containers;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author tgame14
 * @since 05/05/14
 */
public interface IExternalInventory extends IInventory
{
	public ItemStack[] getItems();

	public void writeToNBT(NBTTagCompound nbt);

	public void readFromNBT(NBTTagCompound nbt);

}
