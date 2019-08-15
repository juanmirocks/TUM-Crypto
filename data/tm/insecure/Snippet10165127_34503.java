import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.slf4j.LoggerFactory;




public class RedirectTest {


    private static boolean isLive(String link) {
        HttpURLConnection urlConnection = null;
        try {
          URL url = new URL(link);
          System.out.println("link: " + url);
          urlConnection = (HttpURLConnection) url.openConnection();          
          urlConnection.setInstanceFollowRedirects(false);
          urlConnection.setRequestMethod("GET");
         // urlConnection.connect();

          //urlConnection.setConnectTimeout(5000); /* timeout after 5s if can't connect */
          //urlConnection.setReadTimeout(5000); /* timeout after 5s if the page is too slow */
          urlConnection.connect();
          int resp = urlConnection.getResponseCode();
          System.out.println(resp);
          String redirectLink = urlConnection.getHeaderField("Location");
          System.out.println("Location : " + redirectLink);
          if (redirectLink != null && !link.equals(redirectLink)) {
            boolean b = isLive(redirectLink);   
            System.out.println("isLive " + redirectLink + " =  " + b);
            return b;
          } else {
            return urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK;
          }
        } catch (Exception e) {
            LoggerFactory.getLogger(RedirectTest.class).debug("Exception", e);
          return false;
        } finally {
          if (urlConnection != null) {
            urlConnection.disconnect();
          }
        }
    }


    public static void main(String[] args) throws Exception {

     // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(
                    java.security.cert.X509Certificate[] certs, String authType) {
                }
                public void checkServerTrusted(
                    java.security.cert.X509Certificate[] certs, String authType) {
                }
            }
        };

        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());


        isLive("https://www0.bankofamerica.com/home-loans/mortgage-purchase.go");
    }
}
