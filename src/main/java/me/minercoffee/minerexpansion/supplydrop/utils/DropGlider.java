package me.minercoffee.minerexpansion.supplydrop.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;

import me.minercoffee.minerexpansion.MinerExpansion;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;


public class DropGlider {
    private final HashMap<String, ArmorStand> parts = new HashMap<>();

    @SuppressWarnings("deprecation")
    public DropGlider(World world, double x, double y, double z) {
        Location loc = new Location(world, x + 0.5D, y, z + 0.5D);
        ArmorStand leftSide = makeNewArmorStand(loc.getWorld(), loc.getX() - 0.32D, loc.getY() + 0.93D, loc.getZ());
        leftSide.setHeadPose(new EulerAngle(0.0D, Math.toRadians(270.0D), Math.toRadians(251.0D)));
        leftSide.setHelmet(new ItemStack(Material.BLACK_BANNER));
        ArmorStand middle = makeNewArmorStand(loc.getWorld(), loc.getX() + 1.345D, loc.getY() + 0.76D, loc.getZ());
        middle.setHeadPose(new EulerAngle(0.0D, Math.toRadians(270.0D), Math.toRadians(270.0D)));
        middle.setHelmet(new ItemStack(Material.BLACK_BANNER));
        ArmorStand rightSide = makeNewArmorStand(loc.getWorld(), loc.getX() + 0.32D, loc.getY() + 0.93D, loc.getZ());
        rightSide.setHeadPose(new EulerAngle(0.0D, Math.toRadians(90.0D), Math.toRadians(109.0D)));
        rightSide.setHelmet(new ItemStack(Material.BLACK_BANNER));
        ArmorStand leftStick = makeNewArmorStand(loc.getWorld(), loc.getX() + 0.62D, loc.getY() - 0.55D, loc.getZ() + 0.28D);
        leftStick.setHelmet(new ItemStack(Material.STICK));
        ArmorStand rightStick = makeNewArmorStand(loc.getWorld(), loc.getX() - 0.62D, loc.getY() + 0.92D, loc.getZ() - 0.28D);
        rightStick.setHeadPose(new EulerAngle(0.0D, Math.toRadians(180.0D), Math.toRadians(180.0D)));
        rightStick.setHelmet(new ItemStack(Material.STICK));
        this.parts.put("leftSide", leftSide);
        this.parts.put("middle", middle);
        this.parts.put("rightSide", rightSide);
        this.parts.put("leftStick", leftStick);
        this.parts.put("rightStick", rightStick);
    }

    public void drop(final double finalY) {
        (new BukkitRunnable() {
            public void run() {
                String part;
                Iterator var2;
                if (DropGlider.this.parts.get("middle").getLocation().getY() > finalY + 1.0D) {
                    var2 = DropGlider.this.parts.keySet().iterator();

                    while(var2.hasNext()) {
                        part = (String)var2.next();
                        DropGlider.this.parts.get(part).teleport(DropGlider.this.parts.get(part).getLocation().subtract(0.0D, 1.0D, 0.0D));
                    }
                } else {
                    var2 = DropGlider.this.parts.keySet().iterator();

                    while(var2.hasNext()) {
                        part = (String)var2.next();
                        DropGlider.this.parts.get(part).remove();
                    }

                    this.cancel();
                }

            }
        }).runTaskTimer(MinerExpansion.getPlugin(), 0L, 2L);
    }

    private static ArmorStand makeNewArmorStand(World world, double x, double y, double z) {
        Location asLoc = new Location(world, x, y, z);
        ArmorStand as = Objects.requireNonNull(asLoc.getWorld()).spawn(asLoc, ArmorStand.class);
        as.setBasePlate(false);
        as.setArms(false);
        as.setVisible(false);
        as.setInvulnerable(true);
        as.setCanPickupItems(false);
        as.setGravity(false);
        as.setSmall(false);
        return as;
    }
}
