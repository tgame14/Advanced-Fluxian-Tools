package com.tgame.advfluxtools.nmultiblocks.tank;

import com.tgame.mods.libs.multiblocks.MultiblockValidationException;
import com.tgame.mods.libs.multiblocks.WorldPos;
import com.tgame.mods.libs.multiblocks.grid.AbstractMultiblockNode;
import com.tgame.mods.libs.multiblocks.grid.GridController;
import com.tgame.mods.libs.multiblocks.simpleimpl.TileSimpleNode;
import net.minecraft.nbt.NBTTagCompound;

import java.util.Set;

/**
 * @since 24/05/14
 * @author tgame14
 */
public class TileFluidMultiblock extends TileSimpleNode
{
    @Override
    public boolean isConnected ()
    {
        return false;
    }

    @Override
    public GridController getMultiblockController ()
    {
        return null;
    }

    @Override
    public WorldPos getWorldLocation ()
    {
        return null;
    }

    @Override
    public void onDetached (GridController multiblockController)
    {

    }

    @Override
    public void onOrphaned (GridController oldController, int oldControllerSize, int newControllerSize)
    {

    }

    @Override
    public GridController createNewMultiblock ()
    {
        return null;
    }

    @Override
    public Class<? extends GridController> getMultiblockControllerType ()
    {
        return null;
    }

    @Override
    public void onAssimilated (GridController newController)
    {

    }

    @Override
    public void setVisited ()
    {

    }

    @Override
    public void setUnvisited ()
    {

    }

    @Override
    public boolean isVisited ()
    {
        return false;
    }

    @Override
    public void becomeMultiblockSaveDelegate ()
    {

    }

    @Override
    public void forfeitMultiblockSaveDelegate ()
    {

    }

    @Override
    public boolean isMultiblockSaveDelegate ()
    {
        return false;
    }

    @Override
    public AbstractMultiblockNode[] getNeighboringParts ()
    {
        return new AbstractMultiblockNode[0];
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
    public Set<GridController> attachToNeighbors ()
    {
        return null;
    }

    @Override
    public void assertDetached ()
    {

    }

    @Override
    public boolean hasMultiblockSaveData ()
    {
        return false;
    }

    @Override
    public NBTTagCompound getMultiblockSaveData ()
    {
        return null;
    }

    @Override
    public void onMultiblockDataAssimilated ()
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

    }
}
