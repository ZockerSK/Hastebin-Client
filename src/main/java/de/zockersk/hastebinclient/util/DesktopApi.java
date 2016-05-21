package de.zockersk.hastebinclient.util;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

public class DesktopApi {

    public static void openURL(URI uri) {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(uri);
            } catch (IOException e) {
                System.err.println("An error occurred while opening an url: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            Runtime runtime = Runtime.getRuntime();
            try {
                System.out.println(uri.toString());
                runtime.exec("xdg-open " + uri.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
  