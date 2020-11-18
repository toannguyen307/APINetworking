package com.example.showcase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.showcase.view.PostFragment;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        showScreenPost();
    }

    private void showScreenPost() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, new PostFragment(), PostFragment.class.getSimpleName())
                .commit();

    }
}