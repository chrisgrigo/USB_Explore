package com.example.grigo.usb_explore;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

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
        faqList2.add("\"What can this app be used for?\" \r\n\"" +
                "This application is essentially a mobile guide around the Urban Sciences Building (USB) owned by Newcastle University." +
                " It allows you to look at all floors of the building on an interactive map. You can find directions from any 2 rooms of your choice," +
                " search for \nstaff rooms, and even find available PCs in the building which updates with live statistics every 10 minutes!\"\n\n" +
                " \"What can i do if i find a bug?\" \"If a bug is found, please navigate to the \"help\" section found on the home page. " +
                "There, that will take you to a page which allows you to submit a query. " +
                "Notate the issue and how you caused it to occur, and we will work as quick as we can to fix it.\"\r\n\n" +
                "\"Where can i find information about different rooms?\" \r\n " +
                "\"Navigate to the map via the home page, and click on any room on the map which will cause a small \"i\" icon to appear. " +
                "Click on this, \nand information about the selected room will pop up.\"\r\n"
        );
        termsList2.add("Terms and conditions, as may be amended from time to time, \n" +
        "apply to all of our services. By accessing our application you acknowledge and agree with our Terms and Conditions. \n" +
        "As our application is uploaded to Google Play it follows that user should have read and agree with Google Play Terms of Service: \n" +
                "https://play.google.com/intl/en-us_us/about/play-terms.html");
        aboutList2.add("This application was made by a second year computer  \nscience team for a project as part of the course");

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
