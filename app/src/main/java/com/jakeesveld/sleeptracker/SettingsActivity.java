package com.jakeesveld.sleeptracker;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity implements LoginFragment.OnFragmentInteractionListener{

    TextView login, logout, about, register;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        final FragmentManager fragmentManager = getSupportFragmentManager();
        login = findViewById(R.id.text_login);
        logout = findViewById(R.id.text_logout);
        about = findViewById(R.id.text_about);
        register = findViewById(R.id.text_register);
        context = this;
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginFragment fragment = new LoginFragment();
                fragment.show(fragmentManager, "Login");
                /*FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.layout_parent, new LoginFragment()).addToBackStack(null).commit();*/
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeActivity.dao.logoutHandler();
                Toast.makeText(context, "You have successfully logged out", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
