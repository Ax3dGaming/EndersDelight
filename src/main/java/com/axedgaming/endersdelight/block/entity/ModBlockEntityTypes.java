package com.axedgaming.endersdelight.block.entity;

import com.axedgaming.endersdelight.EndersDelight;
import com.axedgaming.endersdelight.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, EndersDelight.MODID);

    public static final RegistryObject<BlockEntityType<EndstoneStoveBlockEntity>> ENDSTONE_STOVE = TILES.register("endstone_stove",
            () -> BlockEntityType.Builder.of(EndstoneStoveBlockEntity::new, ModBlocks.ENDSTONE_STOVE.get()).build(null));
}
