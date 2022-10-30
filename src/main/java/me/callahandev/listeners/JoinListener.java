package me.callahandev.listeners;

import me.callahandev.FurHealth;
import me.callahandev.files.PlayersYaml;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.yaml.snakeyaml.Yaml;

public class JoinListener implements Listener {
    @EventHandler
    public void setPlayerConfig(PlayerJoinEvent e){
        Player player = e.getPlayer();
        player.sendMessage(ChatColor.GRAY + "Welcome to the server " + ChatColor.AQUA + player.getName());
        FileConfiguration config = PlayersYaml.get();
            if (config.contains(player.getUniqueId().toString())) {
                System.out.println("Player is already in the config");

            }else{
                double health = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
                System.out.println("Player is not in the config");
                config.set(player.getUniqueId().toString(),null);
                config.set(player.getUniqueId().toString() + ".name", player.getDisplayName());
                config.set(player.getUniqueId().toString() + ".health", health);
                // config.set(player.getUniqueId().toString() + ".health", player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
                PlayersYaml.save();

            }




    }
}
