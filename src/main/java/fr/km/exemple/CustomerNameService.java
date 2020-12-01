package fr.km.exemple;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerNameService {

    private CustomeRpo repo ;

    //private ABMapper mapper;


    public List<B> getAllCustomers(){
        List<Customer> customers = repo.getCustomers();
        return customers.stream()
                .map(B::new)
                .collect(Collectors.toList());
    }


//    public List<B> getAllCustomers(){
//        List<Customer> customers = repo.getCustomers();
//        return customers.stream()
//                .map(a -> new B(a))
//                .collect(Collectors.toList());
//    }
//    // we don't need to database
//    public static class ABMapper{
//        public B mapToB(Customer a) {
//            return new B(a);
//        }
//    }


//    public List<B> getAllCustomers(){
//        List<Customer> customers = repo.getCustomers();
//        return customers.stream()
//                .map(mapper::mapToB)
//                .collect(Collectors.toList());
//    }
//    // we don't need to database
//    public static class ABMapper{
//        public B mapToB(Customer a) {
//            return new B(a);
//        }
//    }

//    public List<B> getAllCustomers(){
//        List<Customer> customers = repo.getCustomers();
//        return customers.stream()
//                .map(mapper::mapToB)
//                .collect(Collectors.toList());
//    }
//
//    public static class ABMapper{
//        public B mapToB(Customer a) {
//            B b = new B();
//            b.setFirstName(a.getFirstName());
//            b.setLastName(a.getLastName());
//            return b;
//        }
//    }

//
//    public List<B> getAllCustomers(){
//        List<Customer> customers = repo.getCustomers();
//        return customers.stream()
//                .map(this::mapToB)
//                .collect(Collectors.toList());
//    }
//
//    private B mapToB(Customer a) {
//        B b = new B();
//        b.setFirstName(a.getFirstName());
//        b.setLastName(a.getLastName());
//        return b;
//    }


//        public List<B> getAllCustomers(){
//        List<Customer> customers = repo.getCustomers();
//        return customers.stream()
//                .map(a -> {
//                    B b = new B();
//                    b.setFirstName(a.getFirstName());
//                    b.setLastName(a.getLastName());
//                    return b;
//                })
//                .collect(Collectors.toList());
//    }
}


class B{
    private String firstName;
    private String lastName;

    public B() {
    }

    public B(Customer customer) {
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
interface CustomeRpo {
    List<Customer> getCustomers();
}
