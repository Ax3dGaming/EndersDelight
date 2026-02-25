package com.axedgaming.endersdelight.Common.Registries;

import com.axedgaming.endersdelight.Common.Blocks.Bush.AmberveilMushroom;
import com.axedgaming.endersdelight.Common.Blocks.Bush.ChorusFlameBlock;
import com.axedgaming.endersdelight.Common.Blocks.Bush.EtherealSaffronBush;
import com.axedgaming.endersdelight.Common.Blocks.EndstoneStoveBlock;
import com.axedgaming.endersdelight.Common.Blocks.Bush.VoidPepperBush;
import com.axedgaming.endersdelight.EndersDelight;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import vectorwing.farmersdelight.common.block.FeastBlock;

import static vectorwing.farmersdelight.common.registry.ModBlocks.*;
import static net.minecraft.world.level.block.Blocks.*;

public class EdBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(EndersDelight.MOD_ID);

    public static final DeferredBlock<Block> CHORUS_CRATE = BLOCKS.register("chorus_crate",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(CABBAGE_CRATE.get())));

    public static final DeferredBlock<Block> ENDSTONE_STOVE = BLOCKS.register("endstone_stove",
            () -> new EndstoneStoveBlock(BlockBehaviour.Properties.ofFullCopy(STOVE.get())));

    public static final DeferredBlock<Block> STUFFED_SHULKER = BLOCKS.register("stuffed_shulker",
            () -> new FeastBlock(BlockBehaviour.Properties.ofFullCopy(STUFFED_PUMPKIN_BLOCK.get()),EdItems.STUFFED_SHULKER_BOWL,false));

    public static final DeferredBlock<Block> VOID_PEPPER_BUSH = BLOCKS.register("voidpepper_bush",
            () -> new VoidPepperBush(BlockBehaviour.Properties.ofFullCopy(SWEET_BERRY_BUSH)));

    public static final DeferredBlock<Block> AMBERVEIL_MUSHROOM = BLOCKS.register("amberveil_mushroom",
            () -> new AmberveilMushroom(BlockBehaviour.Properties.ofFullCopy(SWEET_BERRY_BUSH)));

    public static final DeferredBlock<Block> CHORUS_FLAME_BUSH = BLOCKS.register("chorusflame_bush",
            () -> new ChorusFlameBlock(BlockBehaviour.Properties.ofFullCopy(SWEET_BERRY_BUSH)));

    public static final DeferredBlock<Block> ETHEREAL_SAFFRON_BUSH = BLOCKS.register("ethereal_saffron_bush",
            () -> new EtherealSaffronBush(BlockBehaviour.Properties.ofFullCopy(SWEET_BERRY_BUSH)));
}
