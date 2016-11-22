package com.example.hau.quizemall.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hau.quizemall.R;
import com.example.hau.quizemall.utils.Font;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {

    @BindView(R.id.cb_gen_1)
    CheckBox cbGen1;

    @BindView(R.id.cb_gen_2)
    CheckBox cbGen2;

    @BindView(R.id.cb_gen_3)
    CheckBox cbGen3;

    @BindView(R.id.cb_gen_4)
    CheckBox cbGen4;

    @BindView(R.id.cb_gen_5)
    CheckBox cbGen5;

    @BindView(R.id.cb_gen_6)
    CheckBox cbGen6;

    @BindView(R.id.iv_gen_1)
    ImageView ivGen1;

    @BindView(R.id.iv_gen_2)
    ImageView ivGen2;

    @BindView(R.id.iv_gen_3)
    ImageView ivGen3;

    @BindView(R.id.iv_gen_4)
    ImageView ivGen4;

    @BindView(R.id.iv_gen_5)
    ImageView ivGen5;

    @BindView(R.id.iv_gen_6)
    ImageView ivGen6;

    @BindView(R.id.tv_generations_in_quiz)
    TextView tvGenerationsInQuiz;

    CheckBox[] checkBoxes;
    ImageView[] imageViews;


    ArrayList<CheckBox> listCheckBox;
    ArrayList<ImageView> listImageView;

    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        ButterKnife.bind(this, view);
        getReferences();
        setupUI();
        addListeners();
        return view;
    }

    private void getReferences() {
        checkBoxes = new CheckBox[]{
                cbGen1, cbGen2, cbGen3, cbGen4, cbGen5, cbGen6
        };

        imageViews = new ImageView[]{
                ivGen1, ivGen2, ivGen3, ivGen4, ivGen5, ivGen6
        };
        listCheckBox = new ArrayList<>(Arrays.asList(checkBoxes));
        listImageView = new ArrayList<>(Arrays.asList(imageViews));

    }

    private void addListeners() {
        for (int i = 0; i < listCheckBox.size(); i++) {
            final CheckBox checkBox = listCheckBox.get(i);
            final ImageView imageView = listImageView.get(i);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    mappingCheckImage(checkBox, imageView);
                }
            });
        }
    }

    private void setupUI() {

        tvGenerationsInQuiz.setTypeface(Font.getInstance().getFontPoplar());

        for (int i = 0; i < listCheckBox.size(); i++) {
            CheckBox checkBox = listCheckBox.get(i);
            ImageView imageView = listImageView.get(i);
            mappingCheckImage(checkBox, imageView);
        }
    }

    void mappingCheckImage(CheckBox checkBox, ImageView imageView) {
        if (checkBox.isChecked()) {
            imageView.setAlpha((float) 1);
        } else {
            imageView.setAlpha((float) 0.5);
        }
    }

}
