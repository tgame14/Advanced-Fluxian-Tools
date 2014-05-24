package com.tgame.advfluxtools.nmultiblocks.tank;

import com.tgame.mods.libs.multiblocks.MultiblockValidationException;
import com.tgame.mods.libs.multiblocks.WorldPos;
import com.tgame.mods.libs.multiblocks.grid.AbstractMultiblockNode;
import com.tgame.mods.libs.multiblocks.grid.GridController;
import com.tgame.mods.libs.multiblocks.simpleimpl.TileSimpleNode;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.*;

import java.util.Set;

/**
 * @since 24/05/14
 * @author tgame14
 */
public class TileFluidMultiblock extends TileSimpleNode implements IFluidHandler
{
    @Override
    public GridController createNewMultiblock ()
    {
        return new FluidGridController(worldObj);
    }

    public IFluidTank getInternalTank ()
    {
        return (IFluidTank) this.getMultiblockController();
    }

    @Override
    public Class<? extends GridController> getMultiblockControllerType ()
    {
        return FluidGridController.class;
    }

    @Override
    public void onMachineActivated ()
    {

    }

    @Override
    public void onMachineDeactivated ()
    {

    }

    @Override
    public void isGoodForFrame () throws MultiblockValidationException
    {

    }

    @Override
    public void isGoodForSides () throws MultiblockValidationException
    {

    }

    @Override
    public void isGoodForTop () throws MultiblockValidationException
    {

    }

    @Override
    public void isGoodForBottom () throws MultiblockValidationException
    {

    }

    @Override
    public void isGoodForInterior () throws MultiblockValidationException
    {
        throw new MultiblockValidationException("Tank must be empty internally!");
    }

    /// * * * IFLUIDHANDLER * * * ///

    @Override
    public int fill (ForgeDirection from, FluidStack resource, boolean doFill)
    {
        return this.getInternalTank().fill(resource, doFill);
    }

    @Override
    public FluidStack drain (ForgeDirection from, FluidStack resource, boolean doDrain)
    {
        if (resource == null || !resource.isFluidEqual(this.getInternalTank().getFluid()))
        {
            return null;
        }

        return this.getInternalTank().drain(resource.amount, doDrain);
    }

    @Override
    public FluidStack drain (ForgeDirection from, int maxDrain, boolean doDrain)
    {
        return this.getInternalTank().drain(maxDrain, doDrain);
    }

    @Override
    public boolean canFill (ForgeDirection from, Fluid fluid)
    {
        return true;
    }

    @Override
    public boolean canDrain (ForgeDirection from, Fluid fluid)
    {
        return true;
    }

    @Override
    public FluidTankInfo[] getTankInfo (ForgeDirection from)
    {
        return new FluidTankInfo[] { this.getInternalTank().getInfo() };
    }
}
