package com.fiveeus.adminium.inventories;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.List;

public class banMenu implements InventoryHolder {

    public final Inventory inv;

    public banMenu(Player target) {

        inv = Bukkit.createInventory(this, 45, ChatColor.translateAlternateColorCodes('&', "&4Punish Menu &8| &b") + target.getName());
        setItems();

    }

    private void setItems() {
        formatItem("test", Material.STONE, Collections.EMPTY_LIST, 0);

    }

    private void formatItem(String name, Material mat, List<String> lore, int slot) {
        ItemStack item;
        item = createItem(name, mat, lore);
        inv.setItem(slot, item);

    }




    private static ItemStack createItem(String name, Material mat, List<String> lore) {
        ItemStack item = new ItemStack(mat,1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }
}
