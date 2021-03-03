package by.bookstore.console.validator;

public class UserValidator {

    public static boolean invalidName(String name){ return name.length() < 2; }

    public static boolean invalidLogin(String login){ return login.length() < 2; }

    public static boolean invalidPassword(String password){ return password.length() < 2; }

    public static boolean invalidAge(int age){return age < 0;}

    public static boolean invalidId(int id){
        return id < 1;
    }
}
