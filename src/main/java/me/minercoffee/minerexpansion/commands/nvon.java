package me.minercoffee.minerexpansion.commands;

import me.kodysimpson.simpapi.command.SubCommand;
import me.minercoffee.minerexpansion.utils.ColorMsg;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static me.minercoffee.minerexpansion.MinerExpansion.plugin;


public class nvon extends SubCommand implements Listener {
    Map<String, Long> cooldowns = new HashMap<>();
    @Override
    public String getName() {
        return "on";
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
        return "nv on";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if(cooldowns.containsKey(p.getName())){
                if(cooldowns.get(p.getName()) > System.currentTimeMillis()){
                    long timeleft = (cooldowns.get(p.getName()) - System.currentTimeMillis() / 1000);
                    p.sendMessage(ColorMsg.color( "&3The ability will be ready in " + timeleft + " second(s)"));
                    return;
                }
            }
            if (plugin.getConfig().getBoolean("nightvision") && args.length > 0 && p.hasPermission("miner.nightvision")) {
                    if (args[0].equalsIgnoreCase("on")) {
                        cooldowns.put(p.getName(),System.currentTimeMillis()/1000 + 900);
                        p.playSound(p.getLocation(), Sound.BLOCK_BLASTFURNACE_FIRE_CRACKLE, 1, 1);
                        p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 72000, 1, true, false, false));
                    }
                }
            }
        }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] args) {
        return null;
    }
}
