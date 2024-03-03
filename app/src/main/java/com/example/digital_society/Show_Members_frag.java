package com.example.digital_society;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Show_Members_frag extends Fragment {

    ApiInterface apiInterface;
    RecyclerView recycler;
    MyAdapter myAdapter;
    List<Register_Member_Model> modelList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.fragment_show__members_frag,container,false);

       recycler = v.findViewById(R.id.recycler);
       recycler.setHasFixedSize(true);
       recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        apiInterface.getallmember().enqueue(new Callback<List<Register_Member_Model>>() {
            @Override
            public void onResponse(Call<List<Register_Member_Model>> call, Response<List<Register_Member_Model>> response) {

                modelList = response.body();
                myAdapter = new MyAdapter(getContext(),modelList);
                recycler.setAdapter(myAdapter);

            }

            @Override
            public void onFailure(Call<List<Register_Member_Model>> call, Throwable t) {

            }
        });
        return v;

    }

}