package com.tgame.advfluxtools.items;

import cofh.api.energy.IEnergyContainerItem;
import com.tgame.advfluxtools.AFTCreativeTab;
import com.tgame.advfluxtools.Settings;
import com.tgame.advfluxtools.entities.EntityLaserProjectile;
import com.tgame.advfluxtools.utility.EnergyUtility;
import com.tgame.mods.libs.utility.NBTUtility;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

import java.util.List;

/**
 * @author tgame14
 * @since 29/04/14
 */
public class ItemLaserDrill extends Item implements IEnergyContainerItem
{
    protected int capacity = 400000;
    protected int maxTransfer = 2000;


    public ItemLaserDrill ()
    {
        super();

        this.setCreativeTab(AFTCreativeTab.INSTANCE);
        this.setUnlocalizedName(this.getClass().getSimpleName());
        this.setTextureName(Settings.RESOURCE_LOCATION + this.getClass().getSimpleName());

        this.setMaxDamage(100);
        this.setMaxStackSize(1);
        this.setNoRepair();
        canRepair = false;
    }

    /* IEnergyContainerItem */
    @Override
    public int receiveEnergy (ItemStack container, int maxReceive, boolean simulate)
    {
        if (container.stackTagCompound == null)
        {
            EnergyUtility.setDefaultEnergyTag(container, 0);
        }
        int stored = container.stackTagCompound.getInteger("Energy");
        int receive = Math.min(maxReceive, Math.min(capacity - stored, maxTransfer));

        if (!simulate)
        {
            stored += receive;
            container.stackTagCompound.setInteger("Energy", stored);
            container.setItemDamage(1 + (getMaxEnergyStored(container) - stored) * (container.getMaxDamage() - 2) / getMaxEnergyStored(container));
        }

        return receive;
    }

    @Override
    public int extractEnergy (ItemStack container, int maxExtract, boolean simulate)
    {
        if (container.stackTagCompound == null)
        {
            EnergyUtility.setDefaultEnergyTag(container, 0);
        }
        int stored = container.stackTagCompound.getInteger("Energy");
        int extract = Math.min(maxExtract, stored);

        if (!simulate)
        {
            stored -= extract;
            container.stackTagCompound.setInteger("Energy", stored);
            container.setItemDamage(1 + (getMaxEnergyStored(container) - stored) * (container.getMaxDamage() - 1) / getMaxEnergyStored(container));
            //TODO: enable RSA Support
            //            if (stored == 0)
            //            {
            //                setEmpoweredState(container, false);
            //            }
        }
        return extract;
    }

    @Override
    public int getDisplayDamage (ItemStack stack)
    {
        if (!stack.hasTagCompound())
        {
            EnergyUtility.setDefaultEnergyTag(stack, 0);
        }
        return 1 + capacity - stack.getTagCompound().getInteger("Energy");
    }

    @Override
    public int getEnergyStored (ItemStack container)
    {
        if (!container.hasTagCompound())
        {
            EnergyUtility.setDefaultEnergyTag(container, 0);
        }
        return container.getTagCompound().getInteger("Energy");
    }

    @Override
    public int getMaxEnergyStored (ItemStack container)
    {
        return capacity;
    }

    /* Tags and information about the tool */
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation (ItemStack stack, EntityPlayer player, List list, boolean par4)
    {
        if (!stack.hasTagCompound())
        {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setInteger("Energy", 0);
            stack.setTagCompound(tag);
        }

        NBTTagCompound tags = stack.getTagCompound();
        if (tags.hasKey("Energy"))
        {
            String color = "";
            int RF = tags.getInteger("Energy");

            if (RF != 0)
            {
                if (RF <= this.getMaxEnergyStored(stack) / 3)
                    color = "\u00a74";
                else if (RF > this.getMaxEnergyStored(stack) * 2 / 3)
                    color = "\u00a72";
                else
                    color = "\u00a76";
            }

            String energy = new StringBuilder().append(color).append(tags.getInteger("Energy")).append("/").append(getMaxEnergyStored(stack)).append(" RF").toString();
            list.add(energy);

        }
        super.addInformation(stack, player, list, par4);
    }

    @Override
    public ItemStack onItemRightClick (ItemStack itemstack, World world, EntityPlayer player)
    {
        NBTTagCompound tag = NBTUtility.getOrCreateNBTTag(itemstack);

        if (!tag.hasKey("mode"))
        {
            tag.setInteger("mode", 0);
        }
        EnumLaserMode enumLaser = EnumLaserMode.values()[tag.getInteger("mode")];

        if (player.isSneaking())
        {
            EnumLaserMode[] test = EnumLaserMode.values();
            if (tag.getInteger("mode") < EnumLaserMode.values().length - 1)
            {
                tag.setInteger("mode", tag.getInteger("mode") + 1);
            }
            else
            {
                tag.setInteger("mode", 0);
            }
            enumLaser = EnumLaserMode.values()[tag.getInteger("mode")];

            itemstack.setTagCompound(tag);
            if (world.isRemote)
            {
                player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("info.laser.mode").replaceAll("%m", enumLaser.name())));
            }
            return itemstack;
        }

        if (this.getEnergyStored(itemstack) > enumLaser.powerusage)
        {
            this.shootLaserDrill(world, player, itemstack, 600);
            this.extractEnergy(itemstack, enumLaser.powerusage, false);
        }

        tag.setInteger("mode", enumLaser.ordinal());
        itemstack.setTagCompound(tag);
        return super.onItemRightClick(itemstack, world, player);
    }


    public void shootLaserDrill (World world, EntityLivingBase entity, ItemStack stack, int lifetime)
    {
        EntityLaserProjectile laserProjectile = new EntityLaserProjectile(world, entity, EnumLaserMode.values()[stack.getTagCompound().getInteger("mode")], lifetime, EnumLaserMode.values()[stack.getTagCompound().getInteger("mode")].speed);
        world.spawnEntityInWorld(laserProjectile);
    }


}
