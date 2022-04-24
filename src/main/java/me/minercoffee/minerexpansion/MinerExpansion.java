package me.minercoffee.minerexpansion;

import com.jeff_media.updatechecker.UpdateCheckSource;
import com.jeff_media.updatechecker.UpdateChecker;
import com.jeff_media.updatechecker.UserAgentBuilder;
import me.minercoffee.minerexpansion.Files.DataManager;
import me.minercoffee.minerexpansion.Items.ThrowingAxe;
import me.minercoffee.minerexpansion.Items.itemscreation;
import me.minercoffee.minerexpansion.chuck.seechucks;
import me.minercoffee.minerexpansion.commands.*;
import me.minercoffee.minerexpansion.elyra.utils.Bar;
import me.minercoffee.minerexpansion.elyra.utils.ChatUtils;
import me.minercoffee.minerexpansion.elyra.utils.events.Elytra;
import me.minercoffee.minerexpansion.elyra.utils.events.PreventAnvilUse;
import me.minercoffee.minerexpansion.enchantments.*;
import me.minercoffee.minerexpansion.grapplinghook.GrapplingHook;
import me.minercoffee.minerexpansion.grapplinghook.GrapplingHookCooldown;
import me.minercoffee.minerexpansion.rtp.launchpads;
import me.minercoffee.minerexpansion.rtp.rtpcmd;
import me.minercoffee.minerexpansion.silktouchspawners.BlockAlerts;
import me.minercoffee.minerexpansion.silktouchspawners.BreakBlockListener;
import me.minercoffee.minerexpansion.silktouchspawners.SpawnerListeners;
import me.minercoffee.minerexpansion.staffhomes.staffhomecmd;
import me.minercoffee.minerexpansion.supplydrop.commands.CommandDeleteSupplyDrop;
import me.minercoffee.minerexpansion.supplydrop.commands.CommandEditSupplyDrop;
import me.minercoffee.minerexpansion.supplydrop.commands.CommandEnvoy;
import me.minercoffee.minerexpansion.supplydrop.commands.CommandSupplyDrop;
import me.minercoffee.minerexpansion.supplydrop.utils.EnvoysDataManager;
import me.minercoffee.minerexpansion.supplydrop.utils.SupplyDropsDataManager;
import me.minercoffee.minerexpansion.utils.UpdateCheckCommand;
import me.minercoffee.minerexpansion.utils.UpdateCheckListener;
import me.minercoffee.minerexpansion.warps.*;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Location;
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
import static me.minercoffee.minerexpansion.elyra.utils.RecipeUtils.getElytra;
import static org.spigotmc.SpigotConfig.config;


public final class MinerExpansion extends JavaPlugin implements Listener {

    private static final int SPIGOT_RESOURCE_ID = 100584;
    private static final Logger log = Logger.getLogger("Minecraft");
    private static final Economy economy = null;
    public static MinerExpansion plugin;
    public static ArrayList<Player> viewers = new ArrayList<>();
    public static ArrayList<Location> viewerslocs = new ArrayList<>();
    public DataManager data;
    public EnvoysDataManager envoysDataManager;
    public ArrayList<Player> launchpad_players = new ArrayList<>();
    public ArrayList<Player> ore_players = new ArrayList<>();
    public Bar bar;

    public MinerExpansion() {
    }

    public static Economy getEconomy() {
        return economy;
    }

    public static MinerExpansion getPlugin() {
        return plugin;
    }

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
        envoysDataManager = new EnvoysDataManager(this);
        this.data = new DataManager(this);
        Objects.requireNonNull(getCommand("mereload")).setExecutor(new reload());
        plugin = this;
        getLogger().info("Miner Expansion has loaded in!");
        this.getServer().getPluginManager().registerEvents(new BlockAlerts(), this);
        Objects.requireNonNull(getCommand("rtp")).setExecutor(new rtpcmd());
        Objects.requireNonNull(getCommand("nv")).setExecutor(new NightVisionManager());
        Objects.requireNonNull(getCommand("admin")).setExecutor(new AdminCommandManager());
        Objects.requireNonNull(this.getCommand("vaultbal")).setExecutor(new vaultbal());
        Objects.requireNonNull(this.getCommand("megamillion")).setExecutor(new Slots());
        this.getServer().getPluginManager().registerEvents(new Slots(), this);
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        saveConfig();
        itemscreation.init();
        loadConfig();
        loadEnchantment();
        loadEnvoy();
        new DoubleDrops(this);
        new Warpgui(this);
        new warp(this);
        new setwarps(this);
        new delwarp(this);
        new UpdateCheckCommand(this);
        getServer().getPluginManager().registerEvents(new PreventAnvilUse(), this);
        getServer().getPluginManager().registerEvents(new BreakBlockListener(), this);
        getServer().getPluginManager().registerEvents(new SpawnerListeners(), this);
        getServer().getPluginManager().registerEvents(new ThrowingAxe(this), this);
        Objects.requireNonNull(this.getCommand("givethrowingaxe")).setExecutor(new ThrowingAxeCmd());
        getServer().getPluginManager().registerEvents(new GrapplingHook(), this);
        Objects.requireNonNull(getCommand("givegrapplinghook")).setExecutor(new GrapplingHookcmd());
        GrapplingHookCooldown.setupCooldown();
        getServer().getPluginManager().registerEvents(new launchpads(this), this);
        Objects.requireNonNull(this.getCommand("chunkvisualizer")).setExecutor(new seechucks());
        Objects.requireNonNull(getCommand("staffhome")).setExecutor(new staffhomecmd(this));
        NamespacedKey elytra = new NamespacedKey(this, "minerexpansion_elytra");
        ShapedRecipe elytracraft = new ShapedRecipe(elytra, getElytra());
        NamespacedKey bellkey = new NamespacedKey(this, "bell");
        ShapedRecipe bellrecipe = new ShapedRecipe(bellkey, Bell);
        NamespacedKey nametagkey = new NamespacedKey(this, "nametag");
        ShapedRecipe nametagrecipe = new ShapedRecipe(nametagkey, Nametag);
        NamespacedKey leatherkey = new NamespacedKey(this, "leather");
        ShapedRecipe leatherrecipe = new ShapedRecipe(leatherkey, Leather);
        NamespacedKey throwingaxe = new NamespacedKey(this, "throwingaxe");
        ShapedRecipe throwingaxerecipe = new ShapedRecipe(throwingaxe, itemscreation.ThrowingAxe);
        NamespacedKey grappinghook = new NamespacedKey(this, "grappinghook");
        ShapedRecipe grappinghookrecipe = new ShapedRecipe(grappinghook, itemscreation.GrapplingHook);
        NamespacedKey cobweb = new NamespacedKey(this, "cobweb");
        ShapedRecipe cobwebrecipe = new ShapedRecipe(cobweb, Cobweb);

