package com.fiveeus.adminium.events;

import com.fiveeus.adminium.Config;
import com.fiveeus.adminium.commands.staff;
import com.fiveeus.adminium.inventories.playerList;
import com.fiveeus.adminium.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
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
import java.util.Objects;

public class rightClickAir implements Listener {

    private static HashMap<Player, Boolean> buildmode = new HashMap<>();
    public static HashMap<Player, Boolean> getBuildMode() {return buildmode;}

    private static HashMap<Player, Boolean> visible = new HashMap<>();
    public static HashMap<Player, Boolean> getVisible() {return visible;}

    private static HashMap<Player, Integer> page = new HashMap<>();
    public static HashMap<Player, Integer> getPage() {return page;}

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
                    visible.put(player, false);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.prefix + "&7You are now visible."));
                    player.getInventory().setItem(1, createItem(ChatColor.BLACK + "Visible", Material.OBSIDIAN,
                            Collections.singletonList(ChatColor.DARK_GRAY + "(" + ChatColor.GRAY + "Right click" + ChatColor.DARK_GRAY + ")")));
                    for (Player victim : Bukkit.getOnlinePlayers()) {
                        victim.showPlayer(main.getPlugin(), player);
                    }

                } else if (visible.get(player)) {
                    visible.put(player, false);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.prefix + "&7You are now visible."));
                    player.getInventory().setItem(1, createItem(ChatColor.BLACK + "Visible", Material.OBSIDIAN,
                            Collections.singletonList(ChatColor.DARK_GRAY + "(" + ChatColor.GRAY + "Right click" + ChatColor.DARK_GRAY + ")")));
                    for (Player victim : Bukkit.getOnlinePlayers()) {
                        victim.showPlayer(main.getPlugin(), player);
                    }
                } else {
                    visible.put(player, true);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.prefix + "&7You are now hidden."));
                    player.getInventory().setItem(1, createItem(ChatColor.GRAY + "Vanished", Material.GLASS,
                            Collections.singletonList(ChatColor.DARK_GRAY + "(" + ChatColor.GRAY + "Right click" + ChatColor.DARK_GRAY + ")")));
                    for (Player victim : Bukkit.getOnlinePlayers()) {
                        victim.hidePlayer(main.getPlugin(), player);
                    }

                }

            } else if (e.getItem().getType() == Material.LEAD) {
                int rand = (int) (Math.random() * Bukkit.getOnlinePlayers().size());

                for (int i = 0; i < Bukkit.getOnlinePlayers().size(); i++) {
                    if (i == rand) {
                        Player target = (Player) Bukkit.getOnlinePlayers().toArray()[i];
                        e.getPlayer().teleport(Objects.requireNonNull(target.getPlayer()));
                        e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', config.prefix + "&7Teleporting to random player!"));
                        e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_ENDER_EYE_DEATH, 500.0f, 1.0f);

                    }
                }
            } else if (e.getItem().getType() == Material.PLAYER_HEAD) {
                getPage().putIfAbsent(player, 1);
                playerList gui = new playerList(getPage().get(player));
                player.openInventory(gui.getInventory());


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
