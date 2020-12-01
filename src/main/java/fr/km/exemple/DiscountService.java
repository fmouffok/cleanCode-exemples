/**
 * Optional to resolve NPE Exception
 */
package fr.km.exemple;

import java.util.Optional;

import static java.util.Optional.*;

public class DiscountService {

    /**
     *
     public String getDiscountLine(Customer customer){
     return "Discount: " + getApplicablePercentage(customer.getMemberCard());
     }

     private Integer getApplicablePercentage(MemberCard card) {
     if (card.getFidelity() >= 100){
     return 5;
     }
     if (card.getFidelity() >= 50){
     return 3;
     }
     return null;
     }

     public static void main(String[] args) {
     DiscountService service = new DiscountService();
     System.out.println(">" + service.getDiscountLine(new Customer(new MemberCard(60))));
     }
     }

     class Customer {


    private MemberCard memberCard;

    public MemberCard getMemberCard() {
        return memberCard;
    }

    public void setMemberCard(MemberCard memberCard) {
        this.memberCard = memberCard;
    }

    public Customer() {
    }

    public Customer(MemberCard memberCard) {
        this.memberCard = memberCard;
    }
}

class MemberCard{

    public MemberCard(int fidelity) {
        this.fidelity = fidelity;
    }

    private int fidelity;
    public int getFidelity() {
        return fidelity;
    }

    public void setFidelity(int fidelity) {
        this.fidelity = fidelity;
    }
}
     *
     */


    public String getDiscountLine(Customer customer){
        return customer.getMemberCard()
                .flatMap(d -> getApplicablePercentage(d))
                .map(p -> "Discount: "+p)
                .orElse("");

    }

    private Optional<Object> getApplicablePercentage(MemberCard card) {
        // this is a NPE here
        if (card.getFidelity() >= 100){
            return of(5);
        }
        if (card.getFidelity() >= 50){
            return of(3);
        }
        // replace null return by Optional empty
        return empty();
    }

    public static void main(String[] args) {
        DiscountService service = new DiscountService();
        System.out.println(">" + service.getDiscountLine(new Customer(new MemberCard(60))));
        System.out.println(">" + service.getDiscountLine(new Customer(new MemberCard(120))));
        System.out.println(">" + service.getDiscountLine(new Customer(new MemberCard(10))));
        System.out.println(">" + service.getDiscountLine(new Customer()));
    }
}

class Customer {
    /**
     * how to evict this horrible exception
     * NullPoinbterException exemple
     */

    enum Type{
        SIMPLE, SILVER, GOLD;

    }
    private Type type;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    private Integer id;
    private String firstName;
    private String lastName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    private MemberCard memberCard;

    private Profile profile;

    public Customer() {
    }

    public Customer(MemberCard memberCard) {
        this.memberCard = memberCard;
    }


    public Optional<MemberCard> getMemberCard() {
        return ofNullable(memberCard);
    }

    public void setMemberCard(MemberCard memberCard) {
        this.memberCard = memberCard;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
//    public Profile getProfile() {
//        return profile;
//    }

    public Optional<Profile> getProfileOpt(){
        return Optional.ofNullable(profile);
    }

}

class MemberCard{

    public MemberCard(int fidelity) {
        this.fidelity = fidelity;
    }

    private int fidelity;
    public int getFidelity() {
        return fidelity;
    }

    public void setFidelity(int fidelity) {
        this.fidelity = fidelity;
    }
}
