private static String convertToHex(byte[] data) { 
    StringBuffer buf = new StringBuffer();
    for (int i = 0; i < data.length; i++) { 
        int halfbyte = (data[i] >>> 4) & 0x0F;
        int two_halfs = 0;
        do { 
            if ((0 <= halfbyte) && (halfbyte <= 9)) {
                buf.append((char) ('0' + halfbyte));
            }
            else {
                buf.append((char) ('a' + (halfbyte - 10)));
            }
            halfbyte = data[i] & 0x0F;
        } while(two_halfs++ < 1);
    } 
    return buf.toString();
} 


public static String SHA1(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
    MessageDigest md = MessageDigest.getInstance("SHA-1");
    md.update(password.getBytes("iso-8859-1"), 0, password.length());
    byte[] sha1hash = md.digest();
    return convertToHex(sha1hash);
}
