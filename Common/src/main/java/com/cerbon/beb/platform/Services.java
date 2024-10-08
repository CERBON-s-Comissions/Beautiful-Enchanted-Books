package com.cerbon.beb.platform;

import com.cerbon.beb.util.BEBConstants;

import java.util.ServiceLoader;

public class Services {

    public static <T> T load(Class<T> clazz) {
        final T loadedService = ServiceLoader.load(clazz)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
        BEBConstants.LOGGER.debug("Loaded {} for service {}", loadedService, clazz);
        return loadedService;
    }
}
