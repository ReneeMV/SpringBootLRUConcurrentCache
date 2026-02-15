package com.reneeveit.concurrentcache.models;

///TODO: EDIT BASED ON UPDATED UNDERSTANDING OF OMS 
public class Order {
    private int orderId;
    private int instrumentId;
    private int quantity;
    private double price;
    private boolean status;

    public Order(int orderId){
        this.orderId = orderId;
    }

    public int getId(){
        return orderId;
    }

}
