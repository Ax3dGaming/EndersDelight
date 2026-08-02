package com.axedgaming.endersdelight.registry;

import com.axedgaming.endersdelight.EndersDelight;
import com.axedgaming.endersdelight.worldgen.EndHouseStructure;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class EDStructureTypes {
    public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPES = DeferredRegister.create(Registries.STRUCTURE_TYPE, EndersDelight.MODID);

    public static final RegistryObject<StructureType<EndHouseStructure>> END_HOUSE = STRUCTURE_TYPES.register("end_house", () -> () -> EndHouseStructure.CODEC);
}
