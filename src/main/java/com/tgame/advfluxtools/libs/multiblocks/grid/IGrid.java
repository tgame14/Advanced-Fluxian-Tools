package com.tgame.advfluxtools.libs.multiblocks.grid;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.Event;

/**
 * @since 11/03/14
 * @author tgame14
 */
public interface IGrid
{
    public static final String NBT_SAVE_KEY = "gridDataStorage";

    public IGridTicker getGridTicker(Class<? extends IGridTicker> clazz);

    public void postEventToGrid(Event event);

    public NBTTagCompound writeToDelegate();

    public void readFromDelegate(NBTTagCompound tag);

	public void onClientTick();

	public void onServerTick();


}
