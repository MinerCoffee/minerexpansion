package me.minercoffee.minerexpansion.supplydrop.commands;

import me.minercoffee.minerexpansion.supplydrop.utils.SendInfoMessages;
import me.minercoffee.minerexpansion.supplydrop.utils.SupplyDropsDataManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class CommandDeleteSupplyDrop implements CommandExecutor, Listener, TabCompleter {
    public CommandDeleteSupplyDrop() {
    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.hasPermission("miner.staff")) {
                    if (args.length == 0) {
                        SendInfoMessages.sendInfoMessage(p, "SpecifyDropName");
                        return false;
                    }

                    String dropName = args[0];
                    if (SupplyDropsDataManager.getSupplyDropsData().getString("drops." + dropName) != null) {
                        SupplyDropsDataManager.getSupplyDropsData().set("drops." + dropName, null);
                        SupplyDropsDataManager.saveSupplyDropsData();
                        SendInfoMessages.sendInfoMessage(p, "DeleteDrop", dropName, "");
                    } else {
                        SendInfoMessages.sendInfoMessage(p, "SpecifyValidDropName");
                    }
                } else {
                    SendInfoMessages.sendInfoMessage(p, "InsufficientPermissions");
                }
            }
        return false;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equals("supplydrop")) {
            if (args.length == 1) {
                List<String> playerNames = new ArrayList<>();
                Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
                Bukkit.getServer().getOnlinePlayers().toArray(players);
                for (Player player : players) {
                    playerNames.add(player.getName());
                }
                return playerNames;
            }
        }
        return null;
    }
}
