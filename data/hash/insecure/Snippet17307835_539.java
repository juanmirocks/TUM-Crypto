String src = ...
MessageDigest md = MessageDigest.getInstance("SHA-1");
String sha1 = new String(md.digest(src.getBytes()));
