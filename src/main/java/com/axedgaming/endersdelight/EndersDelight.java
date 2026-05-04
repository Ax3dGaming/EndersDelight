package com.axedgaming.endersdelight;

import com.axedgaming.endersdelight.block.ModBlocks;
import com.axedgaming.endersdelight.block.entity.ModBlockEntityTypes;
import com.axedgaming.endersdelight.effect.ModEffects;
import com.axedgaming.endersdelight.item.ModItems;
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

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final RegistryObject<CreativeModeTab> ENDERS_DELIGHT_TAB = CREATIVE_MODE_TABS.register("endersdelight",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.endersdelighttab"))
                    .icon(ENDSTONE_STOVE.get()::getDefaultInstance)
                    .displayItems((displayParams, output) -> {
                        output.accept(ENDSTONE_STOVE.get());
                        output.accept(CHORUS_CRATE.get());
                        output.accept(SHULKER_BOWL.get());
                        output.accept(MITE_CRUST.get());
                        output.accept(ENDER_SHARD.get());
                        output.accept(ENDERMAN_SIGHT.get());
                        output.accept(SIGHT_FRAGMENT.get());
                        output.accept(SHULKER_MOLLUSK.get());
                        output.accept(SHULKER_FILET.get());
                        output.accept(UNCANNY_COOKIES.get());
                        output.accept(STRANGE_ECLAIR.get());
                        output.accept(CHORUS_PIE.get());
                        output.accept(CHORUS_PIE_SLICE.get());
                        output.accept(CHORUS_STEW_WOOD.get());
                        output.accept(CHORUS_STEW.get());
                        output.accept(CRISPY_SKEWER.get());
                        output.accept(CRAWLING_SANDWICH.get());
                        output.accept(TWISTED_CEREAL_WOOD.get());
                        output.accept(TWISTED_CEREAL.get());
                        output.accept(ENDERMITE_STEW_WOOD.get());
                        output.accept(ENDERMITE_STEW.get());
                        output.accept(PEARL_PASTA_WOOD.get());
                        output.accept(PEARL_PASTA.get());
                        output.accept(ENDER_PAELLA_WOOD.get());
                        output.accept(ENDER_PAELLA.get());
                        output.accept(STUFFED_SHULKER_BOWL.get());
                        output.accept(STUFFED_SHULKER.get());
                            }).withSearchBar().build()
    );

    public EndersDelight()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::setup);

        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModBlockEntityTypes.TILES.register(modEventBus);
        ModLootModifiers.LOOT_MODIFIERS.register(modEventBus);
        ModEffects.MOB_EFFECTS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getName());
    }
}