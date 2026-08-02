package com.axedgaming.endersdelight.item;

import com.axedgaming.endersdelight.EndersDelight;
import com.axedgaming.endersdelight.block.ModBlocks;
import com.axedgaming.endersdelight.item.food.ChorusJuice;
import com.axedgaming.endersdelight.item.food.Stew;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.FoodValues;
import vectorwing.farmersdelight.common.item.ConsumableItem;

import javax.annotation.Nullable;
import java.util.List;

import static vectorwing.farmersdelight.common.registry.ModItems.basicItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, EndersDelight.MODID);

    public static Item.Properties shulkerBowlFoodItem(FoodProperties food) {
        return (new Item.Properties()).food(food).craftRemainder(ModItems.SHULKER_BOWL.get()).stacksTo(16);
    }

    public static final RegistryObject<Item> ENDSTONE_STOVE;
    public static final RegistryObject<Item> CHORUS_PIE;
    public static final RegistryObject<Item> CHORUS_CRATE;
    public static final RegistryObject<Item> STUFFED_SHULKER;
    public static final RegistryObject<Item> ETHEREAL_SAFFRON;
    public static final RegistryObject<Item> AMBERVEIL;
    public static final RegistryObject<Item> CHORUS_FLAME;
    public static final RegistryObject<Item> VOIDPEPPER;

    static {
        CHORUS_PIE = ITEMS.register("chorus_pie", () -> new BlockItem((Block) ModBlocks.CHORUS_PIE.get(), basicItem().stacksTo(1)));
        CHORUS_CRATE = ITEMS.register("chorus_crate", () -> new BlockItem((Block) ModBlocks.CHORUS_CRATE.get(), basicItem()));
        STUFFED_SHULKER = ITEMS.register("stuffed_shulker", () -> new BlockItem((Block) ModBlocks.STUFFED_SHULKER_BLOCK.get(), basicItem().stacksTo(1)));
        ENDSTONE_STOVE = ITEMS.register("endstone_stove", () -> new BlockItem((Block) ModBlocks.ENDSTONE_STOVE.get(), basicItem()));
        ETHEREAL_SAFFRON = ITEMS.register("ethereal_saffron", () -> new BlockItem((Block) ModBlocks.ETHEREAL_SAFFRON_BLOCK.get(), basicItem()));
        AMBERVEIL = ITEMS.register("amberveil", () -> new BlockItem((Block) ModBlocks.AMBERVEIL_MUSHROOM.get(), basicItem()));
        CHORUS_FLAME = ITEMS.register("chorusflame", () -> new BlockItem((Block) ModBlocks.CHORUS_FLAME_BLOCK.get(), basicItem()));
        VOIDPEPPER = ITEMS.register("voidpepper", () -> new BlockItem((Block) ModBlocks.VOIDPEPPER_BLOCK.get(), basicItem()));
    }

    public static final RegistryObject<Item> SHULKER_BOWL = ITEMS.register("shulker_bowl",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MITE_CRUST = ITEMS.register("mite_crust",
            () -> new Item(new Item.Properties().food(ModFoods.DIET)) {
                @Override
                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
                    super.appendHoverText(stack, level, tooltip, flag);
                    tooltip.add(Component.translatable("endersdelight.jei.info.mite_crust").withStyle(ChatFormatting.GRAY));
                }

            });

    public static final RegistryObject<Item> ENDER_SHARD = ITEMS.register("ender_shard",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ENDERMAN_SIGHT = ITEMS.register("enderman_sight",
            () -> new Item(new Item.Properties()) {
                @Override
                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
                    super.appendHoverText(stack, level, tooltip, flag);
                    tooltip.add(Component.translatable("endersdelight.jei.info.enderman_sight").withStyle(ChatFormatting.GRAY));
                }

            });

    public static final RegistryObject<Item> SIGHT_FRAGMENT = ITEMS.register("sight_fragment",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SHULKER_MOLLUSK = ITEMS.register("shulker_mollusk",
            () -> new Item(new Item.Properties().food(ModFoods.DIET)) {
                @Override
                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
                    super.appendHoverText(stack, level, tooltip, flag);
                    tooltip.add(Component.translatable("endersdelight.jei.info.shulker_mollusk").withStyle(ChatFormatting.GRAY));
                }

            });

    public static final RegistryObject<Item> SHULKER_FILET = ITEMS.register("shulker_filet",
            () -> new Item(new Item.Properties().food(ModFoods.DIET)));

    public static final RegistryObject<Item> UNCANNY_COOKIES = ITEMS.register("uncanny_cookies",
            () -> new Item(new Item.Properties().food(ModFoods.UNCANNY_COOKIES)));

    public static final RegistryObject<Item> CHORUS_JUICE = ITEMS.register("chorus_juice",
            () -> new DrinkableItem(new Item.Properties().food(ModFoods.FINGER).stacksTo(16)));

    public static final RegistryObject<Item> STRANGE_ECLAIR = ITEMS.register("strange_eclair",
            () -> new Item(new Item.Properties().food(ModFoods.STRANGE_ECLAIR)));

    public static final RegistryObject<Item> CHORUS_PIE_SLICE = ITEMS.register("chorus_pie_slice",
            () -> new Item(new Item.Properties().food(FoodValues.PIE_SLICE)));

    public static final RegistryObject<Item> CHORUS_STEW = ITEMS.register("chorus_stew",
            () -> new com.axedgaming.endersdelight.item.ConsumableItem(shulkerBowlFoodItem(ModFoods.CHORUS_STEW)));

    public static final RegistryObject<Item> CRISPY_SKEWER = ITEMS.register("crispy_skewer",
            () -> new Item(new Item.Properties().food(ModFoods.FINGER)));

    public static final RegistryObject<Item> CRAWLING_SANDWICH = ITEMS.register("crawling_sandwich",
            () -> new Item(new Item.Properties().food(ModFoods.CRAWLING_SANDWICH)));

    public static final RegistryObject<Item> CRUSHED_VOIDPEPPER = ITEMS.register("crushed_voidpepper",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ENDERMITE_STEW = ITEMS.register("endermite_stew",
            () -> new ConsumableItem(shulkerBowlFoodItem(ModFoods.ENDERMITE_STEW)));

    public static final RegistryObject<Item> TWISTED_CEREAL = ITEMS.register("twisted_cereal",
            () -> new ConsumableItem(shulkerBowlFoodItem(ModFoods.TWISTED_CEREAL), true));

    public static final RegistryObject<Item> PEARL_PASTA = ITEMS.register("pearl_pasta",
            () -> new ConsumableItem(shulkerBowlFoodItem(ModFoods.PEARL_PASTA), true));

    public static final RegistryObject<Item> ENDER_PAELLA = ITEMS.register("ender_paella",
            () -> new ConsumableItem(shulkerBowlFoodItem(ModFoods.ENDER_PAELLA), true));

    public static final RegistryObject<Item> STUFFED_SHULKER_BOWL = ITEMS.register("stuffed_shulker_bowl",
            () -> new ConsumableItem(shulkerBowlFoodItem(ModFoods.STUFFED_SHULKER), true));

    public static final RegistryObject<Item> AMBERVEIL_STEW = ITEMS.register("amberveil_stew",
            () -> new ConsumableItem(shulkerBowlFoodItem(ModFoods.AMBERVEIL_STEW), true));

    public static final RegistryObject<Item> AMBERVEILED_CURRY = ITEMS.register("amberveiled_curry",
            () -> new ConsumableItem(shulkerBowlFoodItem(ModFoods.AMBERVEILED_CURRY), true));

    public static final RegistryObject<Item> CHICKEN_CURRY = ITEMS.register("chicken_curry",
            () -> new ConsumableItem(shulkerBowlFoodItem(ModFoods.CHICKEN), true));

    public static final RegistryObject<Item> STEAK_FRIES = ITEMS.register("steak_fries",
            () -> new ConsumableItem(shulkerBowlFoodItem(ModFoods.STEAK_FRIES), false));

    public static final RegistryObject<Item> VEIL_OF_FLAMES_RISOTTO = ITEMS.register("veil_of_flames_risotto",
            () -> new ConsumableItem(shulkerBowlFoodItem(ModFoods.RISOTTO), true));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
