package com.example.grigo.usb_explore;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * Created by Tanmoy on 18/10/2017.
 */


public class search extends AppCompatActivity{
        // Get database and initialise variables
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dbr = database.getInstance().getReference();
        String message;
        //int numberOfRooms = 43;
        int numberOfRooms = 106;
        String[] list = new String[numberOfRooms];
        ArrayAdapter arrayAdapter;
        String roomNo = "";
        String level = "";
        Boolean notFound = false;
        int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // reset variables from last search
        roomNo = "";
        level = "";
        // set all GUI elements
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle("Staff/Room Search");
        SearchView search = findViewById(R.id.searchView);
        final ListView listView = findViewById(R.id.list_view);
        // initialise list
        for (int x = 0; x < list.length; x++){
            list[x] = "";
        }
        // create and initialise adapter to display list
        arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(arrayAdapter);
        search.setActivated(true);
        search.setQueryHint("Enter Staff Name or Room Number..");
        search.onActionViewExpanded();
        search.setIconified(false);
        search.clearFocus();

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // upon search submit
            @Override
            public boolean onQueryTextSubmit(String query) {
                message = query;

                if (!isNetworkAvailable()) {
                    list[0] = (" Please check your internet connection");
                }

                dbr.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        // for each room
                        count = 0;
                        for (int i = 0; i < numberOfRooms; i++) {
                            try {
                                // get data from firebase database
                                String lastName = dataSnapshot.child(Integer.toString(i)).child("Last Name").getValue(String.class);
                                String firstName = dataSnapshot.child(Integer.toString(i)).child("First Name").getValue(String.class);
                                roomNo = dataSnapshot.child(Integer.toString(i)).child("Room Number").getValue(String.class);
                                String title = dataSnapshot.child(Integer.toString(i)).child("Title").getValue(String.class);
                                level = dataSnapshot.child(Integer.toString(i)).child("Level").getValue(String.class);

                                notFound = false;

                                System.out.println(roomNo.equalsIgnoreCase(message));
                                // Use if statements to check if there's any matches for last name
                                if (roomNo.equalsIgnoreCase(message)){ // room number check
                                    addToList(title, firstName, lastName);
                                    break;
                                } else if (lastName.equalsIgnoreCase(message)) {
                                    addToList(title, firstName, lastName);
                                    break;
                                } else if (firstName.equalsIgnoreCase(message)){ // first name check
                                    addToList(title, firstName, lastName);
                                    break;
                                } else if ((firstName + " " + lastName).equalsIgnoreCase(message)){ // first and last name check
                                    addToList(title, firstName, lastName);
                                    break;
                                } else if ((title + " " + firstName + " " + lastName).equalsIgnoreCase(message)){ // full name including title check
                                    addToList(title, firstName, lastName);
                                    break;
                                } else {
                                    notFound = true;
                                }
                            } catch (Exception e) { //if not found
                                list[0] = " Staff member or room not found";
                                notFound = true;
                            }
                        }
                        // display the new data using a new array adapter
                        ArrayAdapter arrayAdapter2 = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, list);
                        listView.setAdapter(arrayAdapter2);
                    }
                    public void onCancelled(DatabaseError er){ // if there's a database error - likely connection failure
                        //list[0] = (" Please check your internet connection");
                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        // create on click listener for list elements
        listView.setOnItemClickListener((parent, view, position, id) -> {
            // if both room number and level aren't empty strings
            if ((!(roomNo.equals(""))) && (!(level.equals(""))) && !notFound){
                // put them in the intent and start the map activity
                notFound = false;
                Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                intent.putExtra("ROOM_NUMBER", roomNo);
                intent.putExtra("LEVEL", level);
                intent.putExtra("RESET", "1");
                finish();
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        int mapActivity = 0;
        try {
            mapActivity = Integer.parseInt(getIntent().getStringExtra("MapActivity"));
            getIntent().removeExtra("MapActivity");
        } catch (Exception e) {
        }

        if (mapActivity == 1) {
            Intent intent = new Intent(getApplicationContext(), MapActivity.class);
            finish();
            startActivity(intent);
        } else {
            super.onBackPressed();
        }
    }

    public void addToList(String title, String firstName, String lastName) {
        if (roomNo.equals("")) {
            list[count] = (title + " " + firstName + " " + lastName + " does not have a room" + "\n");
        } else if (title == null && firstName == null && lastName == null) {
            list[count] = (" " + roomNo + " (Floor " + level + ")" + "\n");
        } else {
            list[count] = (" " + roomNo + "          " + title + " " + firstName + " " + lastName + "\n");
        }
        count = count + 1;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}