package lguhcgroup.lg_uhc.utils;

import lguhcgroup.lg_uhc.roles.LgPlayer;
import org.bukkit.entity.Player;

import java.util.List;

public class Utils {
    public static LgPlayer getPlayer(Player player, List<LgPlayer> lgPlayers){
        for (LgPlayer lgPlayer : lgPlayers) {
            if (lgPlayer.getPlayer().equals(player)) {
                return lgPlayer;
            }
        }
        return null; // player is not found in the list
    }
}
