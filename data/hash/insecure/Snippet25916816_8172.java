static final String md5(final String s) {
    String hash = "";
    try {
        MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
        digest.update(s.getBytes());
        byte messageDigest[] = digest.digest();
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < messageDigest.length; i++) {
            String h = Integer.toHexString(0xFF & messageDigest[i]);
            while (h.length() < 2)
                h = "0" + h;
            hexString.append(h);
        }
        hash =  hexString.toString();

    }
    catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
    }
    return hash;
}
