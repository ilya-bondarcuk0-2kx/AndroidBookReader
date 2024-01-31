package com.book_app.book_reader_prod.activities;



import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.book_app.book_reader_prod.R;
import com.book_app.book_reader_prod.settings.SettingsManager;


public class SettingsActivity extends AppCompatActivity {


    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch switchTheme, switchPlayer;
    SettingsManager settingsManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        settingsManager = new SettingsManager(this);

        switchTheme = findViewById(R.id.settings_switch_theme_switch);
        switchPlayer = findViewById(R.id.settings_player_switch);


        switchTheme.setChecked(settingsManager.getThemeSwitched());
        switchTheme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                settingsManager.setThemeSwitched(isChecked);
            }
        });

        switchPlayer.setChecked(settingsManager.getPlayerActive());
        switchPlayer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                settingsManager.setPlayerActive(isChecked);
            }
        });

    }


//    private void showReloadDialog() {
//
//    AlertDialog.Builder builder = new AlertDialog.Builder(this);
//    builder.setMessage("Для применения выбранной темы необходимо перезапустить приложение. Перезапустить сейчас?");
//    builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
//        @Override
//        public void onClick(DialogInterface dialogInterface, int i) {
//            getApplication().setTheme(R.style.Base_Theme_Dark_Book_Reader_prod);
//            recreate();
//        }
//    });
//    builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
//        @Override
//        public void onClick(DialogInterface dialogInterface, int i) {
//            dialogInterface.dismiss();
//        }
//    });
//    AlertDialog dialog = builder.create();
//    dialog.show();
//}
}