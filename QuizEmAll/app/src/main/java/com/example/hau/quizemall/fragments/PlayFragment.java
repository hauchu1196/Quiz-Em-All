package com.example.hau.quizemall.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.hau.quizemall.R;
import com.example.hau.quizemall.utils.Font;
import com.example.hau.quizemall.utils.Setting;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayFragment extends Fragment {

    @BindView(R.id.tv_current_score_play)
    TextView tvCurrentScorePlay;
    @BindView(R.id.pb_time)
    ProgressBar pbTime;

    public PlayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_play, container, false);
        ButterKnife.bind(this, view);
        setupUI();
        return view;
    }

    private void setupUI() {
        int size = (int) (Setting.getInstance().getWidth() * 0.6);
        pbTime.getLayoutParams().width = size;
        tvCurrentScorePlay.setTypeface(Font.getInstance().getFontStencil());
    }

}
