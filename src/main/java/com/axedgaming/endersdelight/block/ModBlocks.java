package com.axedgaming.endersdelight.block;

import com.axedgaming.endersdelight.EndersDelight;
import com.axedgaming.endersdelight.item.ModCreativeTab;
import com.axedgaming.endersdelight.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.common.block.FeastBlock;
import vectorwing.farmersdelight.common.block.PieBlock;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, EndersDelight.MODID);

    public static final RegistryObject<Block> CHORUS_PIE = BLOCKS.register("chorus_pie",
            () -> new PieBlock(Block.Properties.copy(Blocks.CAKE), ModItems.CHORUS_STEW));

    public static final RegistryObject<Block> STUFFED_SHULKER_BLOCK = BLOCKS.register("stuffed_shulker_block",
            () -> {return new StuffedShulkerBlock(BlockBehaviour.Properties.copy(Blocks.PUMPKIN), ModItems.STUFFED_SHULKER, false);});

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(ModCreativeTab.ENDERS_DELIGHT_TAB)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
