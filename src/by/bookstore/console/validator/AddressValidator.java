package by.bookstore.console.validator;

public class AddressValidator {
    public static boolean invalidStreet(String street){return street.length() < 2;}

    public static boolean invalidHome(int home){return home < 1;}

    public static boolean invalidId(int id){
        return id < 1;
    }

    public static boolean invalidAddress(String address){return address.length() < 2;}

}