        //next custom recipe item starts here //
        List<String> shape = config.isSet("shape") ? config.getStringList("shape")
                : Arrays.asList("DND", "NEN", "DND");
        elytracraft.shape(shape.toArray(new String[3]));
        if (config.isSet("crafting")) {
            for (String key : Objects.requireNonNull(config.getConfigurationSection("crafting")).getKeys(false)) {
                if (config.get("crafting" + key) instanceof String) {
                    elytracraft.setIngredient(key.charAt(0), Material.valueOf(config.getString("crafting" + key)));
                } else {
                    elytracraft.setIngredient(key.charAt(0),
                            new RecipeChoice.MaterialChoice(config.getStringList("crafting" + key).stream()
                                    .map(Material::valueOf).collect(Collectors.toList())));
                }
            }
        } else {
            elytracraft.setIngredient('D', Material.valueOf(plugin.getConfig().getString("D")));
            elytracraft.setIngredient('N', Material.valueOf(plugin.getConfig().getString("N")));
            elytracraft.setIngredient('E', Material.valueOf(plugin.getConfig().getString("E")));
            plugin.saveConfig();
        }
        plugin.getServer().addRecipe(elytracraft);
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
        List<String> shape4 = config.isSet("throwingaxerecipe.shape4") ? config.getStringList("throwingaxerecipe.shape4")
                : Arrays.asList("WW ", "WS ", "GGG");
        throwingaxerecipe.shape(shape4.toArray(new String[3]));
        if (config.isSet("throwingaxerecipe")) {
            for (String key : Objects.requireNonNull(config.getConfigurationSection("throwingaxerecipe")).getKeys(false)) {
                if (config.get("throwingaxerecipe" + key) instanceof String) {
                    throwingaxerecipe.setIngredient(key.charAt(0), Material.valueOf(config.getString("throwingaxerecipe" + key)));
                } else {
                    throwingaxerecipe.setIngredient(key.charAt(0),
                            new RecipeChoice.MaterialChoice(config.getStringList("throwingaxerecipe" + key).stream()
                                    .map(Material::valueOf).collect(Collectors.toList())));
                }
            }
        } else {
            throwingaxerecipe.setIngredient('W', Material.valueOf(plugin.getConfig().getString("throwingaxe_ingredient_1")));
            throwingaxerecipe.setIngredient('S', Material.valueOf(plugin.getConfig().getString("throwingaxe_ingredient_2")));
            throwingaxerecipe.setIngredient('G', Material.valueOf(plugin.getConfig().getString("throwingaxe_ingredient_3")));
            plugin.saveConfig();
        }
        plugin.getServer().addRecipe(throwingaxerecipe);
        //next custom recipe item starts here //
        List<String> shape5 = config.isSet("grappinghookrecipe.shape5") ? config.getStringList("grappinghookrecipe.shape5")
                : Arrays.asList("SS ", "SW ", "  I");
        grappinghookrecipe.shape(shape5.toArray(new String[3]));
        if (config.isSet("grappinghookrecipe")) {
            for (String key : Objects.requireNonNull(config.getConfigurationSection("grappinghookrecipe")).getKeys(false)) {
                if (config.get("grappinghookrecipe" + key) instanceof String) {
                    grappinghookrecipe.setIngredient(key.charAt(0), Material.valueOf(config.getString("grappinghookrecipe" + key)));
                } else {
                    grappinghookrecipe.setIngredient(key.charAt(0),
                            new RecipeChoice.MaterialChoice(config.getStringList("grappinghookrecipe" + key).stream()
                                    .map(Material::valueOf).collect(Collectors.toList())));
                }
            }
        } else {
            grappinghookrecipe.setIngredient('W', Material.valueOf(plugin.getConfig().getString("grappinghook_ingredient_1")));
            grappinghookrecipe.setIngredient('S', Material.valueOf(plugin.getConfig().getString("grappinghook_ingredient_2")));
            grappinghookrecipe.setIngredient('I', Material.valueOf(plugin.getConfig().getString("grappinghook_ingredient_3")));
            plugin.saveConfig();
        }
        plugin.getServer().addRecipe(grappinghookrecipe);
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
        getServer().getPluginManager().registerEvents(new Elytra(), this);
    }

    public void loadEnchantment(){
        DoubleDropsUtils.register();
        VeinMinerUtilsII.register();
        VeinMinerUtilsI.register();
        TelepathyUtils.register();
        Objects.requireNonNull(getCommand("doubledrops")).setExecutor(new DoubleDrops(this));
        getServer().getPluginManager().registerEvents(new DoubleDrops(this), this);
        Objects.requireNonNull(getCommand("veinminerII")).setExecutor(new VeinMinerII());
        getServer().getPluginManager().registerEvents(new VeinMinerII(), this);
        Objects.requireNonNull(getCommand("veinminerI")).setExecutor(new VeinMinerI());
        getServer().getPluginManager().registerEvents(new VeinMinerI(), this);
        Objects.requireNonNull(this.getCommand("telepathy")).setExecutor(new Telepathy());
        getServer().getPluginManager().registerEvents(new Telepathy(), this);
    }

    public void loadEnvoy(){
        Bukkit.getServer().getPluginManager().registerEvents(new CommandEditSupplyDrop(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new CommandEnvoy(), this);
        Objects.requireNonNull(this.getCommand("supplydrop")).setExecutor(new CommandSupplyDrop());
        Objects.requireNonNull(this.getCommand("editsupplydrop")).setExecutor(new CommandEditSupplyDrop());
        Objects.requireNonNull(this.getCommand("deletesupplydrop")).setExecutor(new CommandDeleteSupplyDrop());
        Objects.requireNonNull(this.getCommand("envoy")).setExecutor(new CommandEnvoy());
        SupplyDropsDataManager.saveDefaultConfig();
        EnvoysDataManager.saveDefaultConfig();
    }

    public void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    @Override
    public void onDisable() {
        plugin = null;
        this.getServer().getConsoleSender().sendMessage(ChatUtils.colour("&9MinerExpansion v1.0 beta has been disabled"));
    }
}