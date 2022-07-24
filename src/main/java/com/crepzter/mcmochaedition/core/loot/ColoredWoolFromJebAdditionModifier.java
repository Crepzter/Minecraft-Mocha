package com.crepzter.mcmochaedition.core.loot;

import java.util.List;

import com.crepzter.mcmochaedition.core.config.McMochaEditionCommonConfigs;
import com.crepzter.mcmochaedition.utils.SheepUtils;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;

public class ColoredWoolFromJebAdditionModifier extends LootModifier {
	protected ColoredWoolFromJebAdditionModifier(LootItemCondition[] conditionsIn) {
		super(conditionsIn);
	}

	@Override
	protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
		if(context.getParam(LootContextParams.THIS_ENTITY) instanceof Sheep sheep) {
			if(sheep.hasCustomName() && "jeb_".equals(sheep.getName().getContents()) && McMochaEditionCommonConfigs.JEB_DROP_COLORED_WOOL.get()) {
				int amount = 0;
				for(ItemStack stack : generatedLoot) {
					if(stack.is(ItemTags.WOOL)) {
						amount += stack.getCount();
						generatedLoot.remove(stack);
					}
				}
				if(amount > 0) generatedLoot.add(new ItemStack(SheepUtils.woolForJeb(sheep), amount));
			}
		}
		
		return generatedLoot;
	}
	
	public static class Serializer extends GlobalLootModifierSerializer<ColoredWoolFromJebAdditionModifier> {
		@Override
        public ColoredWoolFromJebAdditionModifier read(ResourceLocation location, com.google.gson.JsonObject json, LootItemCondition[] conditionsIn) {
        	return new ColoredWoolFromJebAdditionModifier(conditionsIn);
        }

        @Override
        public com.google.gson.JsonObject write(ColoredWoolFromJebAdditionModifier instance) {
        	return makeConditions(instance.conditions);
        }
    }
}
