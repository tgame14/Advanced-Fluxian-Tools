package com.tgame.advfluxtools.multiblocks.furnace;

import com.tgame.advfluxtools.multiblocks.energy.TileEnergyMultiblock;
import com.tgame.mods.libs.multiblocks.MultiblockValidationException;
import com.tgame.mods.libs.multiblocks.grid.GridController;

/**
 * @author tgame14
 * @since 10/06/14
 */
public class TileRFFurnaceMultiblock extends TileEnergyMultiblock
{
	@Override
	public void isGoodForFrame() throws MultiblockValidationException
	{
		throw new MultiblockValidationException("Must be on faces of multiblock, frame requires a casing");
	}

	@Override
	public Class<? extends GridController> getMultiblockControllerType()
	{
		return RFFurnaceGridController.class;
	}

	@Override
	public GridController createNewMultiblock()
	{
		return new RFFurnaceGridController(this.getWorldObj());
	}

	@Override
	public void onMachineAssembled(GridController controller)
	{
		super.onMachineAssembled(controller);
		this.getWorldObj().setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 1, 3);
	}

	@Override
	public void onMachineBroken()
	{
		super.onMachineBroken();
		this.getWorldObj().setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 3);
	}

	public static class TileRFFurnaceCasing extends TileRFFurnaceMultiblock
	{
		@Override
		public void isGoodForFrame() throws MultiblockValidationException
		{

		}

		@Override
		public void isGoodForTop() throws MultiblockValidationException
		{
			throw new MultiblockValidationException("Casings are only applicable to frames");
		}

		@Override
		public void isGoodForSides() throws MultiblockValidationException
		{
			throw new MultiblockValidationException("Casings are only applicable to frames");
		}

		@Override
		public void isGoodForBottom() throws MultiblockValidationException
		{
			throw new MultiblockValidationException("Casings are only applicable to frames");
		}
	}
}
