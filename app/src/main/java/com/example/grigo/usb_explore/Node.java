package com.example.grigo.usb_explore;

/**
 * Created by Jake on 21/02/2018.
 */

class Node {

    private Coord location;
    private int floor;

    Node(Coord location, int floor) {
        this.location = location;
        this.floor = floor;
    }

    Coord getLocation() {
        return location;
    }

    int getFloor()  {
        return floor;
    }
}
