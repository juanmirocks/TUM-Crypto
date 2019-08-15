    import java.io.InputStream;
    import java.io.OutputStream;
    import java.io.FileInputStream;
    import java.io.FileOutputStream;
    import java.util.Arrays;

    import javax.crypto.Cipher;
    import javax.crypto.CipherInputStream;
    import javax.crypto.CipherOutputStream;
    import javax.crypto.spec.SecretKeySpec;
    import java.security.SecureRandom;
    import java.security.MessageDigest;

    public class FileEncryptor{

    private String algo;
    private String path;
    private String password;
    public FileEncryptor(String algo,String path, String password) {
        this.algo = algo; //setting algo
        this.path = path;//setting file path
        this.password = password;
    }

public void encrypt() throws Exception{
 SecureRandom padding = new SecureRandom();
 byte[] salt = new byte[16];
 padding.nextBytes(salt);
     //generating key
  byte k[] = password.getBytes();  
 MessageDigest sha = MessageDigest.getInstance("SHA-1");
 k = sha.digest(k);
 k = Arrays.copyOf(k, 16);  
     SecretKeySpec key = new SecretKeySpec(k,algo);  
     //creating and initialising cipher and cipher streams
     Cipher encrypt =  Cipher.getInstance(algo);  
     encrypt.init(Cipher.ENCRYPT_MODE, key);
     //opening streams
     FileOutputStream fos =new FileOutputStream(path+".enc");
     try(FileInputStream fis =new FileInputStream(path)){
        try(CipherOutputStream cout=new CipherOutputStream(fos, encrypt)){
            copy(fis,cout);
        }
     }
 }

 public void decrypt() throws Exception{
 SecureRandom padding = new SecureRandom();
 byte[] salt = new byte[16];
 padding.nextBytes(salt);
     //generating same key
  byte k[] = password.getBytes();  
 MessageDigest sha = MessageDigest.getInstance("SHA-1");
 k = sha.digest(k);
 k = Arrays.copyOf(k, 16); 
     SecretKeySpec key = new SecretKeySpec(k,algo);  
     //creating and initialising cipher and cipher streams
     Cipher decrypt =  Cipher.getInstance(algo);  
     decrypt.init(Cipher.DECRYPT_MODE, key);
     //opening streams
     FileInputStream fis = new FileInputStream(path);
     try(CipherInputStream cin=new CipherInputStream(fis, decrypt)){  
        try(FileOutputStream fos =new FileOutputStream(path.substring(0,path.lastIndexOf(".")))){
           copy(cin,fos);
       }
     }
  }

 private void copy(InputStream is,OutputStream os) throws Exception{
    byte buf[] = new byte[4096];  //4K buffer set
    int read = 0;
    while((read = is.read(buf)) != -1)  //reading
       os.write(buf,0,read);  //writing
 }

 public static void main (String[] args)throws Exception {
     System.out.println("Enter Password: ");
     new FileEncryptor("AES","sample.txt",new java.util.Scanner(System.in).nextLine()).encrypt();
     new FileEncryptor("AES","sample.txt.enc",new java.util.Scanner(System.in).nextLine()).decrypt();
  }
}
