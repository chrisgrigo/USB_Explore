package com.example.grigo.usb_explore;

import android.content.Intent;
import android.os.Bundle;
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
import java.util.logging.Level;

public class search extends AppCompatActivity{

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dbr = database.getInstance().getReference();
        String message;
        int numberOfRooms = 43;
        String[] list = new String[numberOfRooms];
        ArrayAdapter arrayAdapter;
        String roomNo = "";
        String level = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        roomNo = "";
        level = "";
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Staff/Room Search");
        SearchView search = findViewById(R.id.searchView);
        final ListView listView = findViewById(R.id.list_view);
        for (int x = 0; x < list.length; x++){
            list[x] = "";
        }
        arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(arrayAdapter);
        search.setActivated(true);
        search.setQueryHint("Enter Staff Name or Room Number..");
        search.onActionViewExpanded();
        search.setIconified(false);
        search.clearFocus();
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                message = query;
                dbr.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (int i = 0; i < numberOfRooms; i++) {
                            int count = 0;
                            try {
                                String lastName = dataSnapshot.child(Integer.toString(i)).child("Last Name").getValue(String.class);
                                String firstName = dataSnapshot.child(Integer.toString(i)).child("First Name").getValue(String.class);
                                roomNo = dataSnapshot.child(Integer.toString(i)).child("Room Number").getValue(String.class);
                                String title = dataSnapshot.child(Integer.toString(i)).child("Title").getValue(String.class);
                                level = dataSnapshot.child(Integer.toString(i)).child("Level").getValue(String.class);
                                if (lastName.equalsIgnoreCase(message)) {
                                    list[count] = (title + " " + firstName + " " + lastName + "'s room is: " + roomNo + "\n");
                                    count = count + 1;
                                    break;
                                } else if (firstName.equalsIgnoreCase(message)){
                                    list[count] = (title + " " + firstName + " " + lastName + "'s room is: " + roomNo + "\n");
                                    count = count + 1;
                                    break;
                                } else if (roomNo.equalsIgnoreCase(message)){
                                    if (roomNo.equals("null")){
                                        list[count] = (title + " " + firstName + " " + lastName + "'s room is on level " + level + "\n");
                                    }
                                    list[count] = (title + " " + firstName + " " + lastName + "'s room is: " + roomNo + "\n");
                                    count = count + 1;
                                    break;
                                } else if ((firstName + " " + lastName).equalsIgnoreCase(message)){
                                    list[count] = (title + " " + firstName + " " + lastName + "'s room is: " + roomNo + "\n");
                                    count = count + 1;
                                    break;
                                } else if ((title + " " + firstName + " " + lastName).equalsIgnoreCase(message)){
                                    list[count] = (title + " " + firstName + " " + lastName + "'s room is: " + roomNo + "\n");
                                    count = count + 1;
                                    break;
                                }
                            } catch (Exception e) {
                                list[0] = "Staff Member or Room not found!";
                            }
                        }
                        ArrayAdapter arrayAdapter2 = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, list);
                        listView.setAdapter(arrayAdapter2);
                    }
                    public void onCancelled(DatabaseError er){
                        list[0] = ("No internet connection available!");
                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            if ((!(roomNo.equals(""))) && (!(level.equals("")))){
                Intent intent = new Intent(getBaseContext(), MapActivity.class);
                intent.putExtra("ROOM_NUMBER", roomNo);
                intent.putExtra("LEVEL", level);
                startActivity(intent);
            }
        });

    }

}
