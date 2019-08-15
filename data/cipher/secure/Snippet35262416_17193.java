private byte[] getKeyBytes(final byte[] key) throws Exception {
        byte[] keyBytes = new byte[16];
        System.arraycopy(key, 0, keyBytes, 0, Math.min(key.length, keyBytes.length));
        return keyBytes;
    }

    public Cipher getCipherEncrypt(final byte[] key) throws Exception {
        byte[] keyBytes = getKeyBytes(key);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(keyBytes);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        return cipher;
    }

    public void encrypt(File in, File output, byte[] key) throws Exception {

        Cipher cipher = getCipherEncrypt(key);
        FileOutputStream fos = null;
        CipherOutputStream cos = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(in);
            fos = new FileOutputStream(output);
            cos = new CipherOutputStream(fos, cipher);
            byte[] data = new byte[1024];
            int read = fis.read(data);
            while (read != -1) {
                cos.write(data, 0, read);
                read = fis.read(data);
               System.out.println(new String(data, "UTF-8").trim());

            }
            cos.flush();
            System.out.println("name"+ output);


        } finally {
            System.out.println("performed encrypt method now closing streams");
            cos.close();
            fos.close();
            fis.close();
        }
    }




// Working Upload Method To DropBox Cloud
public void uploadFile () throws DbxException, IOException, FileLoadException, NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException, InvalidParameterSpecException, ClassNotFoundException, NoSuchProviderException, InvalidAlgorithmParameterException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException, ShortBufferException, Exception {

// autheticate if there is a accessToken here if not prompt to login by activating the drop method re-auth..
try{
  phoneHome();
}catch(IOException e){
    System.out.println("not saving accessToken");
    JOptionPane.showMessageDialog(null, "Your Access Information Does Not Exist,\n Please Login"+
"Please Login By Clicking 'OK'"); 
 drop(); // will run auth method for user to login
}

// user picks file to upload with JFileChooser
 fc = new JFileChooser();
 fc.setMultiSelectionEnabled(true);
 fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
int dialog = fc.showSaveDialog(this);
 if (dialog == JFileChooser.APPROVE_OPTION) {

   inputFile = fc.getSelectedFile();
   inputFile.getName();
   inputFile.getAbsoluteFile();
    String nameOf = inputFile.getName();
    System.out.println(" File: " + inputFile);


/* calling out inputFIle from out JFileChooser and now passing it into
    our method and encrytping and outputing it.
    */
encrypt(inputFile, new File("Encrypted"),key);

File selected = new File ("Encrypted"+inputFile);
inputStream = new FileInputStream (selected);
uploadedFile = client.uploadFile( "/" +selected ,DbxWriteMode.add(), inputFile.length(), inputStream);

/*
File selectedFile = new File(nameOf+inputFile);
inputStream = new FileInputStream(inputFile);

*/


//uploadedFile = client.uploadFile( "/" +selectedFile ,DbxWriteMode.add(), inputFile.length(), inputStream);
setTitle("Uploading File..");
System.out.println("Uploaded: " + uploadedFile.toString());
JOptionPane.showMessageDialog(null,"File Upload:" + uploadedFile.toString(),
        "Success!", JOptionPane.WARNING_MESSAGE);
}
}
