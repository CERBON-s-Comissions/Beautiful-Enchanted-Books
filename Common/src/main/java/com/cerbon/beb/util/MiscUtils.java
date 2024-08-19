package com.cerbon.beb.util;

import com.cerbon.beb.platform.Services;

public class MiscUtils {

    /**
     * Gets the name of the current platform
     *
     * @return The name of the current platform.
     */
    public static String getPlatformName() {
        return Services.PLATFORM.getPlatformName();
    }
}
