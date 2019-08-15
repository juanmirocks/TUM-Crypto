import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Checksum 
{
    public static void main(String ar[])
    {
        File currentJavaJarFile = new File(Checksum.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        String filepath = currentJavaJarFile.getAbsolutePath();
        StringBuilder sb = new StringBuilder();
        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA-256");// MD5
            FileInputStream fis = new FileInputStream(filepath);
            byte[] dataBytes = new byte[1024];
            int nread = 0; 

            while((nread = fis.read(dataBytes)) != -1)  
                 md.update(dataBytes, 0, nread);

            byte[] mdbytes = md.digest();

            for(int i=0; i<mdbytes.length; i++)
            sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100 , 16).substring(1));  
        }
        catch(NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        System.out.println("Checksum: "+sb);
    }
}
