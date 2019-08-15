public static void main(String[] args) throws IOException,   UnrecoverableKeyException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException {

    // Create a FilterChain using FilterChainBuilder
    FilterChainBuilder filterChainBuilder = FilterChainBuilder.stateless();
    // Add TransportFilter, which is responsible
    // for reading and writing data to the connection
    filterChainBuilder.add(new TransportFilter());

    // Initialize and add SSLFilter which is responsible for SSLFilter, responsible for encoding/decoding SSL secured data.
    final SSLEngineConfigurator serverConfig = initializeSSL();
    final SSLEngineConfigurator clientConfig = serverConfig.copy().setClientMode(true);

    final SSLFilter sslFilter = new SSLFilter(serverConfig, clientConfig);
    filterChainBuilder.add(sslFilter);

    // Add StringFilter, which will be responsible for Buffer <-> String transformation
    filterChainBuilder.add(new StringFilter(Charset.forName("UTF-8")));
    .
    .
    .
    }

private static SSLEngineConfigurator initializeSSL() throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, KeyManagementException {

    // Create/initialize the SSLContext with key material
    char [] passphrase2="zzzzz".toCharArray();

    KeyStore ksTrust = KeyStore.getInstance("JKS");
    ksTrust.load(new FileInputStream("C:\\Program Files\\Java\\jre1.8.0_51\\lib\\security\\trustkeystore.jks"),passphrase2);
    TrustManagerFactory ktf =TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
    ktf.init(ksTrust);
    SSLContext sslContext = SSLContext.getInstance("TLS");
    //initialize the ssl-context
    sslContext.init(null,ktf.getTrustManagers(),null);  
    System.out.println("SSL-Server: SSL-initialization terminate");
    // Create SSLEngine configurator
    return new SSLEngineConfigurator(sslContext,false, false, false);

  } 
