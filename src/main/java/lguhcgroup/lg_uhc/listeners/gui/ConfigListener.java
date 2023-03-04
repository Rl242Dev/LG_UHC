package lguhcgroup.lg_uhc.listeners.gui;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ConfigListener implements Listener {
    @EventHandler
    public void onEventClick(InventoryClickEvent event){
        ItemStack item = event.getCurrentItem();

        if(item == null || item.getType() == Material.AIR){
            return;
        }

        if(item.getType().equals(Material.SKULL_ITEM)){
            if(item.getItemMeta().getDisplayName().equalsIgnoreCase("LG UHC Events")){
                // Open inv to events config
                event.setCancelled(true);
                System.out.println("Events");
            } else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("LG UHC Roles")) {
                // Open inv to roles config
                event.setCancelled(true);
                System.out.println("Roles");
            }
        }
    }
}
