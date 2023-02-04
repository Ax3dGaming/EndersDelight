package com.axedgaming.endersdelight.item;

import com.axedgaming.endersdelight.EndersDelight;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, EndersDelight.MODID);

    public static final RegistryObject<Item> SHULKER_BOWL = ITEMS.register("shulker_bowl",
            () -> new Item(new Item.Properties().tab(ModCreativeTab.ENDERS_DELIGHT_TAB)));

    public static final RegistryObject<Item> MITE_CRUST = ITEMS.register("mite_crust",
            () -> new Item(new Item.Properties().tab(ModCreativeTab.ENDERS_DELIGHT_TAB)));

    public static final RegistryObject<Item> ENDERMAN_SIGHT = ITEMS.register("enderman_sight",
            () -> new Item(new Item.Properties().tab(ModCreativeTab.ENDERS_DELIGHT_TAB)));

    public static final RegistryObject<Item> SIGHT_FRAGMENT = ITEMS.register("sight_fragment",
            () -> new Item(new Item.Properties().tab(ModCreativeTab.ENDERS_DELIGHT_TAB)));

    public static final RegistryObject<Item> ENDER_SHARD = ITEMS.register("ender_shard",
            () -> new Item(new Item.Properties().tab(ModCreativeTab.ENDERS_DELIGHT_TAB)));

    public static final RegistryObject<Item> SHULKER_MOLLUSK = ITEMS.register("shulker_mollusk",
            () -> new Item(new Item.Properties().tab(ModCreativeTab.ENDERS_DELIGHT_TAB)));

    public static final RegistryObject<Item> SHULKER_FILET = ITEMS.register("shulker_filet",
            () -> new Item(new Item.Properties().tab(ModCreativeTab.ENDERS_DELIGHT_TAB)));

    public static final RegistryObject<Item> CRISPY_SKEWER = ITEMS.register("crispy_skewer",
            () -> new Item(new Item.Properties().food(ModFoods.CRISPY_SKEWER).tab(ModCreativeTab.ENDERS_DELIGHT_TAB)));

    public static final RegistryObject<Item> CRAWLING_SANDWICH = ITEMS.register("crawling_sandwich",
            () -> new Item(new Item.Properties().food(ModFoods.CRAWLING_SANDWICH).tab(ModCreativeTab.ENDERS_DELIGHT_TAB)));

    public static final RegistryObject<Item> TWISTED_CEREAL = ITEMS.register("twisted_cereal",
            () -> new Item(new Item.Properties().food(ModFoods.TWISTED_CEREAL).tab(ModCreativeTab.ENDERS_DELIGHT_TAB)));

    public static final RegistryObject<Item> CHORUS_STEW = ITEMS.register("chorus_stew",
            () -> new Item(new Item.Properties().food(ModFoods.CHORUS_STEW).tab(ModCreativeTab.ENDERS_DELIGHT_TAB)));

    public static final RegistryObject<Item> ENDER_PAELLA = ITEMS.register("ender_paella",
            () -> new Item(new Item.Properties().food(ModFoods.ENDER_PAELLA).tab(ModCreativeTab.ENDERS_DELIGHT_TAB)));

    public static final RegistryObject<Item> STRANGE_ECLAIR = ITEMS.register("strange_eclair",
            () -> new Item(new Item.Properties().food(ModFoods.STRANGE_ECLAIR).tab(ModCreativeTab.ENDERS_DELIGHT_TAB)));

    public static final RegistryObject<Item> UNCANNY_COOKIES = ITEMS.register("uncanny_cookies",
            () -> new Item(new Item.Properties().food(ModFoods.UNCANNY_COOKIES).tab(ModCreativeTab.ENDERS_DELIGHT_TAB)));

    public static final RegistryObject<Item> STUFFED_SHULKER = ITEMS.register("stuffed_shulker",
            () -> new Item(new Item.Properties().food(ModFoods.STUFFED_SHULKER).tab(ModCreativeTab.ENDERS_DELIGHT_TAB)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
