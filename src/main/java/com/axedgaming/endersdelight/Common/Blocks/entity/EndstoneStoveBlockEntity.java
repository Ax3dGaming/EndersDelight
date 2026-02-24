package com.axedgaming.endersdelight.Common.Blocks.entity;

import java.util.Optional;

import com.axedgaming.endersdelight.Common.Blocks.EndstoneStoveBlock;
import com.axedgaming.endersdelight.Common.Registries.EdBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.items.ItemStackHandler;
import vectorwing.farmersdelight.common.block.StoveBlock;
import vectorwing.farmersdelight.common.block.entity.SyncedBlockEntity;
import vectorwing.farmersdelight.common.utility.ItemUtils;

public class EndstoneStoveBlockEntity extends SyncedBlockEntity {
    private static final VoxelShape GRILLING_AREA = Block.box((double)3.0F, (double)0.0F, (double)3.0F, (double)13.0F, (double)1.0F, (double)13.0F);
    private static final int INVENTORY_SLOT_COUNT = 6;
    private final ItemStackHandler inventory = this.createHandler();
    private final int[] cookingTimes = new int[INVENTORY_SLOT_COUNT];
    private final int[] cookingTimesTotal = new int[INVENTORY_SLOT_COUNT];
    private final RecipeManager.CachedCheck<SingleRecipeInput, CampfireCookingRecipe> quickCheck;

    public EndstoneStoveBlockEntity(BlockPos pos, BlockState state) {
        super(EdBlockEntityTypes.ENDSTONE_STOVE.get(), pos, state);
        this.quickCheck = RecipeManager.createCheck(RecipeType.CAMPFIRE_COOKING);
    }

    public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        if (tag.contains("Inventory")) {
            this.inventory.deserializeNBT(registries, tag.getCompound("Inventory"));
        } else {
            this.inventory.deserializeNBT(registries, tag);
        }

        if (tag.contains("CookingTimes", 11)) {
            int[] arrayCookingTimes = tag.getIntArray("CookingTimes");
            System.arraycopy(arrayCookingTimes, 0, this.cookingTimes, 0, Math.min(this.cookingTimesTotal.length, arrayCookingTimes.length));
        }

