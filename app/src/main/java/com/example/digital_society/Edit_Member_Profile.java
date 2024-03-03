package com.example.digital_society;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Edit_Member_Profile extends Fragment {

    ApiInterface apiInterface;
    List<Register_Member_Model> models;
    MyShared myShared;
    String id;

    EditText edit_firstname,edit_lastname;
    MaterialButton edit_profile;
    TextView tv5,tv6;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_edit__member__profile,container,false);

        edit_firstname = v.findViewById(R.id.edit_firstname);
        edit_lastname = v.findViewById(R.id.edit_lastname);
        edit_profile = v.findViewById(R.id.edit_profile);
        tv5 = v.findViewById(R.id.tv5);
        tv6 = v.findViewById(R.id.tv6);

        myShared = new MyShared(getContext());
        id = myShared.geLoggedId();
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<Register_Member_Model>> call = apiInterface.getspecificmember(id);

        call.enqueue(new Callback<List<Register_Member_Model>>() {
            @Override
            public void onResponse(Call<List<Register_Member_Model>> call, Response<List<Register_Member_Model>> response) {

                models = response.body();
                edit_firstname.setText(models.get(0).getFirstname());
                edit_lastname.setText(models.get(0).getLastname());
                tv5.setText(models.get(0).getFlat_no());
                tv6.setText(models.get(0).getNo_of_members());
            }

            @Override
            public void onFailure(Call<List<Register_Member_Model>> call, Throwable t) {

            }
        });

        tv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "This Field Can't be updated!", Toast.LENGTH_SHORT).show();
            }
        });
        tv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "This Field Can't be updated!", Toast.LENGTH_SHORT).show();
            }
        });

        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getContext(),Member_Dashboard.class);
                startActivity(i);

               Call<List<Register_Member_Model>> call1 = apiInterface.updateProfile(edit_firstname.getText().toString(),edit_lastname.getText().toString());
               call1.enqueue(new Callback<List<Register_Member_Model>>() {
                   @Override
                   public void onResponse(Call<List<Register_Member_Model>> call, Response<List<Register_Member_Model>> response) {
                       ProgressDialog pd = new ProgressDialog(getContext());
                       pd.setMessage("Updating...");
                       pd.show();
                       Toast.makeText(getContext(), "Successfully Updated!", Toast.LENGTH_SHORT).show();
                       pd.dismiss();

                   }

                   @Override
                   public void onFailure(Call<List<Register_Member_Model>> call, Throwable t) {

                   }
               });

            }
        });

        return v;
    }
}