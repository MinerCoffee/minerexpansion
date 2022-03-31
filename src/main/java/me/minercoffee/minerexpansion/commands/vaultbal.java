package me.minercoffee.minerexpansion.commands;

import me.minercoffee.minerexpansion.MinerExpansion;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import static me.minercoffee.minerexpansion.MinerExpansion.plugin;

public class vaultbal implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            Economy economy = MinerExpansion.getEconomy();
            if (plugin.getConfig().getBoolean("vaultbal")) {
                if (args.length == 0) {
                    //Get a balance of a player
                    p.sendMessage(ChatColor.GREEN + "Current Balance: " + ChatColor.GOLD + economy.format(economy.getBalance(p)));
                } else if (args.length == 2 && args[0].equalsIgnoreCase("withdraw")) {
                    double withdraw_amount = Double.parseDouble(args[1]);
                    //if the method returns an economyresponse, set the method equal to a reference for one
                    //so that you can use it for information on the transaction
                    EconomyResponse response = economy.withdrawPlayer(p, withdraw_amount);
                    if (response.transactionSuccess()) {
                        p.sendMessage("You have successfully removed: " + economy.format(response.amount));
                        p.sendMessage("Your new balance is: " + economy.format(response.balance));
                    } else {
                        p.sendMessage("Failed to withdraw money from your balance.");
                        p.sendMessage(response.errorMessage);
                    }
                }
            }
        }
        return true;
    }
}