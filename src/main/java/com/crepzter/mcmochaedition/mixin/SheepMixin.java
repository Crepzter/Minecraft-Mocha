package com.crepzter.mcmochaedition.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import com.crepzter.mcmochaedition.config.McMochaEditionCommonConfigs;
import com.crepzter.mcmochaedition.utils.SheepUtils;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.item.DyeColor;

@Mixin(Sheep.class)
public abstract class SheepMixin {

	@Inject(locals = LocalCapture.CAPTURE_FAILHARD,
            method = "getColor",
            at = @At(value = "RETURN"),
            cancellable = true
	)
	private void mcmochaedition_mixin_getColor(CallbackInfoReturnable<DyeColor> cir) {
		Sheep sheep = (Sheep)((Object)this);
		
		if(McMochaEditionCommonConfigs.jeb_colored_wool_mixin() && sheep.hasCustomName() && "jeb_".equals(sheep.getName().getContents())) {
			cir.setReturnValue(SheepUtils.dyeForJeb(sheep));
		} else {
			cir.setReturnValue(DyeColor.byId(sheep.getEntityData().get(DATA_WOOL_ID) & 15));
		}
	}
	
	@Shadow private static EntityDataAccessor<Byte> DATA_WOOL_ID;
}
