package com.crepzter.mcmochaedition.core.init;

import com.crepzter.mcmochaedition.McMochaEdition;
import com.crepzter.mcmochaedition.core.loot.ColoredWoolFromJebAdditionModifier;

import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class LootModifierInit {
	private LootModifierInit() {}
	
	public static final DeferredRegister<GlobalLootModifierSerializer<?>> GLM = DeferredRegister.create(ForgeRegistries.Keys.LOOT_MODIFIER_SERIALIZERS, McMochaEdition.MOD_ID);
	
	public static final RegistryObject<ColoredWoolFromJebAdditionModifier.Serializer> WOOL_JEB = GLM.register("colored_wool_from_jeb", ColoredWoolFromJebAdditionModifier.Serializer::new);
}
