package fr.km.exemple;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class HelloService {

    public static void main(String[] args) {
        List<Order> orders = new ArrayList<>();

        boolean found = orders.stream()
                .anyMatch(order -> order.isNotDelivred());

		boolean found2 = orders.stream()
			.filter(order -> order.isNotDelivered())
			.count() >= 1;

		boolean found3 = orders.stream()
			.filter(order -> order.isNotDelivered())
			.findAny()
			.isPresent();

		boolean found4 = !orders.stream()
			.filter(order -> order.isNotDelivered())
			.collect(toList())
			.isEmpty();

		boolean found5 = orders.stream()
			.filter(order -> order.isNotDelivered())
			.collect(toList())
			.size() >= 1;
    }
}
