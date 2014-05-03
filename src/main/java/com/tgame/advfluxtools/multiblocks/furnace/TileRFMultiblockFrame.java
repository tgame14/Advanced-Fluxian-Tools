package com.tgame.advfluxtools.multiblocks.furnace;

import com.tgame.advfluxtools.libs.erogenousbeef.multiblock.MultiblockControllerBase;
import com.tgame.advfluxtools.libs.erogenousbeef.multiblock.MultiblockValidationException;
import com.tgame.advfluxtools.multiblocks.RFMultiblockController;
import com.tgame.advfluxtools.multiblocks.RFTileMultiblock;
import com.tgame.advfluxtools.multiblocks.furnace.RFFurnaceController;
import net.minecraftforge.common.ForgeDirection;

/**
 * @author tgame14
 * @since 03/05/14
 */
public class TileRFMultiblockFrame extends RFTileMultiblock
{

	@Override
	public void isGoodForFrame() throws MultiblockValidationException
	{

	}

	@Override
	public void isGoodForSides() throws MultiblockValidationException
	{
		throw new MultiblockValidationException("A Frame block is placed on the Sides of the multiblock!");
	}

	@Override
	public void isGoodForTop() throws MultiblockValidationException
	{
		throw new MultiblockValidationException("A Frame block is placed on the Sides of the multiblock!");
	}

	@Override
	public void isGoodForBottom() throws MultiblockValidationException
	{
		throw new MultiblockValidationException("A Frame block is placed on the Sides of the multiblock!");
	}

	@Override
	public void onMachineActivated()
	{

	}

	@Override
	public void onMachineDeactivated()
	{

	}

	@Override
	public MultiblockControllerBase createNewMultiblock()
	{
		return new RFFurnaceController(worldObj);
	}

	@Override
	public Class<? extends MultiblockControllerBase> getMultiblockControllerType()
	{
		return RFFurnaceController.class;
	}



	@Override
	public void onMachineAssembled(MultiblockControllerBase controller)
	{
		super.onMachineAssembled(controller);
		worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 1, 3);
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord); //a safety, not required yet does not harm too much.
	}

	@Override
	public void onMachineBroken()
	{
		super.onMachineBroken();
		worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 3);
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}
}
