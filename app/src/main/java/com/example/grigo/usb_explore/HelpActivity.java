package com.example.grigo.usb_explore;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by grigo on 21-Apr-18.
 */

public class HelpActivity extends AppCompatActivity {


    private ExpandableListView listView_Help, listView_Help2;
    private ExpandableListAdapter listAdapter_Help, listAdapter_Help2;
    private List<String> listDataHeader_Help, listDataHeader_Help2;
    private HashMap<String, List<String>> listHash_Help, listHash_Help2;
    private ArrayList<String> feedbackList1, faqList2, termsList2,aboutList2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_layout);
        Toolbar toolbar = findViewById(R.id.help_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Help");







        /* THIS IS FOR FAQ, TERMS, ABOUT  */
        listDataHeader_Help2 = new ArrayList<>();
        listHash_Help2 = new HashMap<>();

        faqList2 = new ArrayList<>();
        termsList2 = new ArrayList<>();
        aboutList2 = new ArrayList<>();

        /*TITLES ARE HERE*/
        listDataHeader_Help2.add("Frequently Asked Questions");
        listDataHeader_Help2.add("Terms & Conditions");
        listDataHeader_Help2.add("About");

        /*CONTENT HERE*/
        faqList2.add("Frequently asked questions here");
        termsList2.add("Terms and conditions to be placed here");
        aboutList2.add("About is placed here");

        listHash_Help2.put(listDataHeader_Help2.get(0),faqList2);
        listHash_Help2.put(listDataHeader_Help2.get(1),termsList2);
        listHash_Help2.put(listDataHeader_Help2.get(2),aboutList2);
        listAdapter_Help2 = new ExpandableListAdapter(this, listDataHeader_Help2, listHash_Help2);
        listView_Help2 = (ExpandableListView) findViewById(R.id.help_faq_terms_about);
        listView_Help2.setAdapter(listAdapter_Help2);

    }

    public void onButtonFeedbackClick(View view) {

        // get a reference to the already created main layout
        LinearLayout mainLayout = (LinearLayout)
                findViewById(R.id.help_layout_id);

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_window, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        popupWindow.showAtLocation(mainLayout, Gravity.TOP, 0, 237);

        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }
}
