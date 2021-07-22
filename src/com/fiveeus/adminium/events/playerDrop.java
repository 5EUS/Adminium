package com.fiveeus.adminium.events;

import com.fiveeus.adminium.commands.staff;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class playerDrop implements Listener {

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        if (staff.getStaffModeList().get(e.getPlayer())) {
            if (!(rightClickAir.getBuildMode().get(e.getPlayer()))) {
                e.setCancelled(true);
            }
        }

    }
}
