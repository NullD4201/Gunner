package nulld.gunner.utils;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.Nullable;

public class Spawn {
    public static void Particle(Player player, String particle, double distance){
        Location origin = player.getEyeLocation();
        Vector direction = origin.getDirection();
        direction.multiply(distance);
        Location destination = origin.clone().add(direction);

        direction.normalize();

        for (int i = 0; i < distance; i += 0.5){
            Location location = origin.add(direction);
            location.getWorld().spawnParticle(Particle.valueOf(particle), location, 1);
        }
    }
}
