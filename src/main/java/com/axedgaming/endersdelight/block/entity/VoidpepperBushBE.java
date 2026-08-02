package com.axedgaming.endersdelight.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class VoidpepperBushBE extends BlockEntity {
    private BlockPos origin;

    // Constructeur avec paramètres pour l'utilisation personnalisée
    public VoidpepperBushBE(BlockPos pPos, BlockState pState, BlockPos origin) {
        super(ModBlockEntityTypes.VOIDPEPPER_BUSH_BE.get(), pPos, pState);
        this.origin = origin;
    }

    // Getter pour l'origine
    public BlockPos getOrigin() {
        return origin;
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        int x = tag.getInt("OriginX");
        int y = tag.getInt("OriginY");
        int z = tag.getInt("OriginZ");
        origin = new BlockPos(x, y, z);
    }

    @Override
    public void saveAdditional(CompoundTag tag) {  // Utilisez saveAdditional à la place de save
        super.saveAdditional(tag); // Appeler la méthode de base pour sauvegarder les données de l'entité
        tag.putInt("OriginX", origin.getX());
        tag.putInt("OriginY", origin.getY());
        tag.putInt("OriginZ", origin.getZ());
    }
}
