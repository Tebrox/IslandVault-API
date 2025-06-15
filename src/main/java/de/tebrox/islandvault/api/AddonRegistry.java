package de.tebrox.islandvault.api;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class AddonRegistry {
    private static final Set<AddonProvider> providers = new HashSet<>();

    public static void registerAddon(AddonProvider provider) {
        providers.add(provider);
    }

    public static Set<ItemStack> getUnlockedItems(Player player) {
        Set<ItemStack> result = new HashSet<>();
        for (AddonProvider provider : providers) {
            for (ItemStack item : provider.getAllItems()) {
                if (provider.isUnlocked(player, item)) {
                    result.add(item);
                }
            }
        }
        return result;
    }

    public static Optional<AddonProvider> getProvider(ItemStack item) {
        return providers.stream()
                .filter(p -> p.matches(item))
                .findFirst();
    }

    public static boolean isAllowed(Player player, ItemStack item) {
        return getProvider(item)
                .map(p -> p.isUnlocked(player, item))
                .orElse(false);
    }

    public static Set<AddonProvider> getAllProviders() {
        return Collections.unmodifiableSet(providers);
    }
}