package com.axedgaming.endersdelight;

import com.axedgaming.endersdelight.Client.renderer.EndstoneStoveRenderer;
import com.axedgaming.endersdelight.Common.Registries.EdBlockEntityTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = EndersDelight.MOD_ID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = EndersDelight.MOD_ID, value = Dist.CLIENT)
public class EndersDelightClient {
    public EndersDelightClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        // Some client setup code
        EndersDelight.LOGGER.info("HELLO FROM CLIENT SETUP");
        EndersDelight.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
    }

    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer((BlockEntityType) EdBlockEntityTypes.ENDSTONE_STOVE.get(), EndstoneStoveRenderer::new);
    }
}
