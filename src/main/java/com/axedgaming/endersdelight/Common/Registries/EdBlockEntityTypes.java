package com.axedgaming.endersdelight.Common.Registries;

import com.axedgaming.endersdelight.Common.Blocks.entity.EndstoneStoveBlockEntity;
import com.axedgaming.endersdelight.EndersDelight;
import com.mojang.datafixers.types.Type;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class EdBlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, EndersDelight.MOD_ID);

    public static final Supplier<BlockEntityType<EndstoneStoveBlockEntity>> ENDSTONE_STOVE = BLOCK_ENTITY_TYPES.register("endstone_stove",
            () -> BlockEntityType.Builder.of(EndstoneStoveBlockEntity::new, EdBlocks.ENDSTONE_STOVE.get()).build(null));
}
