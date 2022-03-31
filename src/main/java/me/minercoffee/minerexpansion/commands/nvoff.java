package me.minercoffee.minerexpansion.commands;

import me.kodysimpson.simpapi.command.SubCommand;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import java.util.List;

import static me.minercoffee.minerexpansion.MinerExpansion.plugin;

public class nvoff extends SubCommand {
    @Override
    public String getName() {
        return "off";
    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public String getDescription() {
        return "gives night vision effect to players.";
    }

    @Override
    public String getSyntax() {
        return "nv off";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        if (plugin.getConfig().getBoolean("nightvision") && args.length > 0 && p.hasPermission("miner.nightvision")) {
            if (args[0].equalsIgnoreCase("off")) {
                p.playSound(p.getLocation(), Sound.BLOCK_SMALL_DRIPLEAF_HIT, 1, 1);
                p.removePotionEffect(PotionEffectType.NIGHT_VISION);
            }
        }
    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] args) {
        return null;
    }
}
