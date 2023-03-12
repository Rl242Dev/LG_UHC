package lguhcgroup.lg_uhc.modules.modules;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PveResistance {
    public static void protect(Player player){
        int duration = 30 * 60 * 20;

        PotionEffect resistance = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, duration, 0);

        player.addPotionEffect(resistance);
    }
}
