package fr.km.exemple;

import org.hibernate.annotations.CreationTimestamp;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class MovieService {


    public static void main(String[] args) {
        final Movies movies = new Movies();
        movies.setType(Movies.MovieType.NEW_RELEASE);
        System.out.println(movies.computePrice(5));

        //System.out.println(new Movies().setType(Movies.MovieType.NEW_RELEASE).computePrice(4));
        // polymorphism
        //System.out.println(new ChildreenMovie().computePrice(4));
    }
}

// use HashMap between type and Function
class Movies{
    enum MovieType{
        REGULAR, NEW_RELEASE, CHILDREN;
    }

    private MovieType type;

    public static Map<MovieType, Function<Integer, Integer>> priceAlgo = new HashMap<>();

    static {
        priceAlgo.put(MovieType.REGULAR, days -> days + 1);
        priceAlgo.put(MovieType.CHILDREN, days -> 5);
        priceAlgo.put(MovieType.NEW_RELEASE, days -> days * 2);
    }

    public MovieType getType() {
        return type;
    }
    public void setType(MovieType type) {
        this.type = type;
    }

    public int computePrice(int days){
        // NPE
        return priceAlgo.get(type).apply(days);
    }
}


// enum with functions
//class Movies{
//    enum MovieType{
//        REGULAR {
//            @Override
//            public int computePrice(int days) {
//                return days+1;
//            }
//        }, NEW_RELEASE{
//            @Override
//            public int computePrice(int days) {
//                return days*2;
//            }
//        }
//        , CHILDREN{
//            @Override
//            public int computePrice(int days) {
//                return 5;
//            }
//        };
//        public abstract int computePrice(int days);
//    }
//
//    private MovieType type;
//
//    public MovieType getType() {
//        return type;
//    }
//    public void setType(MovieType type) {
//        this.type = type;
//    }
//
//    public Movies() {
//    }
//
//    public int computePrice(int days){
//        return type.computePrice(days);
//    }
//}

// polymorphism
//abstract class Movies{
//    enum MovieType{
//        REGULAR, NEW_RELEASE, CHILDREN;
//    }
//    private MovieType type;
//    public abstract int computePrice(int days);
//    public MovieType getType() {
//        return type;
//    }
//    public void setType(MovieType type) {
//        this.type = type;
//    }
//}
//class ChildreenMovie extends Movies{
//    public int computePrice(int days){
//        return 5;
//    }
//}
//
//class RegularMovie extends Movies{
//    public int computePrice(int days){
//        return  days+1;
//    }
//}
//
//class NewReleaseMovie extends Movies{
//    public int computePrice(int days){
//        return days*2;
//    }
//}

//initial case
//class Movies{
//    enum MovieType{
//        REGULAR, NEW_RELEASE, CHILDREN;
//    }
//
//    private MovieType type;
//
//    public MovieType getType() {
//        return type;
//    }
//    public void setType(MovieType type) {
//        this.type = type;
//    }
//
//    public Movies(MovieType type) {
//        this.type = type;
//    }
//
//    public int computePrice(int days){
//        switch (type){
//            case REGULAR: return days+1;
//            case NEW_RELEASE: return days*2;
//            case CHILDREN: return 5;
//            default:throw new IllegalArgumentException("Case not possible : "+type);
//        }
//    }
//}