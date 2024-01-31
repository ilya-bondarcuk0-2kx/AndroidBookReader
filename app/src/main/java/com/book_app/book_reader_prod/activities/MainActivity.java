package com.book_app.book_reader_prod.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.book_app.book_reader_prod.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {


    private static final int READ_REQUEST_CODE = 1;
    Button choose_book_btn;
    TextView books_link;

    ImageButton settingsButton;

    FloatingActionButton guideBtn;
    String link = "https://coderbooks.ru/books/";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        choose_book_btn = findViewById(R.id.choose_book_button);
        books_link = findViewById(R.id.books_link);
        settingsButton = findViewById(R.id.settings_button);
        guideBtn = findViewById(R.id.guide_btn);


        choose_book_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(String.valueOf(Intent.ACTION_OPEN_DOCUMENT));
                intent.setType("*/*");
                startActivityForResult(intent, READ_REQUEST_CODE);
            }
        });

        books_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(link));
                startActivity(intent);
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toSettings();
            }
        });


        guideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), GuideActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            String fpath = data.getDataString();

            String extension = MimeTypeMap.getFileExtensionFromUrl(fpath);
            if
            (
                extension.contains("pdf") ||
                extension.contains("epub") ||
                extension.contains("fb2") ||
                extension.contains("html") ||
                extension.contains("txt") ||
                extension.contains("mobi") ||
                extension.contains("doc") ||
                extension.contains("docx")
            )
            {
                Intent openFileIntent = new Intent(this, BookActivity.class);
                openFileIntent.putExtra("filepath", fpath);
                openFileIntent.putExtra("extension", extension);
                startActivity(openFileIntent);
            }
            else
                Toast.makeText(this, "Неподдерживаемый формат файла", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, "Файл не выбран", Toast.LENGTH_SHORT).show();
    }


    private void toSettings(){
        startActivity(new Intent(this, SettingsActivity.class));
    }

}