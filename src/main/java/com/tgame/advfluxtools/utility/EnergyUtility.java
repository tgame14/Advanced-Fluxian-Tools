package com.tgame.advfluxtools.utility;

import cofh.api.energy.IEnergyContainerItem;
import cofh.api.energy.IEnergyHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @since 11/05/14
 * @author tgame14
 */
public class EnergyUtility
{
    public final int RF_PER_MJ = 10; // Official Ratio of RF to MJ (BuildCraft)
    public final int RF_PER_EU = 4; // Official Ratio of RF to EU (IndustrialCraft)

    private EnergyUtility ()
    {

    }

    /* IEnergyContainer Interaction */
    public static int extractEnergyFromHeldContainer (EntityPlayer player, int maxExtract, boolean simulate)
    {

        ItemStack container = player.getCurrentEquippedItem();

        return isEnergyContainerItem(container) ? ((IEnergyContainerItem) container.getItem()).extractEnergy(container, maxExtract, simulate) : 0;
    }

    public static int insertEnergyIntoHeldContainer (EntityPlayer player, int maxReceive, boolean simulate)
    {

        ItemStack container = player.getCurrentEquippedItem();

        return isEnergyContainerItem(container) ? ((IEnergyContainerItem) container.getItem()).receiveEnergy(container, maxReceive, simulate) : 0;
    }

    public static boolean isPlayerHoldingEnergyContainerItem (EntityPlayer player)
    {

        return isEnergyContainerItem(player.getCurrentEquippedItem());
    }

    public static boolean isEnergyContainerItem (ItemStack container)
    {

        return container != null && container.getItem() instanceof IEnergyContainerItem;
    }

    public static ItemStack setDefaultEnergyTag (ItemStack container, int energy)
    {

        container.setTagCompound(new NBTTagCompound());
        container.stackTagCompound.setInteger("Energy", energy);

        return container;
    }

    public static boolean isEnergyHandlerFromSide (TileEntity tile, ForgeDirection from)
    {

        return tile instanceof IEnergyHandler ? ((IEnergyHandler) tile).canConnectEnergy(from) : false;
    }

}
