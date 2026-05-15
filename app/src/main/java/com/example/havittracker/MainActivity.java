package com.example.havittracker;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.havittracker.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private int count = 0;

    private static final String HABIT_PREF = "HabitTrackingPreference";
    private static final String KEY_COUNT = "daily_count";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                count++;

                GetUI();

            }
        });

        binding.btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                count = 0;
                GetUI();

            }
        });

    }


    // Get The Updated Count UI
    private void GetUI(){
        binding.tvCounter.setText(String.valueOf(count));
    }

    // Save the Count Result
    private void SaveCount(){

        SharedPreferences sharedPreferences = getSharedPreferences(HABIT_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(KEY_COUNT, count);
        editor.apply();
        // ❌ editor.commit() -> commit() blocked the Main thread which can causes of app lagging. apply() is the best practice.

    }

    // Load Saved Data
    private void LoadSavedData(){

        SharedPreferences sharedPreferences = getSharedPreferences(HABIT_PREF, MODE_PRIVATE);

        // If sharedPreferences cannot find any value in the KEY_COUNT than it set the default value 0.
        count = sharedPreferences.getInt(KEY_COUNT, 0);

        // Update th UI
        GetUI();

    }

}