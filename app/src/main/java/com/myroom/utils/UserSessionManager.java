package com.myroom.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import com.myroom.MainActivity;
import java.util.HashMap;

/**
 * Created by Jobin on Jul 12.
 */
public class UserSessionManager {
    // Shared Preferences reference
    SharedPreferences pref;
    // Editor reference for Shared preferences
    SharedPreferences.Editor editor;
    // Context
    Context mContext;
    // Shared pref mode
    int PRIVATE_MODE = 0;
    // Sharedpref file name
    private static final String PREFER_NAME = "ExpendPreference";
    // All Shared Preferences Keys
    private static final String IS_USER_LOGIN = "IsUserLoggedIn";
    // User name (make variable public to access from outside)
    public static final String KEY_GROUP_ID = "groupId";
    public static final String KEY_USER_ID = "userId";
    public static final String KEY_USER_NAME = "username";
    // Email address (make variable public to access from outside)
    public static final String KEY_EMAIL = "email";
    public static final String KEY_IMAGE_SRC = "imagePath";


    public UserSessionManager()
    {
    }
    // Constructor
    public UserSessionManager(Context context){
        this.mContext = context;
        pref = mContext.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public SharedPreferences getSharedPref(){
        return pref;
    }
    /*
     * Returns the preference editor
     */
    public SharedPreferences.Editor getPrefEditor(){
        return editor;
    }

    //Create login session
    public void createUserLoginSession(String name, String email,String userid,String groupid,String picpath){
        // Storing login value as TRUE
        editor.putBoolean(IS_USER_LOGIN, true);
        // Storing group name in pref
        editor.putString(KEY_USER_NAME, name);
        // Storing email in pref
        editor.putString(KEY_EMAIL, email);
        // Storing userid in pref
        editor.putString(KEY_USER_ID, userid);
        // Storing groupid in pref
        editor.putString(KEY_GROUP_ID, groupid);
        // Storing image path in pref
        editor.putString(KEY_IMAGE_SRC, picpath);
        // commit changes
        editor.commit();
    }

    /**
     * Check login method will check user login status
     * If false it will redirect user to login page
     * Else do anything
     * */
    public boolean checkLogin(){
        // Check login status
        if(!this.isUserLoggedIn()){

            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(mContext, MainActivity.class);

            // Closing all the Activities from stack
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            mContext.startActivity(i);

            return true;
        }
        return false;
    }

    /**
     * Get stored session data
     * */
    public HashMap<String, String> getUserDetails(){

        //Use hashmap to store user credentials
        HashMap<String, String> user = new HashMap<String, String>();

        // user name-
        user.put(KEY_USER_NAME, pref.getString(KEY_USER_NAME, null));

        // user email id
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));

        // return user
        return user;
    }

    /**
     * Clear session details
     * */
    public void logoutUser(){

        // Clearing all user data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Login Activity
        Intent i = new Intent(mContext, MainActivity.class);

        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        mContext.startActivity(i);
        //Flush all data of user
        flushAllData();
    }

    private void flushAllData() {

    }
    public String getRestaurantName(){
        return pref.getString(UserSessionManager.KEY_USER_NAME, "");
    }
    public String getUserName(){
        String name = "";
        name = pref.getString(UserSessionManager.KEY_USER_NAME, "");
        return name;
    }

    public String getCityStateCountryZipTextView(){
        return "";
    }
    // Check for login
    public boolean isUserLoggedIn(){
        return pref.getBoolean(IS_USER_LOGIN, false);
    }

    public String getEmail() {
        String email = "";
        email = pref.getString(UserSessionManager.KEY_EMAIL, "");
        return email;
    }

    public String getImagePath() {
        String imagePath = "";
        imagePath = pref.getString(UserSessionManager.KEY_IMAGE_SRC, "");
        return imagePath;
    }
}
