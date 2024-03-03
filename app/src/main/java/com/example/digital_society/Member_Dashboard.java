package com.example.digital_society;

import static com.example.digital_society.R.id.host_nav_main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Member_Dashboard extends AppCompatActivity {

    String id;

    long backpressedtime;
    ApiInterface apiInterface;
    List<Register_Member_Model> model;

    MyShared mysp;

    Toolbar mytoolbar;
    DrawerLayout drawerLayout;
    AppBarConfiguration appBarConfiguration;
    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_dashboard);

        mytoolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(mytoolbar);

        bottomNavigationView = findViewById(R.id.bottom_nb);
        navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawer_layout);

        appBarConfiguration = new AppBarConfiguration.Builder(R.id.mem_dashboard,R.id.mem_events,
                R.id.mem_Notice,R.id.mem_complaint,R.id.view_mem,R.id.mem_payments,R.id.mem_vote,R.id.settings_Member)
                .setDrawerLayout(drawerLayout)
                .build();

        NavController navController = Navigation.findNavController(this,host_nav_main);
        NavigationUI.setupActionBarWithNavController(this,navController,appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView,navController);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<Register_Member_Model>> getcall = apiInterface.getspecificmember(id);

        getcall.enqueue(new Callback<List<Register_Member_Model>>() {
            @Override
            public void onResponse(Call<List<Register_Member_Model>> call, Response<List<Register_Member_Model>> response) {
                model = response.body();
            }

            @Override
            public void onFailure(Call<List<Register_Member_Model>> call, Throwable t) {
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this,host_nav_main);
        return NavigationUI.navigateUp(navController,appBarConfiguration) || super.onSupportNavigateUp();
    }

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
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==R.id.logout)
        {

            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setTitle("Logout!")
                    .setMessage("Are You Sure?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            mysp = new MyShared(Member_Dashboard.this);
                            Toast.makeText(Member_Dashboard.this, "Successfully Logout", Toast.LENGTH_SHORT).show();
                            mysp.Isloggin(false);
                            mysp.cleardata();
                            Intent x = new Intent(Member_Dashboard.this,Member_Login.class);
                            finish();
                            startActivity(x);
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    })
                    .setCancelable(false)
                    .show();

        }
        return super.onOptionsItemSelected(item);
    }
}