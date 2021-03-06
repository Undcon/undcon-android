package com.br.undcon.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.br.undcon.R;
import com.br.undcon.utils.UserCache;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.br.undcon.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private DrawerLayout drawerBinding;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setBindings();
        setMenuConfigs();
        setHeaderValues();
    }

    private void setHeaderValues() {
        View headerView = navigationView.getHeaderView(0);

        TextView userName = (TextView) headerView.findViewById(R.id.username);
        userName.setText(UserCache.getInstance().getLogin().getUser().getLogin() + "@"
            + UserCache.getInstance().getLogin().getTenant());

        TextView inventoryName = (TextView) headerView.findViewById(R.id.inventoryName);
        inventoryName.setText(UserCache.getInstance().getInventory().getLabel());
    }

    private void setBindings() {
        setSupportActionBar(binding.appBarMain.toolbar);
        drawerBinding = binding.drawerLayout;
        navigationView = binding.navView;
    }

    private void setMenuConfigs() {
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_sector, R.id.nav_home)
                .setDrawerLayout(drawerBinding)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}