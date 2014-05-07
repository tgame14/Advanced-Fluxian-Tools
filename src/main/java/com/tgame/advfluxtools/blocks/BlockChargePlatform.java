package com.tgame.advfluxtools.blocks;

import cofh.api.energy.IEnergyContainerItem;
import com.tgame.advfluxtools.AFTCreativeTab;
import com.tgame.advfluxtools.Settings;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tgame14
 * @since 30/04/14
 */
public class BlockChargePlatform extends BlockContainer
{
	protected Icon cellRedstone;
	protected Icon cellHardened;
	protected Icon cellLeadstone;

	public BlockChargePlatform(int id)
	{
		super(id, Material.iron);

		this.setCreativeTab(AFTCreativeTab.INSTANCE);
		this.setUnlocalizedName(this.getClass().getSimpleName());
		this.setHardness(3.0F);
		this.setResistance(5.0F);


	}

	@Override
	public int damageDropped(int metadata)
	{
		return metadata;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
		this.setBlockBounds(0F, 0F, 0F, 1F, 0.2F, 1F);
		super.setBlockBoundsBasedOnState(par1IBlockAccess, par2, par3, par4);
	}

	@Override
	public void setBlockBoundsForItemRender()
	{
		this.setBlockBounds(0F, 0F, 0F, 1F, 0.2F, 1F);
		super.setBlockBoundsForItemRender();
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean isBlockSolid(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
	{
		return false;
	}



	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		int chargeSpeed = 0;
		switch (world.getBlockMetadata(x, y, z))
		{
			case 0:
				chargeSpeed = 80;
				break;
			case 1:
				chargeSpeed = 400;
				break;
			case 2:
				chargeSpeed = 10000;
				break;
			default:
				break;
		}

		if (entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) entity;
			IInventory inv = player.inventory;
			TileChargePlatform tile = (TileChargePlatform) world.getBlockTileEntity(x, y, z);
			ArrayList<ItemStack> chargableItems = new ArrayList<ItemStack>();

			for (int i = 0; i < inv.getSizeInventory(); i++)
			{
				ItemStack stack = inv.getStackInSlot(i);
				if (stack != null && stack.getItem() instanceof IEnergyContainerItem)
				{
					chargableItems.add(stack);
				}

			}
//			System.out.println("PRE chargeSpeed = " + chargeSpeed);
			chargeSpeed /= chargableItems.size();
//			System.out.println("chargableItems = " + chargableItems);
//			System.out.println("chargeSpeed = " + chargeSpeed);

			for (ItemStack stack : chargableItems)
			{
				IEnergyContainerItem energyItem = (IEnergyContainerItem) stack.getItem();
				energyItem.receiveEnergy(stack, tile.extractEnergy(ForgeDirection.UP, chargeSpeed / chargableItems.size(), false), false);
			}
		}

	}

	@Override
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		for (int i = 0; i < 3; i++)
		{
			par3List.add(new ItemStack(this, 1, i));
		}

	}

	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new TileChargePlatform();
	}

	@Override
	public void registerIcons(IconRegister register)
	{
		this.cellLeadstone = register.registerIcon(Settings.RESOURCE_LOCATION + "CellLeadstone");
		this.cellHardened = register.registerIcon(Settings.RESOURCE_LOCATION + "CellHardened");
		this.cellRedstone = register.registerIcon(Settings.RESOURCE_LOCATION + "CellRedstone");
	}

	@Override
	public Icon getIcon(int side, int meta)
	{
		switch (meta)
		{
			case 0:
				return this.cellLeadstone;
			case 1:
				return this.cellHardened;
			case 2:
				return this.cellRedstone;
			default:
				break;
		}
		return super.getIcon(side, meta);
	}

}
