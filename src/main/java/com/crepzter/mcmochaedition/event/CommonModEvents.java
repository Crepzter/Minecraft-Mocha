package com.crepzter.mcmochaedition.event;

import com.crepzter.mcmochaedition.McMochaEdition;
import com.crepzter.mcmochaedition.common.behavior.McMochaShearsDispenseItemBehavior;
import com.crepzter.mcmochaedition.recipe.FletchingRecipe;

import net.minecraft.core.Registry;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = McMochaEdition.MOD_ID, bus = Bus.MOD)
public class CommonModEvents {
	
	@SubscribeEvent
	public static void commonSetup(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> DispenserBlock.registerBehavior(Items.SHEARS, new McMochaShearsDispenseItemBehavior()));
	}
	
	@SubscribeEvent
    public static void registerRecipeTypes(final RegistryEvent.Register<RecipeSerializer<?>> event) {
        Registry.register(Registry.RECIPE_TYPE, FletchingRecipe.Type.ID, FletchingRecipe.Type.INSTANCE);
    }
}
