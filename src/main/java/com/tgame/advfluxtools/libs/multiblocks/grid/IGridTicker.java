package com.tgame.advfluxtools.libs.multiblocks.grid;

import net.minecraft.nbt.NBTTagCompound;

/**
 * give this a constructor with a IGrid in it
 *
 * @since 10/03/14
 * @author tgame14
 */
public interface IGridTicker
{
    /** the update Grid is Called before all Tiles tick */
    public void updateGrid();

    /**
     * this is a Callback for when A node is Added to the grid
     *
     * @param node - the new node
     */
    public void onNodeAdded(IGridNode node);

    /**
     * this is a Callback for when A node is removed from the grid
     *
     * @param node - the removed node
     */
    public void onNodeRemoved(IGridNode node);

    /**
     * Save data using the Netty ByteBuf
     *
     * When considering speed, Footprint and reliability, the NBT System is not as good as the Netty ByteBuf.
     * Although originally the ByteBuf is meant for compressing data and shipping to packets, It is
     * used here as a means to Save state of the grid.
     *
     * @return a {@link net.minecraft.nbt.NBTTagCompound} holding all your stored Data. This is for matters of efficiency and Speed
     */
    public NBTTagCompound saveData();

    public void loadData (NBTTagCompound tag);
}
