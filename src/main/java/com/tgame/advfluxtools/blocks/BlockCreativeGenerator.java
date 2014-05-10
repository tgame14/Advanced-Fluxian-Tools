package com.tgame.advfluxtools.blocks;

import com.tgame.advfluxtools.AFTCreativeTab;
import com.tgame.mods.libs.registry.Registry;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @since 10/05/14
 * @author tgame14
 */
@Registry.BlockData(tileClass = TileCreativeGenerator.class)
public class BlockCreativeGenerator extends BlockContainer
{
    public BlockCreativeGenerator ()
    {
        super(Material.sponge);
        this.setCreativeTab(AFTCreativeTab.INSTANCE);
    }

    @Override
    public TileEntity createNewTileEntity (World var1, int var2)
    {
        return new TileCreativeGenerator();
    }
}
