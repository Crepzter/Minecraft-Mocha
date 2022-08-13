package com.crepzter.mcmochaedition.init;

import com.crepzter.mcmochaedition.McMochaEdition;
import com.crepzter.mcmochaedition.recipe.FletchingRecipe;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RecipeInit {
	private RecipeInit() {}
	
	public static final DeferredRegister<RecipeSerializer<?>> RECIPES = DeferredRegister.create(ForgeRegistries.Keys.RECIPE_SERIALIZERS, McMochaEdition.MOD_ID);
	
	public static final RegistryObject<FletchingRecipe.Serializer> FLETCHING = RECIPES.register("fletching_arrow", FletchingRecipe.Serializer::new);
}
