package me.minercoffee.minerexpansion.utils;

import com.jeff_media.updatechecker.UpdateCheckEvent;
import com.jeff_media.updatechecker.UpdateCheckResult;
import com.jeff_media.updatechecker.UpdateCheckSuccess;
import me.minercoffee.minerexpansion.MinerExpansion;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class UpdateCheckListener implements Listener {
    private final MinerExpansion plugin;

    public UpdateCheckListener(MinerExpansion plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onUpdateCheck(UpdateCheckEvent event) {
        if(event.getRequesters()==null) return;

        for(CommandSender sender : event.getRequesters()) {
            plugin.getLogger().info(sender.getName() + " has requested an update check, aaaaaaaand...");
        }

        if(event.getSuccess() == UpdateCheckSuccess.FAIL) {
            plugin.getLogger().info("it failed, lol");
        } else if(event.getResult() == UpdateCheckResult.NEW_VERSION_AVAILABLE) {
            plugin.getLogger().info("there is a new version available: "+event.getLatestVersion());
        } else {
            plugin.getLogger().info("you are running the latest version :)");
        }

    }
}