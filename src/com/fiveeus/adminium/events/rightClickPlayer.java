package com.fiveeus.adminium.events;

import com.fiveeus.adminium.Config;
import com.fiveeus.adminium.commands.staff;
import com.fiveeus.adminium.inventories.banMenu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;


public class rightClickPlayer implements Listener {

    @EventHandler
    public static void onRightClick(PlayerInteractEntityEvent e) {


        Config config = new Config();


        Player player = e.getPlayer();
        e.getRightClicked();
        if (staff.getStaffModeList().get(player)) {
            e.getPlayer().getInventory().getItemInMainHand();
            if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.DIAMOND_AXE) {
                banMenu gui = new banMenu((Player) e.getRightClicked());
                player.openInventory(gui.getInventory());

            }
        }

    }
}
