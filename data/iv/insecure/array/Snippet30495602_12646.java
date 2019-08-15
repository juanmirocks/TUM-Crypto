public class FileEncryption {

    //Initial Vector
    public static final byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };      

    //EncryptAndDecrypt String -> Input : PlainText + Return : CipherText+DecipherText
    public static String encryptString(String src) throws Exception
    {
        String dst="";
        //Not Input!
        if(src == null || src.length()==0)
            return "";

        //Encryption Setting
        byte[] k="Multimediaproces".getBytes();
        SecretKeySpec Key = new SecretKeySpec(k,"AES");
        IvParameterSpec ivspec = new IvParameterSpec(iv);
        Cipher encryptCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        encryptCipher.init(Cipher.ENCRYPT_MODE,Key,ivspec);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        CipherOutputStream cout = new CipherOutputStream(baos,encryptCipher);
        cout.write(src.getBytes());
        cout.flush();               //ByteOutputStream -> Write Encryption Text
        cout.close();           
     // in encrypt method
        dst = DatatypeConverter.printHexBinary(baos.toByteArray());
        return dst;
    }   

    //String src -> EncryptedData
    public static String decryptString(String src) throws Exception 
    {
        //src value is Encrypted Value!
        //So, src value -> Not Byte!
        String dst="";
        byte[] encryptedBytes = DatatypeConverter.parseHexBinary(src);;         
        //Not Input!
        if(src == null || src.length()==0)
            return "";          
        //Decryption Setting
        IvParameterSpec ivspec = new IvParameterSpec(iv);
        byte[] k="Multimediaproces".getBytes();
        SecretKeySpec Key = new SecretKeySpec(k,"AES");
        Cipher decryptCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        decryptCipher.init(Cipher.DECRYPT_MODE,Key,ivspec); 

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ByteArrayInputStream bais = new ByteArrayInputStream(encryptedBytes);
        CipherInputStream cin = new CipherInputStream(bais,decryptCipher);
        byte[] buf = new byte[1024];
        int read;
        while((read=cin.read(buf))>=0)  //reading encrypted data!
        {
            baos.write(buf,0,read);     //writing decrypted data!
        }

        // closing streams
        cin.close();
        dst = new String(baos.toByteArray());
        return dst;
    }
}
