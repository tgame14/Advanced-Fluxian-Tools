package com.tgame.advfluxtools.nmultiblocks.tank;

import com.tgame.mods.libs.multiblocks.simpleimpl.SimpleGridController;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;

/**
 * @since 24/05/14
 * @author tgame14
 */
public class FluidGridController extends SimpleGridController implements IFluidTank
{
    protected FluidTank tank = new FluidTank(0);

    public FluidGridController (World world)
    {
        super(world);
    }

    @Override
    public FluidStack getFluid ()
    {
        return this.tank.getFluid();
    }

    @Override
    public int getFluidAmount ()
    {
        return this.tank.getFluidAmount();
    }

    @Override
    public int getCapacity ()
    {
        return this.tank.getFluidAmount();
    }

    @Override
    public FluidTankInfo getInfo ()
    {
        return null;
    }

    @Override
    public int fill (FluidStack resource, boolean doFill)
    {
        return this.tank.fill(resource, doFill);
    }

    @Override
    public FluidStack drain (int maxDrain, boolean doDrain)
    {
        return this.tank.drain(maxDrain, doDrain);
    }
}
