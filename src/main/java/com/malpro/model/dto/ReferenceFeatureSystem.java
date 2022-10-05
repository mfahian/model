package com.malpro.model.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fahian on 22.05.22.
 */
public enum ReferenceFeatureSystem {
    ETIM_8_0 ("ETIM-8.0");

    private final String description;
    private static final Map<String, ReferenceFeatureSystem> BY_LABEL = new HashMap<>();

    static {
        for (ReferenceFeatureSystem e: values()) {
            BY_LABEL.put(e.description, e);
        }
    }

    ReferenceFeatureSystem(String description) {
        this.description = description;
    }

    public static ReferenceFeatureSystem valueOfLabel(String label) {
        return BY_LABEL.get(label);
    }
}
