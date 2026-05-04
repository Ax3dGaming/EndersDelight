package com.axedgaming.endersdelight.item;

import com.axedgaming.endersdelight.EndersDelight;
import com.axedgaming.endersdelight.block.ModBlocks;
import com.axedgaming.endersdelight.item.food.ChorusJuice;
import com.axedgaming.endersdelight.item.food.Stew;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.FoodValues;
import vectorwing.farmersdelight.common.item.ConsumableItem;

import static vectorwing.farmersdelight.common.registry.ModItems.basicItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, EndersDelight.MODID);

    public static Item.Properties bowlFoodItem(FoodProperties food) {
        return (new Item.Properties()).food(food).craftRemainder(Items.BOWL).stacksTo(16);
    }

    public static Item.Properties shulkerBowlFoodItem(FoodProperties food) {
        return (new Item.Properties()).food(food).craftRemainder(ModItems.SHULKER_BOWL.get()).stacksTo(16);
    }

    public static final RegistryObject<Item> ENDSTONE_STOVE;
    public static final RegistryObject<Item> CHORUS_PIE;
    public static final RegistryObject<Item> CHORUS_CRATE;
    public static final RegistryObject<Item> STUFFED_SHULKER;

    static {
        CHORUS_PIE = ITEMS.register("chorus_pie", () -> new BlockItem((Block) ModBlocks.CHORUS_PIE.get(), basicItem().stacksTo(1)));
        CHORUS_CRATE = ITEMS.register("chorus_crate", () -> new BlockItem((Block) ModBlocks.CHORUS_CRATE.get(), basicItem()));
        STUFFED_SHULKER = ITEMS.register("stuffed_shulker", () -> new BlockItem((Block) ModBlocks.STUFFED_SHULKER_BLOCK.get(), basicItem()));
        ENDSTONE_STOVE = ITEMS.register("endstone_stove", () -> new BlockItem((Block) ModBlocks.ENDSTONE_STOVE.get(), basicItem()));
    }

    public static final RegistryObject<Item> SHULKER_BOWL = ITEMS.register("shulker_bowl",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MITE_CRUST = ITEMS.register("mite_crust",
            () -> new Item(new Item.Properties().food(ModFoods.DIET)));

    public static final RegistryObject<Item> ENDER_SHARD = ITEMS.register("ender_shard",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ENDERMAN_SIGHT = ITEMS.register("enderman_sight",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SIGHT_FRAGMENT = ITEMS.register("sight_fragment",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SHULKER_MOLLUSK = ITEMS.register("shulker_mollusk",
            () -> new Item(new Item.Properties().food(ModFoods.DIET)));

    public static final RegistryObject<Item> SHULKER_FILET = ITEMS.register("shulker_filet",
            () -> new Item(new Item.Properties().food(ModFoods.DIET)));

    public static final RegistryObject<Item> UNCANNY_COOKIES = ITEMS.register("uncanny_cookies",
            () -> new Item(new Item.Properties().food(ModFoods.UNCANNY_COOKIES)));


    public static final RegistryObject<Item> CHORUS_JUICE = ITEMS.register("chorus_juice",
            () -> new DrinkableItem(new Item.Properties().food(ModFoods.FINGER)));

    public static final RegistryObject<Item> STRANGE_ECLAIR = ITEMS.register("strange_eclair",
            () -> new Item(new Item.Properties().food(ModFoods.STRANGE_ECLAIR)));

    public static final RegistryObject<Item> CHORUS_PIE_SLICE = ITEMS.register("chorus_pie_slice",
            () -> new Item(new Item.Properties().food(FoodValues.PIE_SLICE)));

    public static final RegistryObject<Item> CHORUS_STEW_WOOD = ITEMS.register("chorus_stew_wood",
            () -> new com.axedgaming.endersdelight.item.ConsumableItem(bowlFoodItem(ModFoods.CHORUS_STEW)));

    public static final RegistryObject<Item> CHORUS_STEW = ITEMS.register("chorus_stew",
            () -> new com.axedgaming.endersdelight.item.ConsumableItem(shulkerBowlFoodItem(ModFoods.CHORUS_STEW)));

    public static final RegistryObject<Item> CRISPY_SKEWER = ITEMS.register("crispy_skewer",
            () -> new Item(new Item.Properties().food(ModFoods.FINGER)));

    public static final RegistryObject<Item> CRAWLING_SANDWICH = ITEMS.register("crawling_sandwich",
            () -> new Item(new Item.Properties().food(ModFoods.CRAWLING_SANDWICH)));

    public static final RegistryObject<Item> TWISTED_CEREAL_WOOD;

    static {
        TWISTED_CEREAL_WOOD = ITEMS.register("twisted_cereal_wood", () -> {
            return new ConsumableItem(bowlFoodItem(ModFoods.TWISTED_CEREAL), true);
        });
    }

    public static final RegistryObject<Item> ENDERMITE_STEW_WOOD = ITEMS.register("endermite_stew_wood",
            () -> new com.axedgaming.endersdelight.item.ConsumableItem(bowlFoodItem(ModFoods.ENDERMITE_STEW)));

    public static final RegistryObject<Item> PEARL_PASTA_WOOD;

    static {
        PEARL_PASTA_WOOD = ITEMS.register("pearl_pasta_wood", () -> {
            return new ConsumableItem(bowlFoodItem(ModFoods.PEARL_PASTA), true);
        });
    }

    public static final RegistryObject<Item> ENDER_PAELLA_WOOD;

    static {
        ENDER_PAELLA_WOOD = ITEMS.register("ender_paella_wood", () -> {
            return new ConsumableItem(bowlFoodItem(ModFoods.ENDER_PAELLA), true);
        });
    }

    public static final RegistryObject<Item> TWISTED_CEREAL;

    static {
        TWISTED_CEREAL = ITEMS.register("twisted_cereal", () -> {
            return new ConsumableItem(shulkerBowlFoodItem(ModFoods.TWISTED_CEREAL), true);
        });
    }

    public static final RegistryObject<Item> ENDERMITE_STEW = ITEMS.register("endermite_stew",
            () -> new com.axedgaming.endersdelight.item.ConsumableItem(shulkerBowlFoodItem(ModFoods.ENDERMITE_STEW)));

    public static final RegistryObject<Item> PEARL_PASTA;

    static {
        PEARL_PASTA = ITEMS.register("pearl_pasta", () -> {
            return new ConsumableItem(shulkerBowlFoodItem(ModFoods.PEARL_PASTA), true);
        });
    }

    public static final RegistryObject<Item> ENDER_PAELLA;

    static {
        ENDER_PAELLA = ITEMS.register("ender_paella", () -> {
            return new ConsumableItem(shulkerBowlFoodItem(ModFoods.ENDER_PAELLA), true);
        });
    }

    public static final RegistryObject<Item> STUFFED_SHULKER_BOWL;

    static {
        STUFFED_SHULKER_BOWL = ITEMS.register("stuffed_shulker_bowl", () -> {
            return new ConsumableItem(bowlFoodItem(ModFoods.STUFFED_SHULKER), true);
        });
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
