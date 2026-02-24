package com.axedgaming.endersdelight.Common.Registries;

import com.axedgaming.endersdelight.Common.Blocks.EndstoneStoveBlock;
import com.axedgaming.endersdelight.EndersDelight;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import vectorwing.farmersdelight.common.block.FeastBlock;
import vectorwing.farmersdelight.common.registry.ModBlocks;

import static vectorwing.farmersdelight.common.registry.ModBlocks.*;

public class EdBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(EndersDelight.MOD_ID);

    public static final DeferredBlock<Block> CHORUS_CRATE = BLOCKS.register("chorus_crate",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(CABBAGE_CRATE.get())));

    public static final DeferredBlock<Block> ENDSTONE_STOVE = BLOCKS.register("endstone_stove",
            () -> new EndstoneStoveBlock(BlockBehaviour.Properties.ofFullCopy(STOVE.get())));

    public static final DeferredBlock<Block> STUFFED_SHULKER = BLOCKS.register("stuffed_shulker",
            () -> new FeastBlock(BlockBehaviour.Properties.ofFullCopy(ModBlocks.STUFFED_PUMPKIN_BLOCK.get()),EdItems.BOWL_OF_STUFFED_SHULKER,false));
}
