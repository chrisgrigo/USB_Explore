package com.example.grigo.usb_explore;

/**
 * Created by Jake on 21/02/2018.
 */

class Room {

    private String roomName;
    private Coord topLeftArea;
    private Coord bottomRightArea;
    private Coord pinLocation;
    private Node node;

    Room(String roomName, Coord topLeftArea, Coord bottomRightArea, Coord pinLocation, Node node) {
        this.roomName = roomName;
        this.topLeftArea = topLeftArea;
        this.bottomRightArea = bottomRightArea;
        this.pinLocation = pinLocation;
        this.node = node;
    }

    String getRoomName() {
        return roomName;
    }

    Coord getTopLeftArea() {
        return topLeftArea;
    }

    Coord getBottomRightArea() {
        return bottomRightArea;
    }

    Coord getPinLocation() {
        return pinLocation;
    }

    Node getNode() {
        return node;
    }

}
