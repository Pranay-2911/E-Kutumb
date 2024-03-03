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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Add_Members_frag extends Fragment {

    TextInputLayout firstname,lastname,flat_no,no_of_members,member_password;
    MaterialButton reg_member;
    ApiInterface apiInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.fragment_add__members_frag,container,false);

        firstname =v.findViewById(R.id.firstname);
        lastname = v.findViewById(R.id.lastname);
        flat_no = v.findViewById(R.id.flat_no);
        no_of_members = v.findViewById(R.id.no_of_members);
        member_password = v.findViewById(R.id.member_password);

        reg_member = v.findViewById(R.id.reg_member);

        reg_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (firstname.getEditText().getText().toString().length() <= 2)
                {
                    firstname.setError("Firstname Must be Greater Than 2 Characters!");
                }
                else if (lastname.getEditText().getText().toString().length() <= 3)
                {
                    lastname.setError("Lastname Must be Greater Than At-least 2 Characters!");
                }
                else if (flat_no.getEditText().getText().toString().length() < 2)
                {
                    flat_no.setError("Appartment No. Must Contain At-least 3 Numbers");
                }
                else if (no_of_members.getEditText().getText().toString().length() < 0)
                {
                    no_of_members.setError("Enter Valid No.!");
                }
                else if (member_password.getEditText().getText().toString().length() <= 5)
                {
                    member_password.setError("Password Must be Greater Than 4 Characters or Numbers!");
                }

                else
                {
                    apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

                    Call<Register_Member_Model> call = apiInterface.registerMember(firstname.getEditText().getText().toString(),
                            lastname.getEditText().getText().toString(),flat_no.getEditText().getText().toString(),
                            no_of_members.getEditText().getText().toString(),member_password.getEditText().getText().toString());

                    call.enqueue(new Callback<Register_Member_Model>() {
                        @Override
                        public void onResponse(Call<Register_Member_Model> call, Response<Register_Member_Model> response) {
                            Toast.makeText(getContext(), "Successfully Added!", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Register_Member_Model> call, Throwable t) {

                        }
                    });

                    firstname.getEditText().getText().clear();
                    lastname.getEditText().getText().clear();
                    flat_no.getEditText().getText().clear();
                    no_of_members.getEditText().getText().clear();
                    member_password.getEditText().getText().clear();


                    Intent i = new Intent(getContext(),Chairman_dashboard.class);
                    startActivity(i);
                }
            }
        });

        return v;
    }
}