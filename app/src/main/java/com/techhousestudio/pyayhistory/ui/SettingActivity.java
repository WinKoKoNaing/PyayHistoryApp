package com.techhousestudio.pyayhistory.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.techhousestudio.pyayhistory.R;

import timber.log.Timber;

public class SettingActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("App Setting");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportFragmentManager().beginTransaction().add(R.id.settingContainer, new SettingFragment()).commit();


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this /* Activity context */);
        String colorIndex = sharedPreferences.getString("colors", "0");
        changeBackgroundColor(colorIndex);
    }

    @Override
    public void onResume() {
        super.onResume();
        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                supportFinishAfterTransition();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        if (key.equals("colors")) {
            changeBackgroundColor(sharedPreferences.getString("colors", "0"));
        }
    }

    void changeBackgroundColor(String colorIndex) {
        LinearLayout settingLayout = findViewById(R.id.settingLayout);
        switch (colorIndex) {
            case "0":
                settingLayout.setBackgroundColor(Color.RED);
                break;
            case "1":
                settingLayout.setBackgroundColor(Color.GREEN);
                break;

        }
    }
}
