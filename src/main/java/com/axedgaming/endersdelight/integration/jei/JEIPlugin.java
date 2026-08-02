package com.axedgaming.endersdelight.integration.jei;

import com.axedgaming.endersdelight.EndersDelight;
import com.axedgaming.endersdelight.item.ModItems;
import com.axedgaming.endersdelight.util.EDTextUtil;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;

@JeiPlugin
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@SuppressWarnings("unused")
public class JEIPlugin implements IModPlugin {
    private static final ResourceLocation ID = new ResourceLocation(EndersDelight.MODID, "jei_plugin");

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addIngredientInfo(new ItemStack(ModItems.MITE_CRUST.get()), VanillaTypes.ITEM_STACK, EDTextUtil.getTranslation("jei.info.mite_crust"));
        registration.addIngredientInfo(new ItemStack(ModItems.SHULKER_MOLLUSK.get()), VanillaTypes.ITEM_STACK, EDTextUtil.getTranslation("jei.info.shulker_mollusk"));
        registration.addIngredientInfo(new ItemStack(ModItems.ENDERMAN_SIGHT.get()), VanillaTypes.ITEM_STACK, EDTextUtil.getTranslation("jei.info.enderman_sight"));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModItems.ENDSTONE_STOVE.get()), RecipeTypes.CAMPFIRE_COOKING);
    }

    @Override
    public ResourceLocation getPluginUid() {
        return ID;
    }
}