        if (tag.contains("CookingTotalTimes", 11)) {
            int[] arrayCookingTimesTotal = tag.getIntArray("CookingTotalTimes");
            System.arraycopy(arrayCookingTimesTotal, 0, this.cookingTimesTotal, 0, Math.min(this.cookingTimesTotal.length, arrayCookingTimesTotal.length));
        }

    }

    public void saveAdditional(CompoundTag compound, HolderLookup.Provider registries) {
        this.writeItems(compound, registries);
        compound.putIntArray("CookingTimes", this.cookingTimes);
        compound.putIntArray("CookingTotalTimes", this.cookingTimesTotal);
    }

    private CompoundTag writeItems(CompoundTag compound, HolderLookup.Provider registries) {
        super.saveAdditional(compound, registries);
        compound.put("Inventory", this.inventory.serializeNBT(registries));
        return compound;
    }

    public static void cookingTick(Level level, BlockPos pos, BlockState state, EndstoneStoveBlockEntity stove) {
        boolean isStoveLit = (Boolean)state.getValue(EndstoneStoveBlock.LIT);
        if (stove.isStoveBlockedAbove()) {
            if (!ItemUtils.isInventoryEmpty(stove.inventory)) {
                ItemUtils.dropItems(level, pos, stove.inventory);
                stove.inventoryChanged();
            }
        } else if (isStoveLit) {
            stove.cookAndOutputItems();
        } else {
            for(int i = 0; i < stove.inventory.getSlots(); ++i) {
                if (stove.cookingTimes[i] > 0) {
                    stove.cookingTimes[i] = Mth.clamp(stove.cookingTimes[i] - 2, 0, stove.cookingTimesTotal[i]);
                }
            }
        }

    }

    public static void animationTick(Level level, BlockPos pos, BlockState state, EndstoneStoveBlockEntity stove) {
        for(int i = 0; i < stove.inventory.getSlots(); ++i) {
            if (!stove.inventory.getStackInSlot(i).isEmpty() && level.random.nextFloat() < 0.2F) {
                Vec2 stoveItemVector = stove.getStoveItemOffset(i);
                Direction direction = (Direction)state.getValue(EndstoneStoveBlock.FACING);
                int directionIndex = direction.get2DDataValue();
                Vec2 offset = directionIndex % 2 == 0 ? stoveItemVector : new Vec2(stoveItemVector.y, stoveItemVector.x);
                double x = (double)pos.getX() + (double)0.5F - (double)((float)direction.getStepX() * offset.x) + (double)((float)direction.getClockWise().getStepX() * offset.x);
                double y = (double)pos.getY() + (double)1.0F;
                double z = (double)pos.getZ() + (double)0.5F - (double)((float)direction.getStepZ() * offset.y) + (double)((float)direction.getClockWise().getStepZ() * offset.y);

                for(int k = 0; k < 3; ++k) {
                    level.addParticle(ParticleTypes.SMOKE, x, y, z, (double)0.0F, 5.0E-4, (double)0.0F);
                }
            }
        }

    }

    private void cookAndOutputItems() {
        if (this.level != null) {
            boolean didInventoryChange = false;

            for(int i = 0; i < this.inventory.getSlots(); ++i) {
                ItemStack stoveStack = this.inventory.getStackInSlot(i);
                if (!stoveStack.isEmpty()) {
                    int var10002 = this.cookingTimes[i]++;
                    if (this.cookingTimes[i] >= this.cookingTimesTotal[i]) {
                        Optional<RecipeHolder<CampfireCookingRecipe>> recipe = this.getMatchingRecipe(stoveStack);
                        if (recipe.isPresent()) {
                            ItemStack resultStack = ((CampfireCookingRecipe)((RecipeHolder)recipe.get()).value()).getResultItem(this.level.registryAccess());
                            if (!resultStack.isEmpty()) {
                                ItemUtils.spawnItemEntity(this.level, resultStack.copy(), (double)this.worldPosition.getX() + (double)0.5F, (double)this.worldPosition.getY() + (double)1.0F, (double)this.worldPosition.getZ() + (double)0.5F, this.level.random.nextGaussian() * (double)0.01F, (double)0.1F, this.level.random.nextGaussian() * (double)0.01F);
                            }
                        }

                        this.inventory.setStackInSlot(i, ItemStack.EMPTY);
                        didInventoryChange = true;
                    }
                }
            }

            if (didInventoryChange) {
                this.inventoryChanged();
            }

        }
    }

    public int getNextEmptySlot() {
        for(int i = 0; i < this.inventory.getSlots(); ++i) {
            ItemStack slotStack = this.inventory.getStackInSlot(i);
            if (slotStack.isEmpty()) {
                return i;
            }
        }

        return -1;
    }

    public boolean addItem(ItemStack itemStackIn, RecipeHolder<CampfireCookingRecipe> recipe, int slot) {
        if (0 <= slot && slot < this.inventory.getSlots()) {
            ItemStack slotStack = this.inventory.getStackInSlot(slot);
            if (slotStack.isEmpty()) {
                this.cookingTimesTotal[slot] = ((CampfireCookingRecipe)recipe.value()).getCookingTime();
                this.cookingTimes[slot] = 0;
                this.inventory.setStackInSlot(slot, itemStackIn.split(1));
                this.inventoryChanged();
                return true;
            }
        }

        return false;
    }

    public Optional<RecipeHolder<CampfireCookingRecipe>> getMatchingRecipe(ItemStack stack) {
        return this.level == null ? Optional.empty() : this.quickCheck.getRecipeFor(new SingleRecipeInput(stack), this.level);
    }

    public ItemStackHandler getInventory() {
        return this.inventory;
    }

    public boolean isStoveBlockedAbove() {
        if (this.level != null) {
            BlockState above = this.level.getBlockState(this.worldPosition.above());
            return Shapes.joinIsNotEmpty(GRILLING_AREA, above.getShape(this.level, this.worldPosition.above()), BooleanOp.AND);
        } else {
            return false;
        }
    }

    public Vec2 getStoveItemOffset(int index) {
        float X_OFFSET = 0.3F;
        float Y_OFFSET = 0.2F;
        Vec2[] OFFSETS = new Vec2[]{new Vec2(0.3F, 0.2F), new Vec2(0.0F, 0.2F), new Vec2(-0.3F, 0.2F), new Vec2(0.3F, -0.2F), new Vec2(0.0F, -0.2F), new Vec2(-0.3F, -0.2F)};
        return OFFSETS[index];
    }

    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return this.writeItems(new CompoundTag(), registries);
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(6) {
            public int getSlotLimit(int slot) {
                return 1;
            }
        };
    }
}
