package uts.isd.model;

import java.io.Serializable;

public class SavedTrip implements Serializable {
    private String tripID;
    private String tripName; // A name for the saved trip (e.g., "Home to Office")
    private String startLocation; // Current location
    private String destination; // Destination
    private String userID; // Links the trip to a user

    // Constructor
    public SavedTrip(String tripID, String tripName, String startLocation, String destination, String userID) {
        this.tripID = tripID;
        this.tripName = tripName;
        this.startLocation = startLocation;
        this.destination = destination;
        this.userID = userID;
    }

    // Getters and setters
    public String getTripID() {
        return tripID;
    }

    public void setTripID(String tripID) {
        this.tripID = tripID;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
