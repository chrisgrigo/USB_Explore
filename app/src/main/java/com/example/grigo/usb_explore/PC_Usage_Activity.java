package com.example.grigo.usb_explore;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.Switch;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by grigo on 27-Feb-18.
 */

public class PC_Usage_Activity extends AppCompatActivity {

    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listHash;

    List<String> lvl2 = new ArrayList<>();
    List<String> lvl3 = new ArrayList<>();
    List<String> lvl4 = new ArrayList<>();
    List<String> lvl5 = new ArrayList<>();

    String JSONString = "";
    private static String fileURL = "https://api.myjson.com/bins/qqx1f";
    private final static int REFRESHINTERVAL = 1000 * 60 * 10; //10 minutes
    Handler refreshHandler;
    HandlerThread refreshHandlerThread;
    Thread refreshHandlerTask = new Thread(new Runnable()
    {
        @Override
        public void run() {
            Boolean check = false;
            try {
                JSONString = "";
                URL url = new URL(fileURL);
                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
                String line = br.readLine();
                JSONString = line;
                check = true;
            }catch (MalformedURLException e){
                e.printStackTrace();
            }catch (IOException ioe){
                ioe.printStackTrace();
            }
            if(check) {
                try {
                    lvl2 = new ArrayList<>();
                    lvl3 = new ArrayList<>();
                    lvl4 = new ArrayList<>();
                    lvl5 = new ArrayList<>();
                    JSONObject obj = new JSONObject(JSONString);
                    JSONArray summary = obj.getJSONArray("usb_location_summary");
                    for (int roomNo = 0; roomNo < summary.length(); roomNo++){
                        JSONObject room = summary.getJSONObject(roomNo);
                        addRoomInfo(roomNo, room);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            refreshHandler.postDelayed(refreshHandlerTask, REFRESHINTERVAL);
        }
    });


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pc_usage_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.usageToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Venue Availability");
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();
        listDataHeader.add("Level 2");
        listDataHeader.add("Level 3");
        listDataHeader.add("Level 4");
        listDataHeader.add("Level 5");
        listView = (ExpandableListView) findViewById(R.id.lvExp);
        refreshHandlerThread = new HandlerThread("RefreshThread");
        refreshHandlerThread.start();
        refreshHandler = new Handler(refreshHandlerThread.getLooper());
        refreshHandlerTask.start();
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listHash);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            String roomNo = "";
            int level = Integer.parseInt(listDataHeader.get(position));
            switch (level) {
                case 2:
                    roomNo = lvl2.get(position);
                    break;
                case 3:
                    roomNo = lvl3.get(position - 1);
                    break;
                case 4:
                    roomNo = lvl3.get(position - 5);
                    break;
                case 5:
                    roomNo = lvl3.get(position - 9);
                    break;
            }
            Intent intent = new Intent(getBaseContext(), MapActivity.class);
            intent.putExtra("ROOM_NUMBER", roomNo);
            intent.putExtra("LEVEL", level);
            startActivity(intent);
        });
}


    private void addRoomInfo(int roomNo, JSONObject room){
        try {
            String name = room.getString("location_name");
            String usablePC = room.getString("location_free") + " / " + room.getString("location_total") + " PCs Available";
            if (roomNo == 0){
                lvl2.add(name + " - " + usablePC);
            } else if (roomNo < 5){
                lvl3.add(name + " - " + usablePC);
            } else if (roomNo < 9){
                lvl4.add(name + " - " + usablePC);
            } else {
                lvl5.add(name + " - " + usablePC);
            }
            listHash.put(listDataHeader.get(0),lvl2);
            listHash.put(listDataHeader.get(1),lvl3);
            listHash.put(listDataHeader.get(2),lvl4);
            listHash.put(listDataHeader.get(3),lvl5);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }



}