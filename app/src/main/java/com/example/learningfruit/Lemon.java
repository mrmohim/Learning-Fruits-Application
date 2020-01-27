package com.example.learningfruit;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;



public class Lemon extends Fragment {

    public Lemon() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_lemon, container, false);

        final ImageView imageView = root.findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.lemon);

        final TextView textView = root.findViewById(R.id.textView);
        textView.setText(R.string.lemon);

        final Button learnmore = root.findViewById(R.id.learn_more);
        learnmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://en.wikipedia.org/wiki/Lemon"));
                startActivity(intent);
            }
        });

        return root;
    }
}
