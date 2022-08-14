package com.crepzter.mcmochaedition.init;

import com.crepzter.mcmochaedition.McMochaEdition;
import com.crepzter.mcmochaedition.common.block.SugarBlock;
import com.crepzter.mcmochaedition.common.block.entity.SugarBlockEntity;
import com.crepzter.mcmochaedition.common.item.SugarBlockItem;
import com.crepzter.mcmochaedition.common.item.entity.SugarBlockItemEntity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockInit {
	private BlockInit() {}
	
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, McMochaEdition.MOD_ID);
	public static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, McMochaEdition.MOD_ID);
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, McMochaEdition.MOD_ID);
	public static final DeferredRegister<EntityType<?>> BLOCK_ITEM_ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, McMochaEdition.MOD_ID);
	
	// Sugar - Block
	public static final RegistryObject<SugarBlock> SUGAR_BLOCK = BLOCKS.register("sugar_block", () -> new SugarBlock() );
	
	// Block Item
	public static final RegistryObject<SugarBlockItem> SUGAR_BLOCK_ITEM = BLOCK_ITEMS.register("sugar_block", 
			() -> new SugarBlockItem() );
	
	//Block Entity
	public static final RegistryObject<BlockEntityType<SugarBlockEntity>> SUGAR_BLOCK_ENTITY = BLOCK_ENTITIES.register("sugar_block_entity", 
			() -> BlockEntityType.Builder.of(SugarBlockEntity::new, BlockInit.SUGAR_BLOCK.get()).build(null) );
	
	//Block Item Entity
	public static final RegistryObject<EntityType<SugarBlockItemEntity>> SUGAR_BLOCK_ITEM_ENTITY = BLOCK_ITEM_ENTITIES.register("sugar_block_item_entity", 
			() -> EntityType.Builder.<SugarBlockItemEntity>of(SugarBlockItemEntity::new, MobCategory.MISC)
			.build( new ResourceLocation(McMochaEdition.MOD_ID,"sugar_block_item_entity").toString() )  );
}
