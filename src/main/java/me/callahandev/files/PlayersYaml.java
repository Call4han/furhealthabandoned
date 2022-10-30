package me.callahandev.files;

import me.callahandev.FurHealth;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class PlayersYaml {

    private static File file;
    private static FileConfiguration Players;

    public static void setup() {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("FurHealth").getDataFolder(), "players.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Players = YamlConfiguration.loadConfiguration(file);
    }
    public static FileConfiguration get() {
        return Players;
    }
    public static void save() {
        try {
            Players.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void reload() {
        Players = YamlConfiguration.loadConfiguration(file);
    }
}
