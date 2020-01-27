package com.example.learningfruit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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


        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.home);

        Button login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                LayoutInflater li = LayoutInflater.from(MainActivity.this);
                View promptsView = li.inflate(R.layout.login, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        MainActivity.this);
                alertDialogBuilder.setView(promptsView);
                final EditText email = promptsView.findViewById(R.id.email);
                final EditText password = promptsView.findViewById(R.id.password);

                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Login",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {

                                        int check = 0;
                                        for(int i = 0; i < Data.userEmail.size(); i++) {

                                            if(email.getText().toString().equals( Data.userEmail.get(i) ) && password.getText().toString().equals( Data.userPassword.get(i) )){
                                                check = 1;

                                                Data.email = Data.userEmail.get(i);
                                                Data.name = Data.userName.get(i);
                                                break;
                                            }
                                        }

                                        if (email.getText().toString().length() == 0 || password.getText().toString().length() == 0 || check == 0) {
                                            Snackbar wronglogin = Snackbar.make(view, "Invalid email or password!", Snackbar.LENGTH_LONG);
                                            wronglogin.show();
                                        }
                                        else {
                                            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                                            startActivity(intent);
                                            MainActivity.super.finish();
                                        }
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        Button signup = findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                LayoutInflater li = LayoutInflater.from(MainActivity.this);
                View promptsView = li.inflate(R.layout.signup, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        MainActivity.this);
                alertDialogBuilder.setView(promptsView);
                final EditText name = promptsView.findViewById(R.id.name);
                final EditText email = promptsView.findViewById(R.id.email);
                final EditText password = promptsView.findViewById(R.id.password);

                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Signup",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {

                                        if (name.getText().toString().length() == 0 || email.getText().toString().length() == 0 || password.getText().toString().length() == 0) {
                                            Snackbar wrongsignup = Snackbar.make(view, "Every field must be required!", Snackbar.LENGTH_LONG);
                                            wrongsignup.show();
                                        }
                                        else {
                                            Data.userName.add(name.getText().toString());
                                            Data.userEmail.add(email.getText().toString());
                                            Data.userPassword.add(password.getText().toString());

                                            Data.email = email.getText().toString();
                                            Data.name = name.getText().toString();

                                            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                                            startActivity(intent);
                                            MainActivity.super.finish();
                                        }
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }

    //nnn
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
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

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
