package com.example.hau.quizemall.fragments;


import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hau.quizemall.MainActivity;
import com.example.hau.quizemall.R;
import com.example.hau.quizemall.managers.DbHelper;
import com.example.hau.quizemall.managers.Preferences;
import com.example.hau.quizemall.models.Pokemon;
import com.example.hau.quizemall.models.Score;
import com.example.hau.quizemall.utils.Font;
import com.example.hau.quizemall.utils.Setting;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayFragment extends Fragment {

    private static final String TAG = PlayFragment.class.toString();
    @BindView(R.id.ll_background)
    LinearLayout llBackground;
    @BindView(R.id.tv_current_score_play)
    TextView tvCurrentScorePlay;
    @BindView(R.id.pb_time)
    ProgressBar pbTime;
    @BindView(R.id.iv_pokemon)
    ImageView ivPokemon;
    CountDownTimer countDownTimer;
    @BindView(R.id.tv_answer_1)
    TextView tvAnswer1;
    @BindView(R.id.tv_answer_2)
    TextView tvAnswer2;
    @BindView(R.id.tv_answer_3)
    TextView tvAnswer3;
    @BindView(R.id.tv_answer_4)
    TextView tvAnswer4;
    @BindView(R.id.rl_answer_1)
    RelativeLayout rlAnswer1;
    @BindView(R.id.rl_answer_2)
    RelativeLayout rlAnswer2;
    @BindView(R.id.rl_answer_3)
    RelativeLayout rlAnswer3;
    @BindView(R.id.rl_answer_4)
    RelativeLayout rlAnswer4;
    @BindView(R.id.tv_tag)
    TextView tvTag;

    private Pokemon pokemon;
    ArrayList<TextView> textViews;
    ArrayList<RelativeLayout> relativeLayouts;
    private int currentTime;
    private long currentMilis = 19000;


    public PlayFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
        int score = Integer.parseInt(tvCurrentScorePlay.getText().toString());
        if (score > Preferences.getInstance().getHighScore()) {
            Preferences.getInstance().putIsHighScre(true);
            Preferences.getInstance().putHighScore(score);
        } else {
            Preferences.getInstance().putIsHighScre(false);
        }
        Preferences.getInstance().putCurrentScore(score);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_play, container, false);
        ButterKnife.bind(this, view);
        getReferences();
        setupUI();
        beginPlay();
        return view;
    }

    private void getReferences() {
        TextView[] tvs = new TextView[]{
                tvAnswer1, tvAnswer2, tvAnswer3, tvAnswer4
        };
        textViews = new ArrayList<>(Arrays.asList(tvs));
        RelativeLayout[] rls = new RelativeLayout[]{
                rlAnswer1, rlAnswer2, rlAnswer3, rlAnswer4
        };
        relativeLayouts = new ArrayList<>(Arrays.asList(rls));
    }

    private void beginPlay() {
        currentTime = 0;
        pbTime.setProgress(currentTime);
        countDownTimer = new CountDownTimer(currentMilis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.d(TAG, String.format("current: %s", currentMilis));
                currentMilis = millisUntilFinished;
                currentTime += (pbTime.getMax() / 20);
                if (currentTime < pbTime.getMax()) {
                    pbTime.setProgress(currentTime);
                } else {
                    pbTime.setProgress(pbTime.getMax());
                }
            }

            @Override
            public void onFinish() {
                Log.d(TAG, "onFinish");
                getActivity().onBackPressed();
            }
        };
        countDownTimer.start();
    }

    private void checkAnswer(Pokemon pokemon, TextView textView, RelativeLayout relativeLayout) {
        Log.d(TAG, "checkAnswer");
        String answer = textView.getText().toString();
        if (answer.equalsIgnoreCase(pokemon.getName())) {
            Log.d(TAG, "DUNG");
            int score = Integer.parseInt(tvCurrentScorePlay.getText().toString());
            tvCurrentScorePlay.setText(String.valueOf(score + 1));
            relativeLayout.setBackgroundResource(R.drawable.image_view_success);
        } else {
            Log.d(TAG, "SAI");
            relativeLayout.setBackgroundResource(R.drawable.image_view_error);
            for (int i = 0; i < textViews.size(); i++) {
                String res = textViews.get(i).getText().toString();
                if (res.equalsIgnoreCase(pokemon.getName())) {
                    RelativeLayout relativeLayout1 = relativeLayouts.get(i);
                    relativeLayout1.setBackgroundResource(R.drawable.image_view_success);
                }
            }
        }
        for (RelativeLayout relativeLayout1 : relativeLayouts) {
            relativeLayout1.setEnabled(false);
        }
        ivPokemon.setImageBitmap(loadImageFromAsset(pokemon));
        tvTag.setText(String.format("%s %s", pokemon.getTag(), pokemon.getName()));
        new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                currentMilis -= 1000;
                Log.d(TAG, String.format("onTick : %s", currentMilis));
            }

            @Override
            public void onFinish() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setupUI();
                    }
                });

                countDownTimer = new CountDownTimer(currentMilis, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        currentMilis = millisUntilFinished;
                        Log.d(TAG, String.format("current: %s", currentMilis));
                        currentTime += (pbTime.getMax() / 20);
                        if (currentTime < pbTime.getMax()) {
                            pbTime.setProgress(currentTime);
                        } else {
                            pbTime.setProgress(pbTime.getMax());
                        }
                    }

                    @Override
                    public void onFinish() {
                        Log.d(TAG, "onFinish");
                        getActivity().onBackPressed();
                    }
                };
                countDownTimer.start();
            }
        }.start();
    }


    private void setupUI() {
        pokemon = DbHelper.getInstance().selectRandomPokemon();
        int size = (int) (Setting.getInstance().getWidth() * 0.6);
        pbTime.getLayoutParams().width = size;
        loadPokemonQuestion(pokemon);
        tvCurrentScorePlay.setTypeface(Font.getInstance().getFontStencil());
        tvTag.setText("");
        for (RelativeLayout relativeLayout : relativeLayouts) {
            relativeLayout.setBackgroundResource(R.drawable.custom_image_view);
            relativeLayout.setEnabled(true);
        }
    }

    private void loadPokemonQuestion(Pokemon pokemon) {
        Log.d(TAG, String.format("src: %s", pokemon.getImage()));
        setImageBlack(pokemon);
        setAnswer(pokemon);
        llBackground.setBackgroundColor(Color.parseColor(pokemon.getColor()));

    }

    private void setAnswer(Pokemon pokemon) {
        int answer;
        Random random = new Random();
        answer = random.nextInt(4);
        textViews.get(answer).setText(pokemon.getName());
        for (int i = 0; i < textViews.size(); i++) {
            if (i != answer) {
                TextView textView = textViews.get(i);
                while (true) {
                    Pokemon pokemon1 = DbHelper.getInstance().selectRandomPokemon();
                    if (pokemon.getId() != pokemon1.getId()) {
                        textView.setText(pokemon1.getName());
                        break;
                    }
                }
            }
        }

    }

    private void setImageBlack(Pokemon pokemon) {
        Bitmap bitmap = loadImageFromAsset(pokemon);
        Bitmap b = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        for (int x = 0; x < b.getWidth(); x++) {
            for (int y = 0; y < b.getHeight(); y++) {
                if (b.getPixel(x, y) != Color.TRANSPARENT) {

                    b.setPixel(x, y, Color.BLACK);
                }
            }
        }
        ivPokemon.setImageBitmap(b);
    }

    private Bitmap loadImageFromAsset(Pokemon pokemon) {
        AssetManager assetManager = getActivity().getAssets();
        InputStream inputStream = null;
        try {
            inputStream = assetManager.open("images/" + pokemon.getImage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        return bitmap;
    }

    @OnClick(R.id.rl_answer_1)
    void choseAnswer1() {
        countDownTimer.cancel();
        checkAnswer(pokemon, tvAnswer1, rlAnswer1);
    }

    @OnClick(R.id.rl_answer_2)
    void choseAnswer2() {
        countDownTimer.cancel();
        checkAnswer(pokemon, tvAnswer2, rlAnswer2);
    }

    @OnClick(R.id.rl_answer_3)
    void choseAnswer3() {
        countDownTimer.cancel();
        checkAnswer(pokemon, tvAnswer3, rlAnswer3);
    }

    @OnClick(R.id.rl_answer_4)
    void choseAnswer4() {
        countDownTimer.cancel();
        checkAnswer(pokemon, tvAnswer4, rlAnswer4);
    }

}
