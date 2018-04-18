package com.example.grigo.usb_explore;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by grigo on 27-Feb-18.
 */

public class PC_Usage_Activity extends AppCompatActivity {

    private ExpandableListView listView;
    private ExpandableListView listView2;

    private ExpandableListAdapter listAdapter;
    private ExpandableListAdapter listAdapter2;

    private List<String> listDataHeader;
    private List<String> listDataHeader2;
    private HashMap<String, List<String>> listHash;
    private HashMap<String, List<String>> listHash2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pc_usage_layout);

        listView = (ExpandableListView) findViewById(R.id.lvExp);
        listView2 = (ExpandableListView) findViewById(R.id.lvExp2);
        initData();
        initData2();
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listHash);
        listAdapter2 = new ExpandableListAdapter(this,listDataHeader2,listHash2);
        listView.setAdapter(listAdapter);
        listView2.setAdapter(listAdapter2);
    }

    private void initData(){
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        listDataHeader.add("Level 3");
        listDataHeader.add("Level 4");
        listDataHeader.add("Level 5");

        List<String> lvl3 = new ArrayList<>();
        lvl3.add("3.001");
        lvl3.add("3.002");

        List<String> lvl4 = new ArrayList<>();
        lvl4.add("4.001");
        lvl4.add("4.002");

        List<String> lvl5 = new ArrayList<>();
        lvl5.add("5.001");
        lvl5.add("5.002");
        lvl5.add("5.003");
        lvl5.add("5.004");

        listHash.put(listDataHeader.get(0),lvl3);
        listHash.put(listDataHeader.get(1),lvl4);
        listHash.put(listDataHeader.get(2),lvl5);
    }

    private void initData2(){
        listDataHeader2 = new ArrayList<>();
        listHash2 = new HashMap<>();

        listDataHeader2.add("Level 1");
        listDataHeader2.add("Level 2");
        listDataHeader2.add("Level 3");
        listDataHeader2.add("Level 4");
        listDataHeader2.add("Level 5");

        List<String> lvl1 = new ArrayList<>();
        lvl1.add("1.001");
        lvl1.add("1.002");

        List<String> lvl2 = new ArrayList<>();
        lvl2.add("2.001");
        lvl2.add("2.002");

        List<String> lvl3 = new ArrayList<>();
        lvl3.add("3.001");
        lvl3.add("3.002");
        lvl3.add("3.003");
        lvl3.add("3.004");

        List<String> lvl4 = new ArrayList<>();
        lvl4.add("4.001");
        lvl4.add("4.002");
        lvl4.add("4.003");
        lvl4.add("4.004");

        List<String> lvl5 = new ArrayList<>();
        lvl5.add("5.001");
        lvl5.add("5.002");
        lvl5.add("5.003");

        listHash2.put(listDataHeader2.get(0),lvl1);
        listHash2.put(listDataHeader2.get(1),lvl2);
        listHash2.put(listDataHeader2.get(2),lvl3);
        listHash2.put(listDataHeader2.get(3),lvl4);
        listHash2.put(listDataHeader2.get(4),lvl5);
    }
}