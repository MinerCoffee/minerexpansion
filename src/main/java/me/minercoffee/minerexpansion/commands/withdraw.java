package me.minercoffee.minerexpansion.commands;

import me.kodysimpson.simpapi.command.SubCommand;
import me.minercoffee.minerexpansion.MinerExpansion;
import me.minercoffee.minerexpansion.elyra.utils.ChatUtils;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

import static me.minercoffee.minerexpansion.MinerExpansion.plugin;

public class withdraw extends SubCommand {
    @Override
    public String getName() {
        return "withdraw";
    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public String getDescription() {
        return "withdraws from vaultbal";
    }

    @Override
    public String getSyntax() {
        return "admin withdraw <amount>";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (plugin.getConfig().getBoolean("vaultbal")) {
                if (args.length > 1) {
                    //grab the amount to withdraw from the args
                    double amount = Double.parseDouble(args[1]);

                    //make sure it is greater than zero
                    if (amount <= 0) {
                        p.sendMessage(ChatUtils.colour("&4You must withdraw a value greater than zero."));
                    } else {
                        EconomyResponse response = MinerExpansion.getEconomy().withdrawPlayer(p, amount);
                        if (response.type == EconomyResponse.ResponseType.SUCCESS) {
                            p.sendMessage(ChatUtils.colour("&e" + amount + " has been withdrawn from your account. The new total is " + response.balance));
                        } else {
                            p.sendMessage(ChatUtils.colour("&4Your withdrawal could not be processed. Try again later."));
                        }
                    }
                } else {
                    p.sendMessage(ChatUtils.colour("&4You must withdraw a value greater than zero."));
                    p.sendMessage(ChatUtils.colour("&aExample: &e/admin withdraw 123.4"));
                }
            }
        }
    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] args) {
        return null;
    }
}
