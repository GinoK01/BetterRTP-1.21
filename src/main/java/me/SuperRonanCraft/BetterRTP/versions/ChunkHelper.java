package me.SuperRonanCraft.BetterRTP.versions;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.function.Consumer;

public class ChunkHelper {

    /**
     * Loads a chunk without blocking the region tick thread (required on Folia).
     * The callback always runs on the region that owns the location.
     */
    public static void loadAt(Location loc, Consumer<Chunk> onLoaded) {
        World world = loc.getWorld();
        if (world == null) return;
        world.getChunkAtAsync(loc, true, chunk ->
                AsyncHandler.syncAtLocation(loc, () -> onLoaded.accept(chunk))
        );
    }
}
