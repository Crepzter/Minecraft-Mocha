package com.crepzter.mcmochaedition.event;

import com.crepzter.mcmochaedition.McMochaEdition;
import com.crepzter.mcmochaedition.common.behavior.McMochaShearsDispenseItemBehavior;

import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.DispenserBlock;
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

}
