package by.bookstore.console.action;

import by.bookstore.console.ConsoleApplication;
import by.bookstore.console.util.ConsoleReader;
import by.bookstore.console.util.ConsoleWriter;
import by.bookstore.console.validator.AddressValidator;
import by.bookstore.console.validator.UserValidator;
import by.bookstore.entity.*;
import by.bookstore.service.UserService;
import by.bookstore.service.UserServiceImpl;
import by.bookstore.storage.AddressStorage;

public class UserActionImpl implements UserAction {

    private UserService userService = new UserServiceImpl();

    @Override
    public void save() {
        User user = createUser(Role.USER);
        userService.save(user);
    }

    private User createUser(Role role) {
        ConsoleWriter.writeString("Enter the name");
        String name = ConsoleReader.readString();

        if (UserValidator.invalidName(name)) {
            ConsoleWriter.writeString("Name is invalidate");
            return null;
        }

        ConsoleWriter.writeString("Enter the login");
        String login = ConsoleReader.readString();

        if (UserValidator.invalidLogin(login)) {
            ConsoleWriter.writeString("login is invalidate");
            return null;
        }

        ConsoleWriter.writeString("Enter the password");
        String password = ConsoleReader.readString();

        if (UserValidator.invalidPassword(password)) {
            ConsoleWriter.writeString("password is invalidate");
            return null;
        }

        ConsoleWriter.writeString("Enter the age");
        int age = ConsoleReader.readInt();

        if (UserValidator.invalidAge(age)) {
            ConsoleWriter.writeString("Age is invalidate");
            return null;
        }

        ConsoleWriter.writeString("Enter the street");
        String street = ConsoleReader.readString();

        if (AddressValidator.invalidStreet(street)) {
            ConsoleWriter.writeString("Street is invalidate");
            return null;
        }

        ConsoleWriter.writeString("Enter the home");
        int home = ConsoleReader.readInt();

        if (AddressValidator.invalidHome(home)) {
            ConsoleWriter.writeString("Home is invalidate");
            return null;
        }

        Address address = new Address(street, home);



        return new User(name, login, password, age, address, role);
    }

    @Override
    public void registration() {
        User user = createUser(Role.USER);
        if (userService.save(user)) {
            ConsoleWriter.writeString("The user was saved. \n");
            ConsoleWriter.writeString("Registration is ok!");
        } else {
            ConsoleWriter.writeString("The user was not saved.");
        }
    }

    @Override
    public void logout() {
        ConsoleApplication.session = null;
    }

    @Override
    public void authorization() {
        ConsoleWriter.writeString("Enter the Login");
        String login = ConsoleReader.readString();

        ConsoleWriter.writeString("Enter the Password");
        String password = ConsoleReader.readString();

        User user = userService.findByLogin(login);
        if (user != null) {
            if (password.equals(user.getPassword())) {
                ConsoleApplication.session = new Session(user);
                ConsoleWriter.writeString("Authorization is ok!");
            } else {
                ConsoleWriter.writeString("Wrong password!");
            }
        } else {
            ConsoleWriter.writeString("User not found!");
        }
    }

    @Override
    public void updateName() {

        ConsoleWriter.writeString("Enter the new name");
        String name = ConsoleReader.readString();

        if (UserValidator.invalidName(name)) {
            ConsoleWriter.writeString("name is invalidate");
            return;
        }

        ConsoleWriter.writeString("Enter the id");
        int id = ConsoleReader.readInt();

        if (UserValidator.invalidId(id)) {
            ConsoleWriter.writeString("id is invalidate");
            return;
        }

        userService.updateName(name, id);
        ConsoleWriter.writeString("The name was update. \n");
    }

