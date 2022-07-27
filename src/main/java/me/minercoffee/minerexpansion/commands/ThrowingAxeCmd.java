package me.minercoffee.minerexpansion.commands;

import me.minercoffee.minerexpansion.Items.itemscreation;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ThrowingAxeCmd implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Only players can run this command.");
        }
        else {
            Player p = (Player) sender;
                if (p.isOp()) {
                    if (command.getName().equalsIgnoreCase("givethrowingaxe")) {
                        p.getInventory().addItem(itemscreation.ThrowingAxe);
                    }
                } else {
                    sender.sendMessage("You are not allowed to use this command.");
                }
            }
        return true;
    }
}