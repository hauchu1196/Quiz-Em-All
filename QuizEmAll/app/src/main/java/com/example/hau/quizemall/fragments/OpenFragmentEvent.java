package com.example.hau.quizemall.fragments;

import android.support.v4.app.Fragment;

/**
 * Created by Hau on 18/11/2016.
 */
public class OpenFragmentEvent {
    private Fragment fragment;
    private boolean addToBackStack;

    public OpenFragmentEvent(Fragment fragment, boolean addToBackStack) {
        this.fragment = fragment;
        this.addToBackStack = addToBackStack;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public boolean isAddToBackStack() {
        return addToBackStack;
    }
}
