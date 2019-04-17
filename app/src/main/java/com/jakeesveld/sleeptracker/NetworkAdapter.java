package com.jakeesveld.sleeptracker;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class NetworkAdapter {



    public static String httpRequest(String urlString, Map<String, String> headerProperties){
        return httpRequest(urlString, "GET", null, headerProperties);
    }

    public static String httpRequest(String urlString, String requestMethod, JSONObject requestBody, Map<String, String> headerProperties){
        String result = "";
        HttpURLConnection connection = null;
        InputStream stream = null;
        try{
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();

            if (headerProperties != null) {
                for (Map.Entry<String, String> property : headerProperties.entrySet()) {
                    connection.setRequestProperty(property.getKey(), property.getValue());
                }
            }


            if((requestMethod.equals("POST") || requestMethod.equals("PUT")) && requestBody != null){
                connection.setDoInput(true);
                connection.setRequestMethod(requestMethod);
                OutputStream outputStream = connection.getOutputStream();

                outputStream.write(requestBody.toString().getBytes());
                outputStream.close();
            }else{
                connection.connect();
            }

            int responseCode = connection.getResponseCode();
            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK || connection.getResponseCode() == HttpURLConnection.HTTP_CREATED){
                stream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                String line = reader.readLine();
                StringBuilder builder = new StringBuilder();
                while(line != null){
                    builder.append(line);
                    line = reader.readLine();
                }

                result = builder.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(stream != null){
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                connection.disconnect();
            }
        }
        return result;
    }
}
