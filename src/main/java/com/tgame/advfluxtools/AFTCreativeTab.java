package com.tgame.advfluxtools;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

/**
 * @author tgame14
 * @since 29/04/14
 */
public class AFTCreativeTab extends CreativeTabs
{

	public static final AFTCreativeTab INSTANCE = new AFTCreativeTab();

	public static ItemStack itemStack;

	public AFTCreativeTab()
	{
		super(CreativeTabs.getNextID(), "AFT");
	}

	@Override
	public ItemStack getIconItemStack()
	{
		if (itemStack == null)
		{
			itemStack = new ItemStack(AdvancedFluxTools.itemLaserDrill, 1, 100);
		}

		return itemStack;
	}
}
