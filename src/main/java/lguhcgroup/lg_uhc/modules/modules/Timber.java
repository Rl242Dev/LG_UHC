package lguhcgroup.lg_uhc.modules.modules;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.ArrayList;
import java.util.List;

public class Timber implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        Player player = event.getPlayer();

        // Check if the block broken is a log block
        if (block.getType() == Material.LOG || block.getType() == Material.LOG_2) {
            // Create a list to store all the logs in the tree
            List<Block> treeBlocks = new ArrayList<>();
            treeBlocks.add(block);

            // Iterate through all the blocks in the tree and add the logs to the list
            for (int i = 0; i < treeBlocks.size(); i++) {
                Block currentBlock = treeBlocks.get(i);
                for (BlockFace face : BlockFace.values()) {
                    Block neighbor = currentBlock.getRelative(face);
                    if (neighbor.getType() == currentBlock.getType() && !treeBlocks.contains(neighbor)) {
                        treeBlocks.add(neighbor);
                    }
                }
            }

            // Cut every log in the tree
            for (Block treeBlock : treeBlocks) {
                if (treeBlock.getType() == Material.LOG || treeBlock.getType() == Material.LOG_2) {
                    treeBlock.breakNaturally(player.getInventory().getItemInHand());
                }
            }
        }
    }
}
