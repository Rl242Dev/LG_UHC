package lguhcgroup.lg_uhc.scoreboard;

import fr.mrmicky.fastboard.FastBoard;
import lguhcgroup.lg_uhc.LG_UHC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class scoreboard implements Listener {

    // Kill Listener and scheduler
    private final Map<UUID, FastBoard> boards = new HashMap<>();

    public String CycleCalculator(long Time){
        String day = "";
        if(Time < 12300){
            day = "Jour";
        } else if (Time > 12300) {
            day = "Nuit";
        }
        return day;
    }

    public int GroupCalculator(int Players) {
        int groupes = 0;
        if (Players > 15) {
            groupes = 5;
        } else if (Players >= 10) {
            if (Players <= 14) {
                groupes =  4;
            }
        } else if (Players < 10) {
            groupes = 3;
        }
        return groupes;
    }

    public scoreboard(LG_UHC plugin){
        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            for (FastBoard board : boards.values()){
                String world = plugin.getConfig().getString("Teleport.world");

                updateBoard(board,
                        "",
                        "§bEpisode: "+(plugin.getTime()/1200+1),
                        "§c"+plugin.getAlivePlayer().size()+"§4 Joueurs",
                        "§4Groupes de §c"+GroupCalculator(plugin.getAlivePlayer().size()),
                        "",
                        "§6Timer: §e"+plugin.getTime()/3600+"h:"+(plugin.getTime()%3600)/60+"m:"+plugin.getTime()%60+"s",
                        "§6Cycle: §e"+CycleCalculator(Bukkit.getServer().getWorld(world).getTime()),
                        ""
                        );
            }
        }, 0L, 10L);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        FastBoard board = new FastBoard(player);

        String Title = "§7  LG UHC";
        board.updateTitle(Title);

        boards.put(player.getUniqueId(), board);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        FastBoard board = boards.remove(player.getUniqueId());

        if (board != null){
            board.delete();
        }
    }

    private void updateBoard(FastBoard board, String ... lines){
        for(int a =0; a < lines.length; ++a){
            lines[a] = ChatColor.translateAlternateColorCodes('&', lines[a]);
        }

        board.updateLines(lines);
    }
}
