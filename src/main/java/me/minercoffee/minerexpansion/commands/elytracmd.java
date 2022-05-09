package me.minercoffee.minerexpansion.commands;

import me.kodysimpson.simpapi.command.SubCommand;
import me.minercoffee.minerexpansion.elyra.utils.RecipeUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

import static me.minercoffee.minerexpansion.MinerExpansion.plugin;


public class elytracmd extends SubCommand {
    @Override
    public String getName() {
        return "charcoal-elytra";
    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getSyntax() {
        return "/admin charcoal-elytra";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if (plugin.getConfig().getBoolean("elytra")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Only players can run this command.");
            } else {
                Player p = (Player) sender;
                if (p.isOp()) {
                    p.getInventory().addItem(RecipeUtils.getElytra());
                } else {
                    sender.sendMessage("You are not allowed to use this command.");
                }
            }
        }
    }
    @Override
    public List<String> getSubcommandArguments(Player player, String[] args) {
        if (args.length == 2){
            List<String> playerNames = new ArrayList<>();
            Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
            Bukkit.getServer().getOnlinePlayers().toArray(players);
            for (Player value : players) {
                playerNames.add(value.getName());
            }
            return playerNames;
        }
        return null;
    }
}
