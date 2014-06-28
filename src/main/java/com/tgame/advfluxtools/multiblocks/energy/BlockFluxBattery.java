package com.tgame.advfluxtools.multiblocks.energy;

import com.tgame.advfluxtools.AFTCreativeTab;
import com.tgame.advfluxtools.AdvancedFluxTools;
import com.tgame.advfluxtools.Settings;
import com.tgame.mods.libs.multiblocks.debugger.GridControllerGUI;
import com.tgame.mods.libs.multiblocks.grid.AbstractMultiblockNode;
import com.tgame.mods.libs.registry.BlockData;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author tgame14
 * @since 03/06/14
 */
@BlockData(tileClass = { TileEnergyMultiblock.class } )
public class BlockFluxBattery extends BlockContainer
{
	protected IIcon activeIcon;

	public BlockFluxBattery()
	{
		super(Material.iron);
		this.setCreativeTab(AFTCreativeTab.INSTANCE);
		this.setBlockName(getClass().getSimpleName());
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return new TileEnergyMultiblock();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityplayer, int side, float hitX, float hitY, float hitZ)
	{
		if (!world.isRemote && entityplayer.getHeldItem() != null && entityplayer.getHeldItem().getItem() == AdvancedFluxTools.multimeter.getItem())
		{
			String color = "";
			TileEnergyMultiblock tile = (TileEnergyMultiblock) world.getTileEntity(x, y, z);
			int RF = tile.getEnergyStored(ForgeDirection.UNKNOWN);

			if (RF <= tile.getMaxEnergyStored(ForgeDirection.UNKNOWN) / 3)
				color = "\u00a74";
			else if (RF > tile.getMaxEnergyStored(ForgeDirection.UNKNOWN) * 2 / 3)
				color = "\u00a72";
			else
				color = "\u00a76";

			String energy = new StringBuilder().append(StatCollector.translateToLocal("info.energy.level")).append(color).append(EnumChatFormatting.BOLD.toString()).append(RF).append("/").append(tile.getMaxEnergyStored(ForgeDirection.UNKNOWN)).append(" RF").toString();
			entityplayer.addChatMessage(new ChatComponentText(energy));
			return true;
		}

		if (world.isRemote && entityplayer.getHeldItem() != null && entityplayer.getHeldItem().getItem() == Item.getItemFromBlock( Blocks.bedrock))
		{
			AbstractMultiblockNode node = (AbstractMultiblockNode) world.getTileEntity(x, y, z);

			GridControllerGUI.invoke(node.getMultiblockController());
			return true;
		}
		return false;
	}

	@Override
	public void registerBlockIcons(IIconRegister register)
	{
		this.blockIcon = register.registerIcon(Settings.RESOURCE_LOCATION + "flux_battery");
		this.activeIcon = register.registerIcon(Settings.RESOURCE_LOCATION + "flux_battery_active");
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		return meta == 0 ? this.blockIcon : this.activeIcon;
	}

}
