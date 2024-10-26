package com.log.mq;

import android.app.ActivityManager;
import android.content.Context;
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
    private Context mContext;
    private Preference Kill_pre;
    private SwitchPreference LauncherAD,LauncherSearch,installTG,colnerShow;
    public SettingFragement(Context mContext) {
        this.mContext=mContext;
    }
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.setting);
        Kill_pre=(Preference) findPreference("Kill_pre");
        LauncherAD=(SwitchPreference) findPreference("LauncherAD");
        LauncherSearch=(SwitchPreference) findPreference("LauncherSearch");
        installTG=(SwitchPreference) findPreference("installTG");
        colnerShow=(SwitchPreference) findPreference("colnerShow");
        Kill_pre.setOnPreferenceClickListener((preference -> {
            ActivityManager manager= (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
            manager.killBackgroundProcesses("com.mumu.launcher");
            manager.killBackgroundProcesses("com.mumu.store");
            manager.killBackgroundProcesses("com.android.packageinstaller");
            manager.killBackgroundProcesses("com.netease.mumu.cloner");
            return false;
        }));
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
        colnerShow.setOnPreferenceChangeListener((preference,value)->{
            Sp.putBoolen("colnerShow",(Boolean) value);
            return true;
        });
    }
}
