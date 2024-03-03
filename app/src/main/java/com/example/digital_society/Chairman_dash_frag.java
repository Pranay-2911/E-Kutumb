package com.example.digital_society;

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


public class Chairman_dash_frag extends Fragment {

    RecyclerView chairman_rvdata;
    List<Chairman_Dash_Model> list;

    List<MyModel> models;

    ApiInterface apiInterface;
    TextView tvdisplay;
    MyShared mysp;
    String id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chairman_dash_frag,container,false);
        tvdisplay = v.findViewById(R.id.tvs_display);

        mysp = new MyShared(getContext());
        id = mysp.geLoggedId();



        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<MyModel>> call = apiInterface.get_societyname(id);

        call.enqueue(new Callback<List<MyModel>>() {
            @Override
            public void onResponse(Call<List<MyModel>> call, Response<List<MyModel>> response) {
                models = response.body();

                tvdisplay.setText(models.get(0).getSocietyname());
            }

            @Override
            public void onFailure(Call<List<MyModel>> call, Throwable t) {

            }
        });




        chairman_rvdata = v.findViewById(R.id.chairman_rvdata);
        chairman_rvdata.setHasFixedSize(true);
        chairman_rvdata.setLayoutManager(new LinearLayoutManager(getContext()));
        chairman_rvdata.setAdapter(new Chairman_Dash_Adapter(getContext(),init_chairman_data()));
        return v;
    }

    private List<Chairman_Dash_Model> init_chairman_data()
    {
        ArrayList<Chairman_Dash_Model> iteamList = new ArrayList<>();

        iteamList.add(new Chairman_Dash_Model(R.mipmap.notification,"Add Notice"));
        iteamList.add(new Chairman_Dash_Model(R.mipmap.complaint,"Polling"));
        iteamList.add(new Chairman_Dash_Model(R.mipmap.events,"Generate Bills"));
        iteamList.add(new Chairman_Dash_Model(R.mipmap.members,"Add Members"));
        iteamList.add(new Chairman_Dash_Model(R.mipmap.person,"Show Members"));
        iteamList.add(new Chairman_Dash_Model(R.mipmap.notification,"Show Notice"));

        return  iteamList;
    }
}