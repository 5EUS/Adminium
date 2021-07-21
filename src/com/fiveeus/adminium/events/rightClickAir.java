package com.fiveeus.adminium.events;

import com.fiveeus.adminium.Config;
import com.fiveeus.adminium.commands.staff;
import com.fiveeus.adminium.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class rightClickAir implements Listener {

    private static HashMap<Player, Boolean> buildmode = new HashMap<>();
    public static HashMap<Player, Boolean> getBuildMode() {return buildmode;}

    private static HashMap<Player, Boolean> visible = new HashMap<>();
    public static HashMap<Player, Boolean> getVisible() {return visible;}

    @EventHandler
    public static void onPlace(PlayerInteractEvent e) {

        Config config = new Config();


        Player player = e.getPlayer();
        Action action = e.getAction();
        if ((action == Action.RIGHT_CLICK_AIR) && (staff.getStaffModeList().get(player)) && !(e.getItem() == null)) {
            if (e.getItem().getType() == Material.GRASS_BLOCK) {
                if (!buildmode.containsKey(player)) {
                    buildmode.put(player, true);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.prefix + "&7Build mode enabled."));

                } else if (buildmode.get(player)) {
                    buildmode.put(player, false);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.prefix + "&7Build mode disabled."));

                } else {
                    buildmode.put(player, true);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.prefix + "&7Build mode enabled."));

                }
            } else if (e.getItem().getType() == Material.GLASS || e.getItem().getType() == Material.OBSIDIAN) {
                if (!visible.containsKey(player)) {
                    visible.put(player, true);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.prefix + "&7You are now hidden."));
                    player.getInventory().setItem(1, createItem(ChatColor.BLACK + "Visible", Material.OBSIDIAN,
                            Collections.singletonList(ChatColor.DARK_GRAY + "(" + ChatColor.GRAY + "Right click" + ChatColor.DARK_GRAY + ")")));
                    for (Player victim : Bukkit.getOnlinePlayers()) {
                        player.hidePlayer(main.getPlugin(), victim);
                    }

                } else if (visible.get(player)) {
                    visible.put(player, false);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.prefix + "&7You are now visible."));
                    player.getInventory().setItem(1, createItem(ChatColor.GRAY + "Vanished", Material.GLASS,
                            Collections.singletonList(ChatColor.DARK_GRAY + "(" + ChatColor.GRAY + "Right click" + ChatColor.DARK_GRAY + ")")));
                    for (Player victim : Bukkit.getOnlinePlayers()) {
                        player.showPlayer(main.getPlugin(), victim);
                    }
                } else {
                    visible.put(player, true);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.prefix + "&7You are now hidden."));
                    player.getInventory().setItem(1, createItem(ChatColor.BLACK + "Visible", Material.OBSIDIAN,
                            Collections.singletonList(ChatColor.DARK_GRAY + "(" + ChatColor.GRAY + "Right click" + ChatColor.DARK_GRAY + ")")));
                    for (Player victim : Bukkit.getOnlinePlayers()) {
                        player.hidePlayer(main.getPlugin(), victim);
                    }

                }

            }

        }

    }

    private static ItemStack createItem(String name, Material mat, List<String> lore) {
        ItemStack item = new ItemStack(mat,1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

}
