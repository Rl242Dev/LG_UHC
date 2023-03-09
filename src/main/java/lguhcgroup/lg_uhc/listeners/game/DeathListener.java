package lguhcgroup.lg_uhc.listeners.game;

import lguhcgroup.lg_uhc.LG_UHC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

    /*

    Boolean WinChecker(List<Player> AlivePlayers){
        for(Player player : AlivePlayers){

        }
    }


     */

    //TODO: Method pour check si il reste que des loups, Method pour check si il reste que des villageois, Method pour check si il reste que des loups, Method pour check si il reste que un solo
    private final LG_UHC main;
    public DeathListener(LG_UHC lg_uhc){
        main = lg_uhc;
    }

    public String getRole(String UUID){
        return main.getConfig().getString("players."+UUID+".role");
    }

    private final String[] LgList = {};
    private final String[] VillageList = {};
    private final String[] SoloList = {};

     // GetRole role = new GetRole(main);
    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        Player player = event.getEntity();
        main.getAlivePlayer().remove(player);

        String uuid = player.getUniqueId().toString();

        int x = player.getLocation().getBlockX();
        int y = player.getLocation().getBlockY();
        int z = player.getLocation().getBlockZ();

        main.getConfig().addDefault("players."+uuid+".death-z", z);
        main.getConfig().addDefault("players."+uuid+".death-y", y);
        main.getConfig().addDefault("players."+uuid+".death-x", x);
        main.saveConfig();


        Bukkit.getServer().broadcastMessage("§c=========X=========\n§2Le village a perdu un de ses membres :"+player.getName()+", qui était"+getRole(uuid)+"\n§c=========X=========");

        /*

        for(Player p : main.getAlivePlayer()){
            String role =  role.getRole(uuid);
            if()
        }

        */

        // role.getRole(uuid); // For alive players get role if role in LGRole list lg counter =+ 1 if role in VillageRole list village counter =+ 1 if role in soloRole list solo counter =+1. If one of the counter is > 1 and other empty then win

        /*

        if(WinChecker(main.getAlivePlayer()) == true){
            main.getServer().broadcastMessage("§3[§6LG UHC§3]§9 La partie est fini");
        }


         */
    }
}
