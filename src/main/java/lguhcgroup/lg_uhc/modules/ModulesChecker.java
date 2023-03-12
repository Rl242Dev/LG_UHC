package lguhcgroup.lg_uhc.modules;

import lguhcgroup.lg_uhc.LG_UHC;
import lguhcgroup.lg_uhc.modules.modules.ChancesUp.ChickenDrop;
import lguhcgroup.lg_uhc.modules.modules.ChancesUp.GravelDrop;
import lguhcgroup.lg_uhc.modules.modules.FinalHeal;
import lguhcgroup.lg_uhc.modules.modules.PveResistance;
import lguhcgroup.lg_uhc.modules.modules.Smelting;
import lguhcgroup.lg_uhc.modules.modules.Timber;
import org.bukkit.entity.Player;

public class ModulesChecker {

    private final LG_UHC main;

    public ModulesChecker(LG_UHC lg_uhc) {
        main = lg_uhc;
    }

    public void load(){
        if(main.getConfig().getString("modules.Timber.state").equalsIgnoreCase("enabled")){
            main.getServer().getPluginManager().registerEvents(new Timber(), main);
        }
        if(main.getConfig().getString("modules.Smelting.state").equalsIgnoreCase("enabled")){
            main.getServer().getPluginManager().registerEvents(new Smelting(), main);
        }
        if(main.getConfig().getString("modules.ChancesUp.state").equalsIgnoreCase("enabled")){
            main.getServer().getPluginManager().registerEvents(new ChickenDrop(), main);
            main.getServer().getPluginManager().registerEvents(new GravelDrop(), main);
        }
        if(main.getConfig().getString("modules.PveResistance.state").equalsIgnoreCase("enabled")){
            PveResistance pveResistance = new PveResistance();
            for(Player player : main.getAlivePlayer()){
                pveResistance.protect(player);
            }
        }
        if(main.getConfig().getString("modules.FinalHeal.state").equalsIgnoreCase("enabled")){
            main.getServer().getScheduler().runTaskLater(main, () -> {
                FinalHeal finalHeal = new FinalHeal();
                for(Player player : main.getAlivePlayer()){
                    finalHeal.heal(player);
                }
            }, 36000L);
        }
        //TODO: Diamond Limit
    }

}
