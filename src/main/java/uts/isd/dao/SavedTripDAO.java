package uts.isd.dao;

import uts.isd.model.SavedTrip;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SavedTripDAO {
    private Connection conn;

    public SavedTripDAO(Connection conn) {
        this.conn = conn;
    }

    // Add a new saved trip
    public void addSavedTrip(SavedTrip trip) throws SQLException {
        String query = "INSERT INTO SavedTrip (tripName, startLocation, destination, userID) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, trip.getTripName());
            stmt.setString(2, trip.getStartLocation());
            stmt.setString(3, trip.getDestination());
            stmt.setString(4, trip.getUserID());
            stmt.executeUpdate();
        }
    }

    // Retrieve all saved trips for a specific user
    public List<SavedTrip> getTripsByUserID(String userID) throws SQLException {
        List<SavedTrip> trips = new ArrayList<>();
        String query = "SELECT * FROM SavedTrip WHERE userID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, userID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                SavedTrip trip = new SavedTrip(
                        rs.getString("tripID"),
                        rs.getString("tripName"),
                        rs.getString("startLocation"),
                        rs.getString("destination"),
                        rs.getString("userID"));
                trips.add(trip);
            }
        }
        return trips;
    }

    // Update the name of a saved trip
    public void updateSavedTripName(String tripID, String newTripName) throws SQLException {
        String query = "UPDATE SavedTrip SET tripName = ? WHERE tripID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, newTripName);
            stmt.setString(2, tripID);
            stmt.executeUpdate();
        }
    }

    // Delete a saved trip by tripID
    public void deleteSavedTrip(String tripID) throws SQLException {
        String query = "DELETE FROM SavedTrip WHERE tripID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, tripID);
            stmt.executeUpdate();
        }
    }
}
