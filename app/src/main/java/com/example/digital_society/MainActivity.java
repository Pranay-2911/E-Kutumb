package com.example.digital_society;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView tvregister;
    EditText edemail,edpassword;
    MaterialButton login;

    ApiInterface apiInterface;
    long backpressedtime;
    List<MyModel> model;

    MyShared mysp;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edemail = findViewById(R.id.edemail);
        edpassword = findViewById(R.id.edpassword);
        tvregister = findViewById(R.id.tvregister);
        login = findViewById(R.id.login);

        mysp = new MyShared(MainActivity.this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(edemail.getText().toString()) || TextUtils.isEmpty(edpassword.getText().toString()))
                {
                    edemail.setError("Enter username!");
                    Toast.makeText(MainActivity.this, "Username & Password are required!", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

                    Call<List<MyModel>> getcall = apiInterface.Chairman_login(edemail.getText().toString(),edpassword.getText().toString());

                    getcall.enqueue(new Callback<List<MyModel>>()
                    {

                        @Override
                        public void onResponse(Call<List<MyModel>> call, Response<List<MyModel>> response) {

                            try {
                                if (response.body() != null)
                                {
                                    Log.d("mydata","data found");

                                    model = response.body();

                                    mysp.Isloggin(true);
                                    mysp.setPreference(String.valueOf(model.get(0).getId()),"Chairman");

                                    Intent intent = new Intent(MainActivity.this,Chairman_dashboard.class);
                                    intent.putExtra("id",model.get(0).getId());
                                    intent.putExtra("societyname",model.get(0).getSocietyname());
                                    startActivity(intent);
                                    Log.d("mydata","inside the success");
                                    Toast.makeText(MainActivity.this, "Welcome to E-Kutumb", Toast.LENGTH_SHORT).show();

                                    edemail.getText().clear();
                                    edpassword.getText().clear();
                                }

                            }
                            catch (Exception e)
                            {
                                Log.d("mydata","inside the exception - data not found ");
                                Toast.makeText(MainActivity.this, "Invalid Login!", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<List<MyModel>> call, Throwable t) {
                            Log.d("mydata","inside the failure");
                            Toast.makeText(MainActivity.this, "Throwable"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });

        tvregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,Chairman_Register.class);
                finish();
                startActivity(i);
            }
        });
    }
}