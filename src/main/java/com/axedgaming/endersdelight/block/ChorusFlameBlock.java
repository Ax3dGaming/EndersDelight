package com.axedgaming.endersdelight.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ChorusFlameBlock extends CustomBushBlock {
    public static final int MAX_AGE = 2;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_2;

    public ChorusFlameBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE); // S'assurer que AGE est bien ajouté au BlockState
    }

    @Override
    public boolean isRandomlyTicking(BlockState pState) {
        return pState.getValue(AGE) < MAX_AGE;
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        int currentAge = pState.getValue(AGE);

        // Remplaçons CommonHooks.canCropGrow par une logique personnalisée (ex : chance aléatoire)
        if (currentAge < MAX_AGE && pRandom.nextInt(5) == 0) {

            BlockState newState = pState.setValue(AGE, currentAge + 1);
            pLevel.setBlock(pPos, newState, 2); // Met à jour l'état du bloc avec un changement de niveau

            // Nous avons supprimé fireCropGrowPost (NeoForge) car non nécessaire dans Forge
            pLevel.gameEvent(GameEvent.BLOCK_CHANGE, pPos, GameEvent.Context.of(newState)); // Émet un événement de changement de bloc
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        int age = state.getValue(AGE);
        switch (age) {
            case 0: return Block.box(4, 0, 4, 12, 8, 12);
            case 1: return Block.box(3, 0, 3, 13, 10, 13);
            case 2: return Block.box(3, 0, 3, 13, 15, 13);
            default: return Block.box(4, 0, 4, 12, 8, 12);
        }
    }
}
