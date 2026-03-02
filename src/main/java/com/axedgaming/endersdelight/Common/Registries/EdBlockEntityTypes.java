package com.axedgaming.endersdelight.Common.Registries;

import com.axedgaming.endersdelight.Common.Blocks.entity.EndstoneStoveBlockEntity;
import com.axedgaming.endersdelight.Common.Blocks.entity.VoidPepperBushBlockEntity;
import com.axedgaming.endersdelight.EndersDelight;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class EdBlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, EndersDelight.MOD_ID);

    public static final Supplier<BlockEntityType<EndstoneStoveBlockEntity>> ENDSTONE_STOVE = BLOCK_ENTITY_TYPES.register("endstone_stove",
            () -> BlockEntityType.Builder.of(EndstoneStoveBlockEntity::new, EdBlocks.ENDSTONE_STOVE.get()).build(null));

    public static final Supplier<BlockEntityType<VoidPepperBushBlockEntity>> VOID_PEPPER_BUSH_BLOCK_ENTITY = BLOCK_ENTITY_TYPES.register("void_pepper_bush_be",
            () -> BlockEntityType.Builder.of(VoidPepperBushBlockEntity::new, EdBlocks.VOID_PEPPER_BUSH.get()).build(null));
}