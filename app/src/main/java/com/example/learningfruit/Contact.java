package com.example.learningfruit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Contact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Contact.this.setTitle("Help & feedback");
        Button post_message = findViewById(R.id.post_message);

        post_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void sendEmail() {
        Log.i("Send email", "");
        EditText your_name = findViewById(R.id.your_name);
        EditText your_email = findViewById(R.id.your_email);
        EditText your_subject = findViewById(R.id.your_subject);
        EditText your_message = findViewById(R.id.your_message);
        String yourname = your_name.getText().toString();
        String yoursubject = your_subject.getText().toString();
        String yourmessage = your_message.getText().toString();
        String TO = "moshiur.r@samsung.com";
        String CC = "";
        yoursubject = yoursubject + " - " + yourname;

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, yoursubject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, yourmessage);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            Log.i("Finished sending email!", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(Contact.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
