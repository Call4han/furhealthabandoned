package me.callahandev.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public abstract class GuiManager implements InventoryHolder {

    protected Inventory inventory;
    protected ItemStack glass = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);

    public abstract String getTitle();
    public abstract int getSlots();
    public abstract void handleMenu(InventoryClickEvent e);
    public abstract void setMenuItems();
    public void openInventory(Player p){
         inventory = Bukkit.createInventory(this, getSlots(), getTitle());
         this.setMenuItems();
         p.openInventory(inventory);



    }
    @Override
    public Inventory getInventory() {
        return inventory;
    }
    public void setGlass(){
        for(int i = 0; i < getSlots(); i++){
            if (inventory.getItem(i) == null){
                inventory.setItem(i, glass);
            }
        }
    }
    public ItemStack itemStack(Material material, String name, ArrayList<String> lore){
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
}
