package me.callahandev.utils;

import me.callahandev.FurHealth;
import me.callahandev.files.PlayersYaml;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.text.DecimalFormat;
import java.util.UUID;

public class PlayerData {
    private Player player;
    private UUID uuid;
    private String key;
    private int health;


    public PlayerData(UUID UUID, String key, int health) {
        this.uuid = UUID;
        this.key = key;
        this.health = health;
    }
    public static double getHealth(Player player) {
        player.sendMessage(ChatColor.GREEN + "Your health is " + player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());

                double Attribute = player.getAttribute(org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH).getBaseValue();
                player.sendMessage(ChatColor.GREEN + "Your health has been set to " + Attribute);
                return Attribute;







    }
    public static void setHealth(Player player, double health) {


            double value = player.getAttribute(org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH).getBaseValue();
            double playerhealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue()+health;
            double finalresult = value + health;
            DecimalFormat df = new DecimalFormat("#.##");
            //df.format(finalresult);
            player.sendMessage(ChatColor.GREEN + "Your health has been set to " + finalresult);
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(playerhealth);
            FileConfiguration config = PlayersYaml.get();
            config.set(player.getUniqueId().toString(),null);
            config.set(player.getUniqueId().toString() + ".health", player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
            config.set(player.getUniqueId().toString() + ".name", player.getDisplayName());
            PlayersYaml.save();







    }
    public static void removeHealth(Player p,double health) {


            if (p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() > 20) {

                double base = p.getAttribute(org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH).getBaseValue();
                double playerhealth = p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
                FileConfiguration config = PlayersYaml.get();
                config.set(p.getUniqueId().toString(),null);
                config.set(p.getUniqueId().toString() + ".health", p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() - health);
                config.set(p.getUniqueId().toString() + ".name", p.getDisplayName());
                p.sendMessage(ChatColor.GREEN + "Your health has been set to " + (base - health));
                p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(playerhealth-health);
                PlayersYaml.save();
            }
            else{
                p.sendMessage(ChatColor.RED + "You can't remove health");
            }
        }

    }



