package me.callahandev.listeners;

import me.callahandev.utils.GuiManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;

public class GuiListener implements Listener {
    @EventHandler
    public void onClickGui(InventoryClickEvent e){
        InventoryHolder holder = e.getInventory().getHolder();
        if(holder instanceof GuiManager){
            e.setCancelled(true);
            if (e.getCurrentItem() == null) return;
            GuiManager gui = (GuiManager) holder;
            gui.handleMenu(e);
        }

    }
}
