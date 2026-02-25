package com.axedgaming.endersdelight.Common.Blocks.Bush;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.GameEvent.Context;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.CommonHooks;

public class ChorusFlameBlock extends CustomBushBlock {

    public static final int MAX_AGE = 2;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_2;

    private static final VoxelShape[] SHAPES_BY_AGE = new VoxelShape[] {
            Block.box(4, 0, 4, 12, 8, 12),   // age 0
            Block.box(3, 0, 3, 13, 10, 13),  // age 1
            Block.box(3, 0, 3, 13, 15, 13)   // age 2
    };

    public ChorusFlameBlock(BlockBehaviour.Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(AGE, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(AGE);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(AGE) < MAX_AGE;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        int age = state.getValue(AGE);
        if (age >= MAX_AGE) return;

        if (!CommonHooks.canCropGrow(level, pos, state, random.nextInt(5) == 0)) return;

        BlockState newState = state.setValue(AGE, age + 1);
        level.setBlock(pos, newState, Block.UPDATE_CLIENTS);
        CommonHooks.fireCropGrowPost(level, pos, state);
        level.gameEvent(GameEvent.BLOCK_CHANGE, pos, Context.of(newState));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        int age = state.getValue(AGE);
        return SHAPES_BY_AGE[age];
    }
}
