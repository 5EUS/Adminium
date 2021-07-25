package com.fiveeus.adminium.events;

import com.fiveeus.adminium.commands.staff;
import com.fiveeus.adminium.inventories.banMenu;
import com.fiveeus.adminium.inventories.pManMenu;
import com.fiveeus.adminium.inventories.playerInv;
import com.fiveeus.adminium.inventories.playerList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class playerInvClick implements Listener {


    @EventHandler
    public static void onClick(InventoryClickEvent e) {

        ItemStack item = e.getCurrentItem();
        if (item != null) {



            Player player = (Player) e.getWhoClicked();
            Material type = item.getType();
            String name = ChatColor.stripColor(item.getItemMeta().getDisplayName());
            Player target = Bukkit.getServer().getPlayer(name);


            if (staff.getStaffModeList().get(player)) {

                if (Objects.requireNonNull(type == Material.LIGHT_GRAY_STAINED_GLASS_PANE)) {
                    e.setCancelled(true);


                } else if (ChatColor.stripColor(player.getOpenInventory().getTitle()).equals("Player List")) {
                    if (Objects.requireNonNull(type == Material.GREEN_STAINED_GLASS_PANE)) {
                        rightClickAir.getPage().put(player, rightClickAir.getPage().get(player) + 1);
                        playerList gui = new playerList(rightClickAir.getPage().get(player));
                        player.openInventory(gui.getInventory());

                    } else if (Objects.requireNonNull(type == Material.RED_STAINED_GLASS_PANE)) {
                        rightClickAir.getPage().put(player, rightClickAir.getPage().get(player) - 1);
                        playerList gui = new playerList(rightClickAir.getPage().get(player));
                        player.openInventory(gui.getInventory());

                    } else if (Objects.requireNonNull(type == Material.PLAYER_HEAD)) {
                        pManMenu gui = new pManMenu(target);
                        player.openInventory(gui.getInventory());
                    }
                } else if (ChatColor.stripColor(player.getOpenInventory().getTitle()).contains("PMAN")) {
                    Player t = Bukkit.getPlayer(ChatColor.stripColor(player.getOpenInventory().getTitle().substring(player.getOpenInventory().getTitle().lastIndexOf("|") + 2)));

                    if (Objects.requireNonNull(type == Material.CHEST)) {
                        playerInv gui = new playerInv(t);
                        player.openInventory(gui.getInventory());

                    } else if (Objects.requireNonNull(type == Material.DIAMOND_AXE)) {
                        banMenu gui = new banMenu(t);
                        player.openInventory(gui.getInventory());

                    } else if (Objects.requireNonNull(type == Material.LEAD)) {
                        player.teleport(t);
                        player.playSound(player.getLocation(), Sound.ENTITY_ENDER_EYE_DEATH, 500.0f, 1.0f);

                    }

                }

            }
        }
    }
}
