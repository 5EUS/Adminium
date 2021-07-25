package com.fiveeus.adminium.commands;

import com.fiveeus.adminium.Config;
import com.fiveeus.adminium.inventories.pManMenu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class pman implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {

            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"[Adminium] Something went wrong."));
            return false;
        }
        Player player = ((Player) sender).getPlayer();


        Config config = new Config();



        if (command.getName().equalsIgnoreCase("pman")) {
            if (player.hasPermission(config.pman_perm)) {
                if (args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {
                        if (target.isOnline()) {
                            pManMenu gui = new pManMenu(target);
                            player.openInventory(gui.getInventory());

                        }
                    }
                } else if (args.length == 0) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.prefix + "&7No player specified!"));

                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.prefix + "&7Incorrect usage."));

                }

            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.prefix + config.noPermission));

            }
        }
        return true;
    }
}
