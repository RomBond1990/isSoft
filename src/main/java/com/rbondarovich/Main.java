package com.rbondarovich;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        String ordersFile = "src\\main\\resources\\orders.csv";
        String orderItemsFile = "src\\main\\resources\\order_items.csv";
        String productFile = "src\\main\\resources\\products.csv";

        List<Order> allOrders = new CsvToBeanBuilder(new FileReader(ordersFile))
                .withType(Order.class)
                .build()
                .parse();

        List<OrderItem> allOrderItems = new CsvToBeanBuilder(new FileReader(orderItemsFile))
                .withType(OrderItem.class)
                .build()
                .parse();

        List<Product> allProducts = new CsvToBeanBuilder(new FileReader(productFile))
                .withType(Product.class)
                .build()
                .parse();

        Map<String, List<Order>> ordersByDay = OrderUtils.groupOrdersByDay(allOrders);

        for (Map.Entry<String, List<Order>> entry :
                ordersByDay.entrySet()) {

            List<Order> OrdersByDay = entry.getValue();
            Map<String, Integer> quantityByProductId = OrderUtils.findAllProductsQuantityByDay(OrdersByDay, allOrderItems);
            Map<String, Integer> totalPriceByProduct = OrderUtils.getTotalPriceByProductNameForOneDay(quantityByProductId, allProducts);
            System.out.print("Сегодня " + entry.getKey() + " ");
            OrderUtils.getBestProductByDay(totalPriceByProduct);
        }

    }















}
