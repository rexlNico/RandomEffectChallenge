package de.rexlnico.randomeffect.commands;

import de.rexlnico.randomeffect.RandomEffect;
import de.rexlnico.randomeffect.listener.PlayerDamageListener;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class RestartCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (player.hasPermission("randomeffect.restart")) {
                if (args.length == 0) {
                    World world = Bukkit.createWorld(new WorldCreator(UUID.randomUUID().toString()));
                    RandomEffect.getInstance().getChunkSaves().getChunkData().clear();
                    PlayerDamageListener.getCounter().set(0);
                    Bukkit.getOnlinePlayers().forEach(all -> {
                        all.getActivePotionEffects().stream().toList().forEach(potionEffect -> all.removePotionEffect(potionEffect.getType()));
                        all.teleport(world.getSpawnLocation());
                    });
                }
            }
        }
        return false;
    }
}
