package com.axedgaming.endersdelight.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeTab {
    public static final CreativeModeTab ENDERS_DELIGHT_TAB = new CreativeModeTab("endersdelighttab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.ENDSTONE_STOVE.get());
        }
    };
}
