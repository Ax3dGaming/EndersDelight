package com.axedgaming.endersdelight.Common.Blocks.Bush;

import com.axedgaming.endersdelight.Config;
import com.axedgaming.endersdelight.Common.Blocks.entity.VoidPepperBushBlockEntity;
import com.axedgaming.endersdelight.Common.Registries.EdItems;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.GameEvent.Context;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.util.TriState;

import javax.annotation.Nullable;

public class VoidPepperBush extends BaseEntityBlock {

    public static final MapCodec<VoidPepperBush> CODEC = simpleCodec(VoidPepperBush::new);

    public static final BooleanProperty SPREADING = BooleanProperty.create("spreading");
    public static final BooleanProperty EAST = BooleanProperty.create("east");
    public static final BooleanProperty WEST = BooleanProperty.create("west");
    public static final BooleanProperty NORTH = BooleanProperty.create("north");
    public static final BooleanProperty SOUTH = BooleanProperty.create("south");
    public static final BooleanProperty UP = BooleanProperty.create("up");
    public static final BooleanProperty DOWN = BooleanProperty.create("down");
    public static final BooleanProperty PEPPER = BooleanProperty.create("pepper");

    private static final VoxelShape SHAPE_UP = Block.box(0, 15, 0, 16, 16, 16);
    private static final VoxelShape SHAPE_DOWN = Block.box(0, 0, 0, 16, 1, 16);
    private static final VoxelShape SHAPE_NORTH = Block.box(0, 0, 15, 16, 16, 16);
    private static final VoxelShape SHAPE_SOUTH = Block.box(0, 0, 0, 16, 16, 1);
    private static final VoxelShape SHAPE_EAST = Block.box(0, 0, 0, 1, 16, 16);
    private static final VoxelShape SHAPE_WEST = Block.box(15, 0, 0, 16, 16, 16);
    private static final VoxelShape SHAPE_FALLBACK = Block.box(6, 6, 6, 10, 10, 10);

    public VoidPepperBush(BlockBehaviour.Properties properties) {
        super(properties);

        registerDefaultState(defaultBlockState()
                .setValue(SPREADING, true)
                .setValue(EAST, false)
                .setValue(WEST, false)
                .setValue(NORTH, false)
                .setValue(SOUTH, false)
                .setValue(UP, false)
                .setValue(DOWN, false)
                .setValue(PEPPER, false)
        );
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(SPREADING, EAST, WEST, NORTH, SOUTH, UP, DOWN, PEPPER);
    }

