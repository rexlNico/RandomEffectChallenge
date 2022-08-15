package de.rexlnico.randomeffect.listener;

import de.rexlnico.randomeffect.RandomEffect;
import de.rexlnico.randomeffect.config.ChunkData;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ChunkChangeListener implements Listener {

    @EventHandler
    public void on(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (event.getFrom().getX() != event.getTo().getX() || event.getFrom().getZ() != event.getTo().getZ()) {
            Chunk chunk = event.getTo().getChunk();
            Chunk chunkOld = event.getFrom().getChunk();
            if (!chunkOld.equals(chunk)) {

                ChunkData chunkDataOld = RandomEffect.getInstance().getChunkSaves().getChunkData(chunkOld.getX(), chunkOld.getZ());
                ChunkData chunkData = RandomEffect.getInstance().getChunkSaves().getChunkData(chunk.getX(), chunk.getZ());

                player.removePotionEffect(chunkDataOld.getPotionEffectType());
                int time = chunkData.getPotionEffectType() == PotionEffectType.HARM ? 5 : Integer.MAX_VALUE / 2;
                PotionEffect effect = new PotionEffect(chunkData.getPotionEffectType(), time, chunkData.getStrength());
                player.addPotionEffect(effect);
            }
        }
    }

    @EventHandler
    public void on(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Chunk chunk = player.getChunk();
        ChunkData chunkData = RandomEffect.getInstance().getChunkSaves().getChunkData(chunk.getX(), chunk.getZ());
        player.getActivePotionEffects().stream().filter(potionEffect -> potionEffect.getDuration() >= Integer.MAX_VALUE / 1000).toList()
                .forEach(potionEffect -> player.removePotionEffect(potionEffect.getType()));
        int time = chunkData.getPotionEffectType() == PotionEffectType.HARM ? 5 : Integer.MAX_VALUE / 2;
        PotionEffect effect = new PotionEffect(chunkData.getPotionEffectType(), time, chunkData.getStrength());
        player.addPotionEffect(effect);
    }


    @EventHandler
    public void on(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        Chunk chunk = player.getChunk();
        ChunkData chunkData = RandomEffect.getInstance().getChunkSaves().getChunkData(chunk.getX(), chunk.getZ());
        player.getActivePotionEffects().stream().filter(potionEffect -> potionEffect.getDuration() >= Integer.MAX_VALUE / 1000).toList()
                .forEach(potionEffect -> player.removePotionEffect(potionEffect.getType()));
        int time = chunkData.getPotionEffectType() == PotionEffectType.HARM ? 5 : Integer.MAX_VALUE / 2;
        PotionEffect effect = new PotionEffect(chunkData.getPotionEffectType(), time, chunkData.getStrength());
        player.addPotionEffect(effect);
    }

}
