package com.axedgaming.endersdelight.item;

import com.axedgaming.endersdelight.EndersDelight;
import com.axedgaming.endersdelight.block.ModBlocks;
import com.axedgaming.endersdelight.item.food.ChorusJuice;
import com.axedgaming.endersdelight.item.food.Stew;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.common.FoodValues;

import static vectorwing.farmersdelight.common.registry.ModItems.basicItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, EndersDelight.MODID);

    public static final RegistryObject<Item> SHULKER_BOWL = ITEMS.register("shulker_bowl",
            () -> new Item(new Item.Properties().tab(ModCreativeTab.ENDERS_DELIGHT_TAB)));

    public static final RegistryObject<Item> MITE_CRUST = ITEMS.register("mite_crust",
            () -> new Item(new Item.Properties().tab(ModCreativeTab.ENDERS_DELIGHT_TAB).food(ModFoods.DIET)));

    public static final RegistryObject<Item> ENDERMAN_SIGHT = ITEMS.register("enderman_sight",
            () -> new Item(new Item.Properties().tab(ModCreativeTab.ENDERS_DELIGHT_TAB)));

    public static final RegistryObject<Item> SIGHT_FRAGMENT = ITEMS.register("sight_fragment",
            () -> new Item(new Item.Properties().tab(ModCreativeTab.ENDERS_DELIGHT_TAB)));

    public static final RegistryObject<Item> ENDER_SHARD = ITEMS.register("ender_shard",
            () -> new Item(new Item.Properties().tab(ModCreativeTab.ENDERS_DELIGHT_TAB)));

    public static final RegistryObject<Item> SHULKER_MOLLUSK = ITEMS.register("shulker_mollusk",
            () -> new Item(new Item.Properties().tab(ModCreativeTab.ENDERS_DELIGHT_TAB).food(ModFoods.DIET)));

    public static final RegistryObject<Item> SHULKER_FILET = ITEMS.register("shulker_filet",
            () -> new Item(new Item.Properties().tab(ModCreativeTab.ENDERS_DELIGHT_TAB).food(ModFoods.DIET)));

    public static final RegistryObject<Item> CRISPY_SKEWER = ITEMS.register("crispy_skewer",
            () -> new Item(new Item.Properties().food(ModFoods.FINGER).tab(ModCreativeTab.ENDERS_DELIGHT_TAB)));

    public static final RegistryObject<Item> CRAWLING_SANDWICH = ITEMS.register("crawling_sandwich",
            () -> new Item(new Item.Properties().food(ModFoods.CRAWLING_SANDWICH).tab(ModCreativeTab.ENDERS_DELIGHT_TAB)));

    public static final RegistryObject<Item> TWISTED_CEREAL = ITEMS.register("twisted_cereal",
            () -> new Item(new Item.Properties().food(ModFoods.TWISTED_CEREAL).tab(ModCreativeTab.ENDERS_DELIGHT_TAB)));

    public static final RegistryObject<Item> CHORUS_STEW = ITEMS.register("chorus_stew",
            () -> new Stew(new Item.Properties().food(ModFoods.CHORUS_STEW).tab(ModCreativeTab.ENDERS_DELIGHT_TAB), true));

    public static final RegistryObject<Item> CHORUS_STEW_WOOD = ITEMS.register("chorus_stew_wood",
            () -> new Stew(new Item.Properties().food(ModFoods.CHORUS_STEW).tab(ModCreativeTab.ENDERS_DELIGHT_TAB), true));

    public static final RegistryObject<Item> ENDER_PAELLA = ITEMS.register("ender_paella",
            () -> new Item(new Item.Properties().food(ModFoods.ENDER_PAELLA).tab(ModCreativeTab.ENDERS_DELIGHT_TAB)));

    public static final RegistryObject<Item> ENDER_PAELLA_WOOD = ITEMS.register("ender_paella_wood",
            () -> new Item(new Item.Properties().food(ModFoods.ENDER_PAELLA).tab(ModCreativeTab.ENDERS_DELIGHT_TAB)));

    public static final RegistryObject<Item> STRANGE_ECLAIR = ITEMS.register("strange_eclair",
            () -> new Item(new Item.Properties().food(ModFoods.STRANGE_ECLAIR).tab(ModCreativeTab.ENDERS_DELIGHT_TAB)));

    public static final RegistryObject<Item> UNCANNY_COOKIES = ITEMS.register("uncanny_cookies",
            () -> new Item(new Item.Properties().food(ModFoods.UNCANNY_COOKIES).tab(ModCreativeTab.ENDERS_DELIGHT_TAB)));

    public static final RegistryObject<Item> STUFFED_SHULKER_BOWL = ITEMS.register("stuffed_shulker_bowl",
            () -> new Item(new Item.Properties().food(ModFoods.STUFFED_SHULKER).tab(ModCreativeTab.ENDERS_DELIGHT_TAB)));

    public static final RegistryObject<Item> CHORUS_JUICE = ITEMS.register("chorus_juice",
            () -> new ChorusJuice(new Item.Properties().tab(ModCreativeTab.ENDERS_DELIGHT_TAB).food(ModFoods.FINGER)));

    public static final RegistryObject<Item> CHORUS_PIE_SLICE = ITEMS.register("chorus_pie_slice",
            () -> new Item(new Item.Properties().tab(ModCreativeTab.ENDERS_DELIGHT_TAB).food(FoodValues.PIE_SLICE)));

    public static final RegistryObject<Item> PEARL_PASTA = ITEMS.register("pearl_pasta",
            () -> new Item(new Item.Properties().tab(ModCreativeTab.ENDERS_DELIGHT_TAB).food(ModFoods.MEAL)));

    public static final RegistryObject<Item> PEARL_PASTA_WOOD = ITEMS.register("pearl_pasta_wood",
            () -> new Item(new Item.Properties().tab(ModCreativeTab.ENDERS_DELIGHT_TAB).food(ModFoods.MEAL)));

    public static final RegistryObject<Item> ENDERMITE_STEW = ITEMS.register("endermite_stew",
            () -> new Stew(new Item.Properties().tab(ModCreativeTab.ENDERS_DELIGHT_TAB).food(ModFoods.ENDERMITE_STEW),true));

    public static final RegistryObject<Item> ENDERMITE_STEW_WOOD = ITEMS.register("endermite_stew_wood",
            () -> new Stew(new Item.Properties().tab(ModCreativeTab.ENDERS_DELIGHT_TAB).food(ModFoods.ENDERMITE_STEW), true));


    public static final RegistryObject<Item> CHORUS_PIE;
    public static final RegistryObject<Item> STUFFED_SHULKER;
    public static final RegistryObject<Item> ENDSTONE_STOVE;
    public static final RegistryObject<Item> CHORUS_CRATE;

    static {
        CHORUS_CRATE = ITEMS.register("chorus_crate", () -> {
            return new BlockItem((Block) ModBlocks.CHORUS_CRATE.get(), basicItem().tab(ModCreativeTab.ENDERS_DELIGHT_TAB));
        });
        ENDSTONE_STOVE = ITEMS.register("endstone_stove", () -> {
            return new BlockItem((Block) ModBlocks.ENDSTONE_STOVE.get(), basicItem().tab(ModCreativeTab.ENDERS_DELIGHT_TAB));
        });
        CHORUS_PIE = ITEMS.register("chorus_pie", () -> {
            return new BlockItem((Block) ModBlocks.CHORUS_PIE.get(), basicItem().stacksTo(1).tab(ModCreativeTab.ENDERS_DELIGHT_TAB));
        });

        STUFFED_SHULKER = ITEMS.register("stuffed_shulker", () -> {
            return new BlockItem((Block) ModBlocks.STUFFED_SHULKER_BLOCK.get(), basicItem().stacksTo(1).tab(ModCreativeTab.ENDERS_DELIGHT_TAB));
        });
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
