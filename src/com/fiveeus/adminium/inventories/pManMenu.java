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

public class pManMenu implements InventoryHolder {

    private final Inventory inv;

    public pManMenu(Player target) {

        inv = Bukkit.createInventory(this, 9, ChatColor.translateAlternateColorCodes('&', "&cPMAN &8| &b") + target.getName());
        setItems();

    }

    private void setItems() {
        formatItem(ChatColor.translateAlternateColorCodes('&', "&bInventory"), Material.CHEST, Collections.EMPTY_LIST, 0);
        formatItem(" ", Material.LIGHT_GRAY_STAINED_GLASS_PANE, Collections.EMPTY_LIST, 1);
        formatItem(ChatColor.translateAlternateColorCodes('&', "&4Punish Menu"), Material.DIAMOND_AXE, Collections.EMPTY_LIST, 2);
        formatItem(" ", Material.LIGHT_GRAY_STAINED_GLASS_PANE, Collections.EMPTY_LIST, 3);
        formatItem(ChatColor.translateAlternateColorCodes('&', "&7Teleport to Player"), Material.LEAD, Collections.EMPTY_LIST, 4);
        formatItem(" ", Material.LIGHT_GRAY_STAINED_GLASS_PANE, Collections.EMPTY_LIST, 5);
        formatItem(ChatColor.translateAlternateColorCodes('&', "&3Freeze Player"), Material.PACKED_ICE, Collections.EMPTY_LIST, 6);
        formatItem(" ", Material.LIGHT_GRAY_STAINED_GLASS_PANE, Collections.EMPTY_LIST, 7);
        formatItem(ChatColor.translateAlternateColorCodes('&', "&cPunishment History"), Material.PAPER, Collections.EMPTY_LIST, 8);

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