    @Override
    public void updatePassword() {

        ConsoleWriter.writeString("Enter the new password");
        String password = ConsoleReader.readString();

        if (UserValidator.invalidPassword(password)) {
            ConsoleWriter.writeString("password is invalidate");
            return;
        }

        ConsoleWriter.writeString("Enter the id");
        int id = ConsoleReader.readInt();

        if (UserValidator.invalidId(id)) {
            ConsoleWriter.writeString("id is invalidate");
            return;
        }
        userService.updatePassword(password, id);
        ConsoleWriter.writeString("The password was update. \n");
    }

    @Override
    public void updateAge() {
        ConsoleWriter.writeString("Enter the new age");
        int age = ConsoleReader.readInt();

        if (UserValidator.invalidAge(age)) {
            ConsoleWriter.writeString("Age is invalidate");
            return;
        }

        ConsoleWriter.writeString("Enter the id");
        int id = ConsoleReader.readInt();

        if (UserValidator.invalidId(id)) {
            ConsoleWriter.writeString("id is invalidate");
            return;
        }

        userService.updateAge(age, id);
        ConsoleWriter.writeString("The age was update. \n");

    }

    @Override
    public void updateAddress() {

        ConsoleWriter.writeString("Enter the street");
        String street = ConsoleReader.readString();

        if (AddressValidator.invalidStreet(street)) {
            ConsoleWriter.writeString("Street is invalidate");
            return;
        }

        ConsoleWriter.writeString("Enter the home");
        int home = ConsoleReader.readInt();

        if (AddressValidator.invalidHome(home)) {
            ConsoleWriter.writeString("Home is invalidate");
            return;
        }

        ConsoleWriter.writeString("Enter the id");
        int id = ConsoleReader.readInt();

        if (UserValidator.invalidId(id)) {
            ConsoleWriter.writeString("id is invalidate");
            return;
        }

        Address address = new Address(street, home);

        userService.updateAddress(address, id);
        ConsoleWriter.writeString("The address was update. \n");

    }

    @Override
    public void updateNameForUser() {
        ConsoleWriter.writeString("Enter the new name");
        String name = ConsoleReader.readString();

        if (UserValidator.invalidName(name)) {
            ConsoleWriter.writeString("name is invalidate");
            return;
        }

        userService.updateName(name, ConsoleApplication.session.getUser().getId());
        ConsoleWriter.writeString("The name was update. \n");
    }

    @Override
    public void updatePasswordForUser() {
        ConsoleWriter.writeString("Enter the new password");
        String password = ConsoleReader.readString();

        if (UserValidator.invalidPassword(password)){
            ConsoleWriter.writeString("Password is invalidate");
            return;
        }

        userService.updatePassword(password, ConsoleApplication.session.getUser().getId());
        ConsoleWriter.writeString("The password was update. \n");
    }

    @Override
    public void updateAgeForUser() {
        ConsoleWriter.writeString("Enter the new age");
        int age = ConsoleReader.readInt();

        if (UserValidator.invalidAge(age)){
            ConsoleWriter.writeString("Age is invalidate");
            return;
        }

        userService.updateAge(age, ConsoleApplication.session.getUser().getId());
        ConsoleWriter.writeString("The age was update. \n");

    }

    @Override
    public void updateAddressForUser() {
       ConsoleWriter.writeString("Enter the new street");
        String street = ConsoleReader.readString();

        if (AddressValidator.invalidStreet(street)){
            ConsoleWriter.writeString("Street is invalidate");
            return;
        }

        ConsoleWriter.writeString("Enter the home");
        int home = ConsoleReader.readInt();

        if (AddressValidator.invalidHome(home)){
            ConsoleWriter.writeString("Home is invalidate");
        }

        Address address = new Address(street, home);

        userService.updateAddress(address,ConsoleApplication.session.getUser().getId());
        ConsoleWriter.writeString("The address was update. \n");

    }

    @Override
    public void deleteById() {

        ConsoleWriter.writeString("Enter the id");
        int id = ConsoleReader.readInt();

        if (UserValidator.invalidId(id)) {
            ConsoleWriter.writeString("id is invalidate");
            return;
        }

        userService.deleteById(id);
        ConsoleWriter.writeString("The user was delete. \n");
    }

