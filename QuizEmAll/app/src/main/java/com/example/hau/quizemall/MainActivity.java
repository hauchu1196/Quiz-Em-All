package com.example.hau.quizemall;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hau.quizemall.fragments.HomeFragment;
import com.example.hau.quizemall.fragments.OpenFragmentEvent;
import com.example.hau.quizemall.managers.DbHelper;
import com.example.hau.quizemall.managers.Preferences;
import com.example.hau.quizemall.utils.Font;
import com.example.hau.quizemall.utils.Setting;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends AppCompatActivity {

    public int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Font.init(this);
        Setting.init(this);
        DbHelper.init(this);
        Preferences.init(this);
        changFragment(new HomeFragment(), true);
        EventBus.getDefault().register(this);
    }

    public void changFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_container, fragment);
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }

    @Subscribe
    public void onEvent(OpenFragmentEvent openFragmentEvent) {
        changFragment(openFragmentEvent.getFragment(), openFragmentEvent.isAddToBackStack());
    }

}
