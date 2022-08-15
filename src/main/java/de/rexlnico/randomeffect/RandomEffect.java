package de.rexlnico.randomeffect;

import de.rexlnico.randomeffect.commands.RestartCommand;
import de.rexlnico.randomeffect.config.ChunkSaves;
import de.rexlnico.randomeffect.config.JsonConfiguration;
import de.rexlnico.randomeffect.listener.ChunkChangeListener;
import de.rexlnico.randomeffect.listener.PlayerDamageListener;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.nio.file.Path;

@Getter
public final class RandomEffect extends JavaPlugin {

    @Getter
    private static RandomEffect instance;
    private ChunkSaves chunkSaves;

    @Override
    public void onEnable() {
        instance = this;
        getDataFolder().mkdir();
        Path resolve = getDataFolder().toPath().resolve("chunkSaves.json");
        chunkSaves = JsonConfiguration.loadOrCreateConfiguration(resolve, new ChunkSaves());
        PluginManager pluginManager = Bukkit.getPluginManager();

        getCommand("retry").setExecutor(new RestartCommand());

        pluginManager.registerEvents(new ChunkChangeListener(), this);
        pluginManager.registerEvents(new PlayerDamageListener(), this);
    }

    @Override
    public void onDisable() {
        Path resolve = getDataFolder().toPath().resolve("chunkSaves.json");
        JsonConfiguration.saveConfiguration(resolve, chunkSaves);
    }
}
