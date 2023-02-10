package com.axedgaming.endersdelight.block;

import net.minecraft.world.item.Item;
import vectorwing.farmersdelight.common.block.FeastBlock;

import java.util.function.Supplier;



public class StuffedShulkerBlock extends FeastBlock {

    public StuffedShulkerBlock(Properties properties, Supplier<Item> servingItem, boolean hasLeftovers) {
        super(properties, servingItem, hasLeftovers);
    }
}
