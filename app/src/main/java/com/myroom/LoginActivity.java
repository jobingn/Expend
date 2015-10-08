package com.myroom;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.myroom.utils.CommonUtils;
import com.myroom.utils.UserSessionManager;
import org.json.JSONException;
import org.json.JSONObject;
import cz.msebera.android.httpclient.Header;



public class LoginActivity extends ActionBarActivity {
    EditText email;
    EditText password;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressDialog=new ProgressDialog(this);
        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fadein);
        LinearLayout loginLayout=(LinearLayout)findViewById(R.id.linearLayoutLogin);
        loginLayout.startAnimation(fadeInAnimation);
        email=(EditText)findViewById(R.id.loginEmail);
        password=(EditText)findViewById(R.id.loginPassword);
    }
    public void navigatetoRegisterActivity(View view){
        Intent registerIntent=new Intent(getApplicationContext(),RegisterActivity.class);
        startActivity(registerIntent);
    }
    public void loginUser(View view){
        if(email.getText().toString()=="" && password.getText().toString()=="") {
            CommonUtils.warningToast(getApplicationContext(), "Please fill all fields");
        }else{
            AsyncHttpClient client = new AsyncHttpClient();
            RequestParams params = new RequestParams();
            params.put("email", email.getText().toString());
            params.put("password", password.getText().toString());
            progressDialog.setTitle("Please wait..");
            progressDialog.setMessage("Connecting to the server");
            progressDialog.show();
            client.post(CommonUtils.baseUrl + "/member/login.php", params, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    try {
                        progressDialog.setMessage("Checking user details..");
                        if(response.getString("response").equals("success")) {
                            progressDialog.dismiss();
                            UserSessionManager sessionManager=new UserSessionManager(getApplicationContext());
                            sessionManager.createUserLoginSession(response.getString("fname")+" "+response.getString("lname")
                                    ,response.getString("email")
                                    ,response.getString("userid")
                                    ,response.getString("groupid")
                                    ,response.getString("picpath")
                            );
                            Intent homeIntent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(homeIntent);
                            CommonUtils.infoToast(getApplicationContext(), "Welcome "+
                                    response.getString("fname")+" "+response.getString("lname"));
                        }else {
                            CommonUtils.errorToast(getApplicationContext(),"Login Failed");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    super.onFailure(statusCode, headers, responseString, throwable);
                    CommonUtils.errorToast(getApplicationContext(), statusCode+"Cannot access the server,Please check your device connection");
                    progressDialog.dismiss();
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
