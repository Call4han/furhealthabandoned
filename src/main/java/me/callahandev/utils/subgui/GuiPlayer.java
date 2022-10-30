package me.callahandev.utils.subgui;

import me.callahandev.utils.GuiDecorate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

public class GuiPlayer extends GuiDecorate {
    public static Player player;



    @Override
    public String getTitle() {
        return "Health Inventory " + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers();
    }

    @Override
    public int getSlots() {
        return 54;
    }
     static ItemStack test = null;
    @Override
    public void handleMenu(InventoryClickEvent e) {
        ArrayList<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
        Player player_who = (Player) e.getWhoClicked();
        switch (e.getCurrentItem().getType()) {
            case PLAYER_HEAD:
                ItemStack item = e.getCurrentItem();
                SkullMeta meta = (SkullMeta) item.getItemMeta();
                String namee = meta.getDisplayName();
                String name = meta.getOwnerProfile().getName();
                Player testplayer = Bukkit.getPlayerExact(name);
                setPlayer(testplayer);
                Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "Player: " + getPlayer().getName());


                e.getWhoClicked().sendMessage(name);
                player_who.closeInventory();
                GuiPlayerStatsAdmin guiPlayerStats = new GuiPlayerStatsAdmin();
                guiPlayerStats.openInventory(player_who);
                break;
            case FEATHER:
                if (!((number_page + 1) >= players.size())) {
                    page = page + 1;
                    super.openInventory((Player) e.getWhoClicked());
                } else {
                    e.getWhoClicked().sendMessage(ChatColor.RED + "You are already on the last page");
                }
                break;
            case GOAT_HORN:
                if (page == 0) {
                    e.getWhoClicked().sendMessage(ChatColor.GREEN + "You are already on the first page");
                } else {
                    page = page - 1;
                    super.openInventory((Player) e.getWhoClicked());

                }
                break;
            case PAPER:


        }
    }


    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
          this.player = player;
    }

    @Override
    public void setMenuItems() {
        addGuiDecorate();
        ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta meta = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.PLAYER_HEAD);
        ArrayList<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
        if (!players.isEmpty()){
            for (int i = 0;i < getMaxItems();i++){
                number_page = getMaxItems() * page + i;
                if (number_page>=players.size())
                    break;
                if (players.get(number_page)!=null){
                    meta.setOwningPlayer(players.get(number_page));
                    meta.setDisplayName(ChatColor.GREEN + players.get(number_page).getName());
                    item.setItemMeta(meta);
                    inventory.addItem(item);
                }
            }
        }


    
    







    }




}
