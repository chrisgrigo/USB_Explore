package com.example.grigo.usb_explore;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import java.util.HashMap;
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


public class HomePageActivity extends AppCompatActivity
        implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    SliderLayout sliderLayout ;
    ImageButton floor_maps;
    ImageButton staff_search;
    ImageButton venue_availability;
    ImageButton find_a_pc;

    HashMap<String, Integer> HashMapForLocalRes ;
    ImageButton floormaps, staffsearch, venue, findapc;

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
                /*On Click open staff_search*/
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