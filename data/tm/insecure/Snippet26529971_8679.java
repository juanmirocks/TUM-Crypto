void disableSslVerification() throws NoSuchAlgorithmException, KeyManagementException{
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = [
            new X509TrustManager(){
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null
                }
                public void checkClientTrusted(X509Certificate[] certs, String authType) {

                }
                public void checkServerTrusted(X509Certificate[] certs, String authType) {

                }
            }
        ]

        // Install the all-trusting trust manager
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
                    public boolean verify(String hostname, SSLSession sslSession) {
                        return true;
                    }
                };
        // Install the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
    }
