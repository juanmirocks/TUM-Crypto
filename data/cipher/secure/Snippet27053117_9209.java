modulusString = new BigInteger(1, rsaModulus);
exponentString = new BigInteger(1, rsaExponent);
RSAPublicKeySpec keySpec = new RSAPublicKeySpec(modulusString, exponentString);
KeyFactory factor = KeyFactory.getInstance("RSA"); 
PublicKey publicKey = (RSAPublicKey) factor.generatePublic(keySpec);
rsaCipher = Cipher.getInstance("RSA");
rsaCipher.init(Cipher.DECRYPT_MODE, publicKey);
signature = Signature.getInstance("SHA1withRSA");
signature.initVerify(publicKey);
signature.update(resultBytes);
signature.verify(finalEncryptedMsg);
tempStorage = rsaCipher.doFinal(finalEncryptedMsg);
System.out.println("Decrypted Length = " + tempStorage.length);
