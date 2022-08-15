package de.rexlnico.randomeffect.commands;

import de.rexlnico.randomeffect.RandomEffect;
import de.rexlnico.randomeffect.listener.PlayerDamageListener;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class WorldTeleportCommand implements TabExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (player.hasPermission("randomeffect.world.teleport")) {
                if (args.length == 1) {
                    World world = Bukkit.getWorld(args[0]);
                    if(world != null) {
                        player.teleport(world.getSpawnLocation());
                    } else {
                        player.sendMessage("§cDie Welt §e"+args[0]+" §cexistiert nicht!");
                    }
                }
            }
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Bukkit.getWorlds().stream().map(World::getName).filter(name -> name.startsWith(args[0])).forEach(sender::sendMessage);
        return null;
    }
}
