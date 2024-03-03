package com.example.digital_society;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class mem_dashboard extends Fragment {

    RecyclerView rvdata;
    List<Mem_Dash_Model> list;

    ApiInterface apiInterface;
    List<Register_Member_Model> models;
    MyShared myShared;
    TextView Mem_name_show,Mem_flat_show;
    String id;
    String name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View v = inflater.inflate(R.layout.fragment_mem_dashboard,container,false);

       Mem_name_show = v.findViewById(R.id.Mem_name_show);
       Mem_flat_show = v.findViewById(R.id.Mem_flat_show);

       myShared = new MyShared(getContext());
       id = myShared.geLoggedId();

       apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

       Call<List<Register_Member_Model>> call = apiInterface.getspecificmember(id);

       call.enqueue(new Callback<List<Register_Member_Model>>() {
           @Override
           public void onResponse(Call<List<Register_Member_Model>> call, Response<List<Register_Member_Model>> response) {

               models = response.body();
               name = models.get(0).getFirstname()+" "+models.get(0).getLastname();
               Mem_name_show.setText(name);
               Mem_flat_show.setText("Apartment No:- "+models.get(0).getFlat_no());
           }

           @Override
           public void onFailure(Call<List<Register_Member_Model>> call, Throwable t) {

           }
       });


       rvdata = v.findViewById(R.id.rvdata);
       rvdata.setHasFixedSize(true);
       rvdata.setLayoutManager(new LinearLayoutManager(getContext()));
       rvdata.setAdapter(new Mem_Dash_Adapter(getContext(), (ArrayList<Mem_Dash_Model>) initdata()));
       return  v;

    }

    private List<Mem_Dash_Model> initdata() {

        ArrayList<Mem_Dash_Model> iteamList = new ArrayList<>();

        iteamList.add(new Mem_Dash_Model(R.mipmap.mem_notification,"E-Notice"));
        iteamList.add(new Mem_Dash_Model(R.mipmap.vote,"Polls"));
        iteamList.add(new Mem_Dash_Model(R.mipmap.events,"View E-Bills"));
        iteamList.add(new Mem_Dash_Model(R.mipmap.show_mem,"View Members"));
        iteamList.add(new Mem_Dash_Model(R.mipmap.payments,"Payments"));
        iteamList.add(new Mem_Dash_Model(R.mipmap.picon,"Complaint"));
        //iteamList.add(new Mem_Dash_Model(R.mipmap.google_play,"Rate-Us"));

        return  iteamList;
    }
}