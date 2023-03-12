package lguhcgroup.lg_uhc.listeners.gui.modules;

import lguhcgroup.lg_uhc.LG_UHC;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import static lguhcgroup.lg_uhc.gui.config.config;
import static lguhcgroup.lg_uhc.gui.modules.modules.BuildInventoryModules;

public class ModulesGuiListener implements Listener {

    private final LG_UHC main;
    public ModulesGuiListener(LG_UHC lg_uhc){
        main = lg_uhc;
    }

    @EventHandler
    public void onGuiMove(InventoryClickEvent event){
        HumanEntity player = event.getWhoClicked();
        ItemStack item = event.getCurrentItem();

        if(item == null || item.getType() == Material.AIR){
            return;
        }

        if(event.getView().getTitle().startsWith("LG UHC Modules")){
            if(item.getType().equals(Material.SKULL_ITEM)){
                if(item.getItemMeta().getDisplayName().equalsIgnoreCase("§6Timber")){
                    player.setItemOnCursor(new ItemStack(Material.AIR));
                    event.setCancelled(true);
                    if(main.getConfig().getString("modules.Timber.state").equalsIgnoreCase("enabled")){
                        main.getConfig().set("modules.Timber.state", "disabled");
                    }else{
                        main.getConfig().set("modules.Timber.state", "enabled");
                    }
                    main.saveConfig();
                    try{
                        BuildInventoryModules(player, player.getOpenInventory());
                    }catch (IllegalArgumentException e){
                        System.out.println();
                    }
                } else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§6Smelting")) {
                    if(main.getConfig().getString("modules.Smelting.state").equalsIgnoreCase("enabled")){
                        main.getConfig().set("modules.Smelting.state", "disabled");
                    }else{
                        main.getConfig().set("modules.Smelting.state", "enabled");
                    }
                    player.setItemOnCursor(new ItemStack(Material.AIR));
                    event.setCancelled(true);
                    main.saveConfig();
                    try{
                        BuildInventoryModules(player, player.getOpenInventory());
                    }catch (IllegalArgumentException e){
                        System.out.println();
                    }
                }else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§6ChancesUp")) {
                    if(main.getConfig().getString("modules.ChancesUp.state").equalsIgnoreCase("enabled")){
                        main.getConfig().set("modules.ChancesUp.state", "disabled");
                    }else{
                        main.getConfig().set("modules.ChancesUp.state", "enabled");
                    }
                    player.setItemOnCursor(new ItemStack(Material.AIR));
                    event.setCancelled(true);
                    main.saveConfig();
                    try{
                        BuildInventoryModules(player, player.getOpenInventory());
                    }catch (IllegalArgumentException e){
                        System.out.println();
                    }
                }else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§6PveResistance")) {
                    if(main.getConfig().getString("modules.PveResistance.state").equalsIgnoreCase("enabled")){
                        main.getConfig().set("modules.PveResistance.state", "disabled");
                    }else{
                        main.getConfig().set("modules.PveResistance.state", "enabled");
                    }
                    player.setItemOnCursor(new ItemStack(Material.AIR));
                    event.setCancelled(true);
                    main.saveConfig();
                    try{
                        BuildInventoryModules(player, player.getOpenInventory());
                    }catch (IllegalArgumentException e){
                        System.out.println();
                    }
                }else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§6DiamondLimit")) {
                    if(main.getConfig().getString("modules.DiamondLimit.state").equalsIgnoreCase("enabled")){
                        main.getConfig().set("modules.DiamondLimit.state", "disabled");
                    }else{
                        main.getConfig().set("modules.DiamondLimit.state", "enabled");
                    }
                    player.setItemOnCursor(new ItemStack(Material.AIR));
                    event.setCancelled(true);
                    main.saveConfig();
                    try{
                        BuildInventoryModules(player, player.getOpenInventory());
                    }catch (IllegalArgumentException e){
                        System.out.println();
                    }
                }else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§6FinalHeal")) {
                    player.setItemOnCursor(new ItemStack(Material.AIR));
                    if(main.getConfig().getString("modules.FinalHeal.state").equalsIgnoreCase("enabled")){
                        main.getConfig().set("modules.FinalHeal.state", "disabled");
                    }else{
                        main.getConfig().set("modules.FinalHeal.state", "enabled");
                    }
                    event.setCancelled(true);
                    main.saveConfig();
                    try{
                        BuildInventoryModules(player, player.getOpenInventory());
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
