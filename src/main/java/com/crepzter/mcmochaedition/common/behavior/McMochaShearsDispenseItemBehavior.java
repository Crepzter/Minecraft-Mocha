package com.crepzter.mcmochaedition.common.behavior;

import com.crepzter.mcmochaedition.config.McMochaEditionCommonConfigs;
import com.crepzter.mcmochaedition.utils.SheepUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.dispenser.ShearsDispenseItemBehavior;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.phys.AABB;

public class McMochaShearsDispenseItemBehavior extends ShearsDispenseItemBehavior {
	
	protected ItemStack execute(BlockSource source, ItemStack stack) {
		if(!McMochaEditionCommonConfigs.jeb_colored_wool_wo_mixin()) return super.execute(source, stack);;
		
		Level level = source.getLevel();
		if (!level.isClientSide()) {
			BlockPos blockpos = source.getPos().relative(source.getBlockState().getValue(DispenserBlock.FACING));
			level = (ServerLevel) level;
			
			for(LivingEntity livingentity : level.getEntitiesOfClass(LivingEntity.class, new AABB(blockpos), EntitySelector.NO_SPECTATORS)) {
				if (livingentity instanceof Sheep sheep && sheep.hasCustomName() && "jeb_".equals(sheep.getName().getContents())
					&& McMochaEditionCommonConfigs.JEB_DROP_COLORED_WOOL.get() && sheep.readyForShearing()) {
					
					jebShear(SoundSource.BLOCKS, sheep, sheep.level);
					this.setSuccess(true);
					if(stack.hurt(1, level.getRandom(), (ServerPlayer)null)) {
						stack.setCount(0);
					}
					return stack;
				}
			}
		}
		return super.execute(source, stack);
	}
	
	private static void jebShear(SoundSource sounds, Sheep sheep, Level level) {
		level.playSound((Player) null, sheep, SoundEvents.SHEEP_SHEAR, sounds, 1.0F, 1.0F);
		sheep.setSheared(true);
		int i = 1 + level.random.nextInt(3);

		for (int j = 0; j < i; ++j) {
			ItemEntity itementity = sheep.spawnAtLocation(SheepUtils.woolForJeb(sheep), 1);
			if (itementity != null) {
				itementity.setDeltaMovement(itementity.getDeltaMovement().add(
						(double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F),
						(double) (level.random.nextFloat() * 0.05F),
						(double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F)));
			}
		}
	}
	
	//Inject into Sheep#onSheared   379 - 380
	
	/*
	if(this.hasCustomName() && "jeb_".equals(this.getName().getContents()) && McMochaEditionCommonConfigs.JEB_DROP_COLORED_WOOL.get()) {
		items.clear();
		for (int j = 0; j < i; ++j) {
			items.add(new ItemStack(SheepUtils.woolForJeb(this)));
		}
	}
	*/
}
