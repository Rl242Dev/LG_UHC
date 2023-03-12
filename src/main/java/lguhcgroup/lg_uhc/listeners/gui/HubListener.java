package lguhcgroup.lg_uhc.listeners.gui;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import static lguhcgroup.lg_uhc.gui.modules.modules.modules;
import static lguhcgroup.lg_uhc.gui.roles.roles.roles;
import static lguhcgroup.lg_uhc.gui.events.events.events;
import static lguhcgroup.lg_uhc.gui.config.config;

public class HubListener implements Listener {
    @EventHandler
    public void onEventClick(InventoryClickEvent event){
        ItemStack item = event.getCurrentItem();
        HumanEntity player = event.getWhoClicked();

        if(item == null || item.getType() == Material.AIR){
            return;
        }

        if(event.getView().getTitle().startsWith("LG UHC Config")){
            if(item.getType().equals(Material.SKULL_ITEM)){
                if(item.getItemMeta().getDisplayName().equalsIgnoreCase("§6LG UHC Events")){
                    player.setItemOnCursor(new ItemStack(Material.AIR));
                    player.openInventory(events);
                    event.setCancelled(true);
                } else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§6LG UHC Roles")) {
                    player.setItemOnCursor(new ItemStack(Material.AIR));
                    player.openInventory(roles);
                    event.setCancelled(true);
                } else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§6LG UHC Modules")) {
                    player.setItemOnCursor(new ItemStack(Material.AIR));
                    player.openInventory(modules);
                    event.setCancelled(true);
                }
            } else if (item.getType().equals(Material.BARRIER)) {
                if(item.getItemMeta().getDisplayName().equalsIgnoreCase("§cQuitter le menu")){
                    player.setItemOnCursor(new ItemStack(Material.AIR));
                    player.openInventory(config);
                    event.setCancelled(true);
                }
            }
        }
    }
}
