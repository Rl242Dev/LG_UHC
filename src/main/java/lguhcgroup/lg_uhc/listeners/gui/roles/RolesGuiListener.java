package lguhcgroup.lg_uhc.listeners.gui.roles;

import lguhcgroup.lg_uhc.LG_UHC;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import static lguhcgroup.lg_uhc.gui.config.config;
import static lguhcgroup.lg_uhc.gui.roles.roles.BuildInventoryEvents;

public class RolesGuiListener implements Listener {

    private final LG_UHC main;
    private FileConfiguration configs;

    public RolesGuiListener(LG_UHC lg_uhc){
        main = lg_uhc;
        this.configs = main.getConfig();
    }

    public ConfigurationSection getRoleSection(String roleName) {
        ConfigurationSection roles = configs.getConfigurationSection("roles");
        if (roles == null) {
            return null;
        }
        ConfigurationSection roleSection = roles.getConfigurationSection(roleName);
        return roleSection;
    }

    public int getRoleTotal(String roleName) {
        ConfigurationSection roleSection = getRoleSection(roleName);
        if (roleSection == null) {
            return 0;
        }
        return roleSection.getInt("total");
    }

    public int getRoleMax(String roleName) {
        ConfigurationSection roleSection = getRoleSection(roleName);
        if (roleSection == null) {
            return 0;
        }
        return roleSection.getInt("max");
    }

    @EventHandler
    public void onGuiMove(InventoryClickEvent event){
        HumanEntity player = event.getWhoClicked();
        ItemStack item = event.getCurrentItem();

        if(item == null || item.getType() == Material.AIR){
            return;
        }

        if(event.getView().getTitle().startsWith("LG UHC Roles")){

            if(item.getType().equals(Material.SKULL_ITEM)){
                String roleName = item.getItemMeta().getDisplayName().substring(2);
                int max = getRoleMax(roleName);
                int total = getRoleTotal(roleName) + 1;

                if(roleName.equalsIgnoreCase("Soeurs")){
                    total = 2;
                }

                System.out.println("roles."+roleName+".total");

                if(total > max){
                    configs.set("roles."+roleName+".total", 0);
                }else {
                    configs.set("roles."+roleName+".total", total);
                }

                player.setItemOnCursor(new ItemStack(Material.AIR));
                event.setCancelled(true);
                main.saveConfig();

                try{
                    BuildInventoryEvents(player, player.getOpenInventory());
                }catch (IllegalArgumentException e){
                    System.out.println();
                }
            } else if (item.getType().equals(Material.BARRIER)) {
                player.setItemOnCursor(new ItemStack(Material.AIR));
                player.openInventory(config);
                event.setCancelled(true);
            }
        }
    }
}
