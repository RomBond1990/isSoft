package com.rbondarovich;

import com.opencsv.bean.CsvBindByName;


public class OrderItem {


    @CsvBindByName(column = "ORDER_ID")
    private String id;

    @CsvBindByName(column = "PRODUCT_ID")
    private String productId;

    @CsvBindByName(column = "QUANTITY")
    private String quantity;


    public OrderItem() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}

