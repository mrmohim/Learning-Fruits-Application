package com.example.learningfruit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.Stack;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public int val;
    Stack<Integer> stack = new Stack<>();

    TextView setUsername, setEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //user set
        NavigationView navView = findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(this);
        View header=navView.getHeaderView(0);

        setUsername = header.findViewById(R.id.setUsername);
        setEmail = header.findViewById(R.id.setEmail);

        setEmail.setText(Data.email);
        setUsername.setText(Data.name);

        //nnn
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        stack.push(0);

        Toast logincomplete = Toast.makeText(this, "Login Complete!", Toast.LENGTH_SHORT);
        logincomplete.show();


        final MediaPlayer apple = MediaPlayer.create(this, R.raw.apple);
        final MediaPlayer orange = MediaPlayer.create(this, R.raw.orange);
        final MediaPlayer mango = MediaPlayer.create(this, R.raw.mango);
        final MediaPlayer lemon = MediaPlayer.create(this, R.raw.lemon);
        MediaPlayer welcome = MediaPlayer.create(this,R.raw.welcome);
        welcome.start();

        /*
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
            }
        }, 3000);
        */

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.apple));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.orange));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.mango));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.lemon));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(
                new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        val = tab.getPosition();

                        if (stack.empty()) {
                            stack.push(val);
                        } else if (stack.peek() != val) {
                            stack.push(val);
                        }

                        viewPager.setCurrentItem(tab.getPosition());
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                    }
                });

        FloatingActionButton fbs = findViewById(R.id.pronunciation);
        fbs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (val == 0) {
                    apple.start();
                } else if (val == 1) {
                    orange.start();
                } else if (val == 2) {
                    mango.start();
                } else {
                    lemon.start();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        stack.pop();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else if (stack.empty()) {
            super.onBackPressed();
        }
        else {
            ViewPager viewPager = findViewById(R.id.pager);
            viewPager.setCurrentItem(stack.peek());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_home:
                Intent intent1 = new Intent(this, Home.class);
                startActivity(intent1);
                return true;
            case R.id.menu_setting:
                Intent intent2 = new Intent(this, Setting.class);
                startActivity(intent2);
                return true;
            case R.id.menu_about:
                Intent intent3 = new Intent(this, About.class);
                startActivity(intent3);
                return true;
            case R.id.menu_contact:
                Intent intent4 = new Intent(this, Contact.class);
                startActivity(intent4);
                return true;
            case R.id.menu_share:
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Samsung R&D Institute Bangladesh Ltd.\nFollow us on our official facebook page.\nLink: https://www.facebook.com/Samsung.SRBD/";
                String shareSub = "Samsung R&D Institute Bangladesh Ltd (SRBD)";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "SRBD"));
                return true;
            default:
                //Nothing
        }
        return super.onOptionsItemSelected(item);
    }

    //nnn
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent intent1 = new Intent(this, Home.class);
            startActivity(intent1);
        } else if (id == R.id.nav_profile) {
            Toast.makeText(this, "Profile info not available now", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_settings) {
            Intent intent2 = new Intent(this, Setting.class);
            startActivity(intent2);
        } else if (id == R.id.nav_share) {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = "Samsung R&D Institute Bangladesh Ltd.\nFollow us on our official facebook page.\nLink: https://www.facebook.com/Samsung.SRBD/";
            String shareSub = "Samsung R&D Institute Bangladesh Ltd (SRBD)";
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, "SRBD"));
        } else if (id == R.id.nav_help) {
            Intent intent4 = new Intent(this, Contact.class);
            startActivity(intent4);
        }
        else if (id == R.id.nav_logout) {
            Intent intent5 = new Intent(this, MainActivity.class);
            startActivity(intent5);
            Main2Activity.super.finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
