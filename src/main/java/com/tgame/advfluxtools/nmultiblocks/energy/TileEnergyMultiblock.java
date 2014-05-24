package com.tgame.advfluxtools.nmultiblocks.energy;

import com.tgame.mods.libs.multiblocks.MultiblockValidationException;
import com.tgame.mods.libs.multiblocks.grid.GridController;
import com.tgame.mods.libs.multiblocks.simpleimpl.TileSimpleNode;

/**
 * @since 24/05/14
 * @author tgame14
 */
public class TileEnergyMultiblock extends TileSimpleNode
{
    @Override
    public Class<? extends GridController> getMultiblockControllerType ()
    {
        return RFGridController.class;
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
    public GridController createNewMultiblock ()
    {
        return new RFGridController(getWorldObj());
    }
}
