package com.crepzter.mcmochaedition.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class McMochaEditionCommonConfigs {
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> JEB_DROP_COLORED_WOOL;
    public static final ForgeConfigSpec.ConfigValue<Boolean> JEB_DROP_COLORED_WOOL_MIXIN;
    
    public static final ForgeConfigSpec.ConfigValue<Boolean> FLETCHING_TABLE_MENU;
    public static final ForgeConfigSpec.ConfigValue<Boolean> FLETCHING_TABLE_MENU_MIXIN;

    static {
        BUILDER.push("Configs for Minecraft Mocha Edition");

        // Jeb
        JEB_DROP_COLORED_WOOL = BUILDER.comment("Do \"Jeb_\" named Sheeps drop colored wool!")
                .define("jeb_drop_colored_wool", true);
        JEB_DROP_COLORED_WOOL_MIXIN = BUILDER.comment("Do \"Jeb_\" Sheep drops work with a mixin!")
                .define("jeb_drop_colored_wool_mixin", true);
        
        // Fletching Table
        FLETCHING_TABLE_MENU = BUILDER.comment("Does the Fletching Table have its own crafting menu!")
                .define("fletchin_table_menu", true);
        FLETCHING_TABLE_MENU_MIXIN = BUILDER.comment("Does the Fletching Table Menu work with a mixin!")
                .define("fletchin_table_menu_mixin", true);
        
        //CITRINE_ORE_VEIN_SIZE = BUILDER.comment("How many Citrine Ore Blocks spawn in one Vein!")
        //        .defineInRange("Vein Size", 9, 4, 20);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
    
    public static boolean jeb_colored_wool_mixin() {
    	return JEB_DROP_COLORED_WOOL.get() && JEB_DROP_COLORED_WOOL_MIXIN.get();
    }
    public static boolean jeb_colored_wool_wo_mixin() {
    	return JEB_DROP_COLORED_WOOL.get() && !JEB_DROP_COLORED_WOOL_MIXIN.get();
    }
    
    public static boolean fletchin_table_menu_mixin() {
    	return FLETCHING_TABLE_MENU.get() && FLETCHING_TABLE_MENU_MIXIN.get();
    }
    public static boolean fletchin_table_menu_wo_mixin() {
    	return FLETCHING_TABLE_MENU.get() && !FLETCHING_TABLE_MENU_MIXIN.get();
    }
}
