package com.example.hau.quizemall.fragments;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hau.quizemall.R;
import com.example.hau.quizemall.utils.Font;
import com.example.hau.quizemall.utils.Setting;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    @BindView(R.id.iv_play)
    ImageView ivPlay;
    @BindView(R.id.tv_current_score)
    TextView tvCurrentScore;
    @BindView(R.id.tv_high_score)
    TextView tvHighScore;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        setupUI();
        return view;
    }

    private void setupUI() {
        // set position image play
        Setting setting = Setting.getInstance();
        int size = (int) (setting.getWidth() * 0.4);
        ivPlay.setLayoutParams(new RelativeLayout.LayoutParams(size, size));
        ivPlay.setX((setting.getWidth() - size) / 2);
        ivPlay.setY((setting.getHeight() - size) * 5 / 8);

        // set font for highscore
        tvCurrentScore.setTypeface(Font.getInstance().getFontStencil());
        tvHighScore.setTypeface(Font.getInstance().getFontPoplar());
    }

    @OnClick(R.id.iv_play)
    void playGame() {
        OpenFragmentEvent openFragmentEvent = new OpenFragmentEvent(new PlayFragment(), true);
        EventBus.getDefault().post(openFragmentEvent);
    }
}
