    SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
    sslContext.init(null, null, null);
    SSLContext.setDefault(sslContext);
