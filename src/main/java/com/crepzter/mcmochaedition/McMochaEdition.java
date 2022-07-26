package com.crepzter.mcmochaedition;

import com.crepzter.mcmochaedition.core.config.McMochaEditionCommonConfigs;
import com.crepzter.mcmochaedition.core.init.LootModifierInit;

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
		
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, McMochaEditionCommonConfigs.SPEC, "mcmochaedition-common-config.toml");
	}
}
