package com.crepzter.mcmochaedition;

import com.crepzter.mcmochaedition.config.McMochaEditionCommonConfigs;
import com.crepzter.mcmochaedition.init.BlockInit;
import com.crepzter.mcmochaedition.init.LootModifierInit;
import com.crepzter.mcmochaedition.init.MenuInit;
import com.crepzter.mcmochaedition.init.RecipeInit;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(McMochaEdition.MOD_ID)
public class McMochaEdition {
	public static final String MOD_ID = "mcmochaedition";
	
	public McMochaEdition() {
		MinecraftForge.EVENT_BUS.register(this);
		
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		
		LootModifierInit.GLM.register(bus);
		MenuInit.MENUS.register(bus);
		RecipeInit.RECIPES.register(bus);
		
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, McMochaEditionCommonConfigs.SPEC, "mcmochaedition-common-config.toml");
	}
}
