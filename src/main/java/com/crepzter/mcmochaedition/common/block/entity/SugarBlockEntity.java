package com.crepzter.mcmochaedition.common.block.entity;

import com.crepzter.mcmochaedition.init.BlockInit;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SugarBlockEntity extends BlockEntity {
	
	private int waterTicks = 0;
	public static final int MAX_WATER_TICKS = 40;

	public SugarBlockEntity(BlockPos pos, BlockState state) {
		super(BlockInit.SUGAR_BLOCK_ENTITY.get(), pos, state);
	}
	
	public static void tick(Level level, BlockPos pPos, BlockState pState, SugarBlockEntity pBlockEntity) {
		if(!level.isClientSide()) {
			if(inWater(level, pPos)) pBlockEntity.waterTicks++;
			else if(pBlockEntity.waterTicks > 0) pBlockEntity.waterTicks--;
			
			if(!level.isClientSide() && pBlockEntity.waterTicks > MAX_WATER_TICKS) {
				level.setBlockAndUpdate(pPos, Blocks.WATER.defaultBlockState());
			}
		}
	}
	
	public static boolean inWater(Level level, BlockPos pos) {
		return (level.getBlockState(pos.above()).is(Blocks.WATER) ||
				level.getBlockState(pos.west()).is(Blocks.WATER)  ||
				level.getBlockState(pos.north()).is(Blocks.WATER) ||
				level.getBlockState(pos.east()).is(Blocks.WATER)  ||
				level.getBlockState(pos.south()).is(Blocks.WATER) );
	}
}
