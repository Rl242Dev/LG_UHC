package lguhcgroup.lg_uhc.gui.roles;

import lguhcgroup.lg_uhc.LG_UHC;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static lguhcgroup.lg_uhc.gui.config.getSkull;

public class roles {

    public static ItemStack generateItem(String displayName, String color, String url){
        ItemStack head = getSkull(displayName, url);
        if(color.equals("disabled")){
            head.getItemMeta().setDisplayName("§c"+displayName);
        } else if (color.equals("enabled")) {
            head.getItemMeta().setDisplayName("§a"+displayName);
        }
        return head;
    }


    public static Inventory roles = Bukkit.createInventory(null, 9, "LG UHC Roles");

    static {
        LG_UHC.getInstance().reloadConfig();
        ItemStack CutClean = generateItem("CSDQSD", LG_UHC.getInstance().getConfig().getString("modules.CutClean.state"), "http://textures.minecraft.net/texture/803255c939ff49ceb3abe54488598af684e661b21d45289f63b402fdd2bc7412");
        roles.setItem(0, CutClean);

        ItemStack quit = new ItemStack(Material.BARRIER);
        ItemMeta meta = quit.getItemMeta();
        meta.setDisplayName("§cQuitter le menu");
        quit.setItemMeta(meta);
        roles.setItem(8, quit);
    }
}
