package com.crepzter.mcmochaedition.event;

import com.crepzter.mcmochaedition.McMochaEdition;
import com.crepzter.mcmochaedition.core.init.MenuInit;
import com.crepzter.mcmochaedition.screen.FletchingTableScreen;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@Mod.EventBusSubscriber(modid = McMochaEdition.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ClientModEvents {
	private ClientModEvents() {}
	
	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		MenuScreens.register(MenuInit.FLETCHING_TABLE.get(), FletchingTableScreen::new);
	}
}
