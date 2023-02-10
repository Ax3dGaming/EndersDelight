package com.axedgaming.endersdelight.registry;

import com.axedgaming.endersdelight.EndersDelight;
import com.axedgaming.endersdelight.block.ModBlocks;
import com.axedgaming.endersdelight.block.entity.EndstoneStoveBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.common.block.entity.StoveBlockEntity;

public class EDBlockEntityTypes {

    public static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, EndersDelight.MODID);

    public static final RegistryObject<BlockEntityType<EndstoneStoveBlockEntity>> ENDSTONE_STOVE = TILES.register("endstone_stove",
            () -> BlockEntityType.Builder.of(EndstoneStoveBlockEntity::new, ModBlocks.ENDSTONE_STOVE.get()).build(null));
}