package com.tgame.advfluxtools.libs.inventory.item;

import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * An extention to support multiple inventory objects per tile entity
 *
 * @author tgame14
 * @since 08/05/14
 */
public interface IInventoryStorage
{
	/**
	 * @return a {@link java.util.List} containing all items in the current inventory
	 */
	List<ItemStack> getItems();

	/**
	 * @param item     the {@link net.minecraft.item.ItemStack} being attempted to insert into this inventory
	 * @param simulate if true, will simulate items being inserted
	 * @return an {@link net.minecraft.item.ItemStack} representing how much will remain after item was inserted (or simulated)
	 */
	ItemStack insertItem(ItemStack item, boolean simulate);

	/**
	 * @param item     ItemStack to be extracted. The size of this stack corresponds to the maximum amount to extract. If this is null, then a null ItemStack should
	 *                 immediately be returned.
	 * @param simulate If TRUE, the extraction will only be simulated.
	 * @return An ItemStack representing how much was extracted (or would have been, if simulated) from the container inventory.
	 */
	ItemStack extractItem(ItemStack item, boolean simulate);
}
