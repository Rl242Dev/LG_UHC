package lguhcgroup.lg_uhc.gui.modules;

import lguhcgroup.lg_uhc.LG_UHC;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

import static lguhcgroup.lg_uhc.gui.config.getSkull;

public class modules {
    public static ItemStack generateItem(String displayName, String color, String url){
        ItemStack head = getSkull(displayName, url);

        ItemMeta meta = head.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();

        if(color.equalsIgnoreCase("disabled")){
            lore.add("§cDésactivé");
            meta.setLore(lore);
        } else if (color.equalsIgnoreCase("enabled")) {
            lore.add("§aActivé");
            meta.setLore(lore);
        }

        head.setItemMeta(meta);
        return head;
    }

    public static Inventory modules = Bukkit.createInventory(null, 9, "LG UHC Modules");

    static {
        LG_UHC.getInstance().reloadConfig();
        ItemStack CutClean = generateItem("§6CutClean", LG_UHC.getInstance().getConfig().getString("modules.CutClean.state"), "http://textures.minecraft.net/texture/803255c939ff49ceb3abe54488598af684e661b21d45289f63b402fdd2bc7412");
        modules.setItem(0, CutClean);

        ItemStack Smelting = generateItem("§6Smelting", LG_UHC.getInstance().getConfig().getString("modules.Smelting.state"), "http://textures.minecraft.net/texture/803255c939ff49ceb3abe54488598af684e661b21d45289f63b402fdd2bc7412");
        modules.setItem(1, Smelting);

        ItemStack ChancesUp = generateItem("§6ChancesUp", LG_UHC.getInstance().getConfig().getString("modules.ChancesUp.state"), "http://textures.minecraft.net/texture/803255c939ff49ceb3abe54488598af684e661b21d45289f63b402fdd2bc7412");
        modules.setItem(2, ChancesUp);

        ItemStack PveResistancee = generateItem("§6PveResistance", LG_UHC.getInstance().getConfig().getString("modules.PveResistance.state"),"http://textures.minecraft.net/texture/803255c939ff49ceb3abe54488598af684e661b21d45289f63b402fdd2bc7412");
        modules.setItem(3, PveResistancee);

        ItemStack DiamondLimit = generateItem("§6DiamondLimit", LG_UHC.getInstance().getConfig().getString("modules.DiamondLimit.state"),"http://textures.minecraft.net/texture/803255c939ff49ceb3abe54488598af684e661b21d45289f63b402fdd2bc7412");
        modules.setItem(4, DiamondLimit);

        ItemStack FinalHeal = generateItem("§6FinalHeal", LG_UHC.getInstance().getConfig().getString("modules.FinalHeal.state"),"http://textures.minecraft.net/texture/803255c939ff49ceb3abe54488598af684e661b21d45289f63b402fdd2bc7412");
        modules.setItem(5, FinalHeal);

        ItemStack quit = new ItemStack(Material.BARRIER);
        ItemMeta meta = quit.getItemMeta();
        meta.setDisplayName("§cQuitter le menu");
        quit.setItemMeta(meta);
        modules.setItem(8, quit);
    }

    public static void BuildInventoryModules(HumanEntity player, InventoryView inventory){
        LG_UHC.getInstance().reloadConfig();

        ItemStack CutClean = generateItem("§6Timber", LG_UHC.getInstance().getConfig().getString("modules.Timber.state"), "http://textures.minecraft.net/texture/803255c939ff49ceb3abe54488598af684e661b21d45289f63b402fdd2bc7412");
        inventory.setItem(0, CutClean);

        ItemStack Smelting = generateItem("§6Smelting", LG_UHC.getInstance().getConfig().getString("modules.Smelting.state"), "http://textures.minecraft.net/texture/803255c939ff49ceb3abe54488598af684e661b21d45289f63b402fdd2bc7412");
        inventory.setItem(1, Smelting);

        ItemStack ChancesUp = generateItem("§6ChancesUp", LG_UHC.getInstance().getConfig().getString("modules.ChancesUp.state"), "http://textures.minecraft.net/texture/803255c939ff49ceb3abe54488598af684e661b21d45289f63b402fdd2bc7412");
        inventory.setItem(2, ChancesUp);

        ItemStack PveResistancee = generateItem("§6PveResistance", LG_UHC.getInstance().getConfig().getString("modules.PveResistance.state"),"http://textures.minecraft.net/texture/803255c939ff49ceb3abe54488598af684e661b21d45289f63b402fdd2bc7412");
        inventory.setItem(3, PveResistancee);

        ItemStack DiamondLimit = generateItem("§6DiamondLimit", LG_UHC.getInstance().getConfig().getString("modules.DiamondLimit.state"),"http://textures.minecraft.net/texture/803255c939ff49ceb3abe54488598af684e661b21d45289f63b402fdd2bc7412");
        inventory.setItem(4, DiamondLimit);

        ItemStack FinalHeal = generateItem("§6FinalHeal", LG_UHC.getInstance().getConfig().getString("modules.FinalHeal.state"),"http://textures.minecraft.net/texture/803255c939ff49ceb3abe54488598af684e661b21d45289f63b402fdd2bc7412");
        inventory.setItem(5, FinalHeal);

        ItemStack quit = new ItemStack(Material.BARRIER);
        ItemMeta meta = quit.getItemMeta();
        meta.setDisplayName("§cQuitter le menu");
        quit.setItemMeta(meta);
        inventory.setItem(8, quit);
        player.openInventory(inventory);
    }
}
