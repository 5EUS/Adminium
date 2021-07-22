package com.fiveeus.adminium;

import com.fiveeus.adminium.commands.staff;
import com.fiveeus.adminium.events.*;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Logger;

public class main extends JavaPlugin {

    private File config;

    private static final Logger log = Logger.getLogger("Minecraft");
    private YamlConfiguration yaml;

    private static Plugin plugin;






    @Override
    public void onEnable() {
        plugin = this;
        setupConfig();
        registerCommands();





    }

    @Override
    public void onDisable() {

    }

    public static Plugin getPlugin() {return plugin;}


    private void setupConfig() {
        config = new File(plugin.getDataFolder(), "config.yml");
        if(!config.exists()) {
            plugin.saveDefaultConfig();

        }


    }

    private void registerCommands() {
        this.getCommand("staff").setExecutor(new staff());
        this.getServer().getPluginManager().registerEvents(new playerBlockPlace(), this);
        this.getServer().getPluginManager().registerEvents(new playerBlockBreak(), this);
        this.getServer().getPluginManager().registerEvents(new rightClickAir(), this);
        this.getServer().getPluginManager().registerEvents(new rightClickPlayer(), this);
        this.getServer().getPluginManager().registerEvents(new playerDrop(), this);
    }

//    public void reloadConfig() {
//        try {
//            config = new File(plugin.getDataFolder(), "config.yml");
//            plugin.getConfig().load(config);
//        } catch (IOException | InvalidConfigurationException e) {
//            log.severe("[%s] [Adminium] Unable to load configuration file!");
//        }
//
//    }



}
