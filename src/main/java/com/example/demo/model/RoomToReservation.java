package com.example.demo.model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
@Component
@SessionScope
public class RoomToReservation {
    private Room room;

    public RoomToReservation() {
    }

    public RoomToReservation(Room room) {
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void clear(){
        this.room = null;
    }
}
