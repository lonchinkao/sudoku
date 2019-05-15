package com.lonch.sudoku.utils;

import android.app.Activity;
import android.content.Intent;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.lonch.sudoku.R;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by manan on 2/1/2015.
 */
public class GestureListActivity extends Activity {
    private static final String TAG = "GestureListActivity";
    private String mCurrentGestureNaam,navuNaam;
    private ListView mGestureListView;
    private static ArrayList<GestureHolder> mGestureList;
    private GestureAdapter mGestureAdapter;
    private GestureLibrary gLib;
    //private ImageView mMenuItemView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_list);
        Log.d(TAG, getApplicationInfo().dataDir);

        openOptionsMenu();

        mGestureListView = (ListView) findViewById((R.id.gestures_list));
        mGestureList = new ArrayList<GestureHolder>();
        listBanav();
        mGestureAdapter = new GestureAdapter(mGestureList, GestureListActivity.this);
        mGestureListView.setLongClickable(true);
        mGestureListView.setAdapter(mGestureAdapter);

        // displays the popup context top_menu to either delete or resend measurement
        registerForContextMenu(mGestureListView);
    }

    @Override
    public void onResume(){
        super.onResume();
        setContentView(R.layout.activity_gesture_list);
        Log.d(TAG, getApplicationInfo().dataDir);

        openOptionsMenu();

        mGestureListView = (ListView) findViewById((R.id.gestures_list));
        mGestureList = new ArrayList<GestureHolder>();
        listBanav();
        mGestureAdapter = new GestureAdapter(mGestureList, GestureListActivity.this);
        mGestureListView.setLongClickable(true);
        mGestureListView.setAdapter(mGestureAdapter);
        // displays the popup context top_menu to either delete or resend measurement
        registerForContextMenu(mGestureListView);
    }

    /**
     * badha gestures laine emne list ma mukse
     */
    private void listBanav() {
        try {
            mGestureList = new ArrayList<GestureHolder>();
            gLib = GestureLibraries.fromFile(getExternalFilesDir(null) + "/" + "gesture.txt");
            gLib.load();
            Set<String> gestureSet = gLib.getGestureEntries();
            for(String gestureNaam: gestureSet){
                ArrayList<Gesture> list = gLib.getGestures(gestureNaam);
                for(Gesture g : list) {
                    mGestureList.add(new GestureHolder(g, gestureNaam));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void populateMenu(View view){
        //ImageView idView = (ImageView) view.findViewById(R.id.gesture_id);
        //Log.d(TAG, "ha ha" + idView.getText().toString());
        LinearLayout vwParentRow = (LinearLayout)view.getParent().getParent();
        TextView tv = (TextView)vwParentRow.findViewById(R.id.gesture_name_ref);
        mCurrentGestureNaam = tv.getText().toString();
        PopupMenu popup = new PopupMenu(this, view);
        popup.getMenuInflater().inflate(R.menu.gesture_item_options, popup.getMenu());
        popup.show();
    }

    public void addButtonClick(View view){
        Intent saveGesture = new Intent(GestureListActivity.this, SaveGestureActivity.class);
        startActivity(saveGesture);
    }

    public void testButtonClick(View view){
        Intent testGesture = new Intent(GestureListActivity.this, GestureActivity.class);
        startActivity(testGesture);
    }

    public void deleteButtonClick(MenuItem item){
        gLib.removeEntry(mCurrentGestureNaam);
        gLib.save();
        mCurrentGestureNaam = "";
        onResume();
    }

    public void renameButtonClick(MenuItem item){
        onResume();

}

    private void saveGesture() {
        ArrayList<Gesture> list = gLib.getGestures(mCurrentGestureNaam);
        if (list.size() > 0) {
            gLib.removeEntry(mCurrentGestureNaam);
            gLib.addGesture(navuNaam, list.get(0));
            if (gLib.save()) {
                Log.e(TAG, "gesture renamed!");
                onResume();
            }
        }
        navuNaam = "";
    }
    private void showToast(String string){
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }
}