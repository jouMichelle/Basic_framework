package com.example.infrastructure.utils.tuling;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Utils {

    private static final int CONNECT_TIMEOUT = 2000;

    private static final int READ_TIMEOUT = 5000;

    private static final String ENCODING = "utf-8";


    /**
     * @param url
     * @param param
     * @return
     */
    public static String httpPost(String url, String param) throws Exception {
        OutputStreamWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            URL realUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);

            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(CONNECT_TIMEOUT);
            conn.setReadTimeout(READ_TIMEOUT);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");

            conn.connect();

            out = new OutputStreamWriter(conn.getOutputStream(), ENCODING);
            out.write(param);
            out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), ENCODING));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (out != null) {
                    out.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result.toString();
    }
}
