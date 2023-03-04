package lguhcgroup.lg_uhc;

import lguhcgroup.lg_uhc.commands.lg;
import lguhcgroup.lg_uhc.listeners.gui.ConfigListener;
import lguhcgroup.lg_uhc.listeners.info.ConnectionListener;
import lguhcgroup.lg_uhc.listeners.game.DeathListener;
import lguhcgroup.lg_uhc.listeners.game.FallDamage;
import lguhcgroup.lg_uhc.scoreboard.scoreboard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public final class LG_UHC extends JavaPlugin {

    public static Logger logger = Logger.getLogger("Minecraft");

    private static LG_UHC instance;

    public static Plugin getInstance(){
        return instance;
    }

    public List<Player> getAlivePlayer() {
        return alivePlayer;
    }

    private List<Player> alivePlayer = new ArrayList<>();

    public int getTime() {
        return Time;
    }

    public void setTime(int time) {
        Time = time;
    }

    int DayCalculator(int time){
        return (time/1200);
    }

    private int Time = 0;
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new FallDamage(this), this); // Register Events
        Bukkit.getPluginManager().registerEvents(new ConnectionListener(), this);
        Bukkit.getPluginManager().registerEvents(new DeathListener(this), this);
        Bukkit.getPluginManager().registerEvents(new ConfigListener(), this);

        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, () -> {
            setTime(getTime()+1);
        }, 0L, 20L);

        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, () -> {
            Bukkit.getServer().broadcastMessage("§b-------- Fin Episode "+DayCalculator(this.getTime())+" --------");
        }, 24000L, 24000L);

        getCommand("lg").setExecutor(new lg(this)); // Register commands

        new scoreboard(this);

        instance = this; // Instance
        getConfig().options().copyDefaults(true);
        saveConfig(); // Config

        logger.info("LG UHC has started"); // Start up message
    }

    @Override
    public void onDisable() {
        logger.info("LG UHC has been shutdown");
    }
}
