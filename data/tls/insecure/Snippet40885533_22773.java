public SSLSocketFactory getSSLSocketFactory_Certificate(String keyStoreType, int keystoreResId)
        throws CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException, KeyManagementException, NoSuchProviderException {

    InputStream caInput = context.getResources().openRawResource(keystoreResId);

    // creating a KeyStore containing trusted CAs

    if (keyStoreType == null || keyStoreType.length() == 0) {
        keyStoreType = KeyStore.getDefaultType();
    }
    KeyStore keyStore = KeyStore.getInstance(keyStoreType);

    keyStore.load(caInput, "".toCharArray());

    // creating a TrustManager that trusts the CAs in the KeyStore

    String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
    TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
    tmf.init(keyStore);

    TrustManager[] wrappedTrustManagers = getWrappedTrustManagers(tmf.getTrustManagers());

    SSLContext sslContext = SSLContext.getInstance("TLS");
    sslContext.init(null, wrappedTrustManagers, null);

    return sslContext.getSocketFactory();
}
