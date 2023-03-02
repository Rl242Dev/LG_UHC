package lguhcgroup.lg_uhc.listeners;

import lguhcgroup.lg_uhc.LG_UHC;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class FallDamage implements Listener {

    private final LG_UHC main;

    public FallDamage(LG_UHC lg_uhc){
        main = lg_uhc;
    }
    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event){
        if(main.getTime() < 60){
            if(event.getCause().equals(EntityDamageEvent.DamageCause.FALL)){
                event.setCancelled(true);
            }
        }
    }
}
