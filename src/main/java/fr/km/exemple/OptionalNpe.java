package fr.km.exemple;

import jdk.nashorn.internal.objects.annotations.Setter;

import java.util.Optional;

public class OptionalNpe {

    public static void main(String[] args) {
        Customer customer = new Customer();
//        double discount= customer.getProfile().getGoldCard().getDiscount();

        //step1
//        double discount = customer.getProfileOpt()
//                .map(Profile::getGoldCard)
//                .map(GoldCard::getDiscount)
//                .orElse(0d);

        // step3
        double discount = customer.getProfileOpt()
                .flatMap(Profile::getGoldCardOpt)
                .map(GoldCard::getDiscount)
                .orElse(0d);
        System.out.println("discount:  "+discount);
    }
}

class Profile {
    private GoldCard goldCard;

    public Optional<GoldCard> getGoldCardOpt() {
        return Optional.ofNullable(goldCard);
    }
//    public GoldCard getGoldCard() {
//        return goldCard;
//    }


    public void setGoldCard(GoldCard goldCard) {
        this.goldCard = goldCard;
    }
}

class GoldCard {
    private double discount;

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}