   public static String getPasswordFromKeystore(String entry, String keystoreLocation, String keyStorePassword) throws Exception{

        KeyStore ks = KeyStore.getInstance("JCEKS");
        ks.load(null, keyStorePassword.toCharArray());
        KeyStore.PasswordProtection keyStorePP = new KeyStore.PasswordProtection(keyStorePassword.toCharArray());

        FileInputStream fIn = new FileInputStream(keystoreLocation);

        ks.load(fIn, keyStorePassword.toCharArray());

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBE");

        KeyStore.SecretKeyEntry ske =
                (KeyStore.SecretKeyEntry)ks.getEntry(entry, keyStorePP);

        PBEKeySpec keySpec = (PBEKeySpec)factory.getKeySpec(
                ske.getSecretKey(),
                PBEKeySpec.class);

        char[] password = keySpec.getPassword();

        return new String(password);

    }

    public static void makeNewKeystoreEntry(String entry, String entryPassword, String keyStoreLocation, String keyStorePassword)
            throws Exception {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBE");
        SecretKey generatedSecret =
                factory.generateSecret(new PBEKeySpec(
                        entryPassword.toCharArray()));

        KeyStore ks = KeyStore.getInstance("JCEKS");
        ks.load(null, keyStorePassword.toCharArray());
        KeyStore.PasswordProtection keyStorePP = new KeyStore.PasswordProtection(keyStorePassword.toCharArray());

        ks.setEntry(entry, new KeyStore.SecretKeyEntry(
                generatedSecret), keyStorePP);

        FileOutputStream fos = new java.io.FileOutputStream(keyStoreLocation);
        ks.store(fos, keyStorePassword.toCharArray());
    }
