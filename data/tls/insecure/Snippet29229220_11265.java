public static HttpClient verifiedClient(HttpClient base) {  
    try {  
        SSLContext ctx = SSLContext.getInstance("SSL");  
        X509TrustManager tm = new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {  
                return null;  
            }  
            @Override  
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}  
            @Override  
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}  
        };

        ctx.init(null, new TrustManager[] { tm }, null); 
        SSLSocketFactory ssf = new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER); 
        ClientConnectionManager mgr = base.getConnectionManager();
        SchemeRegistry registry = mgr.getSchemeRegistry(); 
        registry.register(new Scheme("https", 443, ssf)); 
        return new DefaultHttpClient(mgr, base.getParams());  
    } catch (Exception ex) {  
        ex.printStackTrace();  
        return null;  
    }  
}  
