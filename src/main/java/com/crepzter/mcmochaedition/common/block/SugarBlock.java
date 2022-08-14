package com.crepzter.mcmochaedition.common.block;

import com.crepzter.mcmochaedition.common.block.entity.SugarBlockEntity;
import com.crepzter.mcmochaedition.init.BlockInit;
import com.crepzter.mcmochaedition.utils.EntityBlockUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SandBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class SugarBlock extends SandBlock implements EntityBlock {

	/*public SugarBlock() {
		super(BlockBehaviour.Properties.copy(Blocks.SAND) );
	}*/
	
	public SugarBlock() {
		super(0, BlockBehaviour.Properties.copy(Blocks.SAND) );
	}
	
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
		return EntityBlockUtils.createTickerHelper(type, BlockInit.SUGAR_BLOCK_ENTITY.get(), SugarBlockEntity::tick);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return BlockInit.SUGAR_BLOCK_ENTITY.get().create(pos, state);
	}
}
