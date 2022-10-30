package me.callahandev.utils.subgui;

import me.callahandev.FurHealth;
import me.callahandev.commands.HealthCommand;
import me.callahandev.files.PlayersYaml;
import me.callahandev.utils.GuiDecorate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class GuiPlayerStats extends GuiDecorate {
    @Override
    public String getTitle() {
        return "Player Stats";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();
        Inventory inv  = e.getInventory();
        p.sendMessage(ChatColor.GREEN + ":v");


        switch (e.getCurrentItem().getType()){

            case RED_DYE:
                ItemStack item = e.getInventory().getItem(22);
                String itemname = item.getItemMeta().getDisplayName();
                itemname = ChatColor.stripColor(itemname);
                Player target = Bukkit.getPlayerExact(itemname);
                double health_player = target.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
                double toRemove  = FurHealth.getPlugin().getConfig().getDouble("Health-decrease");

                double minHealth = FurHealth.getPlugin().getConfig().getDouble("Health-min");
                if (minHealth>0){
                    if (health_player<=minHealth){//TODO Fix health this

                        p.sendMessage(ChatColor.RED + "You can't add more health");
                        return;
                    }

                }else{
                    Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Change the value of Health-max and Health-min in the config.yml elderly to 0");
                }
                target.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health_player - toRemove);
                double configData = PlayersYaml.get().getDouble(target.getUniqueId().toString() + ".health");
                //PlayersYaml.get().
                PlayersYaml.get().set(target.getUniqueId().toString() + ".health", configData - toRemove);
                PlayersYaml.save();
                break;
                //TODO create in config.yml the translations of the message, etc and system experience or money
            case LIME_DYE:

                 item = e.getInventory().getItem(22);
                System.out.println(item);
                 itemname = item.getItemMeta().getDisplayName();
                 itemname = ChatColor.stripColor(itemname);
                System.out.println(itemname);
                 target = Bukkit.getPlayerExact(itemname);
                System.out.println(target);
                double health = target.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
                double toAdd  = FurHealth.getPlugin().getConfig().getDouble("Health-increase");
                double maxHealth = FurHealth.getPlugin().getConfig().getDouble("Health-max");
                if (maxHealth>0){
                    p.sendMessage(ChatColor.GREEN + "Lime 1");
                    if (health>=maxHealth){
                        p.sendMessage(ChatColor.GREEN + "Lime 2");
                        p.sendMessage(ChatColor.RED + "You can't add more health");
                        return;
                    }
                }
                if(target.getTotalExperience()>=100){
                    target.setTotalExperience(target.getTotalExperience()-100);
                    target.setExp(0);
                    target.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health + toAdd);
                    configData = PlayersYaml.get().getDouble(target.getUniqueId().toString() + ".health");
                    PlayersYaml.get().set(target.getUniqueId().toString() + ".health", configData + toAdd);
                    PlayersYaml.save();
                }else{
                    int need = target.getTotalExperience() - 100;
                    target.sendMessage(ChatColor.GRAY + "You need"+need+ "Exp");
                }

                break;
            default:
                return;




        }


    }

    @Override
    public void setMenuItems() {
        int[] slots = {0,1,2,3,4,5,6,7,8,9,17,18,26,27,35,36,44,45,47,48,50,51,53,46,49,52};
        ItemStack glass_ = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
        ItemMeta glass_meta = glass_.getItemMeta();
        glass_meta.setDisplayName(" ");
        glass_meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        glass_meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        glass_.setItemMeta(glass_meta);
        for (int slot : slots) {
            inventory.setItem(slot, glass_);
        }
        //Todo experience system fixed

        ItemStack head = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta meta = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.PLAYER_HEAD);
        HealthCommand healthCommand = new HealthCommand();
        Player target = healthCommand.getTarget();
        meta.setOwningPlayer(target);
        String display = target.getName();
        meta.setDisplayName(ChatColor.GREEN + display);
        List<String> lore = new ArrayList<>();
        double health = target.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
        lore.add(ChatColor.RED + "❤" + ChatColor.GRAY + " Health: " + ChatColor.RED + health);
        meta.setLore(lore);
        head.setItemMeta(meta);
        inventory.setItem(22, head);
        ItemStack addHealth = new ItemStack(Material.LIME_DYE, 1);
        ItemMeta addHealthMeta = addHealth.getItemMeta();
        addHealthMeta.setDisplayName(ChatColor.GREEN + "Add Health");
        addHealth.setItemMeta(addHealthMeta);
        inventory.setItem(20, addHealth);
        ItemStack removeHealth = new ItemStack(Material.RED_DYE, 1);
        ItemMeta removeHealthMeta = removeHealth.getItemMeta();
        removeHealthMeta.setDisplayName(ChatColor.RED + "Remove Health");
        removeHealth.setItemMeta(removeHealthMeta);
        inventory.setItem(24, removeHealth);
        ItemStack expActual = new ItemStack(Material.EXPERIENCE_BOTTLE, 1);
        ItemMeta expActualMeta = expActual.getItemMeta();
        expActualMeta.setDisplayName(ChatColor.GREEN + "Experience");
        List<String> lore_experience = new ArrayList<>();
        lore_experience.add(ChatColor.GREEN + "Actual Exp: " + ChatColor.GRAY + target.getTotalExperience());
        expActualMeta.setLore(lore_experience);
        expActual.setItemMeta(expActualMeta);
        inventory.setItem(10, expActual);
    }
}
