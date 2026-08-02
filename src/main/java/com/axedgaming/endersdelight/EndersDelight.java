package com.axedgaming.endersdelight;

import com.axedgaming.endersdelight.block.ModBlocks;
import com.axedgaming.endersdelight.block.entity.ModBlockEntityTypes;
import com.axedgaming.endersdelight.client.ModParticleTypes;
import com.axedgaming.endersdelight.effect.ModEffects;
import com.axedgaming.endersdelight.item.ModCreativeTab;
import com.axedgaming.endersdelight.item.ModItems;
import com.axedgaming.endersdelight.registry.EDStructureTypes;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;
import vectorwing.farmersdelight.common.registry.ModLootModifiers;

import static com.axedgaming.endersdelight.item.ModItems.*;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(EndersDelight.MODID)
public class EndersDelight
{
    public static final String MODID = "endersdelight";
    private static final Logger LOGGER = LogUtils.getLogger();

    public EndersDelight()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::setup);

        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModBlockEntityTypes.TILES.register(modEventBus);
        ModLootModifiers.LOOT_MODIFIERS.register(modEventBus);
        ModEffects.MOB_EFFECTS.register(modEventBus);
        ModCreativeTab.TABS.register(modEventBus);
        ModParticleTypes.REGISTRY.register(modEventBus);
        EDStructureTypes.STRUCTURE_TYPES.register(modEventBus);


        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
    }
}