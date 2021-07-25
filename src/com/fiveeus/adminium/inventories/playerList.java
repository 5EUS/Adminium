package com.fiveeus.adminium.inventories;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class playerList implements InventoryHolder {

    private final Inventory inv;

    public playerList(Integer page) {
        inv = Bukkit.createInventory(this, 45, ChatColor.translateAlternateColorCodes('&', "&bPlayer List"));
        setItems(page);
    }

    private void setItems(int page) {
        int i = 0;

        for (Player p : Bukkit.getOnlinePlayers()) {

            if (page != 1) {
                formatItem(ChatColor.translateAlternateColorCodes('&', "&cBack"), Material.RED_STAINED_GLASS_PANE, Collections.EMPTY_LIST, 36);

            }

            if ((page >= 2) && (i * page) >= (35 * page) && i != 0) {
                i = 0;
                continue;
            }

            inv.setItem(i, getHead(p));
            i++;

            if (((i * page) % 36) == 0) {
                formatItem(ChatColor.translateAlternateColorCodes('&', "&aNext"), Material.GREEN_STAINED_GLASS_PANE, Collections.EMPTY_LIST, 44);
                break;

            }

        }

    }

    public void formatItem(String name, Material mat, List<String> lore, int slot) {
        ItemStack item;
        item = createItem(name, mat, lore);
        inv.setItem(slot, item);

    }

    private ItemStack createItem(String name, Material mat, List<String> lore) {
        ItemStack item = new ItemStack(mat,1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public Inventory getInventory() { return inv; }

    private static ItemStack getHead(Player p) {

        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skull = (SkullMeta) head.getItemMeta();
        skull.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&b" + p.getName()));
        ArrayList<String> lore = new ArrayList<>();

        lore.add(ChatColor.translateAlternateColorCodes('&', "&a♡ " + Math.ceil(p.getHealth() * 2) / 2 + "&a/20.0"));
        String x = String.valueOf(Math.ceil(p.getLocation().getX() * 2) / 2);
        String y = String.valueOf(Math.ceil(p.getLocation().getY() * 2) / 2);
        String z = String.valueOf(Math.ceil(p.getLocation().getZ() * 2) / 2);

        lore.add(ChatColor.translateAlternateColorCodes('&', "&8(&7" + x + ", " + y + ", " + z + "&8)"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&8(&b" + p.getAddress().getHostName().replaceAll("/", "") + "&8)"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&8(&c" + p.getUniqueId() + "&8)"));
        skull.setLore(lore);
        skull.setOwningPlayer(p);
        head.setItemMeta(skull);

        return head;
    }
}
