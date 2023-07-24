package com.axedgaming.endersdelight.events;

import com.axedgaming.endersdelight.EndersDelight;
import com.axedgaming.endersdelight.client.renderer.EndstoneStoveRenderer;
import com.axedgaming.endersdelight.registry.EDBlockEntityTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EndersDelight.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetupEvents {
    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(EDBlockEntityTypes.ENDSTONE_STOVE.get(), EndstoneStoveRenderer::new);
    }
}
