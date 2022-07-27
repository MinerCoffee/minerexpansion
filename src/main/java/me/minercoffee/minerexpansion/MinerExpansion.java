package me.minercoffee.minerexpansion;

import com.jeff_media.updatechecker.UpdateCheckSource;
import com.jeff_media.updatechecker.UpdateChecker;
import com.jeff_media.updatechecker.UserAgentBuilder;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import me.minercoffee.minerexpansion.Files.DataManager;
import me.minercoffee.minerexpansion.Items.ThrowingAxe;
import me.minercoffee.minerexpansion.Items.itemscreation;
import me.minercoffee.minerexpansion.commands.*;
import me.minercoffee.minerexpansion.enchantments.*;
import me.minercoffee.minerexpansion.rtp.launchpads;
import me.minercoffee.minerexpansion.staffhomes.staffhomecmd;
import me.minercoffee.minerexpansion.utils.ColorMsg;
import me.minercoffee.minerexpansion.utils.UpdateCheckCommand;
import me.minercoffee.minerexpansion.utils.UpdateCheckListener;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import static me.minercoffee.minerexpansion.Items.itemscreation.*;
import static org.spigotmc.SpigotConfig.config;


public final class MinerExpansion extends JavaPlugin implements Listener {

    private static final int SPIGOT_RESOURCE_ID = 100584;
    private static final Logger log = Logger.getLogger("Minecraft");
    private static final Economy economy = null;
    public static MinerExpansion plugin;
    public DataManager data;
    public ArrayList<Player> launchpad_players = new ArrayList<>();
    public ArrayList<Player> ore_players = new ArrayList<>();
    public MinerExpansion() {
    }

    public static Economy getEconomy() {
        return economy;
    }

    public static MinerExpansion getPlugin() {
        return plugin;
    }
    private ProtectedCuboidRegion region;

