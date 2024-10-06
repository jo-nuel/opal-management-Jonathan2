package uts.isd.dao;

import java.sql.*;
import java.util.ArrayList;
import uts.isd.model.OpalCard;

public class OpalCardDAO {
    private Connection conn;

    public OpalCardDAO(Connection conn) {
        this.conn = conn;
    }

    // Method to add a new Opal card
    public void addOpalCard(OpalCard card) throws SQLException {
        String query = "INSERT INTO opalcard (cardNumber, cardName, cardSecurityCode, cardStatus, userID) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, card.getCardNumber());
            stmt.setString(2, card.getCardName());
            stmt.setString(3, card.getCardSecurityCode()); // Added security code
            stmt.setString(4, card.getCardStatus());
            stmt.setInt(5, card.getUserID());
            stmt.executeUpdate();
        }
    }

    // Method to get all cards for a specific user account
    public ArrayList<OpalCard> getCardsByUserId(int userId) throws SQLException {
        ArrayList<OpalCard> cards = new ArrayList<>();
        String query = "SELECT * FROM opalcard WHERE userID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                OpalCard card = new OpalCard(
                        rs.getInt("cardID"),
                        rs.getString("cardNumber"),
                        rs.getString("cardName"),
                        rs.getString("cardSecurityCode"),
                        rs.getString("cardStatus"),
                        rs.getInt("userID"));
                cards.add(card);
            }
        }
        return cards;
    }

    // Method to delete an Opal card by cardId
    public void deleteOpalCard(int cardId) throws SQLException {
        String query = "DELETE FROM opalcard WHERE cardID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, cardId);
            stmt.executeUpdate();
        }
    }

    // Method to update the card nickname
    public void updateOpalCardName(int cardId, String newCardName) throws SQLException {
        String query = "UPDATE opalcard SET cardName = ? WHERE cardID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, newCardName);
            stmt.setInt(2, cardId);
            stmt.executeUpdate();
        }
    }
}
