package de.rexlnico.randomeffect.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.experimental.Accessors;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@Accessors(fluent = true)
public class JsonConfiguration {

    private static Gson GSON = new GsonBuilder().serializeNulls()
            .registerTypeAdapter(ChunkData.class, new ChunkDataAdapter())
            .setPrettyPrinting().disableHtmlEscaping().create();

    public static <T> T loadOrCreateConfiguration(Path path, T configuration) {
        createConfiguration(path, configuration);
        return (T) loadConfiguration(path, configuration.getClass());
    }

    public static <T> T loadConfiguration(Path path, Class<T> defaultConfiguration) {
        try {
            return GSON.fromJson(Files.readString(path), defaultConfiguration);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> void createConfiguration(Path path, T configuration) {
        try {
            Files.writeString(path, GSON.toJson(configuration), StandardOpenOption.CREATE_NEW);
        } catch (IOException ignored) {
        }
    }

    public static <T> void saveConfiguration(Path path, T configuration) {
        try {
            Files.writeString(path, GSON.toJson(configuration), StandardOpenOption.WRITE);
        } catch (IOException ignored) {
        }
    }

}
