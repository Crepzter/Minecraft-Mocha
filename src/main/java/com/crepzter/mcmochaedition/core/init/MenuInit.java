package com.crepzter.mcmochaedition.core.init;

import com.crepzter.mcmochaedition.McMochaEdition;
import com.crepzter.mcmochaedition.screen.FletchingTableMenu;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MenuInit {
private MenuInit() {}
	
	public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.CONTAINERS, McMochaEdition.MOD_ID);
	
	public static final RegistryObject<MenuType<FletchingTableMenu>> FLETCHING_TABLE = MENUS.register("fletching_table_menu", () -> IForgeMenuType.create(FletchingTableMenu::new));
}
