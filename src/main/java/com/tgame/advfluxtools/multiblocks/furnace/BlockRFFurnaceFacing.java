package com.tgame.advfluxtools.multiblocks.furnace;

import com.tgame.advfluxtools.AFTCreativeTab;
import com.tgame.advfluxtools.AdvancedFluxTools;
import com.tgame.advfluxtools.Settings;
import com.tgame.advfluxtools.libs.erogenousbeef.multiblock.BlockMultiblockBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

/**
 * @author tgame14
 * @since 01/05/14
 */
public class BlockRFFurnaceFacing extends BlockMultiblockBase
{

	protected Icon iconActive;
	protected Icon iconTop;

	public BlockRFFurnaceFacing(int id)
	{
		super(id, Material.piston);
		this.setCreativeTab(AFTCreativeTab.INSTANCE);
		this.setUnlocalizedName(this.getClass().getSimpleName());
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);
		return meta == 0 ? 0 : 15;
	}

	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new TileRFFurnace();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		if (world.isRemote && player.getHeldItem() != null && player.getHeldItem().isItemEqual(AdvancedFluxTools.itemCresentHammer))
		{
			TileRFFurnace tile = (TileRFFurnace) world.getBlockTileEntity(x, y, z);
			if (tile.getMultiblockController().getLastValidationException() != null)
			{
				player.addChatMessage(tile.getMultiblockController().getLastValidationException().getMessage());
			}
			else
			{
				player.addChatMessage("All is Good: Multiblock formed");
				player.addChatMessage(EnumChatFormatting.BOLD + "CLIENT");
				player.addChatMessage(String.valueOf(tile.getMultiblockController().getConnectedParts()));
			}
		}
		else
		{
			player.addChatMessage(EnumChatFormatting.BOLD + "SERVER");
			TileRFFurnace tile = (TileRFFurnace) world.getBlockTileEntity(x, y, z);
			player.addChatMessage(String.valueOf(tile.getMultiblockController().getConnectedParts()));

		}

		return super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);
	}

	@Override
	public void registerIcons(IconRegister register)
	{
		this.blockIcon = register.registerIcon(Settings.RESOURCE_LOCATION + "redstonefurnace_deactive");
		this.iconActive = register.registerIcon(Settings.RESOURCE_LOCATION + "redstonefurnace_active");
	}

	@Override
	public Icon getIcon(int side, int meta)
	{
		return meta == 0 ? this.blockIcon : this.iconActive;
	}
}
