private static OkHttpClient getUnsafeOkHttpClient() {
  try {
    // Create a trust manager that does not validate certificate chains
    final TrustManager[] trustAllCerts = new TrustManager[] {
        new X509TrustManager() {
          @Override
          public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
          }

          @Override
          public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
          }

          @Override
          public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return new java.security.cert.X509Certificate[]{};
          }
        }
    };

    // Install the all-trusting trust manager
    final SSLContext sslContext = SSLContext.getInstance("SSL");
    sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
    // Create an ssl socket factory with our all-trusting manager
    final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

    OkHttpClient.Builder builder = new OkHttpClient.Builder();
    builder.sslSocketFactory(sslSocketFactory, (X509TrustManager)trustAllCerts[0]);
    builder.hostnameVerifier(new HostnameVerifier() {
      @Override
      public boolean verify(String hostname, SSLSession session) {
        return true;
      }
    });

    OkHttpClient okHttpClient = builder
        .connectTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .build();
    return okHttpClient;
  } catch (Exception e) {
    throw new RuntimeException(e);
  }
}
