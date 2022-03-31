package me.minercoffee.minerexpansion.commands;

import me.kodysimpson.simpapi.command.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static me.minercoffee.minerexpansion.MinerExpansion.plugin;


public class Freeze extends SubCommand {

    @Override
    public String getName() {
        return "freeze";
    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public String getDescription() {
        return "freezes the player.";
    }

    @Override
    public String getSyntax() {
        return "/admin freeze";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (plugin.getConfig().getBoolean("freeze")) {
                if (args.length > 1 && p.hasPermission("miner.staff")) {
                    Player target = Bukkit.getPlayer(args[1]);
                    Objects.requireNonNull(target).setWalkSpeed(0);
                    target.playSound(target.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1, 1);
                    target.sendMessage("You just got frozen!");
                } else if (args.length == 1) {
                    p.sendMessage("You did not provide a name!");
                    p.sendMessage(" Do it like /admin freeze MinerCoffee");
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