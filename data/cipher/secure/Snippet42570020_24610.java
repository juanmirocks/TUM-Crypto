KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
keyStore.load(null);
KeyStore.PrivateKeyEntry entry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(mAlias, null);
Cipher cip = null;
RSAPublicKey pubKey = (RSAPublicKey) entry.getCertificate().getPublicKey();
cip = Cipher.getInstance("RSA/ECB/NoPadding");
cip.init(Cipher.ENCRYPT_MODE, pubKey);
byte[] encryptBytes = cip.doFinal(challenge.getBytes());
String encryptedStr64 = Base64.encodeToString(encryptBytes, Base64.DEFAULT);
