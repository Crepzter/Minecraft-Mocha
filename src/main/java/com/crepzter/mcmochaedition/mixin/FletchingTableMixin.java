package com.crepzter.mcmochaedition.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import org.spongepowered.asm.mixin.injection.At;

import com.crepzter.mcmochaedition.config.McMochaEditionCommonConfigs;
import com.crepzter.mcmochaedition.screen.FletchingTableMenu;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FletchingTableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

@Mixin(FletchingTableBlock.class)
public class FletchingTableMixin {
	
	@Inject(locals = LocalCapture.CAPTURE_FAILHARD,
            method = "use",
            at = @At(value = "RETURN"),
            cancellable = true
    )
    private void mcmochaedition_mixin_use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result, CallbackInfoReturnable<InteractionResult> cir) {
		if(!McMochaEditionCommonConfigs.fletchin_table_menu_mixin()) cir.setReturnValue(InteractionResult.PASS);
		else if (level.isClientSide()) {
            cir.setReturnValue(InteractionResult.SUCCESS);
        } else {
            player.openMenu(getMenuProvider(state, level, pos));
            cir.setReturnValue(InteractionResult.CONSUME);
        }
    }

    public MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
        return new SimpleMenuProvider((id, inv, access) -> {
            return new FletchingTableMenu(id, inv, ContainerLevelAccess.create(level, pos));
        }, FletchingTableMenu.CONTAINER_TITLE);
    }
}
