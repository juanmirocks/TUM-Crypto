    byte[] message = Base64.decodeBase64(encryptedText.getBytes("UTF-8"));
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digestOfUsername = md.digest(key.getBytes("UTF-8"));
        byte[] keyBytes = Arrays.copyOf(digestOfUsername, 24);
        SecretKey key = new SecretKeySpec(keyBytes, "DESede");
Cipher decipher = Cipher.getInstance("DESede/ECB/PKCS5Padding",new SunJCE());
        decipher.init(Cipher.DECRYPT_MODE, key);
        byte[] plainText = decipher.doFinal(message);// BadpaddingException 
        String decryptedString = new String(plainText);
