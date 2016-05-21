package de.zockersk.hastebinclient.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by steven on 21.05.16.
 */
public class Reader {

    public static String getContentOfFile(File file) throws IOException {
        FileReader reader = new FileReader(file);
        StringBuilder builder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(reader);
        System.out.println("File is reading....");
        while (true) {
            String line = bufferedReader.readLine();
            if (line != null) {
                builder.append(line);
                builder.append(System.lineSeparator());
            } else break;
        }
        return builder.toString();
    }

    public static String getContentToPush() {
        Scanner scanner = new Scanner(System.in);
        StringBuilder builder = new StringBuilder();
        System.out.println("If you wish to stop editing, enter \"!exit\"");
        System.out.println("Please enter content to push:");
        while (true) {
            System.out.print("> ");
            String line = scanner.nextLine();
            if (line.trim().equalsIgnoreCase("!exit")) break;
            else {
                builder.append(line);
                builder.append(System.lineSeparator());
            }
        }
        return builder.toString();
    }

}
