package com.tgame.advfluxtools;

import com.tgame.advfluxtools.blocks.BlockChargePlatform;
import com.tgame.advfluxtools.blocks.TileChargePlatform;
import com.tgame.advfluxtools.blocks.itemblocks.ItemBlockMetadata;
import com.tgame.advfluxtools.entities.EntityLaserProjectile;
import com.tgame.advfluxtools.items.ItemLaserDrill;
import com.tgame.advfluxtools.libs.erogenousbeef.multiblock.MultiblockEventHandler;
import com.tgame.advfluxtools.multiblocks.furnace.BlockRFMultiblockFrame;
import com.tgame.advfluxtools.multiblocks.furnace.BlockRFFurnaceFacing;
import com.tgame.advfluxtools.multiblocks.furnace.TileRFMultiblockFrame;
import com.tgame.advfluxtools.multiblocks.furnace.TileRFFurnace;
import com.tgame.advfluxtools.utility.Mods;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;

/**
 * @author tgame14
 * @since 29/04/14
 */
@Mod(modid = Settings.ID, name = Settings.NAME, version = Settings.VERSION, dependencies = "after:" + Mods.TE3)
public class AdvancedFluxTools
{
	@Mod.Instance(Settings.ID)
	public static AdvancedFluxTools instance;

	@SidedProxy(clientSide = "com.tgame.advfluxtools.ClientProxy", serverSide = "com.tgame.advfluxtools.CommonProxy")
	public static CommonProxy proxy;

	@Mod.Metadata
	public static ModMetadata metadata;

	public static Item itemLaserDrill;

	public static Block blockChargePlatform;
	public static Block blockRFFurnaceBasic;
	public static Block blockRFMultiblockFrame;

	public static ItemStack itemCresentHammer;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		Settings.CONFIGURATION = new Configuration(event.getSuggestedConfigurationFile());
		Settings.CONFIGURATION.load();
		/** this line took me 2 hours to write. Blame stupidity */
		MinecraftForge.EVENT_BUS.register(new MultiblockEventHandler());

		itemLaserDrill = new ItemLaserDrill(Settings.CONFIGURATION.getItem(ItemLaserDrill.class.getSimpleName(), 22040).getInt());
		GameRegistry.registerItem(itemLaserDrill, itemLaserDrill.getClass().getSimpleName());

		blockChargePlatform = new BlockChargePlatform(Settings.CONFIGURATION.getBlock(BlockChargePlatform.class.getSimpleName(), 2040).getInt());
		GameRegistry.registerBlock(blockChargePlatform, ItemBlockMetadata.class, blockChargePlatform.getClass().getSimpleName());
		GameRegistry.registerTileEntity(TileChargePlatform.class, TileChargePlatform.class.getSimpleName());

		blockRFFurnaceBasic = new BlockRFFurnaceFacing(Settings.CONFIGURATION.getBlock(BlockRFFurnaceFacing.class.getSimpleName(), 2041).getInt());
		GameRegistry.registerBlock(blockRFFurnaceBasic, BlockRFFurnaceFacing.class.getSimpleName());
		GameRegistry.registerTileEntity(TileRFFurnace.class, TileRFFurnace.class.getSimpleName());

		blockRFMultiblockFrame = new BlockRFMultiblockFrame(Settings.CONFIGURATION.getBlock(BlockRFMultiblockFrame.class.getSimpleName(), 2042).getInt());
		GameRegistry.registerBlock(blockRFMultiblockFrame, BlockRFMultiblockFrame.class.getSimpleName());
		GameRegistry.registerTileEntity(TileRFMultiblockFrame.class, TileRFMultiblockFrame.class.getSimpleName());

		//EntityRegistry.registerModEntity(EntityLaserProjectile.class, "EntityLaserProjectile", 0, instance, 80, 3, true);
		Settings.CONFIGURATION.save();
		proxy.preInit();

	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
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
	public void postInit(FMLPostInitializationEvent event)
	{
		// RECIPES

		itemCresentHammer = GameRegistry.findItemStack("ThermalExpansion", "wrench", 1);

		ItemStack leadConduit = GameRegistry.findItemStack("ThermalExpansion", "conduitEnergyBasic", 1);
		ItemStack leadEnergy = GameRegistry.findItemStack("ThermalExpansion", "cellBasic", 1);

		ItemStack hardenedConduit = GameRegistry.findItemStack("ThermalExpansion", "conduitEnergyHardened", 1);
		ItemStack hardenedEnergy = GameRegistry.findItemStack("ThermalExpansion", "cellHardened", 1);

		ItemStack redsConduit = GameRegistry.findItemStack("ThermalExpansion", "conduitEnergyReinforced", 1);
		ItemStack redsEnergy = GameRegistry.findItemStack("ThermalExpansion", "cellReinforced", 1);

		ItemStack ppIron = new ItemStack(Block.pressurePlateIron);

		GameRegistry.addShapedRecipe(new ItemStack(blockChargePlatform, 1, 0), "CPC", "PEP", "CPC", 'C', leadConduit, 'P', ppIron, 'E', leadEnergy);
		GameRegistry.addShapedRecipe(new ItemStack(blockChargePlatform, 1, 1), "CPC", "PEP", "CPC", 'C', hardenedConduit, 'P', ppIron, 'E', hardenedEnergy);
		GameRegistry.addShapedRecipe(new ItemStack(blockChargePlatform, 1, 2), "CPC", "PEP", "CPC", 'C', redsConduit, 'P', ppIron, 'E', redsEnergy);

		proxy.postInit();
	}

}
