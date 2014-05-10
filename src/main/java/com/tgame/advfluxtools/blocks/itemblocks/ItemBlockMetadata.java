package com.tgame.advfluxtools.blocks.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

/**
 * @author tgame14
 * @since 30/04/14
 */
public class ItemBlockMetadata extends ItemBlock
{
	public ItemBlockMetadata(Block block)
	{
		super(block);

		this.setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int damage)
	{
		return damage;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack)
	{
		String localized = StatCollector.translateToLocal(getUnlocalizedName() + "." + itemstack.getItemDamage() + ".name");
		if (localized != null && !localized.isEmpty())
			return getUnlocalizedName() + "." + itemstack.getItemDamage();
		return getUnlocalizedName();
	}
}
