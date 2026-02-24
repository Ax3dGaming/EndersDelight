package com.axedgaming.endersdelight.Common.Registries;

import com.axedgaming.endersdelight.EndersDelight;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EdCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> ED_CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EndersDelight.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ENDERS_DELIGHT_TAB = ED_CREATIVE_MODE_TABS.register("enders_delight_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.endersdelight"))
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> EdItems.CHORUS_CRATE.asItem().getDefaultInstance())
            .displayItems(((itemDisplayParameters, output) -> {
                output.accept(EdItems.CHORUS_CRATE.asItem());
                output.accept(EdItems.STUFFED_SHULKER.asItem());
                output.accept(EdItems.SHULKER_BOWL.asItem());
                output.accept(EdItems.BOWL_OF_STUFFED_SHULKER.asItem());
            })
            ).build()
    );
}