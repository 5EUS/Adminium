package com.fiveeus.adminium;

import org.bukkit.ChatColor;

import java.util.logging.Logger;

public class Config {

    private static final Logger log = Logger.getLogger("Minecraft");

    public String prefix;
    public String noPermission;
    public String permission;
    public String pman_perm;
    public String leave_frozen_ban;
    public String appeal_source;
    public String frozen;
    public String unfrozen;
    public String freezePermission;

    public Config() {
        try {
            prefix = main.getPlugin().getConfig().getString("prefix");
            prefix = ChatColor.translateAlternateColorCodes('&', prefix);

            noPermission = main.getPlugin().getConfig().getString("no-permission");
            noPermission = ChatColor.translateAlternateColorCodes('&', noPermission);

            permission = main.getPlugin().getConfig().getString("main-permission");

            pman_perm = main.getPlugin().getConfig().getString("pman-permission");

            leave_frozen_ban = main.getPlugin().getConfig().getString("leave-ban-reason");
            leave_frozen_ban = ChatColor.translateAlternateColorCodes('&', leave_frozen_ban);

            appeal_source = main.getPlugin().getConfig().getString("appeal");
            appeal_source = ChatColor.translateAlternateColorCodes('&', appeal_source);

            frozen = main.getPlugin().getConfig().getString("frozen");
            frozen = ChatColor.translateAlternateColorCodes('&', frozen);

            unfrozen = main.getPlugin().getConfig().getString("unfrozen");
            unfrozen = ChatColor.translateAlternateColorCodes('&', unfrozen);

            freezePermission = main.getPlugin().getConfig().getString("freeze-permission");

        } catch (Exception e) {
            log.severe("[%s] [Adminium] Unable to load configuration file!");

        }
    }
}
