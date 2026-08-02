package com.axedgaming.endersdelight.block;

import com.axedgaming.endersdelight.block.entity.VoidpepperBushBE;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class VoidpepperBush extends Block {
    public static final BooleanProperty SPREADING = BooleanProperty.create("spreading");
    public static final BooleanProperty EAST = BooleanProperty.create("east");
    public static final BooleanProperty WEST = BooleanProperty.create("west");
    public static final BooleanProperty NORTH = BooleanProperty.create("north");
    public static final BooleanProperty SOUTH = BooleanProperty.create("south");
    public static final BooleanProperty UP = BooleanProperty.create("up");
    public static final BooleanProperty DOWN = BooleanProperty.create("down");
    public static final BooleanProperty PEPPER = BooleanProperty.create("pepper"); // Nouvelle propriété

    private static final int SPREAD_RADIUS = 7; // Rayon max de propagation

    public VoidpepperBush(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(SPREADING, true)
                .setValue(EAST, false).setValue(WEST, false).setValue(NORTH, false)
                .setValue(SOUTH, false).setValue(UP, false).setValue(DOWN, false)
                .setValue(PEPPER, false)); // Initialise la nouvelle propriété
    }

    protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return pState.is(Blocks.END_STONE) || pState.getBlock() instanceof net.minecraft.world.level.block.FarmBlock;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(SPREADING, EAST, WEST, NORTH, SOUTH, UP, DOWN, PEPPER); // Ajoute la nouvelle propriété
    }

    @Override
    public boolean isRandomlyTicking(BlockState pState) {
        return true;
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (!pState.getValue(SPREADING)) {
            return;
        }

        double chance = pRandom.nextDouble(); // Nombre entre 0 et 1

        if (chance < 2.0 / 3.0) { // 2/3 de chances de se répandre
            spread(pLevel, pPos, pRandom);
        } else { // 1/3 de chances de se transformer
            pLevel.setBlock(pPos, pState.setValue(PEPPER, true).setValue(SPREADING, false), 2);
        }
    }

    private void spread(ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        BlockPos origin = pPos; // On prend la position actuelle pour l'origine.

        // Essaye de se répandre dans un rayon de 7 blocs.
        for (int i = 0; i < 15; i++) { // Augmentons les essais à 15 pour augmenter les chances de propagation
            // Génère une nouvelle position dans un cube de 15x15x15 centré sur l'origine
            BlockPos newPos = origin.offset(
                    pRandom.nextInt(SPREAD_RADIUS * 2 + 1) - SPREAD_RADIUS,
                    pRandom.nextInt(SPREAD_RADIUS * 2 + 1) - SPREAD_RADIUS,
                    pRandom.nextInt(SPREAD_RADIUS * 2 + 1) - SPREAD_RADIUS
            );

            // Vérifier la distance avec l'origine
            if (newPos.distManhattan(origin) > SPREAD_RADIUS) {
                continue; // Trop loin, on ignore
            }

            // Vérifie si le bloc en dessous, sur le côté, ou au-dessus est un bloc d'Endstone
            if (pLevel.getBlockState(newPos.below()).is(Blocks.END_STONE) ||
                    pLevel.getBlockState(newPos.north()).is(Blocks.END_STONE) ||
                    pLevel.getBlockState(newPos.south()).is(Blocks.END_STONE) ||
                    pLevel.getBlockState(newPos.west()).is(Blocks.END_STONE) ||
                    pLevel.getBlockState(newPos.east()).is(Blocks.END_STONE) ||
                    pLevel.getBlockState(newPos.above()).is(Blocks.END_STONE)) {

                if (pLevel.isEmptyBlock(newPos)) {
                    // Détermine la direction en vérifiant chaque côté
                    BlockState newState = setBlockStateDirections(pLevel, newPos);

                    // Place le bloc
                    pLevel.setBlock(newPos, newState, 2);
                    pLevel.gameEvent(GameEvent.BLOCK_CHANGE, newPos, GameEvent.Context.of(newState));

                    // Minecraft va maintenant automatiquement associer l'entité du bloc
                    break;
                }
            }
        }
    }

    @Nullable
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new VoidpepperBushBE(pPos, pState, pPos); // Initialise avec lui-même comme origine
    }

    private BlockState setBlockStateDirections(Level level, BlockPos pPos) {
        BlockState newState = this.defaultBlockState();
        newState = newState.setValue(DOWN, level.getBlockState(pPos.below()).is(Blocks.END_STONE));
        newState = newState.setValue(UP, level.getBlockState(pPos.above()).is(Blocks.END_STONE));
        newState = newState.setValue(SOUTH, level.getBlockState(pPos.north()).is(Blocks.END_STONE));
        newState = newState.setValue(NORTH, level.getBlockState(pPos.south()).is(Blocks.END_STONE));
        newState = newState.setValue(EAST, level.getBlockState(pPos.west()).is(Blocks.END_STONE));
        newState = newState.setValue(WEST, level.getBlockState(pPos.east()).is(Blocks.END_STONE));

        return newState;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        BlockState newState = setBlockStateDirections(level, pos);
        level.setBlock(pos, newState, 2);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return setBlockStateDirections(context.getLevel(), context.getClickedPos());
    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        BlockPos blockpos = pPos.below();
        BlockState belowBlockState = pLevel.getBlockState(blockpos);

        // Remplacer TriState par la logique standard de Forge
        return this.mayPlaceOn(belowBlockState, pLevel, blockpos);
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        return !pState.canSurvive(pLevel, pCurrentPos)
                ? Blocks.AIR.defaultBlockState()
                : super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        VoxelShape shape = Shapes.empty();

        if (state.getValue(UP)) {
            shape = Shapes.or(shape, Block.box(0, 15, 0, 16, 16, 16));
        }
        if (state.getValue(DOWN)) {
            shape = Shapes.or(shape, Block.box(0, 0, 0, 16, 1, 16));
        }

        if (state.getValue(NORTH)) {
            shape = Shapes.or(shape, Block.box(0, 0, 15, 16, 16, 16)); // vers sud
        }

        if (state.getValue(SOUTH)) {
            shape = Shapes.or(shape, Block.box(0, 0, 0, 16, 16, 1)); // vers nord
        }

        if (state.getValue(EAST)) {
            shape = Shapes.or(shape, Block.box(0, 0, 0, 1, 16, 16)); // vers ouest
        }

        if (state.getValue(WEST)) {
            shape = Shapes.or(shape, Block.box(15, 0, 0, 16, 16, 16)); // vers est
        }

        if (shape.isEmpty()) {
            shape = Block.box(6, 6, 6, 10, 10, 10);
        }

        return shape;
    }
}
