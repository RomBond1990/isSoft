package com.rbondarovich;

import java.util.List;
import java.util.Map;

import static com.rbondarovich.CSVReaderDecorator.getListObjects;


public class Main {

    public static void main(String[] args) {

        String ordersFile = "src\\main\\resources\\orders.csv";
        String orderItemsFile = "src\\main\\resources\\order_items.csv";
        String productFile = "src\\main\\resources\\products.csv";

        List<Order> allOrders = getListObjects(ordersFile, Order.class);
        List<OrderItem> allOrderItems = getListObjects(orderItemsFile, OrderItem.class);
        List<Product> allProducts = getListObjects(productFile, Product.class);

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
