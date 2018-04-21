package com.example.grigo.usb_explore;

import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.example.grigo.usb_explore.R.layout;
import com.example.grigo.usb_explore.R.id;

import java.util.OptionalInt;
import java.util.stream.IntStream;

public class PinViewFragment extends Fragment {

    private PinView mapView;
    private View rootView;
    static int floorNum = 0; // floor to start with

    // stores the scale and the pan of the map when changing floors
    private static float mapScale;
    private static PointF mapCentre;

    private Button btnDirectionsTo; // sets directionsToEnabled to true
    private Button btnCancelDirectionsTo;
    private Button btnGo; // sets navigationModeEnabled to true
    static boolean directionsToEnabled;
    static boolean navigationModeEnabled;

    // stores the name and floor number of the first pin
    static String pinFromRoomName;
    static int pinFromFloorNum = -1; // initiated to an unreachable floor
    static int pinFromIdx;

    // stores the name and floor number of the second pin
    static String pinToRoomName;
    static int pinToFloorNum = -1; // initiated to an unreachable floor
    static int pinToIdx;

    Button btnFloor0;
    Button btnFloor1;
    Button btnFloor2;
    Button btnFloor3;
    Button btnFloor4;
    Button btnFloor5;
    Button btnFloor6;

    Button btnDisabledAccess;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(layout.pin_view_fragment, container, false);

        mapSetup(); // sets up the map
        buttonSetup(); // sets up all the buttons
        pinSetup(); // sets up the pins for that floor

