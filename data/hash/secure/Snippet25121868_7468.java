public String getMD5Password(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException{
    MessageDigest digest = java.security.MessageDigest.getInstance("SHA-512"); 
    digest.update(password.getBytes("UTF-16LE")); 
    byte messageDigest[] = digest.digest();

    // Create Hex String
    StringBuffer hexString = new StringBuffer();
    for (int i = 0; i < messageDigest.length; i++) {
        String h = Integer.toHexString(0xFF & messageDigest[i]);
        while (h.length() < 2)
            h = "0" + h;
        hexString.append(h);
    }
    return hexString.toString();
}
