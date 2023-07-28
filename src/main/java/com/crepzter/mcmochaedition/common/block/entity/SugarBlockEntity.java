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
	private WaterSource waterSource = WaterSource.NONE;
	
	private enum WaterSource {
		RAIN,
		WATER,
		NONE
	}

	public SugarBlockEntity(BlockPos pos, BlockState state) {
		super(BlockInit.SUGAR_BLOCK_ENTITY.get(), pos, state);
	}
	
	public static void tick(Level level, BlockPos pPos, BlockState pState, SugarBlockEntity pBlockEntity) {
		if(!level.isClientSide()) {
			if(inWater(level, pPos)) {
				pBlockEntity.waterTicks++;
				pBlockEntity.waterSource = WaterSource.WATER;
			} else if(inRain(level,pPos)) {
				pBlockEntity.waterTicks++;
				pBlockEntity.waterSource = WaterSource.RAIN;
			} else if(pBlockEntity.waterTicks > 0) {
				pBlockEntity.waterTicks--;
				pBlockEntity.waterSource = WaterSource.NONE;
			}
			if(pBlockEntity.waterTicks > MAX_WATER_TICKS) {
				if(pBlockEntity.waterSource == WaterSource.WATER) level.setBlockAndUpdate(pPos, Blocks.WATER.defaultBlockState());
				else if(pBlockEntity.waterSource == WaterSource.RAIN) level.setBlockAndUpdate(pPos, Blocks.AIR.defaultBlockState());
				else pBlockEntity.waterTicks = 0;
			}
		}
	}
	
	public static boolean inRain(Level level, BlockPos pos) {
		return (level.isRainingAt(pos.above()));
	}
	
	public static boolean inWater(Level level, BlockPos pos) {
		return (level.getBlockState(pos.above()).getMaterial().isLiquid()	||
				level.getBlockState(pos.west()).getMaterial().isLiquid()	||
				level.getBlockState(pos.north()).getMaterial().isLiquid()	||
				level.getBlockState(pos.east()).getMaterial().isLiquid()	||
				level.getBlockState(pos.south()).getMaterial().isLiquid()	);
	}
}
