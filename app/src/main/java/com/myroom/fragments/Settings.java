package com.myroom.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myroom.R;

/**
 * Created by Jobin on Aug 19.
 */
public class Settings extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parentViewGroup,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settings, parentViewGroup, false);
        return rootView;
    }
}
