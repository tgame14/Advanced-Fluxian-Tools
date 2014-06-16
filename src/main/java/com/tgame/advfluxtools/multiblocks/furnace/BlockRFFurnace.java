package com.tgame.advfluxtools.multiblocks.furnace;

import com.tgame.advfluxtools.AFTCreativeTab;
import com.tgame.advfluxtools.Settings;
import com.tgame.mods.libs.registry.BlockData;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/**
 * @author tgame14
 * @since 10/06/14
 */
@BlockData(tileClass = { TileRFFurnaceMultiblock.class, TileRFFurnaceMultiblock.TileRFFurnaceCasing.class })
public class BlockRFFurnace extends BlockContainer
{
	private boolean isCasing;

	protected IIcon active;

	public BlockRFFurnace(String name, boolean isCasing)
	{
		super(Material.iron);
		this.setHardness(2.0F);
		this.setCreativeTab(AFTCreativeTab.INSTANCE);
		this.setBlockName(name);
		this.isCasing = isCasing;

	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegistry)
	{
		if (isCasing)
		{
			this.blockIcon = iconRegistry.registerIcon(Settings.RESOURCE_LOCATION + "frame_incomplete");
			this.active = iconRegistry.registerIcon(Settings.RESOURCE_LOCATION + "frame_complete");
		}
		else
		{
			this.blockIcon = iconRegistry.registerIcon(Settings.RESOURCE_LOCATION + "redstonefurnace_deactive");
			this.active = iconRegistry.registerIcon(Settings.RESOURCE_LOCATION + "redstonefurnace_active");
		}
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		return meta == 0 ? this.blockIcon : this.active;
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2)
	{
		return isCasing ? new TileRFFurnaceMultiblock.TileRFFurnaceCasing() : new TileRFFurnaceMultiblock();
	}
}
