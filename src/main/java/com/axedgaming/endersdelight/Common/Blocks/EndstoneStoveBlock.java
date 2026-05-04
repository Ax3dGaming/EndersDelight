package com.axedgaming.endersdelight.Common.Blocks;

import com.axedgaming.endersdelight.Common.Blocks.entity.EndstoneStoveBlockEntity;
import com.axedgaming.endersdelight.Common.Registries.EdBlockEntityTypes;
import com.axedgaming.endersdelight.Common.Registries.EdParticles;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import vectorwing.farmersdelight.common.registry.ModSounds;
import vectorwing.farmersdelight.common.block.AbstractStoveBlock;

import javax.annotation.Nullable;

public class EndstoneStoveBlock extends AbstractStoveBlock
{
	public static final MapCodec<EndstoneStoveBlock> CODEC = simpleCodec(EndstoneStoveBlock::new);

	@Override
	public MapCodec<EndstoneStoveBlock> codec() {
        return CODEC;
    }

	public EndstoneStoveBlock(BlockBehaviour.Properties properties) {
		super(properties);
	}

	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new EndstoneStoveBlockEntity(pos, state);
	}

	@Nullable
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
		if (level.isClientSide && state.getValue(LIT)) return createTickerHelper(blockEntityType, EdBlockEntityTypes.ENDSTONE_STOVE.get(), EndstoneStoveBlockEntity::particleTick);
		return createStoveTicker(level, blockEntityType, EdBlockEntityTypes.ENDSTONE_STOVE.get());
	}

	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
		if (!state.getValue(LIT)) return;
		double x = (double) pos.getX() + 0.5D;
		double y = pos.getY();
		double z = (double) pos.getZ() + 0.5D;
		if (random.nextInt(10) == 0) {
			level.playLocalSound(x, y, z, ModSounds.BLOCK_STOVE_CRACKLE.get(), SoundSource.BLOCKS, 1.0F, 1.0F, false);
		}

		Direction direction = state.getValue(HorizontalDirectionalBlock.FACING);
		Direction.Axis direction$axis = direction.getAxis();
		double horizontalOffset = random.nextDouble() * 0.6D - 0.3D;
		double xOffset = direction$axis == Direction.Axis.X ? (double) direction.getStepX() * 0.52D : horizontalOffset;
		double yOffset = random.nextDouble() * 6.0D / 16.0D;
		double zOffset = direction$axis == Direction.Axis.Z ? (double) direction.getStepZ() * 0.52D : horizontalOffset;
		level.addParticle(ParticleTypes.SMOKE, x + xOffset, y + yOffset, z + zOffset, 0.0D, 0.0D, 0.0D);
		level.addParticle(EdParticles.END_FLAME.get(), x + xOffset, y + yOffset, z + zOffset, 0.0D, 0.0D, 0.0D);
	}
}