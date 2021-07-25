package com.fiveeus.adminium.commands;

import com.fiveeus.adminium.Config;
import com.fiveeus.adminium.events.onMove;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class freeze implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {

            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"[Adminium] Something went wrong."));
            return false;
        }
        Player player = ((Player) sender).getPlayer();

        Config config = new Config();

        if (command.getName().equalsIgnoreCase("freeze")) {
            if (player.hasPermission(config.freezePermission)) {
                if (args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {
                        if (target.isOnline()) {
                            freezePlayer(target, player);
                        }
                    } else {
                        player.sendMessage(config.prefix + ChatColor.translateAlternateColorCodes('&', "&7Player not found."));

                    }

                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.prefix + "&7Incorrect usage."));
                }

            }

        }

        return true;
    }

    public static void freezePlayer(Player t, Player s) {
        Config config = new Config();

        if (!onMove.getFrozen().containsKey(t)) {
            onMove.getFrozen().put(t, true);
            t.sendMessage(config.prefix + config.frozen);
            s.sendMessage(config.prefix + ChatColor.translateAlternateColorCodes('&', "&7Froze &b" + t.getName() + "&7."));
            return;

        } else if (onMove.getFrozen().get(t)) {
            onMove.getFrozen().put(t, false);
            t.sendMessage(config.prefix + config.unfrozen);
            s.sendMessage(config.prefix + ChatColor.translateAlternateColorCodes('&', "&7Unfroze &b" + t.getName() + "&7."));

        } else {

            onMove.getFrozen().put(t, true);
            t.sendMessage(config.prefix + config.frozen);
            s.sendMessage(config.prefix + ChatColor.translateAlternateColorCodes('&', "&7Froze &b" + t.getName() + "&7."));

        }

    }

}
