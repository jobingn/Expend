package com.myroom;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.myroom.fragments.Accounts;
import com.myroom.fragments.Group;
import com.myroom.fragments.Messenger;
import com.myroom.fragments.Purchases;
import com.myroom.fragments.Settings;
import com.myroom.fragments.Statistics;
import com.myroom.utils.CommonUtils;
import com.myroom.utils.UserSessionManager;

public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {

    FragmentDrawer drawerFragment;
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UserSessionManager sessionManager=new UserSessionManager(getApplicationContext());
        if(sessionManager.isUserLoggedIn()==false){
            Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
            this.finish();
            startActivity(loginIntent);
        }
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Expend");
        toolbar.setBackgroundColor(Color.parseColor("#607D8B"));
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        drawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);
        drawerFragment = (FragmentDrawer)getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, drawerLayout, toolbar);
        drawerFragment.setDrawerListener(this);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, new Group()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }
        if (id == android.R.id.home) {
          drawerFragment.openNavigationDrawer();
          return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onDrawerItemSelected(View view, int position) {
        Fragment fragment = null;
        switch (position){
            case 0: fragment=new Group(); break;
            case 1: fragment=new Accounts(); break;
            case 2: fragment=new Purchases(); break;
            case 3: fragment=new Messenger(); break;
            case 4: fragment=new Statistics(); break;
            case 5: fragment=new Settings(); break;
            case 6: UserSessionManager sessionManager=new UserSessionManager(getApplicationContext());
                    sessionManager.logoutUser();
                    Intent loginIntent=new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(loginIntent);
                    CommonUtils.infoToast(getApplicationContext(), "Logout Successfully");
                    break;
        }
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragment).commit();
        }
    }
}
