package com.axedgaming.endersdelight;

import com.axedgaming.endersdelight.Client.Particles.EndFlameParticleProvider;
import com.axedgaming.endersdelight.Client.renderer.EndstoneStoveRenderer;
import com.axedgaming.endersdelight.Common.Registries.EdBlockEntityTypes;
import com.axedgaming.endersdelight.Common.Registries.EdParticles;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
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
    }

    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(EdBlockEntityTypes.ENDSTONE_STOVE.get(), EndstoneStoveRenderer::new);
    }

    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(
                EdParticles.END_FLAME.get(),
                EndFlameParticleProvider::new
        );
    }
}
