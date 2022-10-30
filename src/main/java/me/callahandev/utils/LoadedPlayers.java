package me.callahandev.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class LoadedPlayers {
    public void onReload(FileConfiguration config, Player player) {
     double value_config = config.getDouble(player.getUniqueId().toString() + ".health");
     double value_player = player.getAttribute(org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH).getBaseValue();
     if(value_config>=20){
        if(value_config != value_player){
            player.getAttribute(org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH).setBaseValue(value_config);
        }

    }else{
         Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "The limit health is 20, please increase more in config");}
    }
}
