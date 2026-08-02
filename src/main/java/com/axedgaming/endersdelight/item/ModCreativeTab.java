package com.axedgaming.endersdelight.item;

import com.axedgaming.endersdelight.EndersDelight;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static com.axedgaming.endersdelight.item.ModItems.*;

public class ModCreativeTab {
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EndersDelight.MODID);

    public static final RegistryObject<CreativeModeTab> ENDERS_DELIGHT_TAB = TABS.register("endersdelighttab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.endersdelighttab"))
                    .icon(() -> new ItemStack(ModItems.ENDSTONE_STOVE.get()))
                    .displayItems((parameters, output) -> {
                        output.accept(ENDSTONE_STOVE.get());
                        output.accept(CHORUS_CRATE.get());
                        output.accept(MITE_CRUST.get());
                        output.accept(ENDERMAN_SIGHT.get());
                        output.accept(SHULKER_MOLLUSK.get());
                        output.accept(ETHEREAL_SAFFRON.get());
                        output.accept(VOIDPEPPER.get());
                        output.accept(CHORUS_FLAME.get());
                        output.accept(AMBERVEIL.get());
                        output.accept(ENDER_SHARD.get());
                        output.accept(SIGHT_FRAGMENT.get());
                        output.accept(SHULKER_FILET.get());
                        output.accept(CRUSHED_VOIDPEPPER.get());
                        output.accept(SHULKER_BOWL.get());
                        output.accept(CHORUS_JUICE.get());
                        output.accept(UNCANNY_COOKIES.get());
                        output.accept(STRANGE_ECLAIR.get());
                        output.accept(CHORUS_PIE_SLICE.get());
                        output.accept(CHORUS_PIE.get());
                        output.accept(CRAWLING_SANDWICH.get());
                        output.accept(CRISPY_SKEWER.get());
                        output.accept(TWISTED_CEREAL.get());
                        output.accept(ENDERMITE_STEW.get());
                        output.accept(PEARL_PASTA.get());
                        output.accept(ENDER_PAELLA.get());
                        output.accept(CHORUS_STEW.get());
                        output.accept(AMBERVEIL_STEW.get());
                        output.accept(AMBERVEILED_CURRY.get());
                        output.accept(STEAK_FRIES.get());
                        output.accept(VEIL_OF_FLAMES_RISOTTO.get());
                        output.accept(CHICKEN_CURRY.get());
                        output.accept(STUFFED_SHULKER_BOWL.get());
                        output.accept(STUFFED_SHULKER.get());
                    })
                    .build()
    );
}
