package com.alphagnfss.etr3.cli;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

record MediaType(String name, List<String> statuses, List<String> other) {
    @Override
    public String toString() {
        return name;
    }
}

public final class TypeRegistry {
    private static final Map<String, MediaType> TYPES = new LinkedHashMap<>();

    static {
        // I guessed which statuses go with which type. Adjust to your real rules.
        register(new MediaType("movie", List.of("upcoming", "released", "completed"), List.of()));
        register(new MediaType("show", List.of("upcoming", "released", "ongoing", "completed"), List.of()));
        register(new MediaType("episode", List.of("upcoming", "released", "completed"), List.of("--show")));
    }

    private TypeRegistry() {
    }

    public static void register(MediaType type) {
        TYPES.put(type.name(), type);
    }

    public static MediaType find(String name) {
        return TYPES.get(name);
    }

    public static Collection<String> names() {
        return TYPES.keySet();
    }

    public static List<String> allStatuses() {
        return TYPES.values().stream().flatMap(t -> t.statuses().stream()).distinct().toList();
    }
}