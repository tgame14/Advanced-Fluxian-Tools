package com.tgame.advfluxtools.multiblocks.energy;

import com.tgame.mods.libs.multiblocks.MultiblockValidationException;
import com.tgame.mods.libs.multiblocks.grid.AbstractMultiblockNode;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author tgame14
 * @since 28/06/2014
 */
public class FFRFGridController extends RFGridController
{
	public FFRFGridController(World world)
	{
		super(world);
	}

	@Override
	protected void isBlockGoodForBottom(World world, int x, int y, int z) throws MultiblockValidationException
	{

	}

	@Override
	protected void isBlockGoodForInterior(World world, int x, int y, int z) throws MultiblockValidationException
	{
		super.isBlockGoodForInterior(world, x, y, z);
	}

	@Override
	protected void isBlockGoodForSides(World world, int x, int y, int z) throws MultiblockValidationException
	{

	}

	@Override
	protected void isBlockGoodForTop(World world, int x, int y, int z) throws MultiblockValidationException
	{

	}

	@Override
	protected void isBlockGoodForFrame(World world, int x, int y, int z) throws MultiblockValidationException
	{

	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate)
	{
		if (this.getEnergyStored(ForgeDirection.UNKNOWN) == 0)
		{
			for (AbstractMultiblockNode node : this.connectedParts)
			{
				node.onMachineActivated();
			}
		}
		return super.receiveEnergy(from, maxReceive, simulate);
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate)
	{
		final int preVal = this.getEnergyStored(ForgeDirection.UNKNOWN);
		final int returnVal = super.extractEnergy(from, maxExtract, simulate);
		if (preVal > 0 && this.getEnergyStored(ForgeDirection.UNKNOWN) == 0)
		{
			for (AbstractMultiblockNode node : this.connectedParts)
			{
				node.onMachineDeactivated();
			}
		}
		return returnVal;
	}

}