    // --------- BlockEntity ---------

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new VoidPepperBushBlockEntity(pos, state);
    }

    // --------- Placement / survival ---------

    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(Blocks.END_STONE) || state.getBlock() instanceof FarmBlock;
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos belowPos = pos.below();
        BlockState below = level.getBlockState(belowPos);

        TriState decision = below.canSustainPlant(level, belowPos, Direction.UP, state);
        if (!decision.isDefault()) return decision.isTrue();

        return mayPlaceOn(below, level, belowPos);
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction facing, BlockState facingState,
                                     LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        return state.canSurvive(level, currentPos)
                ? super.updateShape(state, facing, facingState, level, currentPos, facingPos)
                : Blocks.AIR.defaultBlockState();
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return stateWithDirections(context.getLevel(), context.getClickedPos());
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        BlockState placed = stateWithDirections(level, pos);
        level.setBlock(pos, placed, Block.UPDATE_CLIENTS);

        if (!level.isClientSide && level.getBlockEntity(pos) instanceof VoidPepperBushBlockEntity be) {
            be.setOrigin(pos);
        }
    }

    private static boolean isEndStone(BlockState state) {
        return state.is(Blocks.END_STONE);
    }

    private static boolean hasAdjacentEndStone(Level level, BlockPos pos) {
        return isEndStone(level.getBlockState(pos.below()))
                || isEndStone(level.getBlockState(pos.above()))
                || isEndStone(level.getBlockState(pos.north()))
                || isEndStone(level.getBlockState(pos.south()))
                || isEndStone(level.getBlockState(pos.east()))
                || isEndStone(level.getBlockState(pos.west()));
    }

    private BlockState stateWithDirections(Level level, BlockPos pos) {
        return defaultBlockState()
                .setValue(DOWN, isEndStone(level.getBlockState(pos.below())))
                .setValue(UP, isEndStone(level.getBlockState(pos.above())))
                .setValue(SOUTH, isEndStone(level.getBlockState(pos.north())))
                .setValue(NORTH, isEndStone(level.getBlockState(pos.south())))
                .setValue(EAST, isEndStone(level.getBlockState(pos.west())))
                .setValue(WEST, isEndStone(level.getBlockState(pos.east())));
    }

    // --------- Random tick / spread / growth ---------

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!state.getValue(SPREADING)) return;

        double spreadChance = clamp01(Config.VOID_PEPPER_SPREAD_CHANCE.get());
        double pepperChance = clamp01(Config.VOID_PEPPER_GROW_PEPPER_CHANCE.get());

        if (random.nextDouble() < spreadChance) {
            trySpread(level, pos, random);
        } else {
            level.setBlock(pos,
                    state.setValue(PEPPER, true).setValue(SPREADING, false),
                    Block.UPDATE_CLIENTS
            );
        }
    }

    private void trySpread(ServerLevel level, BlockPos origin, RandomSource random) {
        int radius = Config.VOID_PEPPER_SPREAD_RADIUS.get();
        int attempts = Config.VOID_PEPPER_SPREAD_ATTEMPTS.get();

        // Safety clamps (avoids insane configs)
        radius = Mth.clamp(radius, 1, 32);
        attempts = Mth.clamp(attempts, 1, 200);

        for (int i = 0; i < attempts; i++) {
            BlockPos target = origin.offset(
                    random.nextInt(radius * 2 + 1) - radius,
                    random.nextInt(radius * 2 + 1) - radius,
                    random.nextInt(radius * 2 + 1) - radius
            );

            if (target.distManhattan(origin) > radius) continue;
            if (!level.isEmptyBlock(target)) continue;
            if (!hasAdjacentEndStone(level, target)) continue;

            BlockState placed = stateWithDirections(level, target);
            level.setBlock(target, placed, Block.UPDATE_CLIENTS);
            level.gameEvent(GameEvent.BLOCK_CHANGE, target, Context.of(placed));

            if (level.getBlockEntity(target) instanceof VoidPepperBushBlockEntity be) {
                be.setOrigin(origin);
            }
            return;
        }
    }

    private static double clamp01(double v) {
        return v < 0.0D ? 0.0D : (v > 1.0D ? 1.0D : v);
    }

    // --------- Harvest : Right Click, Empty Hand ---------

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos,
                                               Player player, BlockHitResult hit) {

        if (!state.getValue(PEPPER)) {
            return InteractionResult.PASS;
        }

        if (!level.isClientSide) {
            popResource(level, pos, new ItemStack(EdItems.VOIDPEPPER.get()));

            level.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES,
                    SoundSource.BLOCKS, 1.0F, 1.0F);

            level.setBlock(pos,
                    state.setValue(PEPPER, false).setValue(SPREADING, true),
                    Block.UPDATE_CLIENTS
            );

            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, Context.of(level.getBlockState(pos)));
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    // --------- Shape ---------

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        VoxelShape shape = Shapes.empty();

        if (state.getValue(UP)) shape = Shapes.or(shape, SHAPE_UP);
        if (state.getValue(DOWN)) shape = Shapes.or(shape, SHAPE_DOWN);
        if (state.getValue(NORTH)) shape = Shapes.or(shape, SHAPE_NORTH);
        if (state.getValue(SOUTH)) shape = Shapes.or(shape, SHAPE_SOUTH);
        if (state.getValue(EAST)) shape = Shapes.or(shape, SHAPE_EAST);
        if (state.getValue(WEST)) shape = Shapes.or(shape, SHAPE_WEST);

        return shape.isEmpty() ? SHAPE_FALLBACK : shape;
    }
}