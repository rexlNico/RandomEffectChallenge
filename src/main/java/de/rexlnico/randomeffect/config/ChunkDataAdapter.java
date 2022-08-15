package de.rexlnico.randomeffect.config;

import com.google.gson.*;
import org.bukkit.potion.PotionEffectType;

import java.lang.reflect.Type;

public class ChunkDataAdapter implements JsonDeserializer<ChunkData>, JsonSerializer<ChunkData> {

    @Override
    public ChunkData deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        return new ChunkData(jsonObject.get("x").getAsInt(), jsonObject.get("z").getAsInt(),
                PotionEffectType.getByName(jsonObject.get("potionEffectType").getAsString()), jsonObject.get("strength").getAsInt());
    }

    @Override
    public JsonElement serialize(ChunkData src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("x", src.getX());
        jsonObject.addProperty("z", src.getZ());
        jsonObject.addProperty("potionEffectType", src.getPotionEffectType().getName());
        jsonObject.addProperty("strength", src.getStrength());
        return jsonObject;
    }
}
