package com.example.digital_society;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Member_Login extends AppCompatActivity {

    TextInputLayout apartment_no,mem_pass;
    MaterialButton btn_member;
    long backpressedtime;
    ApiInterface apiInterface;
    Cursor c;

    List<Register_Member_Model> models;

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
        setContentView(R.layout.activity_member_login);

        apartment_no = findViewById(R.id.apartment_no);
        mem_pass = findViewById(R.id.mem_pass);
        btn_member = findViewById(R.id.btn_member);

        mysp = new MyShared(Member_Login.this);

        btn_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               if (TextUtils.isEmpty(apartment_no.getEditText().getText().toString()) || TextUtils.isEmpty(mem_pass.getEditText().getText().toString()))
               {
                   apartment_no.setError("Enter Correct Apartment No.");
                   Toast.makeText(Member_Login.this, "Fields are required!", Toast.LENGTH_SHORT).show();
                 }

                else {
                   apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

                    Call<List<Register_Member_Model>> getcall = apiInterface.Member_Login(apartment_no.getEditText().getText().toString(),
                            mem_pass.getEditText().getText().toString());

                    getcall.enqueue(new Callback<List<Register_Member_Model>>() {
                        @Override
                        public void onResponse(Call<List<Register_Member_Model>> call, Response<List<Register_Member_Model>> response) {

                           try {
                               if (response.body() != null) {
                                   models = response.body();
                                   mysp.setPreference(String.valueOf(models.get(0).getId()), "Member");
                                   mysp.Isloggin(true);

                                   Intent y = new Intent(Member_Login.this, Member_Dashboard.class);
                                   y.putExtra("id", models.get(0).getId());
                                   y.putExtra("firstname", models.get(0).getFirstname());
                                   y.putExtra("lastname", models.get(0).getLastname());
                                   y.putExtra("flat_no", models.get(0).getFlat_no());
                                   startActivity(y);
                                   Toast.makeText(Member_Login.this, "Welcome to mSociety", Toast.LENGTH_SHORT).show();

                                   apartment_no.getEditText().getText().clear();
                                   mem_pass.getEditText().getText().clear();
                               }
                           }
                           catch (Exception e)
                           {
                               Toast.makeText(Member_Login.this, "Unable to fetch details!", Toast.LENGTH_SHORT).show();
                           }

                        }

                        @Override
                        public void onFailure(Call<List<Register_Member_Model>> call, Throwable t) {
                            Toast.makeText(Member_Login.this, "Throwable"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}