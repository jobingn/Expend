package com.myroom;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import com.myroom.utils.CommonUtils;


public class NewItemActivity extends ActionBarActivity {
    EditText name;
    EditText quantity;
    EditText price;
    Button btnDone;
    ImageButton itemPic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);
        name= (EditText) findViewById(R.id.editTextItemName);
        quantity= (EditText) findViewById(R.id.editTextQuantity);
        price= (EditText) findViewById(R.id.editTextItemPrice);
        btnDone=(Button)findViewById(R.id.buttonDone);
        itemPic=(ImageButton)findViewById(R.id.imageButtonItem);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newItemIntent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(newItemIntent);
                CommonUtils.successToast(getApplicationContext(),"Item added Successfully");
            }
        });
        itemPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itemPicSelectionIntent=new Intent(getApplicationContext(),ItemPicSelectionActivity.class);
                startActivity(itemPicSelectionIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_item, menu);
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
