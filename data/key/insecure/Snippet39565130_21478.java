String strDefaultKey = "QabC-+50";
Key key = new SecretKeySpec(strDefaultKey.getBytes("UTF-8"), "DES");
encryptCipher = Cipher.getInstance(DES_ECB);
encryptCipher.init(Cipher.ENCRYPT_MODE, key);
String seed = "2016-09-19 05:11";
MessageDigest md5 = MessageDigest.getInstance("MD5");
md5.update(seed.getBytes());
byte[] m = md5.digest();
encryptCipher.doFinal(m);
byte[] encodeUrl = Base64.encodeBase64(sEncription.encrypt(m));
String finalUrl = new String(encodeUrl);
finalResult = finalUrl.substring(2, 8) + finalUrl.substring(10, 13);
