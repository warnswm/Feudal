package feudal;

import alterr.command.CommandHandler;
import feudal.commands.KingdomCommands;
import feudal.commands.LocalStaffCommands;
import feudal.commands.PlayerCommands;
import feudal.commands.StaffCommands;
import feudal.data.cache.CacheFeudalKingdoms;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.data.cache.CacheSpyPlayers;
import feudal.listeners.donateItems.*;
import feudal.listeners.fishing.PlayerCaughtFish;
import feudal.listeners.general.ArmorL;
import feudal.listeners.general.MobL;
import feudal.listeners.general.PlayerJoinAndQuitL;
import feudal.listeners.general.PlayerL;
import feudal.listeners.general.craftItems.CraftItemsL;
import feudal.listeners.interact.*;
import feudal.listeners.profession.*;
import feudal.listeners.territory.BlocksL;
import feudal.listeners.territory.InteractL;
import feudal.utils.LoadAndSaveDataUtils;
import feudal.utils.PlannedActivitiesUtils;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;

import static org.bukkit.Bukkit.getPluginManager;

@FieldDefaults(level = AccessLevel.PRIVATE)
public final class Feudal extends JavaPlugin {

    @Getter
    @Setter
    private static Plugin plugin;

    @Override
    public void onEnable() {

        setPlugin(this);

        LoadAndSaveDataUtils.loadAllConfigs();
        LoadAndSaveDataUtils.loadPlacedBlocks();
        LoadAndSaveDataUtils.loadAuction();

        PlannedActivitiesUtils.taxCollection();
        PlannedActivitiesUtils.restart();
        PlannedActivitiesUtils.clearMail();
        PlannedActivitiesUtils.secretOrder();

        registerCommands();
        registerListeners();
        registerEnchantments();

    }

    @Override
    public void onDisable() {

        LoadAndSaveDataUtils.saveAllPlayers();
        LoadAndSaveDataUtils.saveAllKingdoms();
        LoadAndSaveDataUtils.savePlacedBlocks();
        LoadAndSaveDataUtils.saveAllConfigs();
        LoadAndSaveDataUtils.saveAllPlayersMail();
        LoadAndSaveDataUtils.saveAuction();

        CacheFeudalPlayers.getFeudalPlayerInfo().clear();
        CacheFeudalKingdoms.getKingdomInfo().clear();
        CacheSpyPlayers.getSpyPlayerCache().clear();

    }

    private void registerCommands() {

        CommandHandler.registerCommands(StaffCommands.class, this);
        CommandHandler.registerCommands(LocalStaffCommands.class, this);
        CommandHandler.registerCommands(PlayerCommands.class, this);
        CommandHandler.registerCommands(KingdomCommands.class, this);

    }

    private void registerListeners() {

        getPluginManager().registerEvents(new ProfessionChangeMenuL(), this);
        getPluginManager().registerEvents(new AttributesUpMenuL(), this);
        getPluginManager().registerEvents(new ProfessionUpMenuL(), this);
        getPluginManager().registerEvents(new MailMenuL(), this);
        getPluginManager().registerEvents(new ClerkMenuL(), this);
        getPluginManager().registerEvents(new AuctionMenuL(), this);
        getPluginManager().registerEvents(new SwitchingProfessionMenuL(), this);

        getPluginManager().registerEvents(new PlayerJoinAndQuitL(), this);
        getPluginManager().registerEvents(new CraftItemsL(), this);
        getPluginManager().registerEvents(new PlayerL(), this);

        getPluginManager().registerEvents(new BuilderL(), this);
        getPluginManager().registerEvents(new ClerkL(), this);
        getPluginManager().registerEvents(new FishermanL(), this);
        getPluginManager().registerEvents(new MinerL(), this);
        getPluginManager().registerEvents(new WoodcutterL(), this);
        getPluginManager().registerEvents(new HunterL(), this);
        getPluginManager().registerEvents(new FarmerL(), this);
        getPluginManager().registerEvents(new CookL(), this);

        getPluginManager().registerEvents(new MobL(), this);
        getPluginManager().registerEvents(new BlocksL(), this);
        getPluginManager().registerEvents(new InteractL(), this);
        getPluginManager().registerEvents(new ArmorL(), this);
        getPluginManager().registerEvents(new PlayerCaughtFish(), this);

        getPluginManager().registerEvents(new BlindnessL(72), this);
        getPluginManager().registerEvents(new BowStunL(73), this);
        getPluginManager().registerEvents(new DesiccationL(74), this);
        getPluginManager().registerEvents(new DoubleDamageL(75), this);
        getPluginManager().registerEvents(new HookL(76), this);
        getPluginManager().registerEvents(new LevitationL(77), this);
        getPluginManager().registerEvents(new MultiShootingL(78), this);
        getPluginManager().registerEvents(new NauseaL(79), this);
        getPluginManager().registerEvents(new PoisoningL(80), this);
        getPluginManager().registerEvents(new SlowdownL(81), this);
        getPluginManager().registerEvents(new SwordStunL(82), this);
        getPluginManager().registerEvents(new VampirismL(83), this);

    }

    private void registerEnchantments() {

        try {

            try {

                Field f = Enchantment.class.getDeclaredField("acceptingNew");
                f.setAccessible(true);
                f.set(null, Boolean.valueOf(true));

                Enchantment.registerEnchantment(new BlindnessL(72));
                Enchantment.registerEnchantment(new BowStunL(73));
                Enchantment.registerEnchantment(new DesiccationL(74));
                Enchantment.registerEnchantment(new DoubleDamageL(75));
                Enchantment.registerEnchantment(new HookL(76));
                Enchantment.registerEnchantment(new LevitationL(77));
                Enchantment.registerEnchantment(new MultiShootingL(78));
                Enchantment.registerEnchantment(new NauseaL(79));
                Enchantment.registerEnchantment(new PoisoningL(80));
                Enchantment.registerEnchantment(new SlowdownL(81));
                Enchantment.registerEnchantment(new SwordStunL(82));
                Enchantment.registerEnchantment(new VampirismL(83));


            } catch (IllegalArgumentException e) {

                e.printStackTrace();

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}
