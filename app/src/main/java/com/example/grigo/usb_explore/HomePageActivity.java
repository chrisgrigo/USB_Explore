package com.example.grigo.usb_explore;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.media.Image;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class HomePageActivity extends AppCompatActivity
        implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    SliderLayout sliderLayout ;
    ImageButton floor_maps;
    ImageButton staff_search;
    ImageButton venue_availability;
    ImageButton find_a_pc;

    HashMap<String, Integer> HashMapForLocalRes ;
    ImageButton floormaps, staffsearch, venue, findapc;

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference dbr = database.getInstance().getReference();
    String message;
    String[] list = {"Results will appear here"};
    ArrayAdapter arrayAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homeactivity_main);

        sliderLayout = (SliderLayout)findViewById(R.id.slider);

        floor_maps = (ImageButton)findViewById(R.id.floor_maps);
        staff_search = (ImageButton)findViewById(R.id.staff_search);
        venue_availability = (ImageButton)findViewById(R.id.venue_availability);
        find_a_pc = (ImageButton)findViewById(R.id.find_a_pc);

        floor_maps.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                /*On Click open maps*/
            }}
        );

        staff_search.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                setContentView(R.layout.search);
                SearchView search = findViewById(R.id.searchView);
                final ListView listView = findViewById(R.id.list_view);
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
                                for (int i = 0; i < 43; i++) {
                                    try {
                                        String lastName = dataSnapshot.child(Integer.toString(i)).child("Last Name").getValue(String.class);
                                        String firstName = dataSnapshot.child(Integer.toString(i)).child("First Name").getValue(String.class);
                                        String roomNo = dataSnapshot.child(Integer.toString(i)).child("Room Number").getValue(String.class);
                                        String title = dataSnapshot.child(Integer.toString(i)).child("Title").getValue(String.class);
                                        if (lastName.equalsIgnoreCase(message)) {
                                            list[0] = (title + " " + firstName + " " + lastName + "'s room is: " + roomNo + "\n");
                                        } else if (firstName.equalsIgnoreCase(message)){
                                            list[0] = (title + " " + firstName + " " + lastName + "'s room is: " + roomNo + "\n");
                                        } else if (roomNo.equalsIgnoreCase(message)){
                                            list[0] = (title + " " + firstName + " " + lastName + "'s room is: " + roomNo + "\n");
                                        } else if ((firstName + " " + lastName).equalsIgnoreCase(message)){
                                            list[0] = (title + " " + firstName + " " + lastName + "'s room is: " + roomNo + "\n");
                                        } else if ((title + " " + firstName + " " + lastName).equalsIgnoreCase(message)){
                                            list[0] = (title + " " + firstName + " " + lastName + "'s room is: " + roomNo + "\n");
                                        }
                                    } catch (Exception e) {

                                    }
                                }
                            }
                            public void onCancelled(DatabaseError er){
                                list[0] = ("ERROR:" + er.toString());
                            }
                        });
                        arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, list);
                        listView.setAdapter(arrayAdapter);
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        return false;
                    }
                });
            }}
        );

        venue_availability.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                /*On Click open staff_search*/
            }}
        );

        find_a_pc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                /*On Click open find pc*/
                /*Run HomePageActivity*/
            }}
        );

        //Call this method to add images from local drawable folder .
        AddImageUrlFormLocalRes();

        //Call this method to stop automatic sliding.
        //sliderLayout.stopAutoCycle();

        for(String name : HashMapForLocalRes.keySet()){

            TextSliderView textSliderView = new TextSliderView(HomePageActivity.this);

            textSliderView
                    .description(name)
                    .image(HashMapForLocalRes.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            textSliderView.bundle(new Bundle());

            textSliderView.getBundle()
                    .putString("extra",name);

            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.DepthPage);

        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);

        sliderLayout.setCustomAnimation(new DescriptionAnimation());

        sliderLayout.setDuration(3000);

        sliderLayout.addOnPageChangeListener(HomePageActivity.this);
    }

    @Override
    protected void onStop() {

        sliderLayout.stopAutoCycle();

        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

        Toast.makeText(this,slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {

        Log.d("Slider ", "Page Changed: " + position);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void AddImageUrlFormLocalRes(){

        HashMapForLocalRes = new HashMap<String, Integer>();

        HashMapForLocalRes.put("Urban Sciences Building", R.drawable.slide1);
        HashMapForLocalRes.put("Inside USB", R.drawable.slide2);
        HashMapForLocalRes.put("USB 4th Floor Balcony", R.drawable.slide3);

    }

}