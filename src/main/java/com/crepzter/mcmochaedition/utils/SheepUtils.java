package com.crepzter.mcmochaedition.utils;

import java.util.Map;

import com.google.common.collect.Maps;

import net.minecraft.Util;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

public class SheepUtils {
	//from the vanilla Sheep class
	private static final Map<DyeColor, ItemLike> ITEM_BY_DYE = Util.make(Maps.newEnumMap(DyeColor.class), (p_29841_) -> {
	  p_29841_.put(DyeColor.WHITE, Blocks.WHITE_WOOL);
	  p_29841_.put(DyeColor.ORANGE, Blocks.ORANGE_WOOL);
	  p_29841_.put(DyeColor.MAGENTA, Blocks.MAGENTA_WOOL);
	  p_29841_.put(DyeColor.LIGHT_BLUE, Blocks.LIGHT_BLUE_WOOL);
	  p_29841_.put(DyeColor.YELLOW, Blocks.YELLOW_WOOL);
	  p_29841_.put(DyeColor.LIME, Blocks.LIME_WOOL);
	  p_29841_.put(DyeColor.PINK, Blocks.PINK_WOOL);
	  p_29841_.put(DyeColor.GRAY, Blocks.GRAY_WOOL);
	  p_29841_.put(DyeColor.LIGHT_GRAY, Blocks.LIGHT_GRAY_WOOL);
	  p_29841_.put(DyeColor.CYAN, Blocks.CYAN_WOOL);
	  p_29841_.put(DyeColor.PURPLE, Blocks.PURPLE_WOOL);
	  p_29841_.put(DyeColor.BLUE, Blocks.BLUE_WOOL);
	  p_29841_.put(DyeColor.BROWN, Blocks.BROWN_WOOL);
	  p_29841_.put(DyeColor.GREEN, Blocks.GREEN_WOOL);
	  p_29841_.put(DyeColor.RED, Blocks.RED_WOOL);
	  p_29841_.put(DyeColor.BLACK, Blocks.BLACK_WOOL);
	});
	
	public static DyeColor dyeForJeb(Sheep sheep) {
		int a = sheep.tickCount / 25 + sheep.getId();
        int b = DyeColor.values().length;
        int k = a % b;
        int l = (a + 1) % b;
        
        float f3 = (float)(sheep.tickCount % 25) / 25.0F;
        
        DyeColor d;
        if(f3 < 0.5f) d = DyeColor.byId(k);
        else d = DyeColor.byId(l);
        
        return d;
	}
	
	public static Item woolForJeb(Sheep sheep) {
		int a = sheep.tickCount / 25 + sheep.getId();
        int b = DyeColor.values().length;
        int k = a % b;
        int l = (a + 1) % b;
        
        float f3 = (float)(sheep.tickCount % 25) / 25.0F;
        
        DyeColor d;
        if(f3 < 0.5f) d = DyeColor.byId(k);
        else d = DyeColor.byId(l);
        
        return ITEM_BY_DYE.get(d).asItem();
	}
}
