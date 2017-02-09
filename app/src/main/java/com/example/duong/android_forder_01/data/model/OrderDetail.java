package com.example.duong.android_forder_01.data.model;

public class OrderDetail {
    private String mID;
    private Orders mOrders;
    private Product mProduct;
    private int mQuantity;

    public String getID() {
        return mID;
    }

    public void setID(String ID) {
        mID = ID;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int quantity) {
        mQuantity = quantity;
    }

    public Orders getOrders() {
        return mOrders;
    }

    public void setOrders(Orders orders) {
        mOrders = orders;
    }

    public Product getProduct() {
        return mProduct;
    }

    public void setProduct(Product product) {
        mProduct = product;
    }
}
