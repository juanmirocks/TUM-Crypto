static String encodeString(String input)  {

    MessageDigest digest = null;
    try {
        digest = MessageDigest.getInstance("SHA-1");
        byte[] inputBytes = input.getBytes();
        byte[] hashBytes = digest.digest(inputBytes);
        return  Base64.encodeToString(hashBytes, Base64.DEFAULT);
    } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
    }
    return "";
}
