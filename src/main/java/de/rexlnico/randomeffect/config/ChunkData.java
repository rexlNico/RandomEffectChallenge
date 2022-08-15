package de.rexlnico.randomeffect.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.potion.PotionEffectType;

@Getter
@AllArgsConstructor
public class ChunkData {

    private int x;
    private int z;
    private PotionEffectType potionEffectType;
    private int strength;

}
