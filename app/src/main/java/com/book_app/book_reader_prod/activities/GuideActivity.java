package com.book_app.book_reader_prod.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.book_app.book_reader_prod.R;

public class GuideActivity extends AppCompatActivity {


    ImageView guideImages;
    TextView guideText;
    Button btnNext, btnPrevious;

    String [] guideStrings;

    Drawable [] drawables;

    int iterForGuideStrings = 0;
    int iterForImages = 0;



    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        guideImages = findViewById(R.id.guide_images);
        guideText = findViewById(R.id.guide_text);
        btnNext = findViewById(R.id.btn_next);
        btnPrevious = findViewById(R.id.btn_previous);

        guideStrings = getResources().getStringArray(R.array.guide_strings);

        drawables = new Drawable[]{getDrawable(R.drawable.slide_first), getDrawable(R.drawable.slide_second)};

        guideImages.setImageDrawable(drawables[0]);
        guideText.setText(guideStrings[0]);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++iterForImages;
                ++iterForGuideStrings;
                if(!(iterForImages == drawables.length && iterForGuideStrings == guideStrings.length)) {
                    guideImages.setImageDrawable(drawables[iterForImages]);
                    guideText.setText(guideStrings[iterForGuideStrings]);
                }
                else{
                    iterForImages = 1;
                    iterForGuideStrings = 1;
                }
            }
        });

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                --iterForImages;
                --iterForGuideStrings;
                if(!(iterForImages < 0 && iterForGuideStrings < 0)) {
                    guideImages.setImageDrawable(drawables[iterForImages]);
                    guideText.setText(guideStrings[iterForGuideStrings]);
                }
                else{
                    iterForImages = 0;
                    iterForGuideStrings = 0;
                }
            }
        });






    }
}