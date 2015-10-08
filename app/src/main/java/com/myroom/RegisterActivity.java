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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import cz.msebera.android.httpclient.Header;
import com.myroom.utils.CommonUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends ActionBarActivity {
    EditText fullName;
    EditText email;
    EditText password;
    EditText confrimPassword;
    Button register;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        progressDialog=new ProgressDialog(this);
        progressDialog.setIcon(R.drawable.ic_profile);
        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fadein);
        LinearLayout registerLayout=(LinearLayout)findViewById(R.id.linearLayoutRegister);
        registerLayout.startAnimation(fadeInAnimation);
        fullName=(EditText)findViewById(R.id.userName);
        email=(EditText)findViewById(R.id.registerEmail);
        password=(EditText)findViewById(R.id.registerPassword);
        confrimPassword=(EditText)findViewById(R.id.regitserConfrimPassword);
        register=(Button)findViewById(R.id.btnRegister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fullName.getText().toString()!="" || email.getText().toString()!="" ||
                        password.getText().toString()!="") {
                    if(password.getText().toString().equals(confrimPassword.getText().toString())){
                        String fname = CommonUtils.getFirstName(fullName.getText().toString());
                        String lname = CommonUtils.getLastName(fullName.getText().toString());
                        AsyncHttpClient client = new AsyncHttpClient();
                        RequestParams params = new RequestParams();
                        params.add("fname", fname);
                        params.add("lname", lname);
                        params.add("email", email.getText().toString());
                        params.add("password", password.getText().toString());
                        progressDialog.setTitle("Please wait..");
                        progressDialog.setMessage("Connecting to the server");
                        progressDialog.show();
                        client.post(CommonUtils.baseUrl + "/member/new_member.php", params, new JsonHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                super.onSuccess(statusCode, headers, response);
                                progressDialog.setMessage("Processing request");
                                try {
                                    if (response.getString("result").equals("success")) {
                                        progressDialog.setMessage("Registration Success");
                                        Intent homeIntent = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(homeIntent);
                                        progressDialog.dismiss();
                                        CommonUtils.infoToast(getApplicationContext(), "Welcome " + fullName.getText().toString());
                                    } else {
                                        CommonUtils.errorToast(getApplicationContext(),response.getString("message"));
                                        progressDialog.dismiss();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                                super.onFailure(statusCode, headers, responseString, throwable);
                                CommonUtils.errorToast(getApplicationContext(), "Cannot access the server,Please check your device connection");
                                progressDialog.dismiss();
                            }
                        });
                    }else CommonUtils.warningToast(getApplicationContext(),"Passwords not matching");

                }else CommonUtils.warningToast(getApplicationContext(),"Please fill all details");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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
