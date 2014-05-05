package com.tgame.advfluxtools.multiblocks.furnace;

import com.tgame.advfluxtools.AFTCreativeTab;
import com.tgame.advfluxtools.Settings;
import com.tgame.advfluxtools.libs.erogenousbeef.multiblock.BlockMultiblockBase;
import com.tgame.advfluxtools.multiblocks.furnace.TileRFMultiblockFrame;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

/**
 * @author tgame14
 * @since 03/05/14
 */
public class BlockRFMultiblockFrame extends BlockMultiblockBase
{
	protected Icon activeIcon;

	public BlockRFMultiblockFrame(int id)
	{
		super(id, Material.iron);

		this.setCreativeTab(AFTCreativeTab.INSTANCE);
		this.setUnlocalizedName(this.getClass().getSimpleName());
	}

	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new TileRFMultiblockFrame();
	}

	@Override
	public void registerIcons(IconRegister register)
	{
		this.blockIcon = register.registerIcon(Settings.RESOURCE_LOCATION + "frame_incomplete");
		this.activeIcon = register.registerIcon(Settings.RESOURCE_LOCATION + "frame_complete");
	}

	@Override
	public Icon getIcon(int side, int meta)
	{
		return meta == 0 ? this.blockIcon : this.activeIcon;
	}
}
