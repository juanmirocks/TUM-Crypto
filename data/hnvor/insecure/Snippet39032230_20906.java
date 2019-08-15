private static void setupRestClient() {


        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(ROOT)
                //.setClient(new OkClient(new com.squareup.okhttp.OkHttpClient()))
                //.setClient(getOkClient())
                .setClient(setSSLFactoryForClient(new com.squareup.okhttp.OkHttpClient()))
                .setRequestInterceptor(new SessionRequestInterceptor())
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog(new AndroidLog(NetworkUtil.APP_TAG))
                .build();


        REST_CLIENT = restAdapter.create(Restapi.class);
    } 

// SET SSL
public static OkClient setSSLFactoryForClient(OkHttpClient client) {
    try {
        // Create a trust manager that does not validate certificate chains
        final TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                    }

                    @Override
                    public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                    }

                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                }
        };

        // Install the all-trusting trust manager
        final SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
        // Create an ssl socket factory with our all-trusting manager
        final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();


        client.setSslSocketFactory(sslSocketFactory);
        client.setHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });

    } catch (Exception e) {
        throw new RuntimeException(e);
    }
    return new OkClient(client);
}
