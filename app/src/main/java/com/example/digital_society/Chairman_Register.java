package com.example.digital_society;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Chairman_Register extends AppCompatActivity {

    TextInputLayout societyname,reg_username,reg_password;
    MaterialButton regbtn;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chairman_register);

        societyname = findViewById(R.id.societyname);
        reg_username = findViewById(R.id.reg_username);
        reg_password = findViewById(R.id.reg_password);

        regbtn = findViewById(R.id.regbtn);

        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (societyname.getEditText().getText().toString().length() <= 7)
                {
                    societyname.setError("Society Name Must be Greater Than 7 Characters!");
                }
                else if (reg_username.getEditText().getText().toString().length() <= 5)
                {
                    reg_username.setError("Username Must Contain At-least 5 Characters or Numbers");
                }
                else if (reg_password.getEditText().getText().toString().length() <= 6)
                {
                    reg_password.setError("Password Must Contain At-least 6 Characters or Numbers");
                }

                else
                {
                    apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

                    Call<MyModel> call = apiInterface.registerData(societyname.getEditText().getText().toString(),reg_username.getEditText().getText().toString(),reg_password.getEditText().getText().toString());

                    call.enqueue(new Callback<MyModel>() {
                        @Override
                        public void onResponse(Call<MyModel> call, Response<MyModel> response) {
                            Toast.makeText(Chairman_Register.this, "Successfully Registerd! Please Login", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<MyModel> call, Throwable t) {

                        }
                    });

                    Intent i = new Intent(Chairman_Register.this,MainActivity.class);
                    finish();
                    startActivity(i);
                }
            }
        });

    }
}