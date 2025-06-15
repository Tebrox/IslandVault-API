package de.tebrox.islandvault.api;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Set;

public interface AddonProvider {
    String getAddonName();
    Set<ItemStack> getAllItems();
    boolean isUnlocked(Player player, ItemStack item);
    boolean matches(ItemStack item);
}