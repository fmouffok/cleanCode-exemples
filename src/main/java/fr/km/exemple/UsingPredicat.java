package fr.km.exemple;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class UsingPredicat {

    List<Order> orders = new ArrayList<>();

    Predicate<Order> needstracking = order -> order.getPrice() > 1000;

//    Set<Customer> customer = orders.stream()
//            .filter(deliveryDueBefire(new Date()).or(needstracking))
//            .filter(this::hasLinesInStock)
//            .map(Order::getCustomer).collect(Collectors.toSet());



    private boolean hasLinesInStock(Order order) {
        return true;
    }

    private void deliveryDueBefire(Date date){

    }

}
