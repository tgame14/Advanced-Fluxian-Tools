package com.tgame.advfluxtools.multiblocks.furnace;

import com.tgame.advfluxtools.AFTCreativeTab;
import com.tgame.advfluxtools.libs.erogenousbeef.multiblock.BlockMultiblockBase;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author tgame14
 * @since 01/05/14
 */
public class BlockRFFurnaceCasing extends BlockMultiblockBase
{
	public BlockRFFurnaceCasing(int id)
	{
		super(id, Material.piston);
		this.setCreativeTab(AFTCreativeTab.INSTANCE);
		this.setUnlocalizedName(this.getClass().getSimpleName());
	}

	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new TileRFFurnace();
	}
}
