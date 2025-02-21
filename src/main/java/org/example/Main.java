package org.example;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        //Создаем список заказов
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0)
        );

        //Группируем заказы по продуктам
        Map<String, List<Order>> group = orders
                .stream()
                .collect(Collectors.groupingBy(Order::getProduct));

        //Находим общую стоимость всех заказов для продукта
        Map<String, Double> merge = group
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        x -> x.getValue().stream().mapToDouble(Order::getCost).sum()));

        //Сортируем по убыванию стоимости, Выводим три заказа с самой высокой стоимостью
        merge
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(3)
                .forEach((x) -> System.out.printf("Product: %s - Count: %f%n", x.getKey(), x.getValue()));

    }
}