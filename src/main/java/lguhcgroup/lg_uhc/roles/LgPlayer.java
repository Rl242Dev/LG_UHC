package lguhcgroup.lg_uhc.roles;

import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

public class LgPlayer {
    private static Roles role;
    private static String message;
    private static PotionEffectType[] nightEffetcts;
    private static PotionEffectType[] dayEffects;
    private static ItemStack[] items;
    public LgPlayer(Roles role){
        message = role.getMessage();
        nightEffetcts = role.getNightEffetcts();
        dayEffects = role.getDayEffects();
        items = role.getItems();
        this.role = role;
    }

    public Roles getRole(){
        return role;
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

    public static ItemStack[] getItems() {
        return items;
    }
}
