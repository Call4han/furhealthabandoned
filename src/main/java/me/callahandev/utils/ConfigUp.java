package me.callahandev.utils;

import me.callahandev.FurHealth;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigUp {
    public void configCheck(){
        FileConfiguration config = FurHealth.getPlugin().getConfig();
        double maxHealth = config.getDouble("Health-max:");
        double minHealth = config.getDouble("Health-min:");
        if (maxHealth<=0){
            config.set("Health-max:", 100);

            FurHealth.getPlugin().saveConfig();
        }
        if (minHealth<=0){
            config.set("Health-min:", 20);
            FurHealth.getPlugin().saveConfig();

        }

        Bukkit.getConsoleSender().sendMessage(ChatColor.GRAY + "[FurHealth] " + ChatColor.RED + "The config was reset because it can not see invalid numbers");
        Bukkit.getConsoleSender().sendMessage(ChatColor.GRAY + "[FurHealth] " + ChatColor.RED + "Valid numbers are greater than 0");


    }
}
