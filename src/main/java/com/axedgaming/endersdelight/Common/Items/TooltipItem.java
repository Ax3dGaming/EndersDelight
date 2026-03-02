package com.axedgaming.endersdelight.Common.Items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class TooltipItem extends Item {

    private final String tooltipKey;

    public TooltipItem(Properties properties, String tooltipKey) {
        super(properties);
        this.tooltipKey = tooltipKey;
    }

    @Override
    public void appendHoverText(ItemStack stack,
                                TooltipContext context,
                                List<Component> tooltipComponents,
                                TooltipFlag tooltipFlag) {

        if (tooltipKey != null && !tooltipKey.isEmpty()) {
            tooltipComponents.add(
                    Component.translatable(tooltipKey)
                            .withStyle(ChatFormatting.GRAY)
            );
        }

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
