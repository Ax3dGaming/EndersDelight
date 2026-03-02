
package com.axedgaming.endersdelight.Plugin.JEI;

import com.axedgaming.endersdelight.Common.Registries.EdItems;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

@JeiPlugin
public class JEIPlugin implements IModPlugin {
    private static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath("endersdelight", "jei_plugin");

    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack((ItemLike) EdItems.ENDSTONE_STOVE.get()), new RecipeType[]{RecipeTypes.CAMPFIRE_COOKING});
    }

    public void registerRecipes(IRecipeRegistration registration) {
        registration.addIngredientInfo(new ItemStack((ItemLike)EdItems.SHULKER_MOLLUSK.get()), VanillaTypes.ITEM_STACK, new Component[]{Component.translatable("tooltip.endersdelight.shulker_mollusk")});
        registration.addIngredientInfo(new ItemStack((ItemLike)EdItems.MITE_CRUST.get()), VanillaTypes.ITEM_STACK, new Component[]{Component.translatable("tooltip.endersdelight.mite_crust")});
        registration.addIngredientInfo(new ItemStack((ItemLike)EdItems.ENDERMAN_SIGHT.get()), VanillaTypes.ITEM_STACK, new Component[]{Component.translatable("tooltip.endersdelight.enderman_sight")});
    }

    public ResourceLocation getPluginUid() {
        return ID;
    }
}
