package com.crepzter.mcmochaedition.screen;

import java.util.List;

import com.crepzter.mcmochaedition.core.init.MenuInit;
import com.crepzter.mcmochaedition.recipe.FletchingRecipe;
import com.crepzter.mcmochaedition.screen.slot.ModRestrictedSlot;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class FletchingTableMenu extends AbstractContainerMenu {
	
	private final Container inputContainer = new SimpleContainer(3) {
    	@Override
        public void setChanged() {
    		super.setChanged();
            FletchingTableMenu.this.slotChanged(this);
        }
    };
    
    private final ResultContainer resultContainer = new ResultContainer();
	
	private final ContainerLevelAccess access;
	private final Player player;
	private final Level level;
	private FletchingRecipe recipe;
	
	public static final Component CONTAINER_TITLE = new TranslatableComponent("block.minecraft.fletching_table");
	
	//MENU STRUCTURE AND GENERATING
	
	public FletchingTableMenu(int i, Inventory playerInventory, FriendlyByteBuf packetBuffer) {
        this(i, playerInventory, ContainerLevelAccess.NULL);
    }
	
	public FletchingTableMenu(int id, Inventory inv, ContainerLevelAccess access) {
		super(MenuInit.FLETCHING_TABLE.get(), id);
		this.access = access;
		this.player = inv.player;
		this.level = player.level;
		
		this.addSlot(new Slot(this.resultContainer, 3, 116, 36) {
			@Override
			public boolean mayPlace(ItemStack p_40231_) {
				return false;
			}
			
			@Override
			public void onTake(Player player, ItemStack stack) {
				super.onTake(player, stack);
				FletchingTableMenu.this.onTakeOutput(player, stack);
				FletchingTableMenu.this.slotChanged(inputContainer);
			}
		});
		
		this.addSlot(new ModRestrictedSlot(inputContainer, 0, 56, 18, Items.FLINT));
		this.addSlot(new ModRestrictedSlot(inputContainer, 1, 56, 36, Items.STICK));
		this.addSlot(new ModRestrictedSlot(inputContainer, 2, 56, 54, Items.FEATHER));
		
		addPlayerInventory(inv);
		addPlayerHotbar(inv);
	}

	@Override
    public boolean stillValid(Player player) {
		return stillValid(this.access, player, Blocks.FLETCHING_TABLE);
    }

	private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 86 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 144));
        }
    }
    
    //CRAFTING
    
    public void slotChanged(Container container) {
        if (container == inputContainer) {
        	List<FletchingRecipe> list = level.getRecipeManager().getRecipesFor(FletchingRecipe.Type.INSTANCE, inputContainer, level);
            if (list.isEmpty()) {
            	resultContainer.setItem(0, ItemStack.EMPTY);
            } else {
                recipe = list.get(0);
                ItemStack itemstack = recipe.assemble(inputContainer);
                resultContainer.setRecipeUsed(recipe);
                resultContainer.setItem(0, itemstack);
            }
        }

    }

    private void onTakeOutput(Player player, ItemStack stack) {
    	stack.onCraftedBy(player.level, player, stack.getCount());
        resultContainer.awardUsedRecipes(player);
        shrinkStackInSlot(inputContainer, 0);
        shrinkStackInSlot(inputContainer, 1);
        shrinkStackInSlot(inputContainer, 2);
        level.playSound(player, player, SoundEvents.VILLAGER_WORK_FLETCHER, SoundSource.BLOCKS, 0.5f, 1f);
    }

    private void shrinkStackInSlot(Container container, int id) {
        ItemStack itemstack = inputContainer.getItem(id);
        itemstack.shrink(1);
        this.inputContainer.setItem(id, itemstack);
    }

    public void removed(Player player) {
        super.removed(player);
        this.access.execute((plevel, ppos) -> {
            this.clearContainer(player, this.inputContainer);
        });
    }
    
    /*
    
    //QUICK MOVE
    
    // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
    // must assign a slot number to each of the slots used by the GUI.
    // For this container, we can see both the tile inventory's slots as well as the player inventory slots and the hotbar.
    // Each time we add a Slot to the container, it automatically increases the slotIndex, which means
    //  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
    //  9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
    //  36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;
    public static final int TE_INVENTORY_SLOT_COUNT = 3;  // must be the number of slots you have!

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        Slot sourceSlot = slots.get(index);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (index < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (index < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + index);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }
    
    */
    
    
    public ItemStack quickMoveStack(Player pPlayer, int id) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(id);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem(); //itemstack1 at id slot
            itemstack = itemstack1.copy(); //copy of itemstack1
            if (id == 0) {
                this.access.execute((pLevel, pPos) -> {
                    itemstack1.getItem().onCraftedBy(itemstack1, pLevel, pPlayer);
                });
                if (!this.moveItemStackTo(itemstack1, 4, 40, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemstack1, itemstack);
            } else if (id >= 4 && id < 40) {
                if (!this.moveItemStackTo(itemstack1, 0, 4, false)) {
                    if (id < 31) {
                        if (!this.moveItemStackTo(itemstack1, 31, 40, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (!this.moveItemStackTo(itemstack1, 4, 31, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            } else if (!this.moveItemStackTo(itemstack1, 4, 40, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(pPlayer, itemstack1);
            if (id == 0) {
            	pPlayer.drop(itemstack1, false);
            }
        }

        return itemstack;
    }
}