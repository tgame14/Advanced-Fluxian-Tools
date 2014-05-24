package com.tgame.advfluxtools.nmultiblocks.energy;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyStorage;
import com.tgame.mods.libs.inventory.InventoryStorage;
import com.tgame.mods.libs.multiblocks.simpleimpl.SimpleGridController;
import net.minecraft.world.World;

/**
 * @since 24/05/14
 * @author tgame14
 */
public class RFGridController extends SimpleGridController implements IEnergyStorage
{
    protected EnergyStorage energy;

    public RFGridController (World world)
    {
        super(world);

        this.energy = new EnergyStorage(0);
    }

    @Override
    protected void onMachineAssembled ()
    {
        super.onMachineAssembled();

        this.energy.setCapacity(this.getNumConnectedBlocks() * 2000);
        this.energy.setMaxTransfer(10000);
    }

    @Override
    protected void onMachineDisassembled ()
    {
        super.onMachineDisassembled();
        this.energy.setMaxTransfer(0);
    }

    @Override
    protected int getMinimumNumberOfBlocksForAssembledMachine ()
    {
        return 1;
    }

    @Override
    protected int getMaximumXSize ()
    {
        return Integer.MAX_VALUE;
    }

    @Override
    protected int getMaximumZSize ()
    {
        return Integer.MAX_VALUE;
    }

    @Override
    protected int getMaximumYSize ()
    {
        return Integer.MAX_VALUE;
    }

    @Override
    protected boolean updateServer ()
    {
        return false;
    }

    @Override
    protected void onMachinePaused ()
    {
        super.onMachinePaused();
    }

    @Override
    public int receiveEnergy (int maxReceive, boolean simulate)
    {
        return this.energy.receiveEnergy(maxReceive, simulate);
    }

    @Override
    public int extractEnergy (int maxExtract, boolean simulate)
    {
        return this.energy.extractEnergy(maxExtract, simulate);
    }

    @Override
    public int getEnergyStored ()
    {
        return this.energy.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored ()
    {
        return this.energy.getMaxEnergyStored();
    }
}
