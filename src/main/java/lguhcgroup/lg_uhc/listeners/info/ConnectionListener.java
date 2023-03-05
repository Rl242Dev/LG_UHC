package lguhcgroup.lg_uhc.listeners.info;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectionListener implements Listener {
    @EventHandler
    public void joinEvent(PlayerJoinEvent event){
        event.setJoinMessage("§8[§2+§8] §7"+event.getPlayer().getName()+ " ("+Bukkit.getServer().getOnlinePlayers().size()+"/"+Bukkit.getServer().getMaxPlayers()+")");
    }

    @EventHandler
    public void leaveEvent(PlayerQuitEvent event){
        event.setQuitMessage("§8[§c-§8] §7"+event.getPlayer().getName()+" ("+Bukkit.getServer().getOnlinePlayers().size()+"/"+Bukkit.getServer().getMaxPlayers()+")");
    }
}