        mapView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return gestureDetector.onTouchEvent(motionEvent);
            }
        });

        return rootView;
    }

    GestureDetector gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {

            if (mapView.isReady() && !navigationModeEnabled) { // ensure that map is ready and that not in navigation mode (pins shouldn't be able to be added)

                PointF pointF = mapView.viewToSourceCoord(e.getX(), e.getY()); // tap location on mapView
                System.out.println((int)pointF.x + ", " + (int)pointF.y); // (debugging) prints out the touch coordinates on mapView

                // finds matching room coordinates based on floorList room coordinates
                for (int i = 0; i < MapActivity.floorList.get(floorNum).getRoomsList().size(); i++) {
                    if (pointF.x > MapActivity.floorList.get(floorNum).getRoomsList().get(i).getTopLeftArea().getX()
                            && pointF.x < MapActivity.floorList.get(floorNum).getRoomsList().get(i).getBottomRightArea().getX()
                            && pointF.y > MapActivity.floorList.get(floorNum).getRoomsList().get(i).getTopLeftArea().getY()
                            && pointF.y < MapActivity.floorList.get(floorNum).getRoomsList().get(i).getBottomRightArea().getY()) {

                        setPin(MapActivity.floorList.get(floorNum).getRoomsList().get(i).getRoomName(), i); // calls method that sets the pin at the correct coordinates for the tapped room
                        System.out.println(MapActivity.floorList.get(floorNum).getRoomsList().get(i).getRoomName()); // (debugging) prints out room name
                    }
                }
            }
            return true;
        }
    });


    public void mapSetup() {
        mapView = rootView.findViewById(id.mapView);
        mapView.setImage(ImageSource.asset(MapActivity.floorList.get(floorNum).getFloorMap()));
        mapView.setScaleAndCenter(mapScale, mapCentre); // ensures that scaling is constant across floors

        mapView.setPanLimit(SubsamplingScaleImageView.PAN_LIMIT_INSIDE);
        mapView.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_CUSTOM);
        mapView.setDoubleTapZoomStyle(SubsamplingScaleImageView.ZOOM_FOCUS_CENTER);
        mapView.setMinimumDpi(600); // sets how zoomed in the user can go
    }

    public void buttonSetup() {

        // if directions to button pressed
        btnDirectionsTo = rootView.findViewById(id.btnDirectionsTo);
        btnDirectionsTo.setOnClickListener((View view) -> {
            btnCancelDirectionsTo.setVisibility(View.VISIBLE); // makes cancel button visible
            btnDirectionsTo.setEnabled(false); // button already pressed, so disable it
            directionsToEnabled = true; // now in directions to ("mode")
        });

        // if go button pressed
        btnGo = rootView.findViewById(id.btnGo);
        btnGo.setOnClickListener((View view) -> {

            btnDirectionsTo.setVisibility(View.INVISIBLE); // makes directions to button invisible
            btnGo.setVisibility(View.INVISIBLE); // makes go button invisible
            navigationModeEnabled = true; // now in navigation mode

            // change to the floor where the navigation starts
            if (floorNum != pinFromFloorNum) { // if change needed
                floorNum = pinFromFloorNum;
                updateMap();
            } else {
                mapView.setScaleAndCenter(mapView.getScale(), mapView.getCenter()); // method allows onDraw to be called without the need of updateMap method
            }
        });

        // if cancel button pressed
        btnCancelDirectionsTo = rootView.findViewById(id.btnCancelDirectionsTo);
        btnCancelDirectionsTo.setOnClickListener((View view) -> {

            if (navigationModeEnabled) {
                mapView.removePin("PIN FROM");
                btnDirectionsTo.setVisibility(View.INVISIBLE);
                // resets details for when floor is changed
                pinFromRoomName = null;
                pinFromFloorNum = -1;
            }

            mapView.removePin("PIN TO");
            // resets details for when floor is changed
            pinToRoomName = null;
            pinToFloorNum = -1;

            btnDirectionsTo.setEnabled(true);
            btnCancelDirectionsTo.setVisibility(View.INVISIBLE);
            btnGo.setVisibility(View.INVISIBLE);

            directionsToEnabled = false; // "mode" cancelled
            navigationModeEnabled = false; // "mode" cancelled

            // change floor to from pin floor; if it does not exist, stay on current floor
            if (floorNum != pinFromFloorNum & pinFromFloorNum != -1) {
                floorNum = pinFromFloorNum;
                updateMap();
            }
        });


        // general floor button listeners (changes the floors)

        btnFloor0 = rootView.findViewById(id.btnFloor0);
        btnFloor0.setOnClickListener((View view) -> {
            if (floorNum != 0) {
                floorNum = 0;
                updateMap();
            }
        });

        btnFloor1 = rootView.findViewById(id.btnFloor1);
        btnFloor1.setOnClickListener((View view) -> {
            if (floorNum != 1) {
                floorNum = 1;
                updateMap();
            }
        });

        btnFloor2 = rootView.findViewById(id.btnFloor2);
        btnFloor2.setOnClickListener((View view) -> {
            if (floorNum != 2) {
                floorNum = 2;
                updateMap();
            }
        });

        btnFloor3 = rootView.findViewById(id.btnFloor3);
        btnFloor3.setOnClickListener((View view) -> {
            if (floorNum != 3) {
                floorNum = 3;
                updateMap();
            }
        });

        btnFloor4 = rootView.findViewById(id.btnFloor4);
        btnFloor4.setOnClickListener((View view) -> {
            if (floorNum != 4) {
                floorNum = 4;
                updateMap();
            }
        });

        btnFloor5 = rootView.findViewById(id.btnFloor5);
        btnFloor5.setOnClickListener((View view) -> {
            if (floorNum != 5) {
                floorNum = 5;
                updateMap();
            }
        });

        btnFloor6 = rootView.findViewById(id.btnFloor6);
        btnFloor6.setOnClickListener((View view) -> {
            if (floorNum != 6) {
                floorNum = 6;
                updateMap();
            }
        });

        btnDisabledAccess = rootView.findViewById(id.btnDisabledAccess);
        btnDisabledAccess.setOnClickListener((View view) -> {



        });
    }

    public void pinSetup() {
        if (pinFromFloorNum == floorNum) { // if pin from floor number is current floor number
            boolean directionsToEnabledTemp = directionsToEnabled;
            directionsToEnabled = false;
            setPin(pinFromRoomName, pinFromIdx);
            directionsToEnabled = directionsToEnabledTemp; // resets the boolean to previous value
        }
        if (pinToFloorNum == floorNum) { // if pin to floor number is current floor number
            setPin(pinToRoomName, pinToIdx);
        }


        if (pinFromFloorNum != -1) { // if from pin has been dropped
            btnDirectionsTo.setVisibility(View.VISIBLE); // enable the directions to button

            if (directionsToEnabled) { // if directions to button pressed
                btnDirectionsTo.setEnabled(false); // disable the directions to button
                btnCancelDirectionsTo.setVisibility(View.VISIBLE); // enable the cancel button

                if (pinToFloorNum != -1) { // if to pin has been dropped
                    btnGo.setVisibility(View.VISIBLE);

                    if (navigationModeEnabled) { // if in navigation mode (after go button pressed)
                        btnDirectionsTo.setVisibility(View.INVISIBLE); // hide directions to button
                        btnGo.setVisibility(View.INVISIBLE);
                    }
                }
            }
        }
    }

    public void setPin(String roomName, int idx) {
        // gets pin location for the room (from floorList room coordinates)
        PointF pointF1 = new PointF((float)MapActivity.floorList.get(floorNum).getRoomsList().get(idx).getPinLocation().getX(),
                (float)MapActivity.floorList.get(floorNum).getRoomsList().get(idx).getPinLocation().getY());

        if (!directionsToEnabled) { // only one pin on the map
            mapView.setPin(pointF1, "PIN FROM"); // sets the pin
            btnDirectionsTo.setVisibility(View.VISIBLE); // makes directions to button visible

            // stores details for when floor is changed
            pinFromRoomName = roomName;
            pinFromFloorNum = floorNum;

            pinFromIdx = idx;

        } else { // two pins on the map
            mapView.setPin(pointF1, "PIN TO");
            btnGo.setVisibility(View.VISIBLE); // makes go button visible

            // stores details for when floor is changed
            pinToRoomName = roomName;
            pinToFloorNum = floorNum;

            pinToIdx = idx;
        }
    }

    public int getPinIdx(String roomName, int floorNum) {
        OptionalInt indexOpt = IntStream.range(0, MapActivity.floorList.get(floorNum).getRoomsList().size())
                .filter(i -> MapActivity.floorList.get(floorNum).getRoomsList().get(i).getRoomName().equals(roomName))
                .findFirst();
        if (indexOpt.isPresent()) {
            return indexOpt.getAsInt();
        } else {
            return -1;
        }
    }

    public void updateMap() {
        mapScale = mapView.getScale();
        mapCentre = mapView.getCenter();
        ((MapActivity)getActivity()).onPageChanged(1);
    }
}