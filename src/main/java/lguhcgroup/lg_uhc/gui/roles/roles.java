package lguhcgroup.lg_uhc.gui.roles;

import lguhcgroup.lg_uhc.LG_UHC;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static lguhcgroup.lg_uhc.gui.config.getSkull;

public class roles {

    private final LG_UHC main;

    public roles(LG_UHC lg_uhc){
        main = lg_uhc;
    }

    private static final int size = 45;

    public static ItemStack generateItem(String displayName, String url){
        ItemStack head = getSkull(displayName, url);

        return head;
    }

    public static List<ItemStack> roles(){
        List<ItemStack> roles = new ArrayList<>();

        FileConfiguration config = LG_UHC.getInstance().getConfig();
        ConfigurationSection rolesSection = config.getConfigurationSection("roles");

        for (String roleName : rolesSection.getKeys(false)) {

            ArrayList<String> lore = new ArrayList<String>();

            ConfigurationSection roleSection = rolesSection.getConfigurationSection(roleName);

            String total = String.valueOf(roleSection.getInt("total"));
            lore.add("§e"+total);

            ItemStack item = generateItem("§6"+roleName, "http://textures.minecraft.net/texture/803255c939ff49ceb3abe54488598af684e661b21d45289f63b402fdd2bc7412");
            ItemMeta meta = item.getItemMeta();
            meta.setLore(lore);
            item.setItemMeta(meta);
            roles.add(item);
        }

        return roles;
    }


    public static Inventory roles = Bukkit.createInventory(null, size, "LG UHC Roles");

    static {
        LG_UHC.getInstance().reloadConfig();
        int iterator = 0;
        for(ItemStack item : roles()){
            roles.setItem(iterator, item);
            iterator++;
        }

        ItemStack quit = new ItemStack(Material.BARRIER);
        ItemMeta meta = quit.getItemMeta();
        meta.setDisplayName("§cQuitter le menu");
        quit.setItemMeta(meta);
        roles.setItem(size-1, quit);
    }

    public static void BuildInventoryEvents(HumanEntity player, InventoryView inventory){
        LG_UHC.getInstance().reloadConfig();
        int iterator = 0;
        for(ItemStack item : roles()){
            roles.setItem(iterator, item);
            iterator++;
        }

        ItemStack quit = new ItemStack(Material.BARRIER);
        ItemMeta meta = quit.getItemMeta();
        meta.setDisplayName("§cQuitter le menu");
        quit.setItemMeta(meta);
        roles.setItem(size-1, quit);
        player.openInventory(inventory);
    }
}
