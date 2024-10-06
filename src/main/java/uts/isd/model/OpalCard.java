package uts.isd.model;

import java.io.Serializable;

public class OpalCard implements Serializable {

    private int cardID;
    private String cardNumber;
    private String cardName;
    private String cardSecurityCode;
    private String cardStatus;
    private int userID;

    // Constructor
    public OpalCard(int cardID, String cardNumber, String cardName, String cardSecurityCode, String cardStatus,
            int userID) {
        this.cardID = cardID;
        this.cardNumber = cardNumber;
        this.cardName = cardName;
        this.cardSecurityCode = cardSecurityCode;
        this.cardStatus = cardStatus;
        this.userID = userID;
    }

    // Getters
    public int getCardID() {
        return cardID;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardName() {
        return cardName;
    }

    public String getCardSecurityCode() {
        return cardSecurityCode;
    }

    public String getCardStatus() {
        return cardStatus;
    }

    public int getUserID() {
        return userID;
    }

    // Setters
    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public void setCardSecurityCode(String cardSecurityCode) {
        this.cardSecurityCode = cardSecurityCode;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
