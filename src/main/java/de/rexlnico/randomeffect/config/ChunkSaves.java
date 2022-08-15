package de.rexlnico.randomeffect.config;

import lombok.Getter;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Random;

@Getter
public class ChunkSaves {

    private ArrayList<ChunkData> chunkData = new ArrayList<>();

    public ChunkData getChunkData(int x, int z) {
        return chunkData.stream().filter(chunkData1 -> chunkData1.getX() == x).filter(chunkData1 -> chunkData1.getZ() == z).findFirst().orElseGet(() -> {
            PotionEffectType effect = PotionEffectType.values()[new Random().nextInt(PotionEffectType.values().length)];
            int level = new Random().nextInt(effect == PotionEffectType.HARM ? 1 : 3) + 1;
            ChunkData chunkData1 = new ChunkData(x, z, effect, level);
            chunkData.add(chunkData1);
            return chunkData1;
        });
    }

}
