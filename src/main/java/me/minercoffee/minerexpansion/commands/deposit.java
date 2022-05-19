package me.minercoffee.minerexpansion.commands;

import me.kodysimpson.simpapi.command.SubCommand;
import me.minercoffee.minerexpansion.MinerExpansion;
import me.minercoffee.minerexpansion.utils.ColorMsg;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.List;

import static me.minercoffee.minerexpansion.MinerExpansion.plugin;

public class deposit extends SubCommand {
    @Override
    public String getName() {
        return "deposit";
    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public String getDescription() {
        return "deposits money into players vaultbal";
    }

    @Override
    public String getSyntax() {
        return "/admin deposit <amount>";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (plugin.getConfig().getBoolean("vaultbal")) {
            if (args.length > 1 && p.hasPermission("miner.staff")) {
                //grab the amount to deposit from the args
                double amount = Double.parseDouble(args[1]);

                //make sure it is greater than zero
                if (amount <= 0) {
                    p.sendMessage(ColorMsg.color("&4You must deposit a value greater than zero."));
                } else {
                    EconomyResponse response = MinerExpansion.getEconomy().depositPlayer(p, amount);
                    if (response.type == EconomyResponse.ResponseType.SUCCESS) {
                        p.sendMessage(ColorMsg.color("&b" + amount + " has been deposited into your account. The new total is " + response.balance));
                    } else {
                        p.sendMessage(ColorMsg.color("&4Your deposit could not be processed. Try again later."));
                    }
                }
            }
            } else {
                p.sendMessage(ColorMsg.color("&4You must deposit a value greater than zero."));
                p.sendMessage(ColorMsg.color("&aExample: &e/admin deposit 123.4"));
            }

        }
    }
    @Override
    public List<String> getSubcommandArguments(Player player, String[] args) {
        return null;
    }
}
