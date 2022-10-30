package me.callahandev.utils;

import me.callahandev.utils.GuiManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class GuiDecorate extends GuiManager {

    protected int page = 0;
    protected int itemsPorPage = 28;
    protected int number_page = 0;

    public void addGuiDecorate(){
        ItemMeta glassMeta = glass.getItemMeta();
        glassMeta.setDisplayName(" ");
        glassMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        glassMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        glass.setItemMeta(glassMeta);

        int[] slots_gui = {0,1,2,3,4,5,6,7,8,9,17,18,26,27,35,36,44,45,47,48,50,51,53};
        int[] options_gui = {46,49,52};
        int[] slots_used = {0,1,2,3,4,5,6,7,8,9,17,18,26,27,35,36,44,45,47,48,50,51,53,46,49,52};
        int[] slots_players = {10,11,12,13,14,15,16,19,20,21,22,23,24,25,28,29,30,31,32,33,34,37,38,39,40,41,42,43,46,49,52};
        for (int slot : slots_gui) {
            inventory.setItem(slot, glass);
        }

        //next page
        ItemStack next_page = new ItemStack(Material.FEATHER, 1);
        ItemMeta next_page_meta = next_page.getItemMeta();
        next_page_meta.setDisplayName(ChatColor.GREEN + "Next Page");
        next_page.setItemMeta(next_page_meta);
        //previous page
        ItemStack previous_page = new ItemStack(Material.GOAT_HORN, 1);
        ItemMeta previous_page_meta = previous_page.getItemMeta();
        previous_page_meta.setDisplayName(ChatColor.GREEN + "Previous Page");
        previous_page.setItemMeta(previous_page_meta);
        //paper
        ItemStack actual_page = new ItemStack(Material.PAPER, 1);
        ItemMeta actual_page_meta = actual_page.getItemMeta();
        actual_page_meta.setDisplayName(ChatColor.GREEN + "Actual Page" + ChatColor.WHITE + " " + ChatColor.BOLD + (page+1));
        actual_page.setItemMeta(actual_page_meta);

        inventory.setItem(46, previous_page);
        inventory.setItem(49, actual_page);
        inventory.setItem(52, next_page);
    }
    public int getMaxItems() {
        return itemsPorPage;
    }
}

