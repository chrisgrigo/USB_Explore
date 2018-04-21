package com.example.grigo.usb_explore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jake on 28/02/2018.
 */

class Floor {

    private int floorNum;
    private String floorMap;
    private List<Room> roomsList = new ArrayList<>();
    private List<Node> nodesList = new ArrayList<>();

    Floor(int floorNum, String floorMap) {
        this.floorNum = floorNum;
        this.floorMap = floorMap;
    }

    String getFloorMap() {
        return floorMap;
    }

    void addRoom(Room room) {
        roomsList.add(room);
    }

    List<Room> getRoomsList() {
        return roomsList;
    }

    void addNode(Node node) {
        nodesList.add(node);
    }

    List<Node> getNodesList() {
        return nodesList;
    }
}
