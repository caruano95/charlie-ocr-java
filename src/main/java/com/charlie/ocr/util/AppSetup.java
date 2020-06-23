package com.charlie.ocr.util;

import java.util.Map;

/**
 * Class that holds methods to obtain configuration parameters from the environment.
 */
public class AppSetup {
    private final Map<String, String> env;

    public AppSetup() {
        this.env = System.getenv();
    }


    public int getAppPortNumber() {
        String port = env.get("APP_PORT");
        return port != null ? Integer.parseInt(port) : 4567;
    }
}
