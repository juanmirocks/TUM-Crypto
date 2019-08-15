public class ChooseTeamActivity extends ListActivity {

    private static final String apiKey = "4545ggg454hfnf7557kfdkgg454"; 
    private static final String apiUser = "AndroidUser"; 

    long unixTimeStamp = System.currentTimeMillis() / 1000L;

    String newFeedRequest = "1.0/evoStructure?timestamp=" + unixTimeStamp;
    String fixturesFeedURL = "https://secure.TestSite.com/_services/api/" + newFeedRequest;

    public String hash() throws NoSuchAlgorithmException, UnsupportedEncodingException{

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(fixturesFeedURL.getBytes("UTF-8"));
        byte[] digest = md.digest();
        return new String(digest);

    }   



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

    setContentView(R.layout.chooseact);

     String strhash = hash();
     Log.v("myApp", fixturesFeedURL);
     Log.v("myApp", strhash);



    }

}
