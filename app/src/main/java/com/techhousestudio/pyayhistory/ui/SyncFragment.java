package com.techhousestudio.pyayhistory.ui;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.techhousestudio.pyayhistory.R;

public class SyncFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.sync, rootKey);
    }
}
