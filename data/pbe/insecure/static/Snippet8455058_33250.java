public class Crypto {

Cipher ecipher;
Cipher dcipher;

byte[] salt = { 1, 2, 4, 5, 7, 8, 3, 6 };
int iterationCount = 1979;

Crypto(String passPhase) {
    try {
        // Create the key
        KeySpec keySpec = new PBEKeySpec(passPhase.toCharArray(), salt, iterationCount);
        SecretKey key = SecretKeyFactory.getInstance("PBEWITHSHA256AND128BITAES-CBC-BC").generateSecret(keySpec);
        ecipher = Cipher.getInstance(key.getAlgorithm()); 
        dcipher = Cipher.getInstance(key.getAlgorithm());

        AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);

        ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
        dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);

    } catch (Exception e) {
        // TODO: handle exception
        //Toast.makeText(this, "I cought ", Toast.LENGTH_LONG).show();
    }


}

public String encrypt(String str) {
    String rVal;
    try {
        byte[] utf8 = str.getBytes("UTF8");

        byte[] enc = ecipher.doFinal(utf8);

        rVal = toHex(enc);

    } catch (Exception e) {
        // TODO: handle exception
        rVal = "Exception Caught "+e.getMessage();
    }
    return rVal;
}


public String decrypt(String str) {
    String rVal;
    try {
        byte[] dec = toByte(str);
        byte[] utf8 = dcipher.doFinal(dec);

        rVal = new String(utf8, "UTF8");

    } catch(Exception e) {
        rVal = "Error in decrypting :"+e.getMessage();
    }
    return rVal;
}

private static byte[] toByte(String hexString ) {
    int len = hexString.length()/2;
    byte[] result = new byte[len];
    for ( int i=0; i<len; i++ ) {
        result[i] = Integer.valueOf(hexString.substring(2*i, 2*i+2), 16 ).byteValue();

    }
    return result;
}
private static String toHex(byte[] buf) {
    if (buf == null)
        return "";
    StringBuffer result = new StringBuffer( 2*buf.length);
    for ( int i=0; i<buf.length; i++) {
        appendHex(result, buf[i]);

    }
    return result.toString();
}

private final static String HEX = "0123456789ABCDEF";

private static void appendHex(StringBuffer sb, byte b) {
    sb.append(HEX.charAt((b >> 4) & 0x0f)).append(HEX.charAt(b & 0x0f));
}


}
