package fr.km.exemple;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

public class ProductService {

    private ProductRepo productRepo;
    public List<Product> getFrequentOrderedProducts(List<Order> orders){
        return orders.stream()
                .filter(o -> o.getCreationDate().isAfter(LocalDate.now().minusYears(1)))
                .flatMap(o -> o.getOrderLines().stream())
                .collect(groupingBy(OrderLine::getProduct, summingInt(OrderLine::getItemCount)))
                .entrySet()
                .stream()
                .filter(e->e.getValue() >= 10)
                .map(Map.Entry::getKey)
                .filter(p -> !p.isDeleted())
                .filter(p -> productRepo.getHiddenproductIds().contains(p.getId()))
                .collect(Collectors.toList());
    }

    public List<Product> getFrequentOrderedProducts1(List<Order> orders){
        List<Integer> hiddenProductIds = productRepo.getHiddenproductIds();
        Predicate<Product> productIsNotHidden = p -> !hiddenProductIds.contains(p.getId());
        return getFrequentProductOverLastYear(orders)
                .filter(Product::isNotDeleted)
                .filter(productIsNotHidden)
                .collect(Collectors.toList());

    }

    private Stream<Product> getFrequentProductOverLastYear(List<Order> orders) {
        return getProductCounts(orders)
                .entrySet()
                .stream()
                .filter(e -> e.getValue() >= 10)
                .map(Map.Entry::getKey);
    }

    private Map<Product, Integer> getProductCounts(List<Order> orders) {
        return orders.stream()
                .filter(this::orderWasCreatedDuringPreviousYear)
                .flatMap(o -> o.getOrderLines().stream())
                .collect(groupingBy(OrderLine::getProduct, summingInt(OrderLine::getItemCount)));
    }

    private boolean orderWasCreatedDuringPreviousYear(Order o) {
        return o.getCreationDate().isAfter(LocalDate.now().minusYears(1));
    }


}

interface ProductRepo{
    List<Integer> getHiddenproductIds();
}

class OrderLine {
    private Product product;
    private int itemCount;


    public static void activate(User user){
        if (user != null){
            // do something
        }
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public static void activate(OrderLine line) {
    }
}

class Product {
    private Integer id;
    private String name;
    private String description;
    private boolean deleted;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isNotDeleted() {
        return !deleted;
    }
}

class Order {
    private Integer id;
    private String name;
    private String description;
    private double price;
    private Customer customer;
    private LocalDate  creationDate;
    private boolean delivred;


    public boolean isNotDelivred() {
        return !delivred;
    }

    public boolean isDelivred() {
        return delivred;
    }

    public void setDelivred(boolean delivred) {
        this.delivred = delivred;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    private List<OrderLine> orderLines;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean hasDelivredAfter(Date date) {
        return true;
    }
}






