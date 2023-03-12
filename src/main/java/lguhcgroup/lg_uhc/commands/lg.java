package lguhcgroup.lg_uhc.commands;

import lguhcgroup.lg_uhc.LG_UHC;
import lguhcgroup.lg_uhc.modules.ModulesChecker;
import lguhcgroup.lg_uhc.roles.LgPlayer;
import lguhcgroup.lg_uhc.roles.Roles;
import lguhcgroup.lg_uhc.utils.Msg;
import lguhcgroup.lg_uhc.utils.Utils;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static lguhcgroup.lg_uhc.gui.config.config;

public class lg implements CommandExecutor {

    private final LG_UHC main;

    public lg(LG_UHC lg_uhc){
        main = lg_uhc;
    }

    int RandomBound(int min, int max) {
        Random random = new Random();
        int nb;
        nb = min + random.nextInt(max - min);
        return nb;
    }



    static Roles getClassRole(String roleName){ //TODO: Try to remake it using maps
        Roles roles = new Roles();
        Roles role = new Roles();
        switch(roleName){
            case "Villageois":
                role = roles.Villageois();
            case "Loup_Garou":
                role = roles.Loup_Garou();
        }
        return role;
    }

    static List<Roles> getRolesInGame(LG_UHC plugin){
        List<Roles> rolesList = new ArrayList<>();

        FileConfiguration config = plugin.getConfig();
        ConfigurationSection rolesSection = config.getConfigurationSection("roles");

        for (String roleName : rolesSection.getKeys(false)) {
            ConfigurationSection roleSection = rolesSection.getConfigurationSection(roleName);
            int total = roleSection.getInt("total");

            if (total >= 1) {
                for (int i = 0; i < total; i++) {
                    rolesList.add(getClassRole(roleName));
                }
            }
        }

        return rolesList;
    }

    public static List<LgPlayer> generateLgPlayers(List<Roles> rolesList) {
        List<LgPlayer> lgPlayers = new ArrayList<>();

        for (Player ignored : LG_UHC.getInstance().getAlivePlayer()) {

            int roleIndex = (int) (Math.random() * rolesList.size());
            Roles role = rolesList.get(roleIndex);

            rolesList.remove(roleIndex);

            LgPlayer lgPlayer = new LgPlayer(ignored, role);

            lgPlayers.add(lgPlayer);
        }

        return lgPlayers;
    }


    void RegisterPlayer(String UUID, String Role, Boolean Couple, String CoupleMate) {
        main.getConfig().addDefault("players." + UUID, UUID);
        main.getConfig().addDefault("players." + UUID + ".role", Role);
        main.getConfig().addDefault("players." + UUID + ".couple", Couple);
        main.getConfig().addDefault("players." + UUID + ".coupleMate", CoupleMate);
        main.saveConfig();
    }

    public List<LgPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(List<LgPlayer> players){
        this.players = players;
    }

    private List<LgPlayer> players = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            System.out.println("[LG UHC] Seulement les joueurs peuvent utiliser cette commande");
            return true;
        }

        Player player = (Player) sender;

        if (label.equalsIgnoreCase("lg")) {
            switch (args[0]) {
                case "help":
                    Msg.send(player, "§3[§6LG UHC§3]§9"); // TODO: Message aide
                case "start":
                    setPlayers(generateLgPlayers(getRolesInGame(main)));
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

                        /* Load Modules for game */
                        ModulesChecker checkerModules = new ModulesChecker(main);
                        checkerModules.load();

                        for(Player p1 : main.getAlivePlayer()){
                            LgPlayer lgPlayer = Utils.getPlayer(p1, this.getPlayers());
                            Msg.send(p1, lgPlayer.getRole().getMessage());
                        }
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