public class fragment_login extends Fragment {
private Controller controller;
private CallbackManager callbackManager;
private LoginButton loginButton;
private String TAG = "sonder.login";
private VolleyHelper callBack;
private View rootView;
private OpenSansBTextView termsbutton, privacybutton;
private AccessTokenTracker mAccessTokenTracker;
private String fetchData;


public void setFetchData(String fetchData) {
    this.fetchData = fetchData;
}

public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    FacebookSdk.sdkInitialize(getApplicationContext());
    controller=new Controller(getActivity());
    String permission = getResources().getString(R.string.facebookPermissions);
    rootView = inflater.inflate(R.layout.setup_login, container, false);
    ((setup_activity)getActivity()).getSupportActionBar().hide();
    findViewByID();
    onTouchListeners();
    callbackManager = CallbackManager.Factory.create();
    initVolleyCallback();
    loginButton.setReadPermissions(Arrays.asList(permission));

    //Use this code to get app hash for facebook and so
    PackageInfo info;
    try {
        info = getActivity().getPackageManager().getPackageInfo("com.eseed.sonder", PackageManager.GET_SIGNATURES);
        Log.e("start","hash");
        for (Signature signature : info.signatures) {
            MessageDigest md;
            md = MessageDigest.getInstance("SHA");
            md.update(signature.toByteArray());
            String something = new String(Base64.encode(md.digest(), 0));
            Log.e("hash key", something);
        }
    } catch (PackageManager.NameNotFoundException e1) {
        Log.e("name not found", e1.toString());
    } catch (NoSuchAlgorithmException e) {
        Log.e("no such algorithm", e.toString());
    } catch (Exception e) {
        Log.e("exception", e.toString());
    }

    if(!UserData.isLoggedOut())
    loginToMyFbApp();

    return rootView;
}

private void findViewByID(){
    loginButton = (LoginButton) rootView.findViewById(R.id.login_button);
    termsbutton = (OpenSansBTextView) rootView.findViewById(R.id.terms_text);
    privacybutton = (OpenSansBTextView) rootView.findViewById(R.id.policy_text);
}

private boolean CheckLogin() {
    String ID;
    AccountManager mAccountManager = AccountManager.get(getApplicationContext());

    if (mAccountManager.getAccountsByType(getString(R.string.authtype)).length > 0) {
        Account A = mAccountManager.getAccountsByType(getString(R.string.authtype))[0];
        ID = mAccountManager.getPassword(A);
        UserData.setUserId(ID);
        Log.w("Login", "AccountFound" + ID);
        return true;
    }
    return false;
}

public void initVolleyCallback()    {
    callBack = new VolleyHelper() {
        @Override
        public void notifySuccess(JSONObject response) {
            Log.d(TAG, "Volley requester ");
            Log.d(TAG, "Volley JSON response " + response);
            try
            {
                JSONArray data = response.getJSONObject("data").getJSONArray("status");
                Log.i("data",response.getJSONObject("data").toString());
                Log.i("user",response.getJSONObject("data").getJSONObject("user").toString());
                int x = data.length();
                Log.d(TAG,x+"");
                for (int i = 0; i < x; i++) {
                    JSONObject y = data.getJSONObject(i);
                    Log.d(TAG, "Volley JSON response " + y.getString(""));
                }
            } catch (JSONException e) {
                Log.d(TAG, "ERROR " +e.getLocalizedMessage());
            }
        }
        @Override
        public void notifyError(VolleyError error) {
            Log.d(TAG, "Volley requester ");
            Log.d(TAG, "Volley JSON response" + " That didn't work!" +error);
        }
    };
}

public static Bitmap getFacebookProfilePicture(String userID) {
    Bitmap bitmap = null;
    try {
        URL imageURL = new URL("https://graph.facebook.com/" + userID + "/picture?type=large");
        bitmap = BitmapFactory.decodeStream(imageURL.openConnection().getInputStream());
    } catch (Exception e) {
            e.printStackTrace();
    }
    return bitmap;
}

@Override
public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
    Log.i("onActivityResult","onActivityResult");
    callbackManager.onActivityResult(requestCode, resultCode, data);
}
private void loginToMyFbApp() {
    Log.i("LoginToMyFbApp","inside");
    if (AccessToken.getCurrentAccessToken() != null) {
        mAccessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                mAccessTokenTracker.stopTracking();
                if(currentAccessToken == null) {
                    Log.i("Login","User Not Found ON Facebook");
                    //(the user has revoked your permissions -
                    //by going to his settings and deleted your app)
                    //do the simple login to FaceBook

                    registerLoginCallBack();
                }
                else {
                    //you've got the new access token now.
                    //AccessToken.getToken() could be same for both
                    //parameters but you should only use "currentAccessToken"
                    Log.w("test",AccessToken.getCurrentAccessToken().toString());
                    Log.w("Login","User Found");
                    if(CheckLogin()){
                        Log.i("Login","User Found");
                        //loading
                        ((setup_activity)getActivity()).show_loading();
                        UserData.setAcsstkn(AccessToken.getCurrentAccessToken());
                        controller.retrieveFacebookData(AccessToken.getCurrentAccessToken(),fetchData);
                    }
                    else
                    {
                        Log.i("Login","User Not Found");
                        UserData.setAcsstkn(AccessToken.getCurrentAccessToken());
                        Set<String> x = AccessToken.getCurrentAccessToken().getDeclinedPermissions();
                        Log.w("Login Permissions",x.toString());
                        if (x.contains("user_birthday") || x.contains("user_friends")) {
                            Log.w("Login Permissions","will ask for permissions");
                            ((setup_activity)getActivity()).permissions();
                        }
                        else{
                            Log.w("Login Permissions","Continue");
                            ((setup_activity) getActivity()).show_loading();
                            controller.retrieveFacebookData(AccessToken.getCurrentAccessToken(),fetchData);
                        }
                    }
                }
            }
        };
        AccessToken.refreshCurrentAccessTokenAsync();
    }
    else {
        Log.i("NoAccessToken","no token");
        registerLoginCallBack();
    }
}
public void registerLoginCallBack() {
    loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

        @Override
        public void onSuccess(LoginResult loginResult) {
            Log.i("onSuccess","onSuccess");
            Set<String> x = AccessToken.getCurrentAccessToken().getDeclinedPermissions();

            if (x.contains("user_birthday") || x.contains("user_friends")) {
                ((setup_activity)getActivity()).permissions();
            }
            else{
                Log.i("retrieve","FacebookData");
                ((setup_activity) getActivity()).show_loading();
                controller.retrieveFacebookData(AccessToken.getCurrentAccessToken(),fetchData);
            }
        }

        @Override
        public void onCancel() {
            Log.i("onCancel","onCancel");
        }

        @Override
        public void onError(FacebookException error) {
            Log.i("onError","onError");
        }
    });
}

private void onTouchListeners(){
    loginButton.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            loginToMyFbApp();
            return true;
        }
    });

    termsbutton.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            ((setup_activity)getActivity()).terms();
    }});

    privacybutton.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            ((setup_activity)getActivity()).privacy();
        }});
}