    @Override
    public void onEnable() {

        this.getServer().getPluginManager().registerEvents(new UpdateCheckListener(this), this);
        new UpdateChecker(this, UpdateCheckSource.CUSTOM_URL, "https://github.com/MinerCoffee/MinerExpansion/blob/master/src/main/resources/latestversion.txt")
                .setDownloadLink("https://www.spigotmc.org/resources/minerexpansion.100584/")
                .setChangelogLink(SPIGOT_RESOURCE_ID)
                .setDonationLink("https://www.paypal.com/paypalme/MinerCoffee")
                .setNotifyOpsOnJoin(true)
                .setUserAgent(new UserAgentBuilder().addPluginNameAndVersion())
                .setColoredConsoleOutput(true)
                .checkEveryXHours(24)
                .checkNow();
        this.data = new DataManager(this);
        Objects.requireNonNull(getCommand("mereload")).setExecutor(new reload());
        plugin = this;
        getLogger().info("Miner Expansion has loaded in!");
        Objects.requireNonNull(getCommand("admin")).setExecutor(new AdminCommandManager());
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        saveConfig();
        getServer().getPluginManager().registerEvents(new ThrowingAxe(this), this);
        Objects.requireNonNull(getCommand("givethrowingaxe")).setExecutor(new ThrowingAxeCmd());
        itemscreation.init();
        loadConfig();
        loadEnchantment();
        new UpdateCheckCommand(this);
        getCommand("tax").setExecutor(new tax());
        getServer().getPluginManager().registerEvents(new launchpads(this), this);
        Objects.requireNonNull(getCommand("staffhome")).setExecutor(new staffhomecmd(this));
        NamespacedKey bellkey = new NamespacedKey(this, "bell");
        ShapedRecipe bellrecipe = new ShapedRecipe(bellkey, Bell);
        NamespacedKey nametagkey = new NamespacedKey(this, "nametag");
        ShapedRecipe nametagrecipe = new ShapedRecipe(nametagkey, Nametag);
        NamespacedKey leatherkey = new NamespacedKey(this, "leather");
        ShapedRecipe leatherrecipe = new ShapedRecipe(leatherkey, Leather);
        NamespacedKey cobweb = new NamespacedKey(this, "cobweb");
        ShapedRecipe cobwebrecipe = new ShapedRecipe(cobweb, Cobweb);
        //next custom recipe item starts here //
        List<String> shape1 = config.isSet("leather_recipe.shape1") ? config.getStringList("leather_recipe.shape1")
                : Arrays.asList("RRR", "RRR", "RRR");
        leatherrecipe.shape(shape1.toArray(new String[3]));
        if (config.isSet("leather_recipe")) {
            for (String key : Objects.requireNonNull(config.getConfigurationSection("leather_recipe")).getKeys(false)) {
                if (config.get("leather_recipe" + key) instanceof String) {
                    leatherrecipe.setIngredient(key.charAt(0), Material.valueOf(config.getString("leather_recipe" + key)));
                } else {
                    leatherrecipe.setIngredient(key.charAt(0),
                            new RecipeChoice.MaterialChoice(config.getStringList("leather_recipe" + key).stream()
                                    .map(Material::valueOf).collect(Collectors.toList())));
                }
            }
        } else {
            leatherrecipe.setIngredient('R', Material.valueOf(plugin.getConfig().getString("leather_ingredient")));
            plugin.saveConfig();
        }
        plugin.getServer().addRecipe(leatherrecipe);
        //next custom recipe item starts here //
        List<String> shape2 = config.isSet("shape2") ? config.getStringList("shape2")
                : Arrays.asList("SSS", " G ", "GGG");
        bellrecipe.shape(shape2.toArray(new String[3]));
        if (config.isSet("bell_recipe")) {
            for (String key : Objects.requireNonNull(config.getConfigurationSection("bell_recipe")).getKeys(false)) {
                if (config.get("bell_recipe" + key) instanceof String) {
                    bellrecipe.setIngredient(key.charAt(0), Material.valueOf(config.getString("bell_recipe" + key)));
                } else {
                    bellrecipe.setIngredient(key.charAt(0),
                            new RecipeChoice.MaterialChoice(config.getStringList("bell_recipe" + key).stream()
                                    .map(Material::valueOf).collect(Collectors.toList())));
                }
            }
        } else {
            bellrecipe.setIngredient('S', Material.valueOf(plugin.getConfig().getString("bell_ingredient_1")));
            bellrecipe.setIngredient('G', Material.valueOf(plugin.getConfig().getString("bell_ingredient_2")));
            plugin.saveConfig();
        }
        plugin.getServer().addRecipe(bellrecipe);
        //next custom recipe item starts here //
        List<String> shape3 = config.isSet("nametagrecipe.shape3") ? config.getStringList("nametagrecipe.shape3")
                : Arrays.asList("PPS", "PPP", "PPP");
        nametagrecipe.shape(shape3.toArray(new String[3]));
        if (config.isSet("nametagrecipe")) {
            for (String key : Objects.requireNonNull(config.getConfigurationSection("nametagrecipe")).getKeys(false)) {
                if (config.get("nametagrecipe" + key) instanceof String) {
                    nametagrecipe.setIngredient(key.charAt(0), Material.valueOf(config.getString("nametagrecipe" + key)));
                } else {
                    nametagrecipe.setIngredient(key.charAt(0),
                            new RecipeChoice.MaterialChoice(config.getStringList("nametagrecipe" + key).stream()
                                    .map(Material::valueOf).collect(Collectors.toList())));
                }
            }
        } else {
            nametagrecipe.setIngredient('P', Material.valueOf(plugin.getConfig().getString("nametag_ingredient_1")));
            nametagrecipe.setIngredient('S', Material.valueOf(plugin.getConfig().getString("nametag_ingredient_2")));
            plugin.saveConfig();
        }
        plugin.getServer().addRecipe(nametagrecipe);
        //next custom recipe item starts here //
        List<String> shape6 = config.isSet("cobwebrecipe.shape6") ? config.getStringList("cobwebrecipe.shape6")
                : Arrays.asList("SSS", "SSS", "SSS");
        cobwebrecipe.shape(shape6.toArray(new String[3]));
        if (config.isSet("cobwebrecipe")) {
            for (String key : Objects.requireNonNull(config.getConfigurationSection("cobwebrecipe")).getKeys(false)) {
                if (config.get("cobwebrecipe" + key) instanceof String) {
                    cobwebrecipe.setIngredient(key.charAt(0), Material.valueOf(config.getString("cobwebrecipe" + key)));
                } else {
                    cobwebrecipe.setIngredient(key.charAt(0),
                            new RecipeChoice.MaterialChoice(config.getStringList("cobwebrecipe" + key).stream()
                                    .map(Material::valueOf).collect(Collectors.toList())));
                }
            }
        } else {
            cobwebrecipe.setIngredient('S', Material.valueOf(plugin.getConfig().getString("cobweb_ingredient_1")));
            plugin.saveConfig();
        }
        plugin.getServer().addRecipe(cobwebrecipe);
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, () -> {
        }, 20, 20);
    }

    public void loadEnchantment(){
        ReverseUtils.register();
        MobdropsUtils.register();
        DoubleDropsUtils.register();
        VeinMinerUtilsII.register();
        VeinMinerUtilsI.register();
        TelepathyUtils.register();
      //  getCommand("reverse").setExecutor(new Reverse(this));
        Objects.requireNonNull(getCommand("veinminerII")).setExecutor(new VeinMinerII());
        Objects.requireNonNull(getCommand("mobdrops")).setExecutor(new Mobdrops(this));
        Objects.requireNonNull(getCommand("doubledrops")).setExecutor(new DoubleDrops(this));
        Objects.requireNonNull(getCommand("veinminerI")).setExecutor(new VeinMinerI());
        Objects.requireNonNull(this.getCommand("telepathy")).setExecutor(new Telepathy(region));
       // getServer().getPluginManager().registerEvents(new Reverse(this), this);
        getServer().getPluginManager().registerEvents(new Mobdrops(this), this);
        getServer().getPluginManager().registerEvents(new VeinMinerI(), this);
        getServer().getPluginManager().registerEvents(new DoubleDrops(this), this);
        getServer().getPluginManager().registerEvents(new VeinMinerII(), this);
        getServer().getPluginManager().registerEvents(new Telepathy(region), this);
    }

    public void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    @Override
    public void onDisable() {
        plugin = null;
        this.getServer().getConsoleSender().sendMessage(ColorMsg.color(("&9MinerExpansion v1.0 beta has been disabled")));
    }
}