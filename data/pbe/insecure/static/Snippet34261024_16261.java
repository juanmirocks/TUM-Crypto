EncryptedPrivateKeyInfo encryptPKInfo = new EncryptedPrivateKeyInfo("RSA",readFileBytes(filename));
Cipher cipher = Cipher.getInstance(encryptPKInfo.getAlgName());
PBEKeySpec pbeKeySpec = new PBEKeySpec("pwd".toCharArray());
SecretKeyFactory secFac = SecretKeyFactory.getInstance("PBEWithSHA1AndDESede");
Key pbeKey = secFac.generateSecret(pbeKeySpec);
AlgorithmParameters algParams = encryptPKInfo.getAlgParameters();
cipher.init(Cipher.DECRYPT_MODE, pbeKey,algParams);
KeySpec pkcs8KeySpec = encryptPKInfo.getKeySpec(cipher);
KeyFactory kf = KeyFactory.getInstance("RSA");
return kf.generatePrivate(pkcs8KeySpec);
