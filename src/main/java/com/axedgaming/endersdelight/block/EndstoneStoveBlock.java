package com.axedgaming.endersdelight.block;

import com.axedgaming.endersdelight.block.entity.EndstoneStoveBlockEntity;
import com.axedgaming.endersdelight.registry.EDBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolActions;
import vectorwing.farmersdelight.common.block.entity.StoveBlockEntity;
import vectorwing.farmersdelight.common.registry.ModDamageTypes;
import vectorwing.farmersdelight.common.registry.ModSounds;
import vectorwing.farmersdelight.common.utility.ItemUtils;
import vectorwing.farmersdelight.common.utility.MathUtils;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.Random;

@SuppressWarnings("deprecation")
public class EndstoneStoveBlock extends BaseEntityBlock {
    public static final BooleanProperty LIT;
    public static final DirectionProperty FACING;

    public EndstoneStoveBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(FACING, Direction.NORTH)).setValue(LIT, false));
    }

    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack heldStack = player.getItemInHand(hand);
        Item heldItem = heldStack.getItem();
        if ((Boolean)state.getValue(LIT)) {
            if (heldStack.canPerformAction(ToolActions.SHOVEL_DIG)) {
                this.extinguish(state, level, pos);
                heldStack.hurtAndBreak(1, player, (action) -> {
                    action.broadcastBreakEvent(hand);
                });
                return InteractionResult.SUCCESS;
            }

            if (heldItem == Items.WATER_BUCKET) {
                if (!level.isClientSide()) {
                    level.playSound((Player)null, pos, SoundEvents.GENERIC_EXTINGUISH_FIRE, SoundSource.BLOCKS, 1.0F, 1.0F);
                }

                this.extinguish(state, level, pos);
                if (!player.isCreative()) {
                    player.setItemInHand(hand, new ItemStack(Items.BUCKET));
                }

                return InteractionResult.SUCCESS;
            }
        } else {
            if (heldItem instanceof FlintAndSteelItem) {
                level.playSound(player, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, MathUtils.RAND.nextFloat() * 0.4F + 0.8F);
                level.setBlock(pos, (BlockState)state.setValue(BlockStateProperties.LIT, Boolean.TRUE), 11);
                heldStack.hurtAndBreak(1, player, (action) -> {
                    action.broadcastBreakEvent(hand);
                });
                return InteractionResult.SUCCESS;
            }

            if (heldItem instanceof FireChargeItem) {
                level.playSound((Player)null, pos, SoundEvents.FIRECHARGE_USE, SoundSource.BLOCKS, 1.0F, (MathUtils.RAND.nextFloat() - MathUtils.RAND.nextFloat()) * 0.2F + 1.0F);
                level.setBlock(pos, (BlockState)state.setValue(BlockStateProperties.LIT, Boolean.TRUE), 11);
                if (!player.isCreative()) {
                    heldStack.shrink(1);
                }

                return InteractionResult.SUCCESS;
            }
        }

        BlockEntity tileEntity = level.getBlockEntity(pos);
        if (tileEntity instanceof EndstoneStoveBlockEntity stoveEntity) {
            int stoveSlot = stoveEntity.getNextEmptySlot();
            if (stoveSlot < 0 || stoveEntity.isStoveBlockedAbove()) {
                return InteractionResult.PASS;
            }

            Optional<CampfireCookingRecipe> recipe = stoveEntity.getMatchingRecipe(new SimpleContainer(new ItemStack[]{heldStack}), stoveSlot);
            if (recipe.isPresent()) {
                if (!level.isClientSide && stoveEntity.addItem(player.getAbilities().instabuild ? heldStack.copy() : heldStack, (CampfireCookingRecipe)recipe.get(), stoveSlot)) {
                    return InteractionResult.SUCCESS;
                }

                return InteractionResult.CONSUME;
            }
        }

        return InteractionResult.PASS;
    }

    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    public void extinguish(BlockState state, Level level, BlockPos pos) {
        level.setBlock(pos, (BlockState)state.setValue(LIT, false), 2);
        double x = (double)pos.getX() + 0.5;
        double y = (double)pos.getY();
        double z = (double)pos.getZ() + 0.5;
        level.playLocalSound(x, y, z, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 0.5F, 2.6F, false);
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return (BlockState)((BlockState)this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite())).setValue(LIT, true);
    }

    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        boolean isLit = (Boolean)level.getBlockState(pos).getValue(LIT);
        if (isLit && !entity.fireImmune() && entity instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity)entity)) {
            entity.hurt(ModDamageTypes.getSimpleDamageSource(level, ModDamageTypes.STOVE_BURN), 1.0F);
        }

        super.stepOn(level, pos, state, entity);
    }

    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity tileEntity = level.getBlockEntity(pos);
            if (tileEntity instanceof EndstoneStoveBlockEntity) {
                ItemUtils.dropItems(level, pos, ((EndstoneStoveBlockEntity)tileEntity).getInventory());
            }

            super.onRemove(state, level, pos, newState, isMoving);
        }

    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(new Property[]{LIT, FACING});
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, Level level, BlockPos pos, RandomSource rand) {
        if ((Boolean)stateIn.getValue(CampfireBlock.LIT)) {
            double x = (double)pos.getX() + 0.5;
            double y = (double)pos.getY();
            double z = (double)pos.getZ() + 0.5;
            if (rand.nextInt(10) == 0) {
                level.playLocalSound(x, y, z, (SoundEvent)ModSounds.BLOCK_STOVE_CRACKLE.get(), SoundSource.BLOCKS, 1.0F, 1.0F, false);
            }

            Direction direction = (Direction)stateIn.getValue(HorizontalDirectionalBlock.FACING);
            Direction.Axis direction$axis = direction.getAxis();
            double horizontalOffset = rand.nextDouble() * 0.6 - 0.3;
            double xOffset = direction$axis == Direction.Axis.X ? (double)direction.getStepX() * 0.52 : horizontalOffset;
            double yOffset = rand.nextDouble() * 6.0 / 16.0;
            double zOffset = direction$axis == Direction.Axis.Z ? (double)direction.getStepZ() * 0.52 : horizontalOffset;
            level.addParticle(ParticleTypes.SMOKE, x + xOffset, y + yOffset, z + zOffset, 0.0, 0.0, 0.0);
            level.addParticle(ParticleTypes.FLAME, x + xOffset, y + yOffset, z + zOffset, 0.0, 0.0, 0.0);
        }

    }

    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ((BlockEntityType) EDBlockEntityTypes.ENDSTONE_STOVE.get()).create(pos, state);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return (Boolean)state.getValue(LIT) ? createTickerHelper(blockEntityType, (BlockEntityType) EDBlockEntityTypes.ENDSTONE_STOVE.get(), level.isClientSide ? EndstoneStoveBlockEntity::animationTick : EndstoneStoveBlockEntity::cookingTick) : null;
    }

    @Nullable
    public BlockPathTypes getBlockPathType(BlockState state, BlockGetter world, BlockPos pos, @Nullable Mob entity) {
        return (Boolean)state.getValue(LIT) ? BlockPathTypes.DAMAGE_FIRE : null;
    }

    public BlockState rotate(BlockState pState, Rotation pRot) {
        return (BlockState)pState.setValue(FACING, pRot.rotate((Direction)pState.getValue(FACING)));
    }

    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation((Direction)pState.getValue(FACING)));
    }

    static {
        LIT = BlockStateProperties.LIT;
        FACING = BlockStateProperties.HORIZONTAL_FACING;
    }
}