package com.tgame.advfluxtools.multiblocks.furnace;

import com.tgame.advfluxtools.AFTCreativeTab;
import com.tgame.advfluxtools.Settings;
import com.tgame.advfluxtools.libs.erogenousbeef.multiblock.BlockMultiblockBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

/**
 * @author tgame14
 * @since 01/05/14
 */
public class BlockRFFurnaceCasing extends BlockMultiblockBase
{

	protected Icon iconActive;
	protected Icon iconTop;

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

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		if (world.isRemote)
		{
			TileRFFurnace tile = (TileRFFurnace) world.getBlockTileEntity(x, y, z);
			if (tile.getMultiblockController().getLastValidationException() != null)
			{
				player.addChatMessage(tile.getMultiblockController().getLastValidationException().toString());
			}
			else
			{
				player.addChatMessage("All is Good: Multiblock formed");
			}
		}

		return super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);
	}

	@Override
	public void registerIcons(IconRegister register)
	{
		this.blockIcon = register.registerIcon(Settings.RESOURCE_LOCATION + "Machine_Side");
		this.iconActive = register.registerIcon(Settings.RESOURCE_LOCATION + "BlockCasingActive");
		this.iconTop = register.registerIcon(Settings.RESOURCE_LOCATION + "Machine_Top");
	}

	@Override
	public Icon getIcon(int side, int meta)
	{
		switch (ForgeDirection.getOrientation(side))
		{
			case DOWN:
				if (meta == 0)
					return this.iconTop;
				return this.iconActive;
			case UP:
				if (meta == 0)
					return this.iconTop;
				return this.iconActive;
			default:
				if (meta != 0)
					return this.iconActive;
				return this.blockIcon;

		}
	}
}
