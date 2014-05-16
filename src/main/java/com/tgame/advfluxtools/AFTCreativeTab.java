package com.tgame.advfluxtools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * @author tgame14
 * @since 29/04/14
 */
public class AFTCreativeTab extends CreativeTabs
{

	public static final AFTCreativeTab INSTANCE = new AFTCreativeTab();

	private AFTCreativeTab()
	{
		super(CreativeTabs.getNextID(), "AFT");
	}

    @Override
    public Item getTabIconItem ()
    {
        return AdvancedFluxTools.itemLaserDrill.getItem();
    }
}
