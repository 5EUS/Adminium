package com.fiveeus.adminium.commands;

import com.fiveeus.adminium.Config;
import com.fiveeus.adminium.events.rightClickAir;
import com.fiveeus.adminium.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class staff implements CommandExecutor {

    private static HashMap<Player, Boolean> staffmode = new HashMap<>();
    public static HashMap<Player, Boolean> getStaffModeList() {return staffmode;}

    private HashMap<Player, ItemStack[]> inventory = new HashMap<Player, ItemStack[]>();



    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {

            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"[Adminium] Something went wrong."));
            return false;
        }
        Player player = ((Player) sender).getPlayer();


        Config config = new Config();

        if (command.getName().equalsIgnoreCase("staff")) {
            if (sender.hasPermission(config.permission)) {
                if (!staffmode.containsKey(player)) {
                    player.setGameMode(GameMode.CREATIVE);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.prefix + "&7Staff mode enabled."));

                    staffmode.put(player, true);
                    rightClickAir.getBuildMode().put(player, false);
                    inventory.put(player, player.getInventory().getContents());

                    player.getInventory().clear();

                    giveInventory(player);
                    for (Player victim : Bukkit.getOnlinePlayers()) {
                        victim.hidePlayer(main.getPlugin(), player);
                    }


                } else if (staffmode.get(player)) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.prefix + "&7Staff mode disabled."));

                    staffmode.put(player, false);

                    player.getInventory().setContents(inventory.get(player));
                    giveInventory(player);
                    for (Player victim : Bukkit.getOnlinePlayers()) {
                        victim.showPlayer(main.getPlugin(), player);
                    }

                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.prefix + "&7Staff mode enabled."));
                    staffmode.put(player, true);
                    rightClickAir.getBuildMode().put(player, false);
                    inventory.put(player, player.getInventory().getContents());

                    player.getInventory().clear();

                    giveInventory(player);
                    giveInventory(player);
                    for (Player victim : Bukkit.getOnlinePlayers()) {
                        victim.hidePlayer(main.getPlugin(), player);
                    }
                }



            } else {


                player.sendMessage(config.prefix + config.noPermission);
            }
        }
        return true;
    }

    private static ItemStack createItem(String name, Material mat, List<String> lore) {
        ItemStack item = new ItemStack(mat,1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }



    private static void giveInventory(Player player) {


        player.getInventory().setItem(0, createItem(ChatColor.GREEN + "Build mode", Material.GRASS_BLOCK,
                Collections.singletonList(ChatColor.DARK_GRAY + "(" + ChatColor.GRAY + "Right click" + ChatColor.DARK_GRAY + ")")));

        player.getInventory().setItem(1, createItem(ChatColor.GRAY + "Vanished", Material.GLASS,
                Collections.singletonList(ChatColor.DARK_GRAY + "(" + ChatColor.GRAY + "Right click" + ChatColor.DARK_GRAY + ")")));

        player.getInventory().setItem(2, createItem(ChatColor.DARK_RED + "Ban Hammer", Material.DIAMOND_AXE,
                Collections.singletonList(ChatColor.DARK_GRAY + "(" + ChatColor.GRAY + "Right click" + ChatColor.DARK_GRAY + ")")));

        player.getInventory().setItem(3, createItem(ChatColor.BLUE + "Random Teleport", Material.LEAD,
                Collections.singletonList(ChatColor.DARK_GRAY + "(" + ChatColor.GRAY + "Right click" + ChatColor.DARK_GRAY + ")")));

    }


}
