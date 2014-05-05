package com.tgame.advfluxtools.libs.containers;

import net.minecraft.tileentity.TileEntity;

/**
 * @author tgame14
 * @since 05/05/14
 */
public abstract class TileContainer extends TileEntity
{
	protected IExternalInventory inv;
	protected int slotCount;

	public IExternalInventory getInv()
	{
		if (this.inv == null)
		{
			this.inv = new ExternalInventory(this.slotCount);
		}
		return this.inv;
	}


}
