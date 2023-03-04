package lguhcgroup.lg_uhc.utils;

import lguhcgroup.lg_uhc.LG_UHC;

public class GetRole {

    private final LG_UHC main;
    public GetRole(LG_UHC lg_uhc){
        main = lg_uhc;
    }
    public String getRole(String UUID){
        return main.getConfig().getString("players."+UUID+".role");
    }


}
