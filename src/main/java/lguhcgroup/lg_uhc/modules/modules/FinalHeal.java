package lguhcgroup.lg_uhc.modules.modules;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FinalHeal {
    public static void heal(Player player){
        PotionEffect reg = new PotionEffect(PotionEffectType.REGENERATION, 200, 2);

        player.addPotionEffect(reg);
    }
}
