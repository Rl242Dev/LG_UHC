package lguhcgroup.lg_uhc.gui;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Field;
import java.util.Base64;
import java.util.UUID;

public class config {
    public static ItemStack getSkull(String Displayname,String url) {
        ItemStack skull= new ItemStack(Material.SKULL_ITEM, 1, (short) 3);

        if (url == null || url.isEmpty())
            return skull;


        ItemMeta skullMeta = skull.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;

        try {
            profileField = skullMeta.getClass().getDeclaredField("profile");
        } catch (NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }

        profileField.setAccessible(true);

        try {
            profileField.set(skullMeta, profile);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }

        skullMeta.setDisplayName(Displayname);
        skull.setItemMeta(skullMeta);
        return skull;
    }

    public static Inventory config = Bukkit.createInventory(null, 9, "LG UHC Config");

    static {
        ItemStack headConfig = getSkull("LG UHC Events","http://textures.minecraft.net/texture/803255c939ff49ceb3abe54488598af684e661b21d45289f63b402fdd2bc7412");
        config.setItem(0, headConfig);
    }
}
