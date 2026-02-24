package com.axedgaming.endersdelight.Common.Registries;

import com.axedgaming.endersdelight.EndersDelight;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import vectorwing.farmersdelight.common.item.ConsumableItem;

public class EdItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(EndersDelight.MOD_ID);

    /* ~~ Utility ~~ */

    private static Item.Properties shulkerBowlFoodItem(FoodProperties foodProperties) {
        return new Item.Properties().food(foodProperties).craftRemainder(SHULKER_BOWL.asItem()).stacksTo(16);
    }

    /* ~~ Block Items ~~ */

    public static final DeferredItem<Item> CHORUS_CRATE = ITEMS.register("chorus_crate",
            () -> new BlockItem(EdBlocks.CHORUS_CRATE.get(), new Item.Properties()));

    public static final DeferredItem<Item> ENDSTONE_STOVE = ITEMS.register("endstone_stove",
            () -> new BlockItem(EdBlocks.ENDSTONE_STOVE.get(), new Item.Properties()));

    public static final DeferredItem<Item> STUFFED_SHULKER = ITEMS.register("stuffed_shulker",
            () -> new BlockItem(EdBlocks.STUFFED_SHULKER.get(), new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> ETHEREAL_SAFFRON = ITEMS.register("ethereal_saffron",
            () -> new BlockItem(EdBlocks.CHORUS_CRATE.get(), new Item.Properties()));

    public static final DeferredItem<Item> AMBERVEIL = ITEMS.register("amberveil",
            () -> new BlockItem(EdBlocks.CHORUS_CRATE.get(), new Item.Properties()));

    public static final DeferredItem<Item> CHORUSFLAME = ITEMS.register("chorusflame",
            () -> new BlockItem(EdBlocks.CHORUS_CRATE.get(), new Item.Properties()));

    public static final DeferredItem<Item> VOIDPEPPER = ITEMS.register("voidpepper",
            () -> new BlockItem(EdBlocks.CHORUS_CRATE.get(), new Item.Properties()));


    /* ~~ Items ~~ */

    public static final DeferredItem<Item> SHULKER_BOWL = ITEMS.register("shulker_bowl",
            () -> new Item(new Item.Properties().stacksTo(16)));

    public static final DeferredItem<Item> MITE_CRUST = ITEMS.register("mite_crust",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> ENDER_SHARD = ITEMS.register("ender_shard",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> ENDERMAN_SIGHT = ITEMS.register("enderman_sight",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> SIGHT_FRAGMENT =  ITEMS.register("sight_fragment",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> SHULKER_MOLLUSK = ITEMS.register("shulker_mollusk",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> SHULKER_FILET = ITEMS.register("shulker_filet",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> UNCANNY_COOKIES = ITEMS.register("uncanny_cookies",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> CHORUS_JUICE = ITEMS.register("chorus_juice",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> STRANGE_ECLAIR = ITEMS.register("strange_eclair",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> CHORUS_PIE_SLICE = ITEMS.register("chorus_pie_slice",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> CHORUS_STEW = ITEMS.register("chorus_stew",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> CRISPY_SKEWER = ITEMS.register("crispy_skewer",
            () ->  new Item(new Item.Properties()));

    public static final DeferredItem<Item> CRAWLING_SANDWICH = ITEMS.register("crawling_sandwich",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> TWISTED_CEREAL = ITEMS.register("twisted_cereal",
            () -> new ConsumableItem(shulkerBowlFoodItem(Foods.APPLE)));

    public static final DeferredItem<Item> ENDERMITE_STEW = ITEMS.register("endermite_stew",
            () -> new ConsumableItem(shulkerBowlFoodItem(Foods.APPLE)));

    public static final DeferredItem<Item> PEARL_PASTA = ITEMS.register("pearl_pasta",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> ENDER_PAELLA = ITEMS.register("ender_paella",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> BOWL_OF_STUFFED_SHULKER = ITEMS.register("bowl_of_stuffed_shulker",
            () -> new ConsumableItem(shulkerBowlFoodItem(Foods.APPLE)));

    public static final DeferredItem<Item> AMBERVEIL_STEW = ITEMS.register("amberveil_stew",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> AMBERVEILED_CURRY = ITEMS.register("amberveiled_curry",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> CHICKEN_CURRY = ITEMS.register("chicken_curry",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> STEAK_FRIES = ITEMS.register("steak_fries",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> VEIL_OF_FLAMES_RISOTTO = ITEMS.register("veil_of_flames_risotto",
            () -> new Item(new Item.Properties()));
}
