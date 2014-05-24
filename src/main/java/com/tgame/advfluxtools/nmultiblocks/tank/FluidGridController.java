package com.tgame.advfluxtools.nmultiblocks.tank;

import com.tgame.mods.config.Config;
import com.tgame.mods.libs.multiblocks.MultiblockValidationException;
import com.tgame.mods.libs.multiblocks.simpleimpl.SimpleGridController;
import com.tgame.mods.libs.utility.MultiblockUtility;
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

    @Config(category = MultiblockUtility.CATEGORY_MULTIBLOCK)
    public static int INTERNAL_MILLIBUCKET_PER_BLOCK = 8000;

    public FluidGridController (World world)
    {
        super(world);
    }

    @Override
    protected void onMachineAssembled ()
    {
        super.onMachineAssembled();

        this.tank.setCapacity(MultiblockUtility.getInternalBlocks(this) * INTERNAL_MILLIBUCKET_PER_BLOCK);
    }

    @Override
    protected void isBlockGoodForFrame (World world, int x, int y, int z) throws MultiblockValidationException
    {
        if (!world.getBlock(x, y, z).hasTileEntity(world.getBlockMetadata(x, y, z)))
        {
            super.isBlockGoodForFrame(world, x, y, z);
        }
    }

    @Override
    protected void isBlockGoodForTop (World world, int x, int y, int z) throws MultiblockValidationException
    {
        super.isBlockGoodForTop(world, x, y, z);
    }

    @Override
    protected void isBlockGoodForBottom (World world, int x, int y, int z) throws MultiblockValidationException
    {
        super.isBlockGoodForBottom(world, x, y, z);
    }

    @Override
    protected void isBlockGoodForSides (World world, int x, int y, int z) throws MultiblockValidationException
    {
        super.isBlockGoodForSides(world, x, y, z);
    }

    @Override
    protected void isBlockGoodForInterior (World world, int x, int y, int z) throws MultiblockValidationException
    {
        super.isBlockGoodForInterior(world, x, y, z);
    }

    /// * * * IFLUIDTANK * * * ///

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
        return new FluidTankInfo(this);
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
