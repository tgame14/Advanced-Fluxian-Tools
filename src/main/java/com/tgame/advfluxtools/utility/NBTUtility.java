package com.tgame.advfluxtools.utility;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author tgame14
 * @since 30/04/14
 */
public class NBTUtility
{
	public static NBTTagCompound getOrCreateNBTTag(ItemStack itemstack)
	{
		if (itemstack.hasTagCompound())
		{
			return itemstack.getTagCompound();
		}
		NBTTagCompound tag = new NBTTagCompound();
		itemstack.setTagCompound(tag);
		return tag;
	}
}
