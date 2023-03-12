package lguhcgroup.lg_uhc.roles;

import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

public class Roles {

    private static String message;
    private static PotionEffectType[] nightEffetcts;
    private static PotionEffectType[] dayEffects;
    private static ItemStack[] items;
    private static String aura;

    public Roles Villageois(){
        message = "§3[§6LG UHC§3]§9 [Privé] Vous êtes Simple Villageois \nVotre objectif est de gagner avec le Village. Vous ne disposez de rien pour vous aider";
        nightEffetcts = new PotionEffectType[]{PotionEffectType.NIGHT_VISION};
        dayEffects = new PotionEffectType[]{};
        items = new ItemStack[]{};
        aura = "Positive";
        return null;
    }

    public Roles Loup_Garou(){
        String message = "WORLD";
        return null;
    }

    void apply(LgPlayer player){

    }

    public String getMessage(){
        return message;
    }

    public PotionEffectType[] getDayEffects(){
        return dayEffects;
    }

    public PotionEffectType[] getNightEffetcts(){
        return nightEffetcts;
    }

    public ItemStack[] getItems() {
        return items;
    }

    public String getAura(){
        return aura;
    }
}
