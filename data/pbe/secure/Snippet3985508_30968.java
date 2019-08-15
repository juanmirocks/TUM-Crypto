File f = new File(keyFile);
FileInputStream fis = new FileInputStream(f);
DataInputStream dis = new DataInputStream(fis);
byte[] keyBytes = new byte[(int)f.length()];
dis.readFully(keyBytes);
dis.close();
EncryptedPrivateKeyInfo encryptPKInfo = new EncryptedPrivateKeyInfo(keyBytes);
Cipher cipher = Cipher.getInstance(encryptPKInfo.getAlgName());
PBEKeySpec pbeKeySpec = new PBEKeySpec(passwd.toCharArray());
SecretKeyFactory secFac = SecretKeyFactory.getInstance(encryptPKInfo.getAlgName());
Key pbeKey = secFac.generateSecret(pbeKeySpec);
AlgorithmParameters algParams = encryptPKInfo.getAlgParameters();
cipher.init(Cipher.DECRYPT_MODE, pbeKey, algParams);
KeySpec pkcs8KeySpec = encryptPKInfo.getKeySpec(cipher);
KeyFactory kf = KeyFactory.getInstance("RSA");
return kf.generatePrivate(pkcs8KeySpec);
