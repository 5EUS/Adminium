package com.fiveeus.adminium;

import org.bukkit.ChatColor;

import java.util.logging.Logger;

public class Config {

    private static final Logger log = Logger.getLogger("Minecraft");

    public String prefix;
    public String noPermission;
    public String permission;
    public String pman_perm;

    public Config() {
        try {
            prefix = main.getPlugin().getConfig().getString("prefix");
            prefix = ChatColor.translateAlternateColorCodes('&', prefix);

            noPermission = main.getPlugin().getConfig().getString("no-permission");
            noPermission = ChatColor.translateAlternateColorCodes('&', noPermission);

            permission = main.getPlugin().getConfig().getString("main-permission");

            pman_perm = main.getPlugin().getConfig().getString("pman-permission");

        } catch (Exception e) {
            log.severe("[%s] [Adminium] Unable to load configuration file!");

        }
    }
}
