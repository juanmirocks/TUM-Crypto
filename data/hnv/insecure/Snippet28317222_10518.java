           trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(null, null);
            SSLSocketFactory sf = new CustomSSLSocketFactory(trustStore);
            sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            HttpParams bhttpparams = new BasicHttpParams();
            HttpProtocolParams.setVersion(bhttpparams, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(bhttpparams, "utf-8");
            bhttpparams.setBooleanParameter("http.protocol.expect-continue", false);
            HttpConnectionParams.setConnectionTimeout(bhttpparams, 20000);
            HttpConnectionParams.setSoTimeout(bhttpparams, 200000);
            SchemeRegistry registry = new SchemeRegistry();
            registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            registry.register(new Scheme("https", sf, 443));
            ClientConnectionManager ccm = new ThreadSafeClientConnManager(bhttpparams, registry);
            client = new DefaultHttpClient(ccm, bhttpparams);
            client.getCredentialsProvider().setCredentials(new AuthScope(null, -1), new UsernamePasswordCredentials("", ""));
            HttpResponse response = client.execute(urlws);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"), 8);
