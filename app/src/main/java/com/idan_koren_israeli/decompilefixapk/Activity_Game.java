package com.idan_koren_israeli.decompilefixapk;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class Activity_Game extends AppCompatActivity {
    public static final String EXTRA_ID = "EXTRA_ID";
    public static final String EXTRA_STATE = "EXTRA_STATE";
    private ImageButton[] arrows;
    int currentLevel = 0;
    private boolean goodToGo = true;
    int[] steps = {1, 1, 1, 2, 2, 2, 3, 3, 3};

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_game);
        String id = getIntent().getStringExtra(EXTRA_ID);
        for (int i = 0; i < steps.length; i++) {
            steps[i] = Integer.valueOf(String.valueOf(id.charAt(i))).intValue() % 4;
        }

        findViews();
        initViews();
    }

    /* access modifiers changed from: private */
    public void arrowClicked(int direction) {
        if (goodToGo && direction != steps[currentLevel]) {
            goodToGo = false;
        }
        currentLevel++;
        if (currentLevel >= steps.length) {
            finishGame();
        }
    }

    private void finishGame() {
        String state = getIntent().getStringExtra(EXTRA_STATE);
        if (this.goodToGo) {
            Toast.makeText(this, "Survived in " + state, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "You Failed ", Toast.LENGTH_LONG).show();
        }
        finish();
    }

    private void initViews() {
        int i = 0;
        ImageButton[] imageButtonArr = arrows;

        for (ImageButton arrow : imageButtonArr) {
            final int finalI = i;
            arrow.setOnClickListener(new View.OnClickListener() {
                                                     public void onClick(View v) {
                                                         Activity_Game.this.arrowClicked(finalI);
                                                     }
                                                 }
            );
            i++;

        }

    }

    private void findViews() {
        this.arrows = new ImageButton[]{(ImageButton) findViewById(R.id.game_BTN_left),
                (ImageButton) findViewById(R.id.game_BTN_right),
                (ImageButton) findViewById(R.id.game_BTN_up),
                (ImageButton) findViewById(R.id.game_BTN_down)};
    }
}
