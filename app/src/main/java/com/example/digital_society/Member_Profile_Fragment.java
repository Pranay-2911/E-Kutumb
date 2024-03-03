package com.example.digital_society;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Member_Profile_Fragment extends Fragment {

    ApiInterface apiInterface;
    List<Register_Member_Model> models;
    MyShared myShared;
    String id;
    TextView show_firstname,show_lastname,show_apartment_no,show_house_members;
    MaterialButton update_profile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_member_profile,container,false);

        show_firstname = v.findViewById(R.id.show_firstname);
        show_lastname = v.findViewById(R.id.show_lastname);
        show_apartment_no = v.findViewById(R.id.show_apartment_no);
        show_house_members = v.findViewById(R.id.show_house_members);
        update_profile = v.findViewById(R.id.update_profile);


        myShared = new MyShared(getContext());
        id = myShared.geLoggedId();

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<Register_Member_Model>> call = apiInterface.getspecificmember(id);

        call.enqueue(new Callback<List<Register_Member_Model>>() {
            @Override
            public void onResponse(Call<List<Register_Member_Model>> call, Response<List<Register_Member_Model>> response) {

                models = response.body();
                show_firstname.setText(models.get(0).getFirstname());
                show_lastname.setText(models.get(0).getLastname());
                show_apartment_no.setText(models.get(0).getFlat_no());
                show_house_members.setText(models.get(0).getNo_of_members());
            }

            @Override
            public void onFailure(Call<List<Register_Member_Model>> call, Throwable t) {

            }
        });

        update_profile.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {

                Edit_Member_Profile editMemberProfile = new Edit_Member_Profile();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.setReorderingAllowed(true);
                fragmentTransaction.replace(R.id.host_nav_main,editMemberProfile);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        return v;
    }

}