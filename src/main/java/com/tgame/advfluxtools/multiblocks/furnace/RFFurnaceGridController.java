package com.tgame.advfluxtools.multiblocks.furnace;

import com.tgame.advfluxtools.multiblocks.energy.RFGridController;
import com.tgame.mods.libs.multiblocks.MultiblockValidationException;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

/**
 * @author tgame14
 * @since 04/06/14
 */
public class RFFurnaceGridController extends RFGridController
{

	public RFFurnaceGridController(World world)
	{
		super(world);
	}


	@Override
	protected void isBlockGoodForInterior(World world, int x, int y, int z) throws MultiblockValidationException
	{
		if (!world.getBlock(x, y, z).isAir(world, x, y, z))
		{
			throw new MultiblockValidationException(StatCollector.translateToLocal("multiblock.empty_interior.info"));
		}
	}

	@Override
	protected int getMinimumNumberOfBlocksForAssembledMachine()
	{
		return 26;
	}

	@Override
	protected int getMinimumXSize()
	{
		return 3;
	}

	@Override
	protected int getMinimumYSize()
	{
		return 3;
	}

	@Override
	protected int getMinimumZSize()
	{
		return 3;
	}
}
