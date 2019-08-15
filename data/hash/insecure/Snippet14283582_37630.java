PackageInfo info;
try {
info = getPackageManager().getPackageInfo("com.myname.lolo", PackageManager.GET_SIGNATURES);
for (Signature signature : info.signatures) {
           MessageDigest md;
md = MessageDigest.getInstance("SHA");
md.update(signature.toByteArray());
           //String something = new String(Base64.encode(md.digest(), 0));
            * String something = new String(Base64.encodeBytes(md.digest()));
           Log.e("hash key", something);
} 
}
catch (NameNotFoundException e1) {
Log.e("name not found", e1.toString());
}

catch (NoSuchAlgorithmException e) {
Log.e("no such an algorithm", e.toString());
}
catch (Exception e){
Log.e("exception", e.toString());
}
