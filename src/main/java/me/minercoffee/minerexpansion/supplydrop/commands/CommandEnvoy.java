package me.minercoffee.minerexpansion.supplydrop.commands;

import me.minercoffee.minerexpansion.MinerExpansion;
import me.minercoffee.minerexpansion.elyra.utils.ChatUtils;
import me.minercoffee.minerexpansion.supplydrop.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.*;

    public class CommandEnvoy implements CommandExecutor, Listener, TabCompleter {
        FileConfiguration cfg = MinerExpansion.getPlugin().getConfig();
        FileConfiguration envoyCfg = EnvoysDataManager.getEnvoysData();
        FileConfiguration dropsCfg = SupplyDropsDataManager.getSupplyDropsData();

        public CommandEnvoy() {
        }

        public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
            if (sender instanceof Player) {
                Player p = (Player)sender;
                if (p.hasPermission("miner.staff")) {
                    if (args.length == 0) {
                        SendInfoMessages.sendInfoMessage(p, "SpecifyValidEnvoySubcommand");
                        return false;
                    }

                    Location pLoc = p.getLocation();
                    int x = (int)pLoc.getX();
                    int z = (int)pLoc.getZ();
                    int envoyLength;
                    switch (args[0]) {
                        case "create":
                            if (args.length == 1) {
                                SendInfoMessages.sendInfoMessage(p, "SpecifyEnvoyName");
                                return false;
                            }
                            if (this.envoyCfg != null) {
                                this.envoyCfg.createSection("envoys." + args[1]);
                            }
                            if (this.envoyCfg != null) {
                                this.envoyCfg.set("envoys." + args[1] + ".length", 3600);
                            }
                            if (this.envoyCfg != null) {
                                this.envoyCfg.createSection("envoys." + args[1] + ".pos1");
                            }
                            if (this.envoyCfg != null) {
                                this.envoyCfg.createSection("envoys." + args[1] + ".pos2");
                            }
                            if (this.envoyCfg != null) {
                                this.envoyCfg.createSection("envoys." + args[1] + ".drops");
                            }
                            if (this.envoyCfg != null) {
                                this.envoyCfg.set("envoys." + args[1] + ".dropCount", 30);
                            }
                            EnvoysDataManager.saveEnvoysData();
                                SendInfoMessages.sendInfoMessage(p, "CreatedEnvoy", "", args[1]);
                                return false;

                        case "delete":
                            if (args.length == 1) {
                                SendInfoMessages.sendInfoMessage(p, "SpecifyEnvoyName");
                                return false;
                            }

                            if (this.envoyCfg.get("envoys." + args[1]) == null) {
                                SendInfoMessages.sendInfoMessage(p, "SpecifyValidEnvoyName");
                                return false;
                            }

                            this.envoyCfg.set("envoys." + args[1], null);
                            EnvoysDataManager.saveEnvoysData();
                            SendInfoMessages.sendInfoMessage(p, "DeletedEnvoy", "", args[1]);
                            return false;
                        case "adddrop":
                            if (args.length != 4) {
                                SendInfoMessages.sendInfoMessage(p, "SpecifyAddDropArgs");
                                return false;
                            }

                            if (this.envoyCfg.get("envoys." + args[1]) == null) {
                                SendInfoMessages.sendInfoMessage(p, "SpecifyValidEnvoyName");
                                return false;
                            }

                            if (this.dropsCfg.getString("drops." + args[2]) == null) {
                                SendInfoMessages.sendInfoMessage(p, "SpecifyValidDropName");
                                return false;
                            }

                            try {
                                envoyLength = Integer.parseInt(args[3]);
                                this.envoyCfg.set("envoys." + args[1] + ".drops." + args[2], envoyLength);
                                EnvoysDataManager.saveEnvoysData();
                                SendInfoMessages.sendInfoMessage(p, "AddedDropToEnvoy", args[2], args[1]);
                                return false;
                            } catch (Exception var24) {
                                SendInfoMessages.sendInfoMessage(p, "SpecifyValidPercentForChance");
                                return false;
                            }
                        case "setdropcount":
                            if (args.length != 3) {
                                SendInfoMessages.sendInfoMessage(p, "SpecifyDropCountArgs");
                                return false;
                            }

                            if (this.envoyCfg.get("envoys." + args[1]) == null) {
                                SendInfoMessages.sendInfoMessage(p, "SpecifyValidEnvoyName");
                                return false;
                            }

                            try {
                                envoyLength = Integer.parseInt(args[2]);
                                this.envoyCfg.set("envoys." + args[1] + ".dropCount", envoyLength);
                                EnvoysDataManager.saveEnvoysData();
                                SendInfoMessages.sendInfoMessage(p, "SetDropCountForEnvoy", "", args[1]);
                                return false;
                            } catch (Exception var23) {
                                SendInfoMessages.sendInfoMessage(p, "SpecifyValidNumberForDropCount");
                                return false;
                            }
                        case "help":
                            p.sendMessage(ChatUtils.colour("&8-=========================================-\n&7=> &e/envoy create &oenvoyname  &7-&eCreate a new envoy\n&7=> &eenvoy pos1 &oenvoyname &7- &eSet first envoy boundary position\n&7=> &e/envoy pos2 &oenvoyname &7- &eSet second envoy boundary position\n&7=> &e/envoy  adddrop &oenvoyname dropname chance &7- &eAdd a drop chance to an envoy\n&7=> &e/envoy removedrop &oenvoyname dropname &7- &eRemoves a drop from an envoy\n&7=> &e/envoy setlength &oenvoyname &7- &eSet the length of an envoy\n&7=> &e/envoy setdropcount &oenvoyname &7- &eSet the drop count of an envoy\n&7=> &e/envoy  start &oenvoyname &7- &eStart an envoy\n&7=> &e/envoy delete &oenvoyname &7- &eDelete an envoy"));
                            return false;
                        case "pos1":
                            if (args.length == 1) {
                                SendInfoMessages.sendInfoMessage(p, "SpecifyEnvoyName");
                                return false;
                            }

                            if (this.envoyCfg.get("envoys." + args[1]) == null) {
                                SendInfoMessages.sendInfoMessage(p, "SpecifyValidEnvoyName");
                                return false;
                            }

                            this.envoyCfg.set("envoys." + args[1] + ".world", Objects.requireNonNull(pLoc.getWorld()).getName());
                            this.envoyCfg.set("envoys." + args[1] + ".pos1.x", x);
                            this.envoyCfg.set("envoys." + args[1] + ".pos1.z", z);
                            EnvoysDataManager.saveEnvoysData();
                            SendInfoMessages.sendInfoMessage(p, "AddedFirstEnvoyPos", "", args[1]);
                            return false;
                        case "pos2":
                            if (args.length == 1) {
                                SendInfoMessages.sendInfoMessage(p, "SpecifyEnvoyName");
                                return false;
                            }

                            if (this.envoyCfg.get("envoys." + args[1]) == null) {
                                SendInfoMessages.sendInfoMessage(p, "SpecifyValidEnvoyName");
                                return false;
                            }

                            this.envoyCfg.set("envoys." + args[1] + ".pos2.x", x);
                            this.envoyCfg.set("envoys." + args[1] + ".pos2.z", z);
                            EnvoysDataManager.saveEnvoysData();
                            SendInfoMessages.sendInfoMessage(p, "AddedSecondEnvoyPos", "", args[1]);
                            return false;
                        case "start":
                            if (args.length == 1) {
                                SendInfoMessages.sendInfoMessage(p, "SpecifyEnvoyName");
                                return false;
                            }

                            if (this.envoyCfg.get("envoys." + args[1]) == null) {
                                SendInfoMessages.sendInfoMessage(p, "SpecifyValidEnvoyName");
                                return false;
                            }

                            if (this.envoyCfg.get("envoys." + args[1] + ".pos1.x") != null && this.envoyCfg.get("envoys." + args[1] + ".pos2.x") != null) {
                                if (Objects.requireNonNull(this.envoyCfg.getConfigurationSection("envoys." + args[1] + ".drops")).getKeys(false).isEmpty()) {
                                    SendInfoMessages.sendInfoMessage(p, "SpecifyEnvoyDrops");
                                    return false;
                                }

                                envoyLength = this.envoyCfg.getInt("envoys." + args[1] + ".length");
                                final int envoyDropCount = this.envoyCfg.getInt("envoys." + args[1] + ".dropCount");
                                final int envoyPos1X = this.envoyCfg.getInt("envoys." + args[1] + ".pos1.x");
                                final int envoyPos1Z = this.envoyCfg.getInt("envoys." + args[1] + ".pos1.z");
                                final int envoyPos2X = this.envoyCfg.getInt("envoys." + args[1] + ".pos2.x");
                                final int envoyPos2Z = this.envoyCfg.getInt("envoys." + args[1] + ".pos2.z");
                                String worldName = this.envoyCfg.getString("envoys." + args[1] + ".world");

                                assert worldName != null;

                                final World world = Bukkit.getWorld(worldName);
                                 final ArrayList<RandomDrop> randomDrops = new ArrayList();

                                for (String key : Objects.requireNonNull(this.envoyCfg.getConfigurationSection("envoys." + args[1] + ".drops")).getKeys(false)) {
                                    int val = this.envoyCfg.getInt("envoys." + args[1] + ".drops." + key);
                                    randomDrops.add(new RandomDrop(key, val));
                                }

                                (new BukkitRunnable() {
                                    int droppedCount = 0;

                                    public void run() {
                                        ++this.droppedCount;
                                        Random randPos = new Random();
                                        int totalSum = 0;

                                        RandomDrop drop;
                                        for(Iterator var4 = randomDrops.iterator(); var4.hasNext(); totalSum += drop.dropProbability) {
                                            drop = (RandomDrop)var4.next();
                                        }

                                        int index = randPos.nextInt(totalSum);
                                        int sum = 0;

                                        int i;
                                        for(i = 0; sum < index; sum += randomDrops.get(i++).dropProbability) {
                                        }

                                        String selectedDrop = randomDrops.get(Math.max(0, i - 1)).dropName;
                                        int rndLocX = me.minercoffee.minerexpansion.supplydrop.commands.CommandEnvoy.this.getRandomBetween(envoyPos1X, envoyPos2X);
                                        int rndLocZ = me.minercoffee.minerexpansion.supplydrop.commands.CommandEnvoy.this.getRandomBetween(envoyPos1Z, envoyPos2Z);
                                        int curHeight = 256;
                                        boolean isChecking = true;

                                        while(isChecking) {
                                            --curHeight;
                                            Location testLoc = new Location(world, rndLocX, curHeight, rndLocZ);
                                            Material testBlock = testLoc.getBlock().getType();
                                            if (!testBlock.equals(Material.AIR)) {
                                                isChecking = false;
                                            }
                                        }

                                        new Drop(selectedDrop, world, rndLocX, curHeight + 1, rndLocZ, 256, true);
                                        if (this.droppedCount == envoyDropCount) {
                                            this.cancel();
                                        }

                                    }
                                }).runTaskTimer(MinerExpansion.getPlugin(), 0L, envoyLength / envoyDropCount);
                                SendInfoMessages.sendInfoMessage(p, "StartedEnvoy", "", args[1]);
                                return false;
                            }

                            SendInfoMessages.sendInfoMessage(p, "SpecifyBothBoundaryPos");
                            return false;
                        case "removedrop":
                            if (args.length != 3) {
                                SendInfoMessages.sendInfoMessage(p, "SpecifyRemoveDropArgs");
                                return false;
                            }

                            if (this.envoyCfg.get("envoys." + args[1]) == null) {
                                SendInfoMessages.sendInfoMessage(p, "SpecifyValidEnvoyName");
                                return false;
                            }

                            if (this.dropsCfg.getString("drops." + args[2]) == null) {
                                SendInfoMessages.sendInfoMessage(p, "SpecifyValidDropName");
                                return false;
                            }

                            this.envoyCfg.set("envoys." + args[1] + ".drops." + args[2], null);
                            EnvoysDataManager.saveEnvoysData();
                            EnvoysDataManager.saveEnvoysData();
                            SendInfoMessages.sendInfoMessage(p, "RemovedDropFromEnvoy", args[2], args[1]);
                            return false;
                        case "setlength":
                            if (args.length != 3) {
                                SendInfoMessages.sendInfoMessage(p, "SpecifySetLengthArgs");
                                return false;
                            }

                            if (this.envoyCfg.get("envoys." + args[1]) == null) {
                                SendInfoMessages.sendInfoMessage(p, "SpecifyValidEnvoyName");
                                return false;
                            }

                            try {
                                envoyLength = Integer.parseInt(args[2]);
                                this.envoyCfg.set("envoys." + args[1] + ".length", envoyLength * 60 * 20);
                                EnvoysDataManager.saveEnvoysData();
                                SendInfoMessages.sendInfoMessage(p, "SetLengthOfEnvoy", "", args[1]);
                                return false;
                            } catch (Exception var22) {
                                SendInfoMessages.sendInfoMessage(p, "SpecifyValidNumberForLength");
                                return false;
                            }
                    }

                    SendInfoMessages.sendInfoMessage(p, "SpecifyValidEnvoySubcommand");
                } else {
                    SendInfoMessages.sendInfoMessage(p, "InsufficientPermissions");
                }
            }

            return false;
        }

        public int getRandomBetween(int min, int max) {
            Random r = new Random();
            int mn = min;
            int mx = max;
            if (max < min) {
                mn = max;
                mx = min;
            }

            return r.nextInt(mx + 1 - mn) + mn;
        }

        public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
            if (command.getName().equals("supplydrop")) {
                List<String> arguments = new ArrayList<>();
                if (args.length == 0) {
                    arguments.add("create");
                    arguments.add("help");
                    return arguments;
                }

                if (args.length == 1) {
                    arguments.add("create");
                    arguments.add("delete");
                    arguments.add("pos1");
                    arguments.add("pos2");
                    arguments.add("start");
                    return arguments;
                }

                if (args.length == 3) {
                    arguments.add("setdropcount");
                    arguments.add("removedrop");
                    arguments.add("setlength");
                    return arguments;
                }

                if (args.length == 4) {
                    arguments.add("adddrop");
                    return arguments;
                }
            }

            return null;
        }
    }
