package com.crepzter.mcmochaedition.screen.slot;

import java.util.function.Predicate;

import javax.annotation.Nonnull;

import net.minecraft.tags.TagKey;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ModRestrictedSlot extends Slot {
	private final Predicate<ItemStack> testPre;

	public ModRestrictedSlot(Container container, int index, int xPosition, int yPosition, Item testItem) {
		super(container, index, xPosition, yPosition);
		this.testPre = (i) -> i.is(testItem);
	}
	
	public ModRestrictedSlot(Container container, int index, int xPosition, int yPosition, TagKey<Item> testItem) {
		super(container, index, xPosition, yPosition);
		this.testPre = (i) -> i.is(testItem);
	}
	
	@Override
    public boolean mayPlace(@Nonnull ItemStack stack)
    {
        if (stack.isEmpty() || !testPre.test(stack)) return false;
        return true;
    }
}
