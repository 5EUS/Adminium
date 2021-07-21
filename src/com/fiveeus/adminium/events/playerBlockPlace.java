package com.fiveeus.adminium.events;

import com.fiveeus.adminium.commands.staff;
import org.bukkit.Particle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class playerBlockPlace implements Listener {

    @EventHandler
    public static void onPlace(BlockPlaceEvent e) {
        if (staff.getStaffModeList().get(e.getPlayer())) {
            if (!rightClickAir.getBuildMode().get(e.getPlayer())) {
                e.getPlayer().spawnParticle(Particle.SMOKE_LARGE, e.getBlock().getLocation(), 4);
                e.setCancelled(true);
            }
        }

    }
}
