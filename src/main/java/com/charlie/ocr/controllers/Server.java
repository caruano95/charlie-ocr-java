package com.charlie.ocr.controllers;


import com.charlie.ocr.controllers.controllers.WebappController;
import com.charlie.ocr.util.AppSetup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.*;

import static spark.Spark.*;

/**
 * Main application class. The environment is set up here, and all necessary services are run.
 */
public class Server {

    private static final Logger logger = LoggerFactory.getLogger(Server.class);

    public static void main(String[] args) {
        AppSetup appSetup = new AppSetup();
        port(appSetup.getAppPortNumber());

        /**
         * Sets the port in which the application will run. Takes the port value from PORT
         * environment variable, if not set, uses default port 4567.
         */
        port(appSetup.getAppPortNumber());

        /**
         * Specifies the directory within resources that will be publicly available when the
         * application is running. Place static web files in this directory (JS, CSS).
         */
        Spark.staticFileLocation("/public");

        /**
        /**
         * Frontend app
         */
        WebappController webappController = new WebappController();

        get("/about", webappController.about);
    }

}
