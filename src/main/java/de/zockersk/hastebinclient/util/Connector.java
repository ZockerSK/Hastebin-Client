package de.zockersk.hastebinclient.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Created by steven on 21.05.16.
 */
public class Connector {

    public static String postToHasteServer(String server, String data) {
        try {
            URL url = new URL(server + "/documents");
            byte[] postData = data.getBytes(StandardCharsets.UTF_8);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length", postData.length + "");
            connection.setDoOutput(true);
            connection.getOutputStream().write(postData);
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder builder = new StringBuilder();
            for (int c; (c = reader.read()) >= 0; ) {
                builder.append((char) c);
            }
            JsonObject object = new Gson().fromJson(builder.toString(), JsonObject.class);
            return object.get("key").getAsString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
