public class CreateSignature extends CreateSignatureBase
{

    /**
     * Initialize the signature creator with a keystore and certficate password.
     *
     * @param keystore the pkcs12 keystore containing the signing certificate
     * @param pin the password for recovering the key
     * @throws KeyStoreException if the keystore has not been initialized (loaded)
     * @throws NoSuchAlgorithmException if the algorithm for recovering the key cannot be found
     * @throws UnrecoverableKeyException if the given password is wrong
     * @throws CertificateException if the certificate is not valid as signing time
     * @throws IOException if no certificate could be found
     */
    public CreateSignature(KeyStore keystore, char[] pin)
            throws KeyStoreException, UnrecoverableKeyException, NoSuchAlgorithmException, CertificateException, IOException
    {
        super(keystore, pin);
    }

    /**
     * Signs the given PDF file. Alters the original file on disk.
     * @param file the PDF file to sign
     * @throws IOException if the file could not be read or written
     */
    public void signDetached(File file) throws IOException
    {
        signDetached(file, file, null);
    }

    /**
     * Signs the given PDF file.
     * @param inFile input PDF file
     * @param outFile output PDF file
     * @throws IOException if the input file could not be read
     */
    public void signDetached(File inFile, File outFile) throws IOException
    {
        signDetached(inFile, outFile, null);
    }

    /**
     * Signs the given PDF file.
     * @param inFile input PDF file
     * @param outFile output PDF file
     * @param tsaClient optional TSA client
     * @throws IOException if the input file could not be read
     */
    public void signDetached(File inFile, File outFile, TSAClient tsaClient) throws IOException
    {
        if (inFile == null || !inFile.exists())
        {
            throw new FileNotFoundException("Document for signing does not exist");
        }

        FileOutputStream fos = new FileOutputStream(outFile);

        // sign
        PDDocument doc = PDDocument.load(inFile);
        signDetached(doc, fos, tsaClient);
        doc.close();
    }

    public void signDetached(PDDocument document, OutputStream output, TSAClient tsaClient)
            throws IOException
    {
        setTsaClient(tsaClient);

        int accessPermissions = SigUtils.getMDPPermission(document);
        if (accessPermissions == 1)
        {
            throw new IllegalStateException("No changes to the document are permitted due to DocMDP transform parameters dictionary");
        }     

        // create signature dictionary
        PDSignature signature = new PDSignature();
        signature.setFilter(PDSignature.FILTER_ADOBE_PPKLITE);
        signature.setSubFilter(PDSignature.SUBFILTER_ADBE_PKCS7_DETACHED);
        signature.setName("Example User");
        signature.setLocation("Los Angeles, CA");
        signature.setReason("Testing");
        // TODO extract the above details from the signing certificate? Reason as a parameter?

        // the signing date, needed for valid signature
        signature.setSignDate(Calendar.getInstance());

        // Optional: certify 
        if (accessPermissions == 0)
        {
            SigUtils.setMDPPermission(document, signature, 2);
        }        

        if (isExternalSigning())
        {
            System.out.println("Sign externally...");
            document.addSignature(signature);
            ExternalSigningSupport externalSigning =
                    document.saveIncrementalForExternalSigning(output);
            // invoke external signature service
            byte[] cmsSignature = sign(externalSigning.getContent());
            // set signature bytes received from the service
            externalSigning.setSignature(cmsSignature);
        }
        else
        {
            // register signature dictionary and sign interface
            document.addSignature(signature, this);

            // write incremental (only for signing purpose)
            document.saveIncremental(output);
        }
    }

    public static void main(String[] args) throws IOException, GeneralSecurityException
    {
        if (args.length < 3)
        {
            usage();
            System.exit(1);
        }

        String tsaUrl = null;
        boolean externalSig = false;
        for (int i = 0; i < args.length; i++)
        {
            if (args[i].equals("-tsa"))
            {
                i++;
                if (i >= args.length)
                {
                    usage();
                    System.exit(1);
                }
                tsaUrl = args[i];
            }
            if (args[i].equals("-e"))
            {
                externalSig = true;
            }
        }

        // load the keystore
        KeyStore keystore = KeyStore.getInstance("PKCS12");
        char[] password = args[1].toCharArray(); // TODO use Java 6 java.io.Console.readPassword
        keystore.load(new FileInputStream(args[0]), password);
        // TODO alias command line argument

        // TSA client
        TSAClient tsaClient = null;
        if (tsaUrl != null)
        {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            tsaClient = new TSAClient(new URL(tsaUrl), null, null, digest);
        }

        // sign PDF
        CreateSignature signing = new CreateSignature(keystore, password);
        signing.setExternalSigning(externalSig);

        File inFile = new File(args[2]);
        String name = inFile.getName();
        String substring = name.substring(0, name.lastIndexOf('.'));

        File outFile = new File(inFile.getParent(), substring + "_signed.pdf");
        signing.signDetached(inFile, outFile, tsaClient);
    }

    private static void usage()
    {
        System.err.println("usage: java " + CreateSignature.class.getName() + " " +
                           "<pkcs12_keystore> <password> <pdf_to_sign>\n" + "" +
                           "options:\n" +
                           "  -tsa <url>    sign timestamp using the given TSA server\n" +
                           "  -e            sign using external signature creation scenario");
    }
}
