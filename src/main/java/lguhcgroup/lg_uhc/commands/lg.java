package lguhcgroup.lg_uhc.commands;

import lguhcgroup.lg_uhc.LG_UHC;
import lguhcgroup.lg_uhc.roles.LgPlayer;
import lguhcgroup.lg_uhc.roles.Roles;
import lguhcgroup.lg_uhc.utils.Msg;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static lguhcgroup.lg_uhc.gui.config.config;

public class lg implements CommandExecutor {
    private final LG_UHC main;
    public lg(LG_UHC lg_uhc){
        main = lg_uhc;
    }

    int RandomBound(int min, int max){
        Random random = new Random();
        int nb;
        nb = min+random.nextInt(max-min);
        return nb;
    }

    public Roles getRoleClass(String roleName) throws ClassNotFoundException {
        Class<?> roleClass = Class.forName("lguhcgroup.lg_uhc.roles.Roles");
        try {
            Method method = roleClass.getMethod(roleName);
            return (Roles) method.invoke(roleClass.newInstance());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Roles> getRolesInGame() throws NoSuchMethodException, ClassNotFoundException {
        List<Roles> rolesList = new ArrayList<>();

        FileConfiguration config = main.getConfig();
        ConfigurationSection rolesSection = config.getConfigurationSection("roles");

        for (String roleName : rolesSection.getKeys(false)) {
            ConfigurationSection roleSection = rolesSection.getConfigurationSection(roleName);
            int total = roleSection.getInt("total");

            System.out.println(roleName);
            if (total >= 1) {
                for (int i = 0; i < total; i++) {
                    rolesList.add(getRoleClass(roleName));
                }
            }
        }

        return rolesList;
    }

    void RegisterPlayer(String UUID, String Role, Boolean Couple, String CoupleMate){
        main.getConfig().addDefault("players."+UUID, UUID);
        main.getConfig().addDefault("players."+UUID+".role", Role);
        main.getConfig().addDefault("players."+UUID+".couple", Couple);
        main.getConfig().addDefault("players."+UUID+".coupleMate", CoupleMate);
        main.saveConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            System.out.println("[LG UHC] Seulement les joueurs peuvent utiliser cette commande");
            return true;
        }

        Player player = (Player) sender;

        if (label.equalsIgnoreCase("lg")){
            switch(args[0]) {
                case "":
                    Msg.send(player, "§3[§6LG UHC§3]§9 Commande non reconnue");
                    return true;
                case "help":
                    Msg.send(player, "§3[§6LG UHC§3]§9"); // TODO: Message aide
                case "start": //TODO: Refaire
                    try {
                        List<Roles> RolesList = getRolesInGame();
                        main.setTime(0);
                        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                            main.getAlivePlayer().add(p);
                            int stack = main.getConfig().getInt("Teleport.food-stack");
                            boolean food = main.getConfig().getBoolean("Teleport.food");
                            if (food) {
                                p.getInventory().setItem(0, new ItemStack(Material.COOKED_BEEF, stack));
                            }

                            int Xmax = main.getConfig().getInt("Teleport.x");
                            int Xmin = 300;
                            int Zmax = main.getConfig().getInt("Teleport.z");
                            int Zmin = 300;

                            int X = RandomBound(Xmin, Xmax);
                            int Z = RandomBound(Zmin, Zmax);

                            String world = main.getConfig().getString("Teleport.world");

                            p.teleport(new Location(Bukkit.getWorld(world), X, 120, Z, 0, 0));

                            main.getServer().broadcastMessage("§3[§6LG UHC§3]§9 Rôles dans 20 minutes");

                            for(Player player1 : main.getAlivePlayer()){ // Make a method to return an array of players and redo this part
                                int iterator = RandomBound(RandomBound(0, RolesList.size()), RandomBound(0, main.getAlivePlayer().size()));
                                Player player3 = main.getAlivePlayer().get(iterator);
                                Roles roles = RolesList.get(iterator);
                                LgPlayer player2 = new LgPlayer(roles);
                                Msg.send(player3, player2.getRole().getMessage());
                            }
                            //Give roles to players
                        }
                    } catch (NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }

                    return true;

                case "say":
                    StringBuilder message = new StringBuilder();
                    for (int i = 1; i < args.length; i++) {
                        message.append(" ").append(args[i]);
                    }
                    Bukkit.broadcastMessage("§6[INFO] §a" + message);
                    return true;

                case "revive":
                    Player p;
                    try {
                        p = Bukkit.getPlayer(args[1]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        Msg.send(player, "Commande : /lg revive [Player] ");
                        return true;
                    }

                    main.getAlivePlayer().add(p);

                    String UUID = p.getUniqueId().toString();

                    int z = main.getConfig().getInt("players." + UUID + ".death-z");
                    int x = main.getConfig().getInt("players." + UUID + ".death-x");
                    int y = main.getConfig().getInt("players." + UUID + ".death-y");

                    String world = main.getConfig().getString("Teleport.world");

                    p.teleport(new Location(Bukkit.getWorld(world), x, y, z, 0, 0));
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