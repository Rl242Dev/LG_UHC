package lguhcgroup.lg_uhc.modules.modules.ChancesUp;

import org.bukkit.Material;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class GravelDrop implements Listener {
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Chicken) {
            // Double the chances of getting a feather
            if (Math.random() < 0.5) {
                entity.getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.FEATHER, 2));
            }
        }
    }
}
