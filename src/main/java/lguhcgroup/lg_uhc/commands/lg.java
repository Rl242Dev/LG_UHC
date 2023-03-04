package lguhcgroup.lg_uhc.commands;

import lguhcgroup.lg_uhc.LG_UHC;
import lguhcgroup.lg_uhc.utils.Msg;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Random;

import static lguhcgroup.lg_uhc.gui.config.config;

import lguhcgroup.lg_uhc.roles.village.villager;

public class lg implements CommandExecutor {
    private final LG_UHC main;
    public lg(LG_UHC lg_uhc){
        main = lg_uhc;
    }

    int RandomTeleport(int min, int max){
        Random random = new Random();
        int nb;
        nb = min+random.nextInt(max-min);
        return nb;
    }

    void RegisterPlayer(String UUID, String Role, Boolean Couple, String CoupleMate){
        main.getConfig().addDefault("players."+UUID, UUID);
        main.getConfig().addDefault("players."+UUID+".role", Role);
        main.getConfig().addDefault("players."+UUID+".couple", Couple);
        main.getConfig().addDefault("players."+UUID+".coupleMate", CoupleMate);
        main.saveConfig();
    }

    Runnable GenerateLove(){
        System.out.println("Prendre deux personnes et trql tu vois");

        return null;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            System.out.println("[MoneyIsTime] Seulement les joueurs peuvent utiliser cette commande");
            return true;
        }

        Player player = (Player) sender;

        if (label.equalsIgnoreCase("lg")){
            switch(args[0]){
                case "start":
                    main.setTime(0);
                    for(Player p : Bukkit.getServer().getOnlinePlayers()){
                        main.getAlivePlayer().add(p);
                        int stack = main.getConfig().getInt("Teleport.food-stack");
                        boolean food = main.getConfig().getBoolean("Teleport.food");
                        if(food){
                            p.getInventory().setItem(0, new ItemStack(Material.COOKED_BEEF, stack));
                        }

                        int Xmax = main.getConfig().getInt("Teleport.x");
                        int Xmin = 300;
                        int Zmax = main.getConfig().getInt("Teleport.z");
                        int Zmin = 300;

                        int X = RandomTeleport(Xmin, Xmax);
                        int Z = RandomTeleport(Zmin, Zmax);

                        String world = main.getConfig().getString("Teleport.world");

                        p.teleport(new Location(Bukkit.getWorld(world), X,120,Z,0,0));

                        main.getServer().broadcastMessage("§3[§6LG UHC§3]§9 Rôles dans 20 minutes");
                        main.getServer().getScheduler().runTaskLater(main, () -> {
                            List<Player> players = main.getAlivePlayer();

                            for(Player players1 : players){ //TODO: A refaire pour l'instnat je test juste les messages et tout
                                villager Villager = new villager(); // Minus in total in config

                                Msg.send(players1, Villager.messageRole);

                                String UUID = players1.getUniqueId().toString(); // Give to player role in config and send him message
                                RegisterPlayer(UUID, "Villager", false, "None");
                            }
                        }, 200L);

                        if(main.getConfig().getBoolean("events.RandomLove") == true){
                            main.getServer().getScheduler().runTaskLater(main, GenerateLove(), 30000L);
                        }
                    }
                    return true;

                case "say":
                    StringBuilder message = new StringBuilder();
                    for(int i = 1; i < args.length; i++){
                        message.append(" ").append(args[i]);
                    }
                    Bukkit.broadcastMessage("§6[INFO] §a"+message);
                    return true;

                case "revive":
                    Player p = Bukkit.getPlayer(args[1]);
                    main.getAlivePlayer().add(p);

                    String UUID = p.getUniqueId().toString();

                    int z = main.getConfig().getInt("players."+UUID+".death-z");
                    int x = main.getConfig().getInt("players."+UUID+".death-x");
                    int y = main.getConfig().getInt("players."+UUID+".death-y");

                    String world = main.getConfig().getString("Teleport.world");

                    p.teleport(new Location(Bukkit.getWorld(world), x,y,z,0,0));
                    return true;

                case "config":
                    player.openInventory(config);
                    return true;
            }
            return true;
        }
        return true;
    }
}