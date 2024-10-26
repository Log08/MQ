package com.log.mq;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import androidx.annotation.Nullable;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;

/**
 * Created by Log husy on 2024/10/26.
 *
 * @link https://github.com/Log08
 * @Gdescription:
 */
public class SettingFragement extends PreferenceFragmentCompat{
    private SwitchPreference LauncherAD,LauncherSearch,installTG;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.setting);
        LauncherAD=(SwitchPreference) findPreference("LauncherAD");
        LauncherSearch=(SwitchPreference) findPreference("LauncherSearch");
        installTG=(SwitchPreference) findPreference("installTG");
        LauncherAD.setOnPreferenceChangeListener((preference,value)->{
            Sp.putBoolen("LauncherAD",(Boolean) value);
            return true;
        });
        LauncherSearch.setOnPreferenceChangeListener((preference,value)->{
            Sp.putBoolen("LauncherSearch",(Boolean) value);
            return true;
        });
        installTG.setOnPreferenceChangeListener((preference,value)->{
            Sp.putBoolen("installTG",(Boolean) value);
            return true;
        });
    }
}
