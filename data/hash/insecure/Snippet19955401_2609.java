   try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;

                    md = MessageDigest.getInstance("SHA");
                    md.update(signature.toByteArray());
                    String something = new String(Base64.encode(md.digest(), 0));
                   //Toast.makeText(StartingPlace.this, something,
                        //  Toast.LENGTH_LONG).show();
                    Log.e("hash key", something);
        } 
        }
        catch (NameNotFoundException e1) {
            // TODO Auto-generated catch block
            Log.e("name not found", e1.toString());
        }

             catch (NoSuchAlgorithmException e1) {
                // TODO Auto-generated catch block
                 Log.e("no such an algorithm", e1.toString());
            }
             catch (Exception e1){
                 Log.e("exception", e1.toString());
             }
        //code to get hash code
