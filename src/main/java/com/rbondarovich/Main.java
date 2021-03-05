package com.rbondarovich;

import com.opencsv.bean.CsvToBeanBuilder;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        String ordersFile = "D:\\JavaEE\\projects\\isSoft\\src\\main\\resources\\orders.csv";
        String orderItemsFile = "D:\\JavaEE\\projects\\isSoft\\src\\main\\resources\\orders.csv";
        String productFile = "D:\\JavaEE\\projects\\isSoft\\src\\main\\resources\\products.csv";

        List<Order> ordersList = new CsvToBeanBuilder(new FileReader(ordersFile))
                .withType(Order.class)
                .build()
                .parse();

        List<OrderItem> orderItems = new CsvToBeanBuilder(new FileReader(orderItemsFile))
                .withType(OrderItem.class)
                .build()
                .parse();

        List<Product> productsList = new CsvToBeanBuilder(new FileReader(productFile))
                .withType(OrderItem.class)
                .build()
                .parse();

       List <Order> orderInDay = new ArrayList<>();
       for(Order bean : ordersList) {
           if("2021-01-21".contains(bean.getDay())) orderInDay.add(bean);
       }



    }
}
