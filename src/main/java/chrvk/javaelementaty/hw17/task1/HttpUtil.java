package chrvk.javaelementaty.hw17.task1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {

    public static Response sendRequest(String url) {
        Response response = new Response();

        HttpURLConnection urlConnection = null;
        try {
            URL requestUrl = new URL(url);
            urlConnection = (HttpURLConnection) requestUrl.openConnection();
            urlConnection.setReadTimeout(20000);
            urlConnection.setConnectTimeout(20000);
            urlConnection.setRequestMethod("GET"); // optional, GET already by default
            int status = urlConnection.getResponseCode();
            response.responseCode = status;
            if (status == HttpURLConnection.HTTP_OK) {
                response.body = getStringFromStream(urlConnection.getInputStream());
            }
        } catch (Exception e) {
            response.exception = e;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return response;
    }

    private static String getStringFromStream(InputStream inputStream) {
        final int BUFFER_SIZE = 4096;
        ByteArrayOutputStream resultStream = new ByteArrayOutputStream(BUFFER_SIZE);
        byte[] buffer = new byte[BUFFER_SIZE];
        int length;

        try {
            while ((length = inputStream.read(buffer)) != -1) {
                resultStream.write(buffer, 0, length);
            }
            return resultStream.toString("UTF-8");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}