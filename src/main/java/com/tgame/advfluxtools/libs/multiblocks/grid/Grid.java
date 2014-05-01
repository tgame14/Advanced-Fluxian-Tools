package com.tgame.advfluxtools.libs.multiblocks.grid;

import com.tgame.advfluxtools.Settings;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.Event;

import java.lang.reflect.Constructor;
import java.util.*;

/**
 * @since 10/03/14
 * @author tgame14
 */
public class Grid implements IGrid
{

    private Set<IGridNode> nodeSet;
    private List<IGridTicker> gridTickers;
    private IGridNode saveDelegate;

    public Grid (IGridNode node)
    {
        this.nodeSet = new HashSet<IGridNode>();
        this.nodeSet.add(node);

        this.gridTickers = new ArrayList<IGridTicker>(GridTickerRegistry.tickerClasses.size());

        for (Class<? extends IGridTicker> clazz : GridTickerRegistry.tickerClasses)
        {
            for (Constructor<?> con : clazz.getConstructors())
            {
                try
                {
                    if (con.getParameterTypes().length == 1 && con.getParameterTypes()[0].isInstance(this))
                    {
                        gridTickers.add((IGridTicker) con.newInstance(this));
                    }
                }
                catch (Exception e)
                {
                    Settings.LOGGER.severe("An IGridTicker doesn't have a Constructor with IGrid in it! This is an error");
                }
            }
        }
    }
// TODO: Replace with a tickhandler.. Sadly this isn't 1.7
//    //@Override
//    //@SubscribeEvent
//    public void tickEvent (TickEvent event)
//    {
//        if (event.phase == TickEvent.Phase.START && event.type == TickEvent.Type.WORLD)
//        {
//            for (IGridTicker gridTicker : this.gridTickers)
//            {
//                gridTicker.updateGrid();
//            }
//        }
//
//    }

    @Override
    public IGridTicker getGridTicker (Class<? extends IGridTicker> clazz)
    {
        for (IGridTicker ticker : this.gridTickers)
        {
            if (ticker.getClass().equals(clazz))
                return ticker;
        }
        return null;
    }

    @Override
    public void postEventToGrid (Event event)
    {

    }

    public IGridNode getSaveDelegate ()
    {
        if (saveDelegate == null)
        {
			IGridNode[] nodeArray = (IGridNode[]) nodeSet.toArray();
			Arrays.sort(nodeArray);
			this.saveDelegate = nodeArray[0];
        }

        return this.saveDelegate;
    }

    @Override
    public void writeToDelegate ()
    {
		NBTTagCompound nbt = new NBTTagCompound("MultiblockAFTKey");
        for (IGridTicker gridTicker : gridTickers)
        {
            nbt.setCompoundTag(gridTicker.getClass().getName(), gridTicker.saveData());
        }

        getSaveDelegate().saveGridData(nbt, IGrid.NBT_SAVE_KEY);

    }

    @Override
    public void readFromDelegate (NBTTagCompound tag)
    {
        NBTTagCompound nbt = tag.getCompoundTag(IGrid.NBT_SAVE_KEY);

        for (IGridTicker gridTicker : gridTickers)
        {
            gridTicker.loadData(tag.getCompoundTag(gridTicker.getClass().getName()));
        }

    }


}
