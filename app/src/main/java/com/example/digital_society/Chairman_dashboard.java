package com.example.digital_society;

import static androidx.navigation.ui.NavigationUI.setupActionBarWithNavController;
import static com.example.digital_society.R.id.host_nav_chairman;
import static com.example.digital_society.R.id.tvs_display;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Chairman_dashboard extends AppCompatActivity{

    String id;
    Toolbar toolbar;

    ApiInterface apiInterface;
    long backpressedtime;

    List<MyModel> model;
    String name;

    MyShared mysp;
    HashMap hm;

    DrawerLayout drawerLayout;
    AppBarConfiguration appBarConfiguration;
    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;


//    @Override
//    public void onBackPressed() {
//
//        if (backpressedtime + 2000 > System.currentTimeMillis())
//        {
//            super.onBackPressed();
//            finish();
//            return;
//        }else {
//            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();
//        }
//        backpressedtime = System.currentTimeMillis();
//    }

    @SuppressLint("UseSupportActionBar")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chairman_dashboard);



        toolbar = findViewById(R.id.chairman_toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitle("Welcome to, E-Kutumb");


        bottomNavigationView = findViewById(R.id.chairman_bottom_nb);
        navigationView = findViewById(R.id.chairman_nav_view);
        drawerLayout = findViewById(R.id.draw_layout);


        appBarConfiguration = new AppBarConfiguration.Builder(R.id.chairman_dash_frag,R.id.add_Members_frag,R.id.show_Members_frag
                ,R.id.add__Notice,R.id.chairman_Show_Notice,R.id.settings_Chairman)
                .setDrawerLayout(drawerLayout)
                .build();

        NavController navController = Navigation.findNavController(this,host_nav_chairman);
        setupActionBarWithNavController(this,navController,appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView,navController);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);



        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<MyModel>> getcall = apiInterface.get_societyname(id);

        getcall.enqueue(new Callback<List<MyModel>>() {
            @Override
            public void onResponse(Call<List<MyModel>> call, Response<List<MyModel>> response) {

                model = response.body();

            }

            @Override
            public void onFailure(Call<List<MyModel>> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this,R.id.host_nav_chairman);

        return NavigationUI.navigateUp(navController,appBarConfiguration) || super.onSupportNavigateUp();
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
                            mysp = new MyShared(Chairman_dashboard.this);
                            mysp.Isloggin(false);
                            mysp.cleardata();
                            Toast.makeText(Chairman_dashboard.this, "Successfully Logout!", Toast.LENGTH_SHORT).show();
                            Intent y = new Intent(Chairman_dashboard.this,MainActivity.class);

                            finish();
                            startActivity(y);
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