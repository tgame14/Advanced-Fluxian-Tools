package com.tgame.advfluxtools.multiblocks.energy;

import cofh.api.energy.IEnergyHandler;
import com.tgame.advfluxtools.prefabs.TGTileEnergyHandler;
import com.tgame.mods.libs.multiblocks.MultiblockValidationException;
import com.tgame.mods.libs.multiblocks.grid.GridController;
import com.tgame.mods.libs.multiblocks.simpleimpl.TileSimpleNode;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author tgame14
 * @since 24/05/14
 */
public class TileEnergyMultiblock extends TileSimpleNode implements IEnergyHandler
{
	@Override
	public GridController createNewMultiblock()
	{
		return new FFRFGridController(getWorldObj());
	}

	@Override
	public Class<? extends GridController> getMultiblockControllerType()
	{
		return FFRFGridController.class;
	}

	@Override
	public void isGoodForFrame() throws MultiblockValidationException
	{

	}

	@Override
	public void isGoodForSides() throws MultiblockValidationException
	{

	}

	@Override
	public void isGoodForTop() throws MultiblockValidationException
	{

	}

	@Override
	public void isGoodForBottom() throws MultiblockValidationException
	{

	}

	@Override
	public void isGoodForInterior() throws MultiblockValidationException
	{

	}

	@Override
	public void onMachineActivated()
	{
		this.getWorldObj().setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 1, 3);
	}

	@Override
	public void onMachineDeactivated()
	{
		this.getWorldObj().setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 3);
	}

	@Override
	public void onMachineBroken()
	{
		super.onMachineBroken();
		this.getWorldObj().setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 3);

	}

	public IEnergyHandler getInternalEnergy()
	{
		if (this.getMultiblockController() == null)
			return new TGTileEnergyHandler(0);
		return (IEnergyHandler) this.getMultiblockController();
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection from)
	{
		return getInternalEnergy().canConnectEnergy(from);
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate)
	{
		return getInternalEnergy().receiveEnergy(from, maxReceive, simulate);
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate)
	{
		return getInternalEnergy().extractEnergy(from, maxExtract, simulate);
	}

	@Override
	public int getEnergyStored(ForgeDirection from)
	{
		return getInternalEnergy().getEnergyStored(from);
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from)
	{
		return getInternalEnergy().getMaxEnergyStored(from);
	}
}
