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
    public static final RegistryObject<Block> ETHEREAL_SAFFRON_BLOCK;
    public static final RegistryObject<Block> AMBERVEIL_MUSHROOM;
    public static final RegistryObject<Block> CHORUS_FLAME_BLOCK;
    public static final RegistryObject<Block> VOIDPEPPER_BLOCK;
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
            return new EndstoneStoveBlock(BlockBehaviour.Properties.copy(Blocks.END_STONE_BRICKS).lightLevel(litBlockEmission(13)));
        });
        CHORUS_PIE = BLOCKS.register("chorus_pie", () -> {
            return new PieBlock(BlockBehaviour.Properties.copy(Blocks.CAKE), ModItems.CHORUS_PIE_SLICE);
        });
        STUFFED_SHULKER_BLOCK = BLOCKS.register("stuffed_shulker_block", () -> {
            return new StuffedShulkerBlock(BlockBehaviour.Properties.copy(Blocks.PUMPKIN), ModItems.STUFFED_SHULKER_BOWL, false);
        });
        ETHEREAL_SAFFRON_BLOCK = BLOCKS.register("ethereal_saffron_bush", () -> {
            return new EtherealSaffronBush(BlockBehaviour.Properties.copy(Blocks.SWEET_BERRY_BUSH));
        });
        AMBERVEIL_MUSHROOM = BLOCKS.register("amberveil_mushroom", () -> {
            return new AmberveilMushroom(BlockBehaviour.Properties.copy(Blocks.RED_MUSHROOM));
        });
        CHORUS_FLAME_BLOCK = BLOCKS.register("chorus_flame_block", () -> {
            return new ChorusFlameBlock(BlockBehaviour.Properties.copy(Blocks.RED_MUSHROOM));
        });
        VOIDPEPPER_BLOCK = BLOCKS.register("voidpepper_block", () -> {
            return new VoidpepperBush(BlockBehaviour.Properties.copy(Blocks.TWISTING_VINES));
        });
    }
}

