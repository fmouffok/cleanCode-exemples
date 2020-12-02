package fr.km.exemple;

/**
 * to be able to pass any function who don't have any parameter and return no thing
 * use a Runnable interface like mesureTime method
 */

public class WrapAround {


    // use a Runnable interface because it have  run void method without any parameters
    public static void main(String[] args) {
        mesureTime(WrapAround::insertCountries);
        mesureTime(WrapAround::insertLanguages);
    }

    public static void mesureTime(Runnable functionToMesure){
        long t0 = System.nanoTime();
        functionToMesure.run();
        long t1 = System.nanoTime();
        System.out.println("time : "+ (t1-t0));
    }

    public static void insertLanguages(){
        System.out.println("Insert languages");
    }

    public static void insertCountries(){
        System.out.println("Insert Countries");
    }


    // initial case
//    public static void main(String[] args) {
//        mesureTime_InsertCountries();
//        mesureTime_InsertLanguages();
//    }
//
//    public static void mesureTime_InsertCountries(){
//        long t0 = System.nanoTime();
//        insertCountries();
//        long t1 = System.nanoTime();
//        System.out.println("time : "+ (t1-t0));
//    }
//
//    public static void mesureTime_InsertLanguages(){
//        long t0 = System.nanoTime();
//        insertLanguages();
//        long t1 = System.nanoTime();
//        System.out.println("time : "+ (t1-t0));
//    }
//
//    public static void insertLanguages(){
//        System.out.println("Insert languages");
//    }
//
//    public static void insertCountries(){
//        System.out.println("Insert Countries");
//    }

}
