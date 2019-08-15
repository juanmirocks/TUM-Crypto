try {
        PackageInfo info = getPackageManager().getPackageInfo(
                "Your package name", 
                PackageManager.GET_SIGNATURES);
        for (Signature signature : info.signatures) {
            MessageDigest md = MessageDigest.getInstance("SHA");
            md.update(signature.toByteArray());
            System.out.println(Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
    } catch (NameNotFoundException e) {

    } catch (NoSuchAlgorithmException e) {

    }
