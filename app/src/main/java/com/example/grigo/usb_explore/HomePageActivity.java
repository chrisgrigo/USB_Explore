package com.example.grigo.usb_explore;

import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;


public class HomePageActivity extends AppCompatActivity
        implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    SliderLayout sliderLayout ;
    ImageButton floor_maps;
    ImageButton staff_search;
    ImageButton venue_availability;
    ImageButton settings_button;
    ImageButton help_button;

    HashMap<String, Integer> HashMapForLocalRes ;
    ImageButton floormaps, staffsearch, venue, findapc;

    //String requiredSSID = "ASK4 Wireless"; // testing
    String requiredSSID = "newcastle-university";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homeactivity_main);
        Toolbar toolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);


        sliderLayout = (SliderLayout)findViewById(R.id.slider);

        floor_maps = (ImageButton)findViewById(R.id.floor_maps);
        staff_search = (ImageButton)findViewById(R.id.staff_search);
        venue_availability = (ImageButton)findViewById(R.id.venue_availability);
        settings_button = (ImageButton)findViewById(R.id.settings_button);
        help_button = (ImageButton)  findViewById(R.id.help_button);

        floor_maps.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("RESET", "1");
                startActivity(intent);
            }}
        );

        staff_search.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), search.class);
                startActivity(intent);
            }}
        );

        venue_availability.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (getSSID(getApplicationContext()).equals(requiredSSID)) {
                    Intent intent = new Intent(getApplicationContext(), PCUsageActivity.class);
                    intent.putExtra("RESET", "1");
                    startActivity(intent);
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Not connected to the " + requiredSSID + " network", Toast.LENGTH_LONG);
                    TextView textView = (TextView) toast.getView().findViewById(android.R.id.message);
                    textView.setGravity(Gravity.CENTER);
                    toast.show();
                }
            }}
        );

        settings_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
            }}
        );

        help_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), HelpActivity.class);
                startActivity(intent);
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

    public String getSSID(Context context) {
        String ssid = "none";
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();

        if (WifiInfo.getDetailedStateOf(wifiInfo.getSupplicantState()) == NetworkInfo.DetailedState.CONNECTED
                || WifiInfo.getDetailedStateOf(wifiInfo.getSupplicantState()) == NetworkInfo.DetailedState.OBTAINING_IPADDR) {
            ssid = wifiInfo.getSSID();
        }
        ssid = ssid.replace("\"", "");
        return ssid;
    }
}