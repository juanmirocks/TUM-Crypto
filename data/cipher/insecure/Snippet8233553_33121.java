String input = "{\"action\":\"getQuestion\"}";
String key = "4288f0b8060ca1b682bf795f2617cfdc";
byte[] data = input.getBytes();
byte[] encrypted = null;
byte[] keyBytes = new BigInteger(key, 16).toByteArray();
SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
cipher.init(Cipher.ENCRYPT_MODE, keySpec);
encrypted = cipher.doFinal(data);
System.out.println(Base64.encodeBytes(encrypted));
