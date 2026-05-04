package com.axedgaming.endersdelight.block;

import com.axedgaming.endersdelight.item.ModItems;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.ToIntFunction;


public class ModBlocks {
    public static final RegistryObject<Block> ENDSTONE_STOVE;
    public static final RegistryObject<Block> CHORUS_PIE;
    public static final RegistryObject<Block> STUFFED_SHULKER_BLOCK;
    public static final RegistryObject<Block> CHORUS_CRATE;
    public static DeferredRegister<Block> BLOCKS;
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

    private static ToIntFunction<BlockState> litBlockEmission(int lightValue) {
        return (state) -> {
            return (Boolean)state.getValue(BlockStateProperties.LIT) ? lightValue : 0;
        };
}

    static {
        BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "endersdelight");
        CHORUS_CRATE = BLOCKS.register("chorus_crate", () -> {
            return new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sound(SoundType.WOOD));
        });

        ENDSTONE_STOVE = BLOCKS.register("endstone_stove", () -> {
            return new EndstoneStoveBlock(BlockBehaviour.Properties.copy(Blocks.END_STONE_BRICKS));
        });
        CHORUS_PIE = BLOCKS.register("chorus_pie", () -> {
            return new PieBlock(BlockBehaviour.Properties.copy(Blocks.CAKE), ModItems.CHORUS_PIE_SLICE);
        });
        STUFFED_SHULKER_BLOCK = BLOCKS.register("stuffed_shulker_block", () -> {
            return new StuffedShulkerBlock(BlockBehaviour.Properties.copy(Blocks.PUMPKIN), ModItems.STUFFED_SHULKER_BOWL, false);
        });
    }
}

