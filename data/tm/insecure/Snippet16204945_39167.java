httpClient = new DefaultHttpClient();

SSLContext ctx = SSLContext.getInstance("TLS");
X509TrustManager tm = new X509TrustManager() {
    public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException { }

    public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException { }

    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
};

ctx.init(null, new TrustManager[]{tm}, null);
SSLSocketFactory ssf = new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", 443, ssf));
