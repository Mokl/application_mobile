package be.ecam.a11209.am_lab1;

/**
 * Created by 11209 on 07-02-17.
 */
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class NetCon
{
    public static String getResponseFromHttpUrl(String url) throws IOException
    {
        URL urlObj=new URL(url);
        HttpURLConnection urlConnection = (HttpURLConnection) urlObj.openConnection();

        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        }
        finally {
            urlConnection.disconnect();
        }
    }

}
