package de.zockersk.hastebinclient;

import de.zockersk.hastebinclient.options.OptionsManager;
import de.zockersk.hastebinclient.util.Connector;
import de.zockersk.hastebinclient.util.DesktopApi;
import de.zockersk.hastebinclient.util.Reader;
import org.apache.commons.cli.*;

import java.io.File;
import java.io.IOException;
import java.net.URI;

/**
 * Created by steven on 21.05.16.
 */
public class HastebinClient {

    private static boolean fileEnabled = false;
    private static String uploadString = null;

    public static void main(String[] args) {
        OptionsManager manager = new OptionsManager();
        CommandLineParser parser = new DefaultParser();
        CommandLine line = null;
        String server = "http://hastebin.com";
        try {
            line = parser.parse(manager.getOptions(), args);
        } catch (ParseException e) {
            System.err.println("An error occurred while parsing arguments: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        if (line.hasOption('h') || line.hasOption("help")) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("hastebin-client", manager.getOptions());
            System.exit(1);
        }
        if (line.hasOption('s') || line.hasOption("server")) {
            server = (line.getOptionValue('s') != null ? line.getOptionValue('s') : line.getOptionValue("server"));
            if (!server.startsWith("http://") || !server.startsWith("https://")) server = "http://" + server;
            if (server.endsWith("/")) server = server.substring(0, server.length() - 1);
        }
        boolean open = false;
        if (line.hasOption('o') || line.hasOption("open")) open = true;
        if (line.hasOption('f') || line.hasOption("file")) fileEnabled = true;
        if (fileEnabled) {
            File file = new File((line.hasOption('f') ? line.getOptionValue('f') : line.getOptionValue("file")));
            if (!file.exists()) {
                System.err.println("The file " + file.getName() + " doesn't exists.");
                System.exit(1);
            }
            try {
                uploadString = Reader.getContentOfFile(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (uploadString == null) {
            uploadString = Reader.getContentToPush();
        }
        System.out.println("Posting, please wait...");
        final String id = Connector.postToHasteServer(server, uploadString);
        if (id == null) {
            System.err.println("An error occurred while posting to server.");
            System.exit(1);
        } else {
            System.out.println("URL to Post: " + server + "/" + id);
            if (open) {
                System.out.println("Opening browser...");
                final String finalServer = server;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        DesktopApi.openURL(URI.create(finalServer + "/" + id));
                    }
                }).start();
            }
        }
    }

}
