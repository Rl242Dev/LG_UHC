package lguhcgroup.lg_uhc.roles.village;

import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

public class villager {

    public String name = "Simple Villageois";
    public PotionEffectType[] nightEffects = {};
    public PotionEffectType[] dayEffects = {};
    public ItemStack[] items = {}; // Items given at 20min
    public String[] commands = {}; // Command he can use
    public String messageRole = "§3[§6LG UHC§3]§9 [Privé] Vous êtes "+name+"\nVotre objectif est de gagner avec le Village. Vous ne disposez de rien pour vous aider";

}
