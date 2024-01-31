package com.book_app.book_reader_prod.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.Toast;
import com.book_app.book_reader_prod.IBookHandlers.BookHandler;
import com.book_app.book_reader_prod.R;
import com.book_app.book_reader_prod.bookBuilder.BookHandlerBuilder;
import com.book_app.book_reader_prod.settings.SettingsManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.io.IOException;
import java.util.Objects;

public class BookActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 1;
    BookHandler handler;
    MediaPlayer audioBook;
    FloatingActionButton chooseAndPlayBtn;
    SeekBar seekBar;
    SettingsManager manager;
    FrameLayout contextView;
    private final Handler musicUpdHandler = new Handler();
    private final Runnable updateSeekBar = new Runnable() {
        @Override
        public void run() {
            seekBar.setProgress(audioBook.getCurrentPosition());
            musicUpdHandler.postDelayed(this, 100);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        chooseAndPlayBtn = findViewById(R.id.choose_and_play_button);
        seekBar = findViewById(R.id.seekBar_for_audio);
        handler = BookHandlerBuilder.buildHandler(Objects.requireNonNull(getIntent().getStringExtra("extension")));
        manager = new SettingsManager(this);
        contextView = findViewById(R.id.context_view);




        if(handler != null) {
            try {
                handler.open(getBaseContext(),Objects.requireNonNull(getIntent().getStringExtra("filepath")));
                contextView.addView(handler.getView());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            Toast.makeText(this, "Ошибка при считывании файла", Toast.LENGTH_SHORT).show();
        }


        if(manager.getPlayerActive()) {
            chooseAndPlayBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (audioBook != null) {
                        if (!audioBook.isPlaying()) {
                            audioBook.start();
                            musicUpdHandler.postDelayed(updateSeekBar, 100);
                            chooseAndPlayBtn.setImageResource(android.R.drawable.ic_media_pause);
                        } else {
                            audioBook.pause();
                            musicUpdHandler.removeCallbacks(updateSeekBar);
                            chooseAndPlayBtn.setImageResource(android.R.drawable.ic_media_play);
                        }
                    } else {
                        Toast.makeText(getBaseContext(), "Файл не выбран", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            chooseAndPlayBtn.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                    intent.setType("audio/mpeg");
                    startActivityForResult(intent, REQUEST_CODE);
                    return false;
                }
            });
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if (fromUser && audioBook != null) {
                        if (!audioBook.isPlaying())
                            chooseAndPlayBtn.setImageResource(android.R.drawable.ic_media_play);
                        audioBook.seekTo(progress);
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            });
        }
        else {
            chooseAndPlayBtn.setVisibility(View.INVISIBLE);
            seekBar.setVisibility(View.INVISIBLE);
            contextView.setLayoutParams(new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null){

            if(audioBook != null) {
                audioBook.stop();
                audioBook.reset();
                chooseAndPlayBtn.setImageResource(android.R.drawable.ic_media_play);
            }

            audioBook = MediaPlayer.create(this, data.getData());
            seekBar.setMax(audioBook.getDuration());
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(handler != null)
            handler.close();
        if(audioBook != null)
            audioBook.stop();
        contextView.removeAllViews();
    }
}