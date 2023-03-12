package lguhcgroup.lg_uhc.listeners.gui.events;

import lguhcgroup.lg_uhc.LG_UHC;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import static lguhcgroup.lg_uhc.gui.config.config;
import static lguhcgroup.lg_uhc.gui.events.events.BuildInventoryEvents;

public class EventGuiListener implements Listener {

    private final LG_UHC main;

    public EventGuiListener(LG_UHC lg_uhc){
        main = lg_uhc;
    }

    @EventHandler
    public void onGuiMove(InventoryClickEvent event){
        HumanEntity player = event.getWhoClicked();
        ItemStack item = event.getCurrentItem();

        if(item == null || item.getType() == Material.AIR){
            return;
        }

        if(event.getView().getTitle().startsWith("LG UHC Events")){
            if(item.getType().equals(Material.SKULL_ITEM)){
                if(item.getItemMeta().getDisplayName().equalsIgnoreCase("§6Exposed")){
                    int chances = main.getConfig().getInt("events.Exposed.chances");
                    if(chances == 100){
                        chances = -10;
                    }
                    player.setItemOnCursor(new ItemStack(Material.AIR));
                    event.setCancelled(true);
                    main.getConfig().set("events.Exposed.chances", chances+10);
                    main.saveConfig();
                    try {
                        BuildInventoryEvents(player, player.getOpenInventory());
                    }catch (IllegalArgumentException e){
                        System.out.println();
                    }
                } else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§6LGAlone")) {
                    int chances = main.getConfig().getInt("events.LGAlone.chances");
                    if(chances == 100){
                        chances = -10;
                    }
                    player.setItemOnCursor(new ItemStack(Material.AIR));
                    event.setCancelled(true);
                    main.getConfig().set("events.LGAlone.chances", chances+10);
                    main.saveConfig();
                    try {
                        BuildInventoryEvents(player, player.getOpenInventory());
                    }catch (IllegalArgumentException e){
                        System.out.println();
                    }
                }else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§6Trouple")) {
                    int chances = main.getConfig().getInt("events.Trouple.chances");
                    if(chances == 100){
                        chances = -10;
                    }
                    player.setItemOnCursor(new ItemStack(Material.AIR));
                    event.setCancelled(true);
                    main.getConfig().set("events.Trouple.chances", chances+10);
                    main.saveConfig();
                    try {
                        BuildInventoryEvents(player, player.getOpenInventory());
                    }catch (IllegalArgumentException e){
                        System.out.println();
                    }
                }else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§6Rumeurs")) {
                    int chances = main.getConfig().getInt("events.Rumeurs.chances");
                    if(chances == 100){
                        chances = -10;
                    }
                    player.setItemOnCursor(new ItemStack(Material.AIR));
                    event.setCancelled(true);
                    main.getConfig().set("events.Rumeurs.chances", chances+10);
                    main.saveConfig();
                    try {
                        BuildInventoryEvents(player, player.getOpenInventory());
                    }catch (IllegalArgumentException e){
                        System.out.println();
                    }
                }
            }else if (item.getType().equals(Material.BARRIER)) {
                player.setItemOnCursor(new ItemStack(Material.AIR));
                player.openInventory(config);
                event.setCancelled(true);
            }
        }
    }
}
