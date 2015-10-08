package com.myroom.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.myroom.R;

/**
 * Created by Jobin on Jun 28.
 */
public class CommonUtils {
    public static String baseUrl="http://roomassist.hol.es/myroom/";

    public static void infoToast(Context context,String msg){
     LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
     View layout = inflater.inflate(R.layout.infotoast_layout,null);
     Toast toast = new Toast(context);
     toast.setDuration(Toast.LENGTH_LONG);
     TextView tv =(TextView)layout.findViewById(R.id.textView1);
     tv.setText(msg);
     toast.setView(layout);
     toast.show();
 }
 public static void warningToast(Context context,String msg){
     LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
     View layout = inflater.inflate(R.layout.warningtoast_layout,null);
     Toast toast = new Toast(context);
     toast.setDuration(Toast.LENGTH_LONG);
     TextView tv =(TextView)layout.findViewById(R.id.textView1);
     tv.setText(msg);
     toast.setView(layout);
     toast.show();
 }
 public static void successToast(Context context,String msg){
     LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
     View layout = inflater.inflate(R.layout.successtoast_layout,null);
     Toast toast = new Toast(context);
     toast.setDuration(Toast.LENGTH_LONG);
     TextView tv =(TextView)layout.findViewById(R.id.textView1);
     tv.setText(msg);
     toast.setView(layout);
     toast.show();

 }
 public static void errorToast(Context context,String msg){
     LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
     View layout = inflater.inflate(R.layout.errortoast_layout,null);
     Toast toast = new Toast(context);
     toast.setDuration(Toast.LENGTH_LONG);
     TextView tv =(TextView)layout.findViewById(R.id.textView1);
     tv.setText(msg);
     toast.setView(layout);
     toast.show();
 }

    public static String getFirstName(String name) {
        int start = name.indexOf(' ');
        int end = name.lastIndexOf(' ');

        String firstName = "";
        String middleName = "";
        String lastName = "";

        if (start >= 0) {
            firstName = name.substring(0, start);
            if (end > start)
                middleName = name.substring(start + 1, end);
            lastName = name.substring(end + 1, name.length());
        }

        return firstName;
    }

    public static String getLastName(String name) {
        int start = name.indexOf(' ');
        int end = name.lastIndexOf(' ');

        String firstName = "";
        String middleName = "";
        String lastName = "";

        if (start >= 0) {
            firstName = name.substring(0, start);
            if (end > start)
                middleName = name.substring(start + 1, end);
            lastName = name.substring(end + 1, name.length());
        }
        return lastName;
    }
    /** Check for connection **/
    public static boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(
                Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifiNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiNetwork != null && wifiNetwork.isConnected()) {
            return true;
        }

        NetworkInfo mobileNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (mobileNetwork != null && mobileNetwork.isConnected()) {
            return true;
        }

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            return true;
        }
        return false;
    }
}
