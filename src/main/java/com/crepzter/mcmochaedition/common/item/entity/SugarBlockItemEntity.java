package com.crepzter.mcmochaedition.common.item.entity;

import com.crepzter.mcmochaedition.common.block.entity.SugarBlockEntity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;

public class SugarBlockItemEntity extends ItemEntity {

	private int waterTicks = 0;
	public static final int MAX_WATER_TICKS = SugarBlockEntity.MAX_WATER_TICKS;

	public SugarBlockItemEntity(EntityType<? extends ItemEntity> entity, Level level) {
		super(entity, level);
	}

	@Override
	public void tick() {
		super.tick();
		
		if(!level.isClientSide()) {
			if(this.isInWater()) waterTicks++;
			else if(waterTicks > 0) waterTicks--;
			
			if(!level.isClientSide() && waterTicks > MAX_WATER_TICKS) {
				this.discard();
			}
		}
	}
}
