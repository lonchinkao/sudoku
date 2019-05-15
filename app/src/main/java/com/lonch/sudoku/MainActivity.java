package com.lonch.sudoku;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.lonch.sudoku.gui.FolderListActivity;
import com.lonch.sudoku.gui.GameSettingsActivity;
import com.lonch.sudoku.utils.GestureActivity;
import com.lonch.sudoku.utils.GestureListActivity;


public class MainActivity extends Activity {
    // Remove the below line after defining your own ad unit ID.
    private static final String TOAST_TEXT = "Test ads are being shown. "
            + "To show live ads, replace the ad unit ID in res/values/strings.xml with your own ad unit ID.";

    private static final int START_LEVEL = 1;
    private int mLevel;
    private Button mNextLevelButton, mButtonplay, mButonsetting, mButtonhelp, mButtonhand, mButtonNumlist;

    private TextView mLevelTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonplay = ((Button) findViewById(R.id.buttonplay));
        mButtonplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    startplay();
            }
        });
        mButonsetting = ((Button) findViewById(R.id.buttonsetting));
        mButonsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameSetting();
            }
        });

        mButtonhand = ((Button) findViewById(R.id.buttonhand));
        mButtonhand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GamehandSetting();
            }
        });

        mButtonhelp = ((Button) findViewById(R.id.buttonhelp));
        mButtonhelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gamehelp();
            }
        });

        mButtonNumlist = ((Button) findViewById(R.id.buttonnumlist));
        mButtonNumlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameNumberList();
            }
        });


         // Toasts the test ad message on the screen. Remove this after defining your own ad unit ID.
        Toast.makeText(this, TOAST_TEXT, Toast.LENGTH_LONG).show();
    }

    private void GameNumberList() {
        Intent i = new Intent(this, GestureListActivity.class);
        startActivity(i);
    }

    private void GamehandSetting() {
        Intent i = new Intent(this, GestureActivity.class);
        startActivity(i);
    }

    private void Gamehelp() {
        Intent i = new Intent(this, AboutActivity.class);
        startActivity(i);

    }

    private void GameSetting() {
        final int REQUEST_SETTINGS;
        REQUEST_SETTINGS = 1;
        Intent i = new Intent();
        i.setClass(this, GameSettingsActivity.class);
        startActivityForResult(i, REQUEST_SETTINGS);

    }

    private void startplay() {
        Intent i = new Intent(this, FolderListActivity.class);
         startActivity(i);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
