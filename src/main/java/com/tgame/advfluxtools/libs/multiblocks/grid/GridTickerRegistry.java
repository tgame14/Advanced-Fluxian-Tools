package com.tgame.advfluxtools.libs.multiblocks.grid;

import com.google.common.collect.ImmutableList;

/**
 * @since 11/03/14
 * @author tgame14
 */
@Deprecated
public final class GridTickerRegistry
{
    private static ImmutableList.Builder<Class<? extends IGridTicker>> tickerBuilder = new ImmutableList.Builder<Class<? extends IGridTicker>>();
    protected static ImmutableList<Class<? extends IGridTicker>> tickerClasses;

    public static void register(Class<? extends IGridTicker> clazz)
    {
        tickerBuilder.add(clazz);
    }

    public static void postLoad()
    {
        tickerClasses = tickerBuilder.build();
        tickerBuilder = null;
    }
}
