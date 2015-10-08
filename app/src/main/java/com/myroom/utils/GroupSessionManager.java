package com.myroom.utils;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import com.myroom.MainActivity;
import java.util.HashMap;
/**
 * Created by Jobin on Sep 15.
 */
public class GroupSessionManager {
    // Shared Preferences reference
    SharedPreferences pref;
    // Editor reference for Shared preferences
    SharedPreferences.Editor editor;
    // Context
    Context mContext;
    // Shared pref mode
    int PRIVATE_MODE = 0;
    // Sharedpref file name
    private static final String PREFER_NAME = "ExpendGroupPreference";
    // All Shared Preferences Keys
    private static final String IS_USER_INSIDE_GROUP = "IsUserInsideGroup";
    // User name (make variable public to access from outside)
    public static final String KEY_GROUP_ID = "groupId";
    public static final String KEY_GROUP_NAME = "groupName";
    public static final String KEY_MEMBER_COUNT = "memberCount";
    public static final String KEY_IMAGE_SRC = "imagePath";

    public GroupSessionManager()
    {
    }
    // Constructor
    public GroupSessionManager(Context context){
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
    public void createGroupSwitchSession(String name,String groupid,String picpath,String memberCount){
        // Storing login value as TRUE
        editor.putBoolean(IS_USER_INSIDE_GROUP, true);
        // Storing group name in pref
        editor.putString(KEY_GROUP_NAME, name);
        // Storing groupid in pref
        editor.putString(KEY_GROUP_ID, groupid);
        // Storing image path in pref
        editor.putString(KEY_IMAGE_SRC, picpath);
        // Storing member count in pref
        editor.putString(KEY_MEMBER_COUNT, memberCount);
        // commit changes
        editor.commit();
    }

    /**
     * Check login method will check user login status
     * If false it will redirect user to login page
     * Else do anything
     * */
    public boolean checkUserInsideGroup(){
        // Check login status
        if(!this.isUserInsideGroup()){

            // load group details to application
            // populate sqlite group tables
            return true;
        }
        return false;
    }

    /**
     * Get stored session data
     * */
    public HashMap<String, String> getGroupDetails(){
        //Use hashmap to store user credentials
        HashMap<String, String> group = new HashMap<String, String>();
        // group name-
        group.put(KEY_GROUP_NAME, pref.getString(KEY_GROUP_NAME, null));
        // group id
        group.put(KEY_GROUP_ID, pref.getString(KEY_GROUP_ID, null));
        // group image path
        group.put(KEY_IMAGE_SRC, pref.getString(KEY_IMAGE_SRC, null));
        // return user
        return group;
    }

    /**
     * Clear session details
     * */
    public void switchGroup(){

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

    public String getGroupName(){
        String name = "";
        name = pref.getString(GroupSessionManager.KEY_GROUP_NAME, "");
        return name;
    }


    // Check for group
    public boolean isUserInsideGroup(){
        return pref.getBoolean(IS_USER_INSIDE_GROUP, false);
    }

    public String getImagePath() {
        String imagePath = "";
        imagePath = pref.getString(GroupSessionManager.KEY_IMAGE_SRC, "");
        return imagePath;
    }
}

