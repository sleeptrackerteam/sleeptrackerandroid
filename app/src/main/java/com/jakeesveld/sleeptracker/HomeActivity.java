package com.jakeesveld.sleeptracker;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements NewEntryFragment.OnFragmentInteractionListener, LoginFragment.OnFragmentInteractionListener {

    public static final String FRAGMENT_KEY = "New Entry";
    Context context;
    private TextView mTextMessage;
    public static SleepEntryDAO dao;
    public static UsersDAO usersDao;
    ArrayList<SleepEntry> entryList;
    HomeRecyclerListAdapter listAdapter;
    TextView textViewGreeting;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_settings:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_create:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_breakdown:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        context = this;
        textViewGreeting = findViewById(R.id.text_view_greeting);
        dao = new SleepEntryDAO();
        usersDao = new UsersDAO(context);
        entryList = new ArrayList<>();
        listAdapter = new HomeRecyclerListAdapter(entryList);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(listAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        String greetingText = "Hello, " + usersDao.getUsername();
        textViewGreeting.setText(greetingText);

        final FragmentManager fragmentManager = getSupportFragmentManager();

        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        findViewById(R.id.navigation_settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, SettingsActivity.class));
            }
        });

        findViewById(R.id.navigation_create).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction
                        .replace(R.id.container, new NewEntryFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        int userId = usersDao.getUserId();
        if (usersDao.getUserId() != 0) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    JSONObject userInfo = new JSONObject();
                    try {
                        userInfo.put("username", usersDao.getUsername());
                        userInfo.put("password", usersDao.getPassword());
                        dao.loginHandler(userInfo);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    final ArrayList<SleepEntry> daoEntryList = dao.getAllEntries();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            entryList.addAll(daoEntryList);
                            listAdapter.notifyDataSetChanged();
                            String greetingText = "Hello, " + usersDao.getUsername();
                            textViewGreeting.setText(greetingText);
                        }
                    });

                }
            }).start();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

/*    public void refreshList(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                final ArrayList<SleepEntry> daoEntryList = dao.getAllEntries();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        entryList = daoEntryList;
                        listAdapter.notifyDataSetChanged();
                    }
                });

            }
        }).start();
    }*/
}
