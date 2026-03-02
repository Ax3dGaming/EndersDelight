package com.axedgaming.endersdelight.Common.Registries;

import com.axedgaming.endersdelight.EndersDelight;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import vectorwing.farmersdelight.common.registry.ModCreativeTabs;

public class EdCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> ED_CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EndersDelight.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ENDERS_DELIGHT_TAB = ED_CREATIVE_MODE_TABS.register("enders_delight_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.endersdelight"))
            .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
            .icon(() -> EdItems.ENDSTONE_STOVE.asItem().getDefaultInstance())
            .displayItems(((itemDisplayParameters, output) -> {
                /* ~~ Block Items ~~ */
                output.accept(EdItems.CHORUS_CRATE.asItem());
                output.accept(EdItems.ENDSTONE_STOVE.asItem());
                output.accept(EdItems.STUFFED_SHULKER.asItem());
                output.accept(EdItems.ETHEREAL_SAFFRON.asItem());
                output.accept(EdItems.AMBERVEIL.asItem());
                output.accept(EdItems.CHORUSFLAME.asItem());
                output.accept(EdItems.VOIDPEPPER.asItem());

                /* ~~ Items ~~ */
                output.accept(EdItems.SHULKER_BOWL.asItem());
                output.accept(EdItems.MITE_CRUST.asItem());
                output.accept(EdItems.ENDER_SHARD.asItem());
                output.accept(EdItems.ENDERMAN_SIGHT.asItem());
                output.accept(EdItems.SIGHT_FRAGMENTS.asItem());
                output.accept(EdItems.SHULKER_MOLLUSK.asItem());
                output.accept(EdItems.SHULKER_FILET.asItem());
                output.accept(EdItems.UNCANNY_COOKIES.asItem());
                output.accept(EdItems.CHORUS_JUICE.asItem());
                output.accept(EdItems.STRANGE_ECLAIR.asItem());
                output.accept(EdItems.CHORUS_PIE.asItem());
                output.accept(EdItems.CHORUS_PIE_SLICE.asItem());
                output.accept(EdItems.CHORUS_STEW.asItem());
                output.accept(EdItems.CRISPY_SKEWER.asItem());
                output.accept(EdItems.CRAWLING_SANDWICH.asItem());
                output.accept(EdItems.TWISTED_CEREAL.asItem());
                output.accept(EdItems.ENDERMITE_STEW.asItem());
                output.accept(EdItems.PEARL_PASTA.asItem());
                output.accept(EdItems.ENDER_PAELLA.asItem());
                output.accept(EdItems.STUFFED_SHULKER_BOWL.asItem());
                output.accept(EdItems.AMBERVEIL_STEW.asItem());
                output.accept(EdItems.AMBERVEILED_CURRY.asItem());
                output.accept(EdItems.CHICKEN_CURRY.asItem());
                output.accept(EdItems.STEAK_FRIES.asItem());
                output.accept(EdItems.VEIL_OF_FLAMES_RISOTTO.asItem());
            })
            ).build()
    );
}
