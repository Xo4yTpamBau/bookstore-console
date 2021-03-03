package by.bookstore.console.validator;

public class AuthorValidator {

    public static boolean invalidName(String name){ return name.length() < 2; }

    public static boolean invalidId(int id){
        return id < 1;
    }

    public static boolean invalidDescription(String description){
        return description.length() < 2;
    }
}
