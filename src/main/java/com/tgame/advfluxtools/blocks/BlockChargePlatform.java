package com.tgame.advfluxtools.blocks;

import cofh.api.energy.IEnergyContainerItem;
import com.tgame.advfluxtools.AFTCreativeTab;
import com.tgame.advfluxtools.AdvancedFluxTools;
import com.tgame.advfluxtools.Settings;
import com.tgame.advfluxtools.blocks.itemblocks.ItemBlockMetadata;
import com.tgame.mods.libs.registry.BlockData;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tgame14
 * @since 30/04/14
 */
@BlockData(tileClass = { TileChargePlatform.class }, itemBlockClass = ItemBlockMetadata.class)
public class BlockChargePlatform extends BlockContainer
{
    protected IIcon cellRedstone;
    protected IIcon cellHardened;
    protected IIcon cellLeadstone;

    public BlockChargePlatform ()
    {
        super(Material.iron);

        this.setCreativeTab(AFTCreativeTab.INSTANCE);
        this.setBlockName(this.getClass().getSimpleName());
        this.setHardness(3.0F);
        this.setResistance(5.0F);


    }

    @Override
    public int damageDropped (int metadata)
    {
        return metadata;
    }

    @Override
    public void setBlockBoundsBasedOnState (IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        this.setBlockBounds(0F, 0F, 0F, 1F, 0.2F, 1F);
        super.setBlockBoundsBasedOnState(par1IBlockAccess, par2, par3, par4);
    }

    @Override
    public void setBlockBoundsForItemRender ()
    {
        this.setBlockBounds(0F, 0F, 0F, 1F, 0.2F, 1F);
        super.setBlockBoundsForItemRender();
    }

    @Override
    public boolean isOpaqueCube ()
    {
        return false;
    }

    @Override
    public boolean isBlockSolid (IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return false;
    }


    @Override
    public void onEntityCollidedWithBlock (World world, int x, int y, int z, Entity entity)
    {
        int chargeSpeed = 0;
        switch (world.getBlockMetadata(x, y, z) % 3)
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
            TileChargePlatform tile = (TileChargePlatform) world.getTileEntity(x, y, z);
            if (world.getBlockMetadata(x, y, z) < 3)
            {
                ArrayList<ItemStack> chargableItems = new ArrayList<ItemStack>();

                for (int i = 0; i < inv.getSizeInventory(); i++)
                {
                    ItemStack stack = inv.getStackInSlot(i);
                    if (stack != null && stack.getItem() instanceof IEnergyContainerItem)
                    {
                        chargableItems.add(stack);
                    }
                }

                if (chargableItems.size() != 0)
                {
                    chargeSpeed /= chargableItems.size();
                }

                for (ItemStack stack : chargableItems)
                {
                    IEnergyContainerItem energyItem = (IEnergyContainerItem) stack.getItem();
                    if (!(energyItem.getEnergyStored(stack) >= energyItem.getMaxEnergyStored(stack)))
                        energyItem.receiveEnergy(stack, tile.extractEnergy(ForgeDirection.UP, chargeSpeed / chargableItems.size(), false), false);
                }
            }
            else
            {
                for (int i = 0; i < inv.getSizeInventory(); i++)
                {
                    ItemStack stack = inv.getStackInSlot(i);
                    if (stack != null && stack.getItem() instanceof IEnergyContainerItem)
                    {
                        IEnergyContainerItem energyItem = (IEnergyContainerItem) stack.getItem();
                        if (!(energyItem.getEnergyStored(stack) >= energyItem.getMaxEnergyStored(stack)))
                        {
                            energyItem.receiveEnergy(stack, chargeSpeed, false);
                            break;
                        }
                    }
                }
            }
        }

    }

    @Override
    public boolean onBlockActivated (World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        if (!world.isRemote)
        {
            if (player.getHeldItem() != null && player.getHeldItem().isItemEqual(AdvancedFluxTools.wrench))
            {
                if (player.isSneaking())
                {
                    player.addChatMessage(new ChatComponentText("Tile: " + world.getTileEntity(x, y, z)));
                    return super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);
                }

                int meta = world.getBlockMetadata(x, y, z);
                if (meta > 2)
                {
                    world.setBlockMetadataWithNotify(x, y, z, meta - 3, 3);
                    player.addChatMessage(new ChatComponentText("Cover Entire Inventory"));
                }
                else
                {
                    world.setBlockMetadataWithNotify(x, y, z, meta + 3, 3);
                    player.addChatMessage(new ChatComponentText("First item First"));
                }
            }
        }
        return super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);
    }

    @Override
    public void getSubBlocks (Item item, CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int i = 0; i < 3; i++)
        {
            par3List.add(new ItemStack(this, 1, i));
        }

    }

    @Override
    public TileEntity createNewTileEntity (World world, int meta)
    {
        return new TileChargePlatform();
    }

    @Override
    public void registerBlockIcons (IIconRegister register)
    {
        this.cellLeadstone = register.registerIcon(Settings.RESOURCE_LOCATION + "CellLeadstone");
        this.cellHardened = register.registerIcon(Settings.RESOURCE_LOCATION + "CellHardened");
        this.cellRedstone = register.registerIcon(Settings.RESOURCE_LOCATION + "CellRedstone");
    }

    @Override
    public IIcon getIcon (int side, int meta)
    {
        switch (meta % 3)
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
