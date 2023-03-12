package lguhcgroup.lg_uhc.roles;

import org.bukkit.entity.Player;

public class LgPlayer {

    private static Roles role;
    private static Player player;

    public LgPlayer(Player player,Roles role){
        this.role = role;
        this.player = player;
    }

    public Roles getRole(){
        return role;
    }

    public Player getPlayer(){
        return player;
    }
}
