package fr.km.exemple;

import org.mockito.Mockito;

import java.util.function.BiFunction;

import static org.mockito.Mockito.when;

public class TypeSpecific_Functionnality {




    public TypeSpecific_Functionnality() {

    }

    public static void main(String[] args) {
        MovieFactorRepo repo = Mockito.mock(MovieFactorRepo.class);
        when(repo.getFactor()).thenReturn(2d);
        PriceService priceService = new PriceService(repo);
        System.out.println(priceService.computePrice(Movie.Type.NEW_RELEASE, 5));
        System.out.println(priceService.computePrice(Movie.Type.REGULAR, 5));
        System.out.println(priceService.computePrice(Movie.Type.CHILDREN, 5));

    }

}

//class Movie {
//    enum Type{
//        REGULAR, NEW_RELEASE, CHILDREN, ELDER
//    }
//    private final Type type;
//
//    public Movie(Type type) {
//        this.type = type;
//    }
//    public int computePrice(int days){
//        switch (type){
//            case REGULAR: return days+1;
//            case NEW_RELEASE: return days*2;
//            case CHILDREN: return 5;
//            case ELDER: return 1;
//            default:throw new IllegalArgumentException("Case not possible : "+type);
//        }
//    }
//}
//
//abstract class Movie{
//    public abstract int computePrice(int days);
//}
//class RegularMovie extends Movie{
//
//    @Override
//    public int computePrice(int days) {
//        return 0;
//    }
//}
//class ChildrenMovie extends Movie{
//    @Override
//    public int computePrice(int days) {
//        return 0;
//    }
//}
//
//class NewReleaseMovie extends Movie{
//    @Override
//    public int computePrice(int days) {
//        return 0;
//    }
//}

//class Moviestep1 {
//    enum Type{
//        REGULAR{
//            public int computePrice(int days) {
//                return days+1;
//            }
//        }, NEW_RELEASE{
//            public int computePrice(int days) {
//                return days*2;
//            }
//        }, CHILDREN{
//            public int computePrice(int days) {
//                return 5;
//            }
//        };
//        public abstract int computePrice(int days);
//    }
//    private final Type type;
//
//    public Movie(Type type) {
//        this.type = type;
//    }
//    public int computePrice(int days){
//        return type.computePrice(days);
//    }
//}

// it will inject data (entity) from the data base
//class Movie {
//    enum Type{
//        REGULAR{
//            public int computePrice(int days) {
//                return days + 1;
//            }
//        }, NEW_RELEASE{
//            public int computePrice(int days) {
//                return days * 2; // 2 to be taken from database ??
//            }
//        }, CHILDREN{
//            public int computePrice(int days) {
//                return 5;
//            }
//        };
//        public abstract int computePrice(int days);
//    }
//    private final Type type;
//
//    public Movie(Type type) {
//        this.type = type;
//    }
//    public int computePrice(int days){
//        return type.computePrice(days);
//    }
//}

class Movie {
    enum Type{
        REGULAR(PriceService::computeRegularPrice),
        NEW_RELEASE(PriceService::computeNewReleasePrice),
        CHILDREN(PriceService::computeChildrenPrice);
        public final BiFunction<PriceService, Integer, Integer> priceAlgo;

        Type(BiFunction<PriceService, Integer, Integer> priceAlgo) {
            this.priceAlgo = priceAlgo;
        }
    }

    private final Type type;

    public Movie(Type type) {
        this.type = type;
    }
}
// repository
interface MovieFactorRepo{
    Double getFactor();
}

class PriceService{
    private  final MovieFactorRepo repo;

    public PriceService(MovieFactorRepo repo) {
        this.repo = repo;
    }

    protected Integer computeNewReleasePrice(int days){
        return (int) (repo.getFactor() * days);
    }
    protected Integer computeRegularPrice(int days){
        return days +1;
    }
    protected Integer computeChildrenPrice(int days){
        return 5;
    }

    public Integer computePrice(Movie.Type type, int days){
//        switch (type){
//            case REGULAR: return computeRegularPrice(days);
//            case NEW_RELEASE: return computeNewReleasePrice(days);
//            case CHILDREN: return computeChildrenPrice(days);
//            default: throw new IllegalArgumentException("bad case") ;
//        }
        return  type.priceAlgo.apply(this, days);
    }
}