package com.rbondarovich;

import java.util.*;

public class OrderUtils {

    public static Map<String, Integer> getTotalPriceByProductNameForOneDay(Map<String, Integer> quantityByProductId, List<Product> allProducts) {
        Map<String, Integer> totalPriceByProduct = new HashMap<>();
        for (Map.Entry<String, Integer> e :
                quantityByProductId.entrySet()) {
            Product product = getProductById(allProducts, e.getKey());
            int productPrice = 0;
            try {
                productPrice = Integer.parseInt(product.getPricePerUnit());
            } catch (NumberFormatException ex) {
                System.out.println("Некоректное значение цены у продука: " + product.getName());
            }
            int totalPriceByDay = productPrice * e.getValue();
            totalPriceByProduct.put(product.getName(), totalPriceByDay);
        }
        return totalPriceByProduct;
    }

    public static Map<String, List<Order>> groupOrdersByDay(List<Order> allOrders) {
        Map<String, List<Order>> ordersByDay = new TreeMap<>();

        for (Order order : allOrders) {
            String day = order.getDay().substring(0, 10);
            if (!ordersByDay.containsKey(day)) {
                ordersByDay.put(day, new ArrayList<>(Arrays.asList(order)));
            } else {
                ordersByDay.get(day).add(order);
            }
        }
        return ordersByDay;
    }

    public static Map<String, Integer> findAllProductsQuantityByDay(List<Order> ordersByDay, List<OrderItem> allOrders) {
        Map<String, Integer> quantityByProductId = new HashMap<>();

        //Поиск заказов сделанных в день todayOrders среди ВСЕХ ЗАКАЗОВ
        for (Order todayOrder : ordersByDay) {
            for (OrderItem oneOfAll : allOrders) {
                if (todayOrder.getId().equals(oneOfAll.getId())) {
                    int quantity = 0;
                    try {
                        quantity = Integer.parseInt(oneOfAll.getQuantity());
                    } catch (NumberFormatException e) {
                        System.out.println("У заказа " + oneOfAll.getId() + " некорректно указано количество");
                    }
                    quantityByProductId.merge(oneOfAll.getProductId(), quantity, (oldQuantity, newQuantity) -> oldQuantity + newQuantity);
                }
            }
        }
        return quantityByProductId;
    }

    public static Integer getQuantityByProductId(Map<String, Integer> quantityByProductId, String id) {
        return quantityByProductId.entrySet().stream()
                .filter(key -> id.equals(key.getKey()))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(null);
    }

    public static Product getProductById(List<Product> products, String productId) {
        for (Product product : products) {
            if (product.getId().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    public static void getBestProductByDay(Map<String, Integer> map) {
        String keyByMaxValue = map.entrySet().stream()
                .max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1)
                .get().getKey();
        System.out.println("Товар дня: " + keyByMaxValue + ". Продано на сумму: " + map.get(keyByMaxValue));
    }

}
