package com.example.digital_society;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.HashMap;

public class Select_Role extends AppCompatActivity {

    AppCompatSpinner  appCompatSpinner;
    MaterialButton submit_btn;
    long backpressedtime;

    String selectedRole;
    HashMap hm,hmdata;
    MyShared mysp;
    Boolean status;
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_role);

        appCompatSpinner = findViewById(R.id.spdata);
        submit_btn = findViewById(R.id.submit_btn);

        mysp = new MyShared(Select_Role.this);
        hm = mysp.checkLogin();
        hmdata = mysp.getdata();
        status = (Boolean) hm.get("status");
        user= (String) hmdata.get(MyShared.KEY_ROLE);
        Log.d("mydata","---> select role "+status);
        if(status)
        {
            Log.d("mydata", ""+(String) hmdata.get(MyShared.KEY_ROLE));

            if(user.equals("Chairman"))
            {
                Intent i=new Intent(Select_Role.this,Chairman_dashboard.class);
                finish();
                startActivity(i);
            }
            else
            {
                Log.d("mydata", "inside the member"+(String) hmdata.get(MyShared.KEY_ROLE));
                Intent i=new Intent(Select_Role.this,Member_Dashboard.class);
                finish();
                startActivity(i);
            }

        }

        appCompatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedRole = (String) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (selectedRole.equals("Society Member"))
                {
                    Intent j = new Intent(Select_Role.this,Member_Login.class);
                    startActivity(j);
                }
                else if (selectedRole.equals("Chairman"))
                {
                    Intent y = new Intent(Select_Role.this,MainActivity.class);
                    startActivity(y);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {

        if (backpressedtime + 2000 > System.currentTimeMillis())
        {
            super.onBackPressed();
            finish();
            return;
        }else {
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        backpressedtime = System.currentTimeMillis();
    }
}