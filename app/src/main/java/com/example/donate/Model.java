package com.example.donate;

public class Model {
    private int id;
    private String username;
    private String itemname;
    private byte[] proavatar;
    private String quantity;
    private String details;


    public Model(int id, String username, String itemname, String quantity, String details, byte[] proavatar) {
        this.id = id;
        this.username = username;
        this.itemname = itemname;
        this.proavatar = proavatar;
        this.quantity=quantity;
        this.details = details;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public byte[] getProavatar() {
        return proavatar;
    }

    public void setProavatar(byte[] proavatar) {
        this.proavatar = proavatar;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}