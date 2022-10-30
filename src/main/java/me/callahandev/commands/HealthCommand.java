package me.callahandev.commands;

import me.callahandev.FurHealth;
import me.callahandev.files.PlayersYaml;
import me.callahandev.utils.ConfigUp;
import me.callahandev.utils.LoadedPlayers;
import me.callahandev.utils.subgui.GuiPlayer;
import me.callahandev.utils.subgui.GuiPlayerStats;
import me.callahandev.utils.subgui.GuiPlayerStatsAdmin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class HealthCommand implements TabExecutor {
    private static Player target;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player player = (Player) sender;
            if (args.length==0){
                player.sendMessage(ChatColor.RED + "You need more arguments");
            }else if (args.length == 1){
                if (args[0].equalsIgnoreCase("admin")) {
                    if(player.hasPermission("Furhealth.admin")){
                    GuiPlayer guiPlayer = new GuiPlayer();
                    guiPlayer.openInventory(player);
                }else {
                        player.sendMessage(ChatColor.RED + "You don't have permission");
                    }
                }
                else if (args[0].equalsIgnoreCase("reload")){
                    if (player.hasPermission("Furhealth.admin")){
                        ConfigUp configUp = new ConfigUp();
                    FurHealth.getPlugin().reloadConfig();
                    PlayersYaml.reload();
                    player.sendMessage(ChatColor.GREEN + "Reloaded correctly");
                    LoadedPlayers loadedPlayers = new LoadedPlayers();
                    configUp.configCheck();
                    for (Player player1 : Bukkit.getOnlinePlayers()){
                        loadedPlayers.onReload(PlayersYaml.get(),player1);
                    }}else {
                        player.sendMessage(ChatColor.RED + "You don't have permission");
                    }
                }else if (args[0].equalsIgnoreCase("open")){
                    GuiPlayerStats guiPlayerStats = new GuiPlayerStats();
                    setTarget(player);
                    guiPlayerStats.openInventory(player);

                    player.sendMessage(ChatColor.RED + "Amy");
                }


            }else if (args.length == 2){
                if (player.hasPermission("Furhealth.admin")){
                if (args[0].equalsIgnoreCase("open")){
                String arg = args[1];
                Player target = Bukkit.getPlayer(arg);
                if (target == null) {
                    player.sendMessage(ChatColor.RED + "Player not found");
                }else {
                    String name = target.getName();
                    Player target1 = Bukkit.getPlayer(name);
                    setTarget(target1);
                   // GuiPlayerStats guiPlayerStats = new GuiPlayerStats();
                    //guiPlayerStats.openInventory(player);
                    GuiPlayerStatsAdmin guiPlayerStatsAdmin = new GuiPlayerStatsAdmin();
                    guiPlayerStatsAdmin.openInventory(player);
                    player.sendMessage(arg);
                    //TODO put for experience trade for health
                }
            }}else {
                    player.sendMessage(ChatColor.RED + "You don't have permission");
                }
            }


        }else if (sender instanceof ConsoleCommandSender){
            ConsoleCommandSender console = (ConsoleCommandSender) sender;
            console.sendMessage(ChatColor.RED + "You need to be a player to use this command");
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length==1){
            List<String> list = new ArrayList<>();
            list.add("open");
            list.add("admin");
            return list;

        }
        return null;
    }

    public Player getTarget() {
        return target;
    }

    public void setTarget(Player target) {
        this.target = target;
    }
}
//else if (args[0].equalsIgnoreCase("open2")){
//                    GuiPlayerStatsAdmin guiPlayerStats = new GuiPlayerStatsAdmin();
//                    guiPlayerStats.openInventory(player);
//
//                }