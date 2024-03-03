package com.example.digital_society;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
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


public class mem_Notice extends Fragment {

    ApiInterface apiInterface;
    RecyclerView view_notice;
    Notice_Adapter adapter;
    List<Notice_Model> modelList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_mem_notice,container,false);

        view_notice = v.findViewById(R.id.view_notice);
        view_notice.setHasFixedSize(true);
        view_notice.setLayoutManager(new LinearLayoutManager(getContext()));

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        apiInterface.getNotice().enqueue(new Callback<List<Notice_Model>>() {
            @Override
            public void onResponse(Call<List<Notice_Model>> call, Response<List<Notice_Model>> response) {
                modelList = response.body();
                adapter = new Notice_Adapter(getContext(),modelList);
                view_notice.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Notice_Model>> call, Throwable t) {

            }
        });
        return v;
    }

}