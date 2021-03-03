package by.bookstore.console.validator;

public class StoreValidator {

    public static boolean invalidId(int id){
        return id < 1;
    }

    public static boolean invalidTitle(String title){
        return title.length() < 2;
    }
}
