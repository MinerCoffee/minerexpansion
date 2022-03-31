package me.minercoffee.minerexpansion.elyra.utils;

import me.minercoffee.minerexpansion.MinerExpansion;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class ChargeBar {
    public static BossBar chargeBar;
    public static ArrayList<Player> charged = new ArrayList<>();
    public static int task;

    public ChargeBar() {
    }

    public static void run(Player p) {
        chargeBar = Bukkit.createBossBar("", BarColor.BLUE, BarStyle.SOLID);
        chargeBar.setProgress(0.0D);
        chargeBar.setVisible(true);
        chargeBar.addPlayer(p);
        charge(p);
    }

    public static void stop(Player p) {
        Bukkit.getScheduler().cancelTask(task);
        if (chargeBar != null) {
            chargeBar.removePlayer(p);
        }

        charged.remove(p);
    }

    private static void charge(final Player p) {
        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(MinerExpansion.plugin, new Runnable() {
            double progress = 0.0D;
            final double time = 0.025D;

            public void run() {
                ChargeBar.chargeBar.setProgress(this.progress);
                this.progress += 0.025D;
                if (this.progress >= 1.0D) {
                    Bukkit.getScheduler().cancelTask(ChargeBar.task);
                    ChargeBar.chargeBar.setProgress(1.0D);
                    ChargeBar.charged.add(p);
                    ChargeBar.runWhenFull(p);
                }

            }
        }, 0L, 1L);
    }

    private static void runWhenFull(Player p) {
    }
}