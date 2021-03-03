package by.bookstore.console.validator;

public class OrderValidator {

    public static boolean invalidId(int id){
        return id < 1;
    }
}
