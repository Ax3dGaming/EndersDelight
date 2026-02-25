package com.axedgaming.endersdelight.Common.Blocks.entity;

import com.axedgaming.endersdelight.Common.Registries.EdBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class VoidPepperBushBlockEntity extends BlockEntity {
    private static final String TAG_ORIGIN = "Origin";

    @Nullable
    private BlockPos origin;

    public VoidPepperBushBlockEntity(BlockPos pos, BlockState state) {
        super(EdBlockEntityTypes.VOID_PEPPER_BUSH_BLOCK_ENTITY.get(), pos, state);
        this.origin = pos;
    }

    public BlockPos getOrigin() {
        return origin == null ? worldPosition : origin;
    }

    public void setOrigin(BlockPos origin) {
        this.origin = origin;
        setChanged();
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putLong(TAG_ORIGIN, getOrigin().asLong());
    }

    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        if (tag.contains(TAG_ORIGIN)) {
            this.origin = BlockPos.of(tag.getLong(TAG_ORIGIN));
        } else {
            this.origin = worldPosition;
        }
    }
}