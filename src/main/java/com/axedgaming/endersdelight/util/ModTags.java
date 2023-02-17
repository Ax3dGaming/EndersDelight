package com.axedgaming.endersdelight.util;

import com.axedgaming.endersdelight.EndersDelight;
import com.axedgaming.endersdelight.block.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

import static net.minecraft.tags.Tag.Builder.tag;

public class ModTags {
    public static class Items {
        public static final TagKey<Item> SHULKER_LOOT = modItemTag("shulker_loot");
        public static final TagKey<Item> ENDERMAN_LOOT = modItemTag("enderman_loot");
        public static final TagKey<Item> ENDERMAN_SIGHT = modItemTag("enderman_sight");
    }

    private static TagKey<Item> modItemTag(String path) {
        return TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(EndersDelight.MODID + ":" + path));
    }

    private static TagKey<Block> modBlockTag(String path) {
        return TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(EndersDelight.MODID + ":" + path));
    }
}
