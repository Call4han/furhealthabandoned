package me.callahandev;

import me.callahandev.commands.HealthCommand;
import me.callahandev.files.PlayersYaml;
import me.callahandev.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class FurHealth extends JavaPlugin {
    public static FurHealth plugin;
    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "Kts is Enabled");
        getCommand("FurHealth").setExecutor(new HealthCommand());


        getServer().getPluginManager().registerEvents(new GuiListener(), this);
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        //getServer().getPluginManager().registerEvents(new PreReloadListener(), this);
        plugin = this;
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        plugin.saveResource("players.yml", false);
        PlayersYaml.setup();
        PlayersYaml.save();


    }

    public static FurHealth getPlugin() {
        return plugin;
    }

}

