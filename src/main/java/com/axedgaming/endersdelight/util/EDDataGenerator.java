package com.axedgaming.endersdelight.util;

import com.axedgaming.endersdelight.EndersDelight;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = EndersDelight.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EDDataGenerator {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();
        if (event.includeServer()) {
            BlockTags blockTags = new BlockTags(generator, EndersDelight.MODID, helper);
            generator.addProvider(blockTags);
        }
    }
}
