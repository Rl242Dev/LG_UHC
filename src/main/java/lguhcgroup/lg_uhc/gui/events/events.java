package lguhcgroup.lg_uhc.gui.events;

import lguhcgroup.lg_uhc.LG_UHC;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

import static lguhcgroup.lg_uhc.gui.config.getSkull;

public class events {
    public static ItemStack generateItem(String displayName, String chances, String url){
        ItemStack head = getSkull(displayName, url);
        ItemMeta meta = head.getItemMeta();

        ArrayList<String> lore = new ArrayList<String>();
        lore.add("§eChances: "+chances);
        meta.setLore(lore);

        head.setItemMeta(meta);

        return head;
    }

    public static Inventory events = Bukkit.createInventory(null, 9, "LG UHC Events");

    static {
        LG_UHC.getInstance().reloadConfig();
        ItemStack Exposed = generateItem("§6Exposed", LG_UHC.getInstance().getConfig().getString("events.Exposed.chances"), "http://textures.minecraft.net/texture/803255c939ff49ceb3abe54488598af684e661b21d45289f63b402fdd2bc7412");
        events.setItem(0, Exposed);

        ItemStack LGAlone = generateItem("§6LGAlone", LG_UHC.getInstance().getConfig().getString("events.LGAlone.chances"), "http://textures.minecraft.net/texture/803255c939ff49ceb3abe54488598af684e661b21d45289f63b402fdd2bc7412");
        events.setItem(1, LGAlone);

        ItemStack Trouple = generateItem("§6Trouple", LG_UHC.getInstance().getConfig().getString("events.Trouple.chances"), "http://textures.minecraft.net/texture/803255c939ff49ceb3abe54488598af684e661b21d45289f63b402fdd2bc7412");
        events.setItem(2, Trouple);

        ItemStack Rumeurs = generateItem("§6Rumeurs", LG_UHC.getInstance().getConfig().getString("events.Rumeurs.chances"), "http://textures.minecraft.net/texture/803255c939ff49ceb3abe54488598af684e661b21d45289f63b402fdd2bc7412");
        events.setItem(3, Rumeurs);

        ItemStack quit = new ItemStack(Material.BARRIER);
        ItemMeta meta = quit.getItemMeta();
        meta.setDisplayName("§cQuitter le menu");
        quit.setItemMeta(meta);
        events.setItem(8, quit);
    }

    public static void BuildInventoryEvents(HumanEntity player, InventoryView inventory){
        LG_UHC.getInstance().reloadConfig();

        ItemStack Exposed = generateItem("§6Exposed", LG_UHC.getInstance().getConfig().getString("events.Exposed.chances"), "http://textures.minecraft.net/texture/803255c939ff49ceb3abe54488598af684e661b21d45289f63b402fdd2bc7412");
        inventory.setItem(0, Exposed);

        ItemStack LGAlone = generateItem("§6LGAlone", LG_UHC.getInstance().getConfig().getString("events.LGAlone.chances"), "http://textures.minecraft.net/texture/803255c939ff49ceb3abe54488598af684e661b21d45289f63b402fdd2bc7412");
        inventory.setItem(1, LGAlone);

        ItemStack Trouple = generateItem("§6Trouple", LG_UHC.getInstance().getConfig().getString("events.Trouple.chances"), "http://textures.minecraft.net/texture/803255c939ff49ceb3abe54488598af684e661b21d45289f63b402fdd2bc7412");
        inventory.setItem(2, Trouple);

        ItemStack Rumeurs = generateItem("§6Rumeurs", LG_UHC.getInstance().getConfig().getString("events.Rumeurs.chances"), "http://textures.minecraft.net/texture/803255c939ff49ceb3abe54488598af684e661b21d45289f63b402fdd2bc7412");
        inventory.setItem(3, Rumeurs);

        ItemStack quit = new ItemStack(Material.BARRIER);
        ItemMeta meta = quit.getItemMeta();
        meta.setDisplayName("§cQuitter le menu");
        quit.setItemMeta(meta);
        inventory.setItem(8, quit);
        player.openInventory(inventory);
    }

}
