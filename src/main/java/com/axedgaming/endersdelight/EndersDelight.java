package com.axedgaming.endersdelight;

import com.axedgaming.endersdelight.Common.Registries.*;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

@Mod(EndersDelight.MOD_ID)
public class EndersDelight {
    public static final String MOD_ID = "endersdelight";
    public static final Logger LOGGER = LogUtils.getLogger();

    public EndersDelight(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        EdBlocks.BLOCKS.register(modEventBus);
        EdItems.ITEMS.register(modEventBus);
        EdCreativeTabs.ED_CREATIVE_MODE_TABS.register(modEventBus);
        EdBlockEntityTypes.BLOCK_ENTITY_TYPES.register(modEventBus);
        EdParticles.ED_PARTICLES.register(modEventBus);
        EdEffects.EFFECTS.register(modEventBus);


        NeoForge.EVENT_BUS.register(this);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }
}
