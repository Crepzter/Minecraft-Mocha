package com.crepzter.mcmochaedition.core.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class McMochaEditionCommonConfigs {
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> JEB_DROP_COLORED_WOOL;

    static {
        BUILDER.push("Configs for Minecraft Mocha Edition");

        JEB_DROP_COLORED_WOOL = BUILDER.comment("Do \"Jeb_\" named Sheeps drop colored wool!")
                .define("jeb_drop_colored_wool", true);
        //CITRINE_ORE_VEIN_SIZE = BUILDER.comment("How many Citrine Ore Blocks spawn in one Vein!")
        //        .defineInRange("Vein Size", 9, 4, 20);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
