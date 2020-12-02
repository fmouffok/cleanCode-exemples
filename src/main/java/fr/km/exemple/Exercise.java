package fr.km.exemple;

import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Exercise {

    private CustomerMapper mapper;
    List<CustomerDto> getIdsOfCustomerDtoOfType(List<Customer> allCustomer, Customer.Type type){
        final Predicate<fr.km.exemple.Customer> customerPredicate = customer -> customer.getType() == type;
        return  allCustomer.stream()
                .filter(customerPredicate)
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    public static class CustomerMapper{
        public CustomerDto mapToDto(fr.km.exemple.Customer customer) {
            CustomerDto dto = new CustomerDto();
            dto.setId(customer.getId());
            dto.setName(customer.getLastName());
            return dto;
        }
    }
//    List<CustomerDto> getIdsOfCustomerDtoOfType(List<Customer> allCustomer, Customer.Type type){
//        final Predicate<fr.km.exemple.Customer> customerPredicate = customer -> customer.getType() == type;
//        return  allCustomer.stream()
//                .filter(customerPredicate)
//                .map(this::mapToDto)
//                .collect(Collectors.toList());
//    }

    private CustomerDto mapToDto(fr.km.exemple.Customer customer) {
        CustomerDto dto = new CustomerDto();
        dto.setId(customer.getId());
        dto.setName(customer.getLastName());
        return dto;
    }

    List<Integer> getIdsOfCustomerOfType(List<Customer> allCustomer, Customer.Type type){
        final Predicate<fr.km.exemple.Customer> customerPredicate = customer -> customer.getType() == type;
        return  allCustomer.stream()
                .filter(customerPredicate)
                .map(fr.km.exemple.Customer::getId)
                .collect(Collectors.toList());
    }

//    List<CustomerDto> getIdsOfCustomerDtoOfType(List<Customer> allCustomer, Customer.Type type){
//        final Predicate<fr.km.exemple.Customer> customerPredicate = customer -> customer.getType() == type;
//        return  allCustomer.stream()
//                .filter(customerPredicate)
//                .map(customer ->{
//                    CustomerDto dto = new CustomerDto();
//                    dto.setId(customer.getId());
//                    dto.setName(customer.getLastName());
//                    return dto;
//                })
//                .collect(Collectors.toList());
//    }
}


class CustomerDto{
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}



class Exerc3{

    public boolean hasOverdueOrders(List<Order> orders){
        final Predicate<Order> predicate = order -> order.hasDelivredAfter(new Date());
        return orders.stream().anyMatch(predicate);
    }

//    public boolean hasOverdueOrders(List<Order> orders){
//        return orders.stream()
//                .anyMatch(order -> order.hasDelivredAfter(new Date()));
//    }
}