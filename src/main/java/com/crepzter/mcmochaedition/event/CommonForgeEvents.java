package com.crepzter.mcmochaedition.event;

import com.crepzter.mcmochaedition.McMochaEdition;
import com.crepzter.mcmochaedition.config.McMochaEditionCommonConfigs;
import com.crepzter.mcmochaedition.screen.FletchingTableMenu;
import com.crepzter.mcmochaedition.utils.SheepUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = McMochaEdition.MOD_ID, bus = Bus.FORGE)
public class CommonForgeEvents {
	
	@SubscribeEvent
	public static void onPlayerEntityInteractEvent(PlayerInteractEvent.EntityInteract event) {
		if(!McMochaEditionCommonConfigs.jeb_colored_wool_wo_mixin()) return;
		
		if(!event.getPlayer().level.isClientSide() && event.getTarget() instanceof Sheep sheep && event.getPlayer().getItemInHand(event.getHand()).is(Items.SHEARS) && sheep.readyForShearing()) {
			if(sheep.hasCustomName() && "jeb_".equals(sheep.getName().getContents()) && McMochaEditionCommonConfigs.JEB_DROP_COLORED_WOOL.get()) {
				sheep.setSheared(true);
				
				Level level = sheep.level;
				Item wool = SheepUtils.woolForJeb(sheep);
				
				int i = 1 + level.random.nextInt(3);

			    for(int j = 0; j < i; j++) {
			       ItemEntity itementity = sheep.spawnAtLocation(wool, 1);
			       if (itementity != null) {
			          itementity.setDeltaMovement(itementity.getDeltaMovement().add((double)((level.random.nextFloat() - level.random.nextFloat()) * 0.1F), (double)(level.random.nextFloat() * 0.05F), (double)((level.random.nextFloat() - level.random.nextFloat()) * 0.1F)));
			       }
			    }
			    
			    level.playSound(null, sheep, SoundEvents.SHEEP_SHEAR, event.getPlayer() == null ? SoundSource.BLOCKS : SoundSource.PLAYERS, 1.0F, 1.0F);
			    
			    event.setCanceled(true);
			}
		}
	}
	
	
	@SubscribeEvent
	public static void onRightClickFletchingTable(PlayerInteractEvent.RightClickBlock event) {
		if(!McMochaEditionCommonConfigs.fletchin_table_menu_wo_mixin()) return;
		
		Player player = event.getPlayer();
		BlockState state = player.level.getBlockState(event.getPos());
		
		if(state.is(Blocks.FLETCHING_TABLE)) {
			event.setCanceled(true);
			
			if(!player.level.isClientSide()) player.openMenu(getMenuProvider(state, player.level, event.getPos()));
		}
	}
	
	public static MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
		return new SimpleMenuProvider((id, inv, access) -> {
			return new FletchingTableMenu(id, inv, ContainerLevelAccess.create(level, pos));
		}, FletchingTableMenu.CONTAINER_TITLE);
	}
	
}
