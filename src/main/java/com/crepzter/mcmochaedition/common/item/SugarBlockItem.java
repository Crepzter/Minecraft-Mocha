package com.crepzter.mcmochaedition.common.item;

import com.crepzter.mcmochaedition.common.item.entity.SugarBlockItemEntity;
import com.crepzter.mcmochaedition.init.BlockInit;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SugarBlockItem extends BlockItem {

	public SugarBlockItem() {
		super(BlockInit.SUGAR_BLOCK.get(), new BlockItem.Properties().stacksTo(64).tab(CreativeModeTab.TAB_DECORATIONS) );
	}

	@Override
	public Entity createEntity(Level level, Entity pEntity, ItemStack stack) {
		Entity entity = new SugarBlockItemEntity(BlockInit.SUGAR_BLOCK_ITEM_ENTITY.get(),level);
		CompoundTag tag = new CompoundTag();
		pEntity.save(tag);
		entity.load(tag);
		return entity;
	}
	
	@Override
	public boolean hasCustomEntity(ItemStack stack) {
		return true;
	}
}
