package com.tgame.advfluxtools;

import com.tgame.advfluxtools.blocks.BlockChargePlatform;
import com.tgame.advfluxtools.blocks.BlockCreativeGenerator;
import com.tgame.advfluxtools.items.ItemLaserDrill;
import com.tgame.mods.config.ConfigHandler;
import com.tgame.mods.libs.registry.IItemDefinition;
import com.tgame.mods.libs.registry.RegistryHandler;
import com.tgame.mods.libs.utility.Mods;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * @author tgame14
 * @since 29/04/14
 */
@Mod(modid = Settings.ID, name = Settings.NAME, version = Settings.VERSION, dependencies = "after:" + Mods.TE3 + ";required-after:tgamecore" /*+ ";required-after" + Mods.COFH*/)
//@NetworkMod(channels = Settings.CHANNEL, clientSideRequired = true, serverSideRequired = true)
public class AdvancedFluxTools
{
    @Mod.Instance(Settings.ID)
    public static AdvancedFluxTools instance;

    @SidedProxy(clientSide = "com.tgame.advfluxtools.ClientProxy", serverSide = "com.tgame.advfluxtools.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Metadata
    public static ModMetadata metadata;
    public static Block blockRFFurnaceBasic;
    public static Block blockRFMultiblockFrame;
    public static ItemStack wrench;

    public static IItemDefinition itemLaserDrill;
    public static IItemDefinition blockChargePlatform;
    private RegistryHandler registry;

    public AdvancedFluxTools ()
    {
        this.registry = new RegistryHandler();
    }


    @Mod.EventHandler
    public void preInit (FMLPreInitializationEvent event)
    {
        Settings.CONFIGURATION = new Configuration(event.getSuggestedConfigurationFile());
        blockChargePlatform = registry.registerBlock(new BlockChargePlatform(), BlockChargePlatform.class);
        itemLaserDrill = registry.registerItem(new ItemLaserDrill(), ItemLaserDrill.class);
        registry.registerBlock(new BlockCreativeGenerator(), BlockCreativeGenerator.class);

		GameRegistry.registerCustomItemStack("laserDrill", itemLaserDrill.getItemStack(1, 1));

        //        Settings.CONFIGURATION.load();

        //        /** this line took me 2 hours to write. Blame stupidity */
        //        //MinecraftForge.EVENT_BUS.register(new MultiblockEventHandler());
        //
        //        itemLaserDrill = new ItemLaserDrill(Settings.CONFIGURATION.getItem(ItemLaserDrill.class.getSimpleName(), 22040).getInt());
        //        GameRegistry.registerItem(itemLaserDrill, itemLaserDrill.getClass().getSimpleName());
        //
        //        blockChargePlatform = new BlockChargePlatform(Settings.CONFIGURATION.getBlock(BlockChargePlatform.class.getSimpleName(), 2040).getInt());
        //        GameRegistry.registerBlock(blockChargePlatform, ItemBlockMetadata.class, blockChargePlatform.getClass().getSimpleName());
        //        GameRegistry.registerTileEntity(TileChargePlatform.class, TileChargePlatform.class.getSimpleName());
        //
        //        blockRFFurnaceBasic = new BlockRFFurnaceFacing(Settings.CONFIGURATION.getBlock(BlockRFFurnaceFacing.class.getSimpleName(), 2041).getInt());
        //        GameRegistry.registerBlock(blockRFFurnaceBasic, BlockRFFurnaceFacing.class.getSimpleName());
        //        GameRegistry.registerTileEntity(TileRFFurnace.class, TileRFFurnace.class.getSimpleName());
        //
        //        blockRFMultiblockFrame = new BlockRFMultiblockFrame(Settings.CONFIGURATION.getBlock(BlockRFMultiblockFrame.class.getSimpleName(), 2042).getInt());
        //        GameRegistry.registerBlock(blockRFMultiblockFrame, BlockRFMultiblockFrame.class.getSimpleName());
        //        GameRegistry.registerTileEntity(TileRFMultiblockFrame.class, TileRFMultiblockFrame.class.getSimpleName());
        //
        //        //EntityRegistry.registerModEntity(EntityLaserProjectile.class, "EntityLaserProjectile", 1, instance, 80, 3, true);
        //        Settings.CONFIGURATION.save();
        proxy.preInit();

    }

    @Mod.EventHandler
    public void init (FMLInitializationEvent event)
    {
        metadata.authorList.add("@AUTHOR@");
        metadata.credits = "@AUTHOR@, https://github.com/tgame14/Advanced-Fluxian-Tools/blob/master/CREDITS.md";
        metadata.description = "A Mod that adds Advanced tools and machinery";
        metadata.modId = Settings.ID;
        metadata.version = Settings.VERSION;
        metadata.name = Settings.NAME;

        metadata.autogenerated = false;

        proxy.registerRenderers();
        proxy.init();
    }

    @Mod.EventHandler
    public void postInit (FMLPostInitializationEvent event)
    {
        // RECIPES
        if (Loader.isModLoaded(Mods.TE3))
        {
            wrench = GameRegistry.findItemStack(Mods.TE3, "wrench", 1);

            ItemStack leadConduit = GameRegistry.findItemStack(Mods.TE3, "conduitEnergyBasic", 1);
            ItemStack leadEnergy = GameRegistry.findItemStack(Mods.TE3, "cellBasic", 1);

            ItemStack hardenedConduit = GameRegistry.findItemStack(Mods.TE3, "conduitEnergyHardened", 1);
            ItemStack hardenedEnergy = GameRegistry.findItemStack(Mods.TE3, "cellHardened", 1);

            ItemStack redsConduit = GameRegistry.findItemStack(Mods.TE3, "conduitEnergyReinforced", 1);
            ItemStack redsEnergy = GameRegistry.findItemStack(Mods.TE3, "cellReinforced", 1);

            ItemStack gearElectrum = GameRegistry.findItemStack(Mods.TE3, "gearElectrum", 1);
            ItemStack hardenedCapacitor = GameRegistry.findItemStack(Mods.TE3, "capacitorHardened", 1);

            ItemStack ppIron = new ItemStack(GameData.getBlockRegistry().getObject("light_weighted_pressure_plate"));

            GameRegistry.addRecipe(new ShapedOreRecipe(blockChargePlatform.getItemStack(1, 0), "CPC", "PEP", "CPC", 'C', leadConduit, 'P', ppIron, 'E', leadEnergy));
            GameRegistry.addRecipe(new ShapedOreRecipe(blockChargePlatform.getItemStack(1, 1), "CPC", "PEP", "CPC", 'C', hardenedConduit, 'P', ppIron, 'E', hardenedEnergy));
            GameRegistry.addRecipe(new ShapedOreRecipe(blockChargePlatform.getItemStack(1, 2), "CPC", "PEP", "CPC", 'C', redsConduit, 'P', ppIron, 'E', redsEnergy));

            GameRegistry.addRecipe(new ShapedOreRecipe(itemLaserDrill.getItemStack(1, 0), " B ", " E ", "GG ", 'B', hardenedCapacitor, 'E', GameData.getItemRegistry().getObject("emerald"), 'G', gearElectrum));

        }
        else
        {
            ItemStack quartz = new ItemStack(Blocks.quartz_block);
            ItemStack redTorch = new ItemStack(Blocks.redstone_torch);
            ItemStack ppIron = new ItemStack(Blocks.heavy_weighted_pressure_plate);
            ItemStack ppGold = new ItemStack(Blocks.light_weighted_pressure_plate);
            ItemStack soulSand = new ItemStack(Blocks.soul_sand);
            ItemStack ironBlock = new ItemStack(Blocks.iron_block);
            ItemStack anvil = new ItemStack(Blocks.anvil);
            ItemStack enderEye = new ItemStack(Items.ender_eye);
            ItemStack emerald = new ItemStack(Items.emerald);

            GameRegistry.addRecipe(new ShapedOreRecipe(blockChargePlatform.getItemStack(1, 0), "CPC", "PEP", "CPC", 'C', redTorch, 'P', ppIron, 'E', soulSand));
            GameRegistry.addRecipe(new ShapedOreRecipe(blockChargePlatform.getItemStack(1, 1), "CPC", "PEP", "CPC", 'C', quartz, 'P', ppIron, 'E', soulSand));
            GameRegistry.addRecipe(new ShapedOreRecipe(blockChargePlatform.getItemStack(1, 2), "CPC", "PEP", "CPC", 'C', anvil, 'P', ppGold, 'E', enderEye));

            GameRegistry.addRecipe(new ShapedOreRecipe(itemLaserDrill.getItemStack(1, 0), " B ", " E ", "GG ", 'B', redTorch, 'E', emerald, 'G', quartz));
        }
        if (wrench == null)
        {
            wrench = new ItemStack(GameData.getItemRegistry().getObject("golden_hoe"));
        }

        ConfigHandler.configure(Settings.CONFIGURATION, Settings.DOMAIN);

        proxy.postInit();
    }

}