    @Override
    public void deleteByLogin() {

        ConsoleWriter.writeString("Enter the login");
        String login = ConsoleReader.readString();

        if (UserValidator.invalidLogin(login)) {
            ConsoleWriter.writeString("login is invalidate");
            return;
        }

        userService.deleteByLogin(login);
        ConsoleWriter.writeString("The user was delete. \n");
    }

    @Override
    public void findAll() {
        ConsoleWriter.writeString("User: ");
        int count = 1;
        User[] all = userService.findAll();
        for (User user : all) {
            ConsoleWriter.writeString(count + ". " + user.getLogin());
            count++;
        }
        ConsoleWriter.writeString(" ");
    }

    @Override
    public void findAllByAge() {

        ConsoleWriter.writeString("Enter the age");
        int age = ConsoleReader.readInt();

        if (UserValidator.invalidAge(age)){
            ConsoleWriter.writeString("Age is invalidate");
            return;
        }

        ConsoleWriter.writeString("User: ");
        int count = 1;

        User[] allAge = userService.findAllByAge(age);
        for (User user : allAge) {
            ConsoleWriter.writeString(count + ". " + user.getLogin());
            count++;
        }
        ConsoleWriter.writeString(" ");
    }


    @Override
    public void findAllByName() {
        ConsoleWriter.writeString("Enter the name");
        String name = ConsoleReader.readString();

        if (UserValidator.invalidName(name)) {
            ConsoleWriter.writeString("name is invalidate");
            return;
        }

        ConsoleWriter.writeString("User: ");
        int count = 1;

        User[] allName = userService.findAllByName(name);
        for (User user : allName) {
            ConsoleWriter.writeString(count + ". " + user.getLogin());
        }
        ConsoleWriter.writeString(" ");
    }


    @Override
    public void findByAddress() {
        ConsoleWriter.writeString("Enter the street");
        String street = ConsoleReader.readString();

        if (AddressValidator.invalidStreet(street)){
            ConsoleWriter.writeString("Street is invalidate");
            return;
        }

        ConsoleWriter.writeString("Enter the home");
        int home = ConsoleReader.readInt();

        if (AddressValidator.invalidHome(home)){
            ConsoleWriter.writeString("Home is invalidate");
        }
        Address address = new Address(street, home);
        User findByAddress = userService.findByAddress(address);

        if (findByAddress == null){
            ConsoleWriter.writeString("Address not found");
            return;
        }

        ConsoleWriter.writeString("User: " + findByAddress.getName() + "\n" + findByAddress.getLogin() + "\n"+
                findByAddress.getAge() + "\n" +
                findByAddress.getAddress().getStreet() + " " + findByAddress.getAddress().getHome() + "\n");
    }



    @Override
    public void findById() {
        ConsoleWriter.writeString("Enter the id");
        int id = ConsoleReader.readInt();

        if (UserValidator.invalidId(id)) {
            ConsoleWriter.writeString("id is invalidate");
            return;
        }
        User byId = userService.findById(id);
        ConsoleWriter.writeString("User: " + byId.getName() + "\n" +  byId.getLogin() + "\n"+
                byId.getAge() + "\n" + byId.getAddress().getStreet() + " " + byId.getAddress().getHome() + "\n");
    }


    @Override
    public void findByLogin() {
        ConsoleWriter.writeString("Enter the login");
        String login = ConsoleReader.readString();

        if (UserValidator.invalidLogin(login)) {
            ConsoleWriter.writeString("login is invalidate");
            return;
        }

        User byLogin = userService.findByLogin(login);
        ConsoleWriter.writeString("User: " + byLogin.getName() + "\n" +  byLogin.getLogin() + "\n"+
                byLogin.getAge() + "\n" + byLogin.getAddress().getStreet() + " " + byLogin.getAddress().getHome() + "\n");

    }


}
