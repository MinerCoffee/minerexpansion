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
    private final HashMap<String, ArmorStand> parts = new HashMap();

    public DropGlider(World world, double x, double y, double z) {
        Location loc = new Location(world, x + 0.5, y, z + 0.5);
        ArmorStand leftSide = makeNewArmorStand(loc.getWorld(), loc.getX() - 0.32, loc.getY() + 0.93, loc.getZ());
        leftSide.setHeadPose(new EulerAngle(0.0, Math.toRadians(270.0), Math.toRadians(251.0)));
        leftSide.setHelmet(new ItemStack(Material.BLACK_BANNER));
        ArmorStand middle = makeNewArmorStand(loc.getWorld(), loc.getX() + 1.345, loc.getY() + 0.76, loc.getZ());
        middle.setHeadPose(new EulerAngle(0.0, Math.toRadians(270.0), Math.toRadians(270.0)));
        middle.setHelmet(new ItemStack(Material.BLACK_BANNER));
        ArmorStand rightSide = makeNewArmorStand(loc.getWorld(), loc.getX() + 0.32, loc.getY() + 0.93, loc.getZ());
        rightSide.setHeadPose(new EulerAngle(0.0, Math.toRadians(90.0), Math.toRadians(109.0)));
        rightSide.setHelmet(new ItemStack(Material.BLACK_BANNER));
        ArmorStand leftStick = makeNewArmorStand(loc.getWorld(), loc.getX() + 0.62, loc.getY() - 0.55, loc.getZ() + 0.28);
        leftStick.setHelmet(new ItemStack(Material.STICK));
        ArmorStand rightStick = makeNewArmorStand(loc.getWorld(), loc.getX() - 0.62, loc.getY() + 0.92, loc.getZ() - 0.28);
        rightStick.setHeadPose(new EulerAngle(0.0, Math.toRadians(180.0), Math.toRadians(180.0)));
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
                if (DropGlider.this.parts.get("middle").getLocation().getY() > finalY + 1.0) {
                    var2 = DropGlider.this.parts.keySet().iterator();

                    while(var2.hasNext()) {
                        part = (String)var2.next();
                        DropGlider.this.parts.get(part).teleport(DropGlider.this.parts.get(part).getLocation().subtract(0.0, 1.0, 0.0));
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
