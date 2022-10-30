package me.callahandev.utils.subgui;

import me.callahandev.FurHealth;
import me.callahandev.commands.HealthCommand;
import me.callahandev.files.PlayersYaml;
import me.callahandev.utils.GuiDecorate;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GuiPlayerStatsAdmin extends GuiDecorate {
    private static Player target_two;
    @Override
    public String getTitle() {
        return "Player Stats "+ChatColor.RED + "Admin";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();




        switch (e.getCurrentItem().getType()) {



            case PLAYER_HEAD:
                return;


            case RED_DYE :
                ItemStack item = e.getInventory().getItem(22);
                String itemname = item.getItemMeta().getDisplayName();
                itemname = ChatColor.stripColor(itemname);
                Player player = Bukkit.getPlayerExact(itemname);
                System.out.println("RED_DYE" + player);
                double health_player = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
                double toRemove  = FurHealth.getPlugin().getConfig().getDouble("Health-decrease");
                double maxHealth = FurHealth.getPlugin().getConfig().getDouble("Health-max");
                double minHealth = FurHealth.getPlugin().getConfig().getDouble("Health-min");
                if (minHealth>0){
                    if (health_player<=minHealth){
                        p.sendMessage(ChatColor.RED + "You can't add more health");
                        return;
                    }

                }else{
                    Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Change the value of Health-max and Health-min in the config.yml elderly to 0");
                }
                player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health_player - toRemove);
                double configData = PlayersYaml.get().getDouble(player.getUniqueId().toString() + ".health");
                PlayersYaml.get().set(player.getUniqueId().toString() + ".health", configData - toRemove);
                PlayersYaml.save();
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.GREEN + "You have removed 2 health to " + player.getName()));
                p.updateInventory();
                break;

            case LIME_DYE:
                item = e.getInventory().getItem(22);
                itemname = item.getItemMeta().getDisplayName();
                itemname = ChatColor.stripColor(itemname);
                player = Bukkit.getPlayerExact(itemname);
                System.out.println("RED_DYE" + player);
                double health = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
                double toAdd  = FurHealth.getPlugin().getConfig().getDouble("Health-increase");
                maxHealth = FurHealth.getPlugin().getConfig().getDouble("Health-max");
                if (maxHealth>0){
                    if (health>=maxHealth){
                        p.sendMessage(ChatColor.RED + "You can't add more health");
                        return;
                    }
                }
                player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health + toAdd);
                configData = PlayersYaml.get().getDouble(player.getUniqueId().toString() + ".health");
                PlayersYaml.get().set(player.getUniqueId().toString() + ".health", configData + toAdd);
                PlayersYaml.save();
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.GREEN + "You have added 2 health to " + player.getName()));
                break;
            case FEATHER:
                p.closeInventory();
                GuiPlayer guiPlayer = new GuiPlayer();
                guiPlayer.openInventory(p);
                break;
            case GLOWSTONE_DUST:
                p.closeInventory();
                item = e.getInventory().getItem(22);
                itemname = item.getItemMeta().getDisplayName();
                itemname = ChatColor.stripColor(itemname);
                player =  Bukkit.getPlayerExact(itemname);
                System.out.println("RED_DYE" + player);
                double base =  FurHealth.getPlugin().getConfig().getDouble("Health-base");
                if (base>0) {
                    player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(base);
                    PlayersYaml.get().set(player.getUniqueId().toString() + ".health", base);
                    PlayersYaml.save();
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.GREEN + "You have reset the health of " + player.getName()));
                    p.sendMessage(ChatColor.GREEN + "You has set to 20 Health to " + ChatColor.GRAY + player.getName());
                }

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
        ArrayList<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());





        ItemStack increase_health = new ItemStack(Material.LIME_DYE, 1);
        ItemMeta increase_health_meta = increase_health.getItemMeta();
        increase_health_meta.setDisplayName(ChatColor.GREEN + "Increase Health of player");
        List<String> lore_increase = new ArrayList<>();
        lore_increase.add(ChatColor.GRAY + "+2 Health"+ ChatColor.GREEN + "❤");
        increase_health_meta.setLore(lore_increase);
        increase_health.setItemMeta(increase_health_meta);
        inventory.setItem(24, increase_health);

        ItemStack decrease_health = new ItemStack(Material.RED_DYE, 1);
        ItemMeta decrease_health_meta = decrease_health.getItemMeta();
        decrease_health_meta.setDisplayName(ChatColor.RED + "Decrease Health of player");
        List<String> lore_decrease = new ArrayList<>();
        lore_decrease.add(ChatColor.GRAY + "-2 Health"+ ChatColor.RED + "❤");
        decrease_health_meta.setLore(lore_decrease);
        decrease_health.setItemMeta(decrease_health_meta);
        inventory.setItem(20, decrease_health);

        ItemStack base_health = new ItemStack(Material.GLOWSTONE_DUST, 1);
        ItemMeta base_health_meta = base_health.getItemMeta();
        base_health_meta.setDisplayName(ChatColor.AQUA + "Reset Health of player");
        List<String> lore_base = new ArrayList<>();
        lore_base.add(ChatColor.DARK_PURPLE + "Reset Health to 20");
        lore_base.add(ChatColor.RED + "❤");
        base_health_meta.setLore(lore_base);
        base_health.setItemMeta(base_health_meta);
        inventory.setItem(40, base_health);

        ItemStack back = new ItemStack(Material.FEATHER, 1);
        ItemMeta back_meta = back.getItemMeta();
        back_meta.setDisplayName(ChatColor.RED + "Back");
        back.setItemMeta(back_meta);
        inventory.setItem(37, back);
        ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta meta = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.PLAYER_HEAD);
        GuiPlayer guiPlayer = new GuiPlayer();
        Player namess = guiPlayer.getPlayer();
        if (namess == null) {
            HealthCommand command = new HealthCommand();
            Player target = command.getTarget();
            String name = target.getName();
            meta.setOwningPlayer(target);
            meta.setDisplayName(ChatColor.GREEN + name);
            List<String> lore_2= new ArrayList<>();
            double health_two = target.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
            lore_2.add(ChatColor.RED + "❤" + ChatColor.GRAY + " Health: " + ChatColor.RED + health_two);
            meta.setLore(lore_2);
            item.setItemMeta(meta);
            inventory.setItem(22, item);
            return;
        }
        String display = namess.getName();

        meta.setOwningPlayer(namess);
        meta.setDisplayName(ChatColor.GREEN + display);
        List<String> lore = new ArrayList<>();
        double health = namess.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
        lore.add(ChatColor.RED + "❤" + ChatColor.GRAY + " Health: " + ChatColor.RED + health);


        meta.setLore(lore);
        item.setItemMeta(meta);


        inventory.setItem(22, item);



    }

    public static Player getTarget_two() {
        return target_two;
    }

    public static void setTarget_two(Player target_two) {
        GuiPlayerStatsAdmin.target_two = target_two;
    }
}
