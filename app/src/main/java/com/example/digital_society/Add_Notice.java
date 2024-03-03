package com.example.digital_society;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Add_Notice extends Fragment {

    TextInputLayout r_notice;
    MaterialButton submit,shownotice;
    ApiInterface apiInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add__notice,container,false);

        r_notice = v.findViewById(R.id.r_notice);
        submit = v.findViewById(R.id.submit);
        shownotice = v.findViewById(R.id.shownotice);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (r_notice.getEditText().getText().toString().length() <= 2)
                {
                    Toast.makeText(getContext(), "Enter Valid Notice!", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

                    Call<Notice_Model> call = apiInterface.addNotice(r_notice.getEditText().getText().toString());

                    call.enqueue(new Callback<Notice_Model>() {
                        @Override
                        public void onResponse(Call<Notice_Model> call, Response<Notice_Model> response) {
                            Toast.makeText(getContext(), "Successfully Submitted!", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Notice_Model> call, Throwable t) {

                        }
                    });
                    r_notice.getEditText().getText().clear();

                    Intent i = new Intent(getContext(),Chairman_dashboard.class);
                    startActivity(i);
                }

            }
        });

        return v;
    }
}