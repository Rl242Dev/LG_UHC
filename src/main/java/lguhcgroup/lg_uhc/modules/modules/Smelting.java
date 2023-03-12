package lguhcgroup.lg_uhc.modules.modules;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class Smelting implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        ItemStack tool = event.getPlayer().getInventory().getItemInHand();
        Player player = event.getPlayer();

        // Check if the block broken is a gold or iron ore block and the tool used is a pickaxe
        if ((block.getType() == Material.GOLD_ORE || block.getType() == Material.IRON_ORE)
                && tool.getType().toString().contains("_PICKAXE")) {

            // Drop the smelted version of the ore
            if (block.getType() == Material.GOLD_ORE) {
                block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.GOLD_INGOT));
            } else if (block.getType() == Material.IRON_ORE) {
                block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.IRON_INGOT));
            }

            // Play sound and add experience to the player
            block.getWorld().playSound(block.getLocation(), Sound.ORB_PICKUP, 1.0f, 1.0f);
            player.giveExp(event.getExpToDrop());

            // Cancel the block break event to prevent the block from dropping its original drops
            event.setCancelled(true);
        }
    }
}
