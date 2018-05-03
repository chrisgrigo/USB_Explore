package com.example.grigo.usb_explore;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

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

public class PCUsageActivity extends AppCompatActivity {
    //create initial views and lists
    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listHash;

    List<String> lvl2 = new ArrayList<>();
    List<String> lvl3 = new ArrayList<>();
    List<String> lvl4 = new ArrayList<>();
    List<String> lvl5 = new ArrayList<>();

    String JSONString = "";
    // USED FOR TESTING PURPOSES OFF CAMPUS
    //private static String fileURL = "https://api.myjson.com/bins/qqx1f";
    private static String fileURL = "https://csi.ncl.ac.uk/usb/?json=y";
    private final static int REFRESHINTERVAL = 1000 * 60 * 10; //10 minutes
    Handler refreshHandler;
    HandlerThread refreshHandlerThread;
    // Create a new thread for the network reliant task
    Thread refreshHandlerTask = new Thread(new Runnable()
    {
        @Override
        public void run() {
            Boolean check = false;
            try { // pull the json string from the URL
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
            // if JSON string has been imported
            if(check) {
                try {
                    // initialise lists
                    lvl2 = new ArrayList<>();
                    lvl3 = new ArrayList<>();
                    lvl4 = new ArrayList<>();
                    lvl5 = new ArrayList<>();
                    // get the array from the parsed object
                    JSONObject obj = new JSONObject(JSONString);
                    JSONArray summary = obj.getJSONArray("usb_location_summary");
                    // add the room info for each room
                    for (int roomNo = 0; roomNo < summary.length(); roomNo++){
                        JSONObject room = summary.getJSONObject(roomNo);
                        addRoomInfo(roomNo, room);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            // refresh upon the interval time limit being reached
            refreshHandler.postDelayed(refreshHandlerTask, REFRESHINTERVAL);
        }
    });


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set up GUI elements
        setContentView(R.layout.pc_usage_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.usageToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Venue Availability");
        // create and add initial data to list/map
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();
        listDataHeader.add("Level 2");
        listDataHeader.add("Level 3");
        listDataHeader.add("Level 4");
        listDataHeader.add("Level 5");
        listView = (ExpandableListView) findViewById(R.id.lvExp);
        // create a new handler thread for network activity
        refreshHandlerThread = new HandlerThread("RefreshThread");
        refreshHandlerThread.start();
        // create a new handler to work on that thread
        refreshHandler = new Handler(refreshHandlerThread.getLooper());
        refreshHandlerTask.start();
        // create a new list adapter and set it to the view
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listHash);
        listView.setAdapter(listAdapter);
        // upon clicking a list child item
        listView.setOnChildClickListener((parent, view, grouppos, childpos, id) -> {
            String roomNo = "";
            String level = Integer.toString(grouppos + 2);
            // get the relevant room number from the position in the list
            switch (grouppos) {
                case 0:
                    roomNo = lvl2.get(childpos);
                    break;
                case 1:
                    roomNo = lvl3.get(childpos);
                    break;
                case 2:
                    roomNo = lvl4.get(childpos);
                    break;
                case 3:
                    roomNo = lvl5.get(childpos);
                    break;
            }
            String roomNames[] = {"2.014", "3.005", "3.014", "3.015", "3.018", "4.015", "4.020", "4.021", "4.022", "5.011", "5.023", "MSc Teaching"};
            for (String rName: roomNames){
                // get the position of where the string starts
                int pos = roomNo.indexOf(rName);
                if (pos != -1){ // if the roomname exists within that string
                    roomNo = roomNo.substring(pos, pos + rName.length()); // create substring
                    System.out.println(roomNo);

                    Intent intent = new Intent(getBaseContext(), MapActivity.class);

                    String notOnMap[] = {"2.014", "3.014", "4.015", "5.011", "5.023", "MSc Teaching"};
                    for (int i = 0; i < notOnMap.length; i++) {
                        if (roomNo.equals(notOnMap[i])) {

                            Toast toast = Toast.makeText(getApplicationContext(), "Room exists but is currently not on the map", Toast.LENGTH_LONG);
                            TextView textView = (TextView) toast.getView().findViewById(android.R.id.message);
                            textView.setGravity(Gravity.CENTER);
                            toast.show();
                        }
                    }

                    intent.putExtra("ROOM_NUMBER", roomNo);
                    intent.putExtra("LEVEL", level);
                    finish();
                    startActivity(intent); // start map activity and give it room number and level via intent
                    return false;
                }
            }
            return false;
        });
}


    private void addRoomInfo(int roomNo, JSONObject room){ //initialises the hashmap with the data from JSON
        try {
            // get the name and the amount of usable PCs
            String name = room.getString("location_name");
            String usablePC = room.getString("location_free") + " / " + room.getString("location_total") + " PCs Available";
            if (roomNo == 0){ // checked room level against JSON file
                lvl2.add(name + " - " + usablePC); // add to relevant level list
            } else if (roomNo < 5){
                lvl3.add(name + " - " + usablePC);
            } else if (roomNo < 9){
                lvl4.add(name + " - " + usablePC);
            } else {
                lvl5.add(name + " - " + usablePC);
            }
            // put all the data into a hashmap with all the levels
            listHash.put(listDataHeader.get(0),lvl2);
            listHash.put(listDataHeader.get(1),lvl3);
            listHash.put(listDataHeader.get(2),lvl4);
            listHash.put(listDataHeader.get(3),lvl5);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}