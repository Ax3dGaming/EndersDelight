package com.axedgaming.endersdelight.util;

import com.axedgaming.endersdelight.EndersDelight;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.Tags;
public class ModTags {
    public static class Items {
        public static final TagKey<Item> SHULKER_LOOT = modItemTag("shulker_loot");
        public static final TagKey<Item> ENDERMAN_LOOT = modItemTag("enderman_loot");
        public static final TagKey<Item> ENDERMAN_SIGHT = modItemTag("enderman_sight");
    }

    private static TagKey<Item> modItemTag(String path) {
        return TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(EndersDelight.MODID + ":" + path));
    }
}
