package com.fiveeus.adminium.events;

import com.fiveeus.adminium.Config;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class onLeave implements Listener {

    @EventHandler
    public static void onPlayerLeave(PlayerQuitEvent e) {
        if (onMove.getFrozen().get(e.getPlayer())) {
            Config config = new Config();
            Bukkit.getBanList(BanList.Type.NAME).addBan(e.getPlayer().getName(),
                    ChatColor.translateAlternateColorCodes('&', "\n &8Reason: ") + config.leave_frozen_ban
                            + "\n"
                            + "\n"
                            + ChatColor.translateAlternateColorCodes('&',"&8Appeal: ") + config.appeal_source,
                    null, null);

        }

    }
}
