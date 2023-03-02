package lguhcgroup.lg_uhc.listeners;

import lguhcgroup.lg_uhc.LG_UHC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

    //TODO: Method pour check si il reste que des loups, Method pour check si il reste que des villageois, Method pour check si il reste que des loups, Method pour check si il reste que un solo
    private final LG_UHC main;
    public DeathListener(LG_UHC lg_uhc){
        main = lg_uhc;
    }
    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        Player player = event.getEntity();
        main.getAlivePlayer().remove(player);

        String uuid = player.getUniqueId().toString();

        int x = player.getLocation().getBlockX();
        int y = player.getLocation().getBlockY();
        int z = player.getLocation().getBlockZ();

        main.getConfig().addDefault("players."+uuid+".death-z", z);
        main.getConfig().addDefault("players."+uuid+".death-y", y);
        main.getConfig().addDefault("players."+uuid+".death-x", z);

        // Win checker
    }
}
