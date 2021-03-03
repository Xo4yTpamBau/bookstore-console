package by.bookstore.console.action;

import by.bookstore.console.util.ConsoleReader;
import by.bookstore.console.util.ConsoleWriter;
import by.bookstore.console.validator.AddressValidator;
import by.bookstore.entity.Address;
import by.bookstore.entity.Author;
import by.bookstore.service.AddressService;
import by.bookstore.service.AddressServiceImpl;

public class AddressActionImpl implements AddressAction {
    private AddressService addressService = new AddressServiceImpl();

    @Override
    public void save() {

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

        addressService.save(new Address(street, home));
        ConsoleWriter.writeString("The address was saved. \n");
    }

    @Override
    public void deleteById() {
        ConsoleWriter.writeString("Enter the id");
        int id = ConsoleReader.readInt();

        if (AddressValidator.invalidId(id)) {
            ConsoleWriter.writeString("id is invalidate");
            return;
        }

        addressService.delete(id);
        ConsoleWriter.writeString("The address was deleted. \n");
    }

    @Override
    public void deleteByAddress() {

        ConsoleWriter.writeString("Choose the Address:");

        Address[] all = addressService.getAll();
        for (int i = 0; i < all.length; i++) {
            ConsoleWriter.writeString((i + 1) + ". " +  all[i].getStreet() + " " + all[i].getHome());

        }

        int i = ConsoleReader.readInt() - 1;
        Address address = all[i];


        addressService.delete(address);
        ConsoleWriter.writeString("The address was deleted. \n");
    }

    @Override
    public void updateStreet() {

        ConsoleWriter.writeString("Enter the new Street");
        String street = ConsoleReader.readString();

        if (AddressValidator.invalidStreet(street)) {
            ConsoleWriter.writeString("Street is invalidate");
        }

        ConsoleWriter.writeString("Enter the id");
        int id = ConsoleReader.readInt();

        if (AddressValidator.invalidId(id)) {
            ConsoleWriter.writeString("id is invalidate");
        }

        addressService.updateStreet(street, id);
        ConsoleWriter.writeString("The street was updated. \n");

    }

    @Override
    public void updateHome() {

        ConsoleWriter.writeString("Enter the new Home");
        int home = ConsoleReader.readInt();

        if (AddressValidator.invalidHome(home)) {
            ConsoleWriter.writeString("Home is invalidate");
        }

        ConsoleWriter.writeString("Enter the id");
        int id = ConsoleReader.readInt();

        if (AddressValidator.invalidId(id)) {
            ConsoleWriter.writeString("id is invalidate");
        }

        addressService.updateHome(home, id);
        ConsoleWriter.writeString("The home was updated. \n");
    }

    @Override
    public void getAll() {
        ConsoleWriter.writeString("Address: ");

        Address[] all = addressService.getAll();
        for (int i = 0; i < all.length; i++) {
            ConsoleWriter.writeString((i + 1) + ". " +  all[i].getStreet() + " " + all[i].getHome());
        }
        ConsoleWriter.writeString(" ");
    }

    @Override
    public void getStreet() {

        ConsoleWriter.writeString("Enter the Street");
        String street = ConsoleReader.readString();

        if (AddressValidator.invalidStreet(street)) {
            ConsoleWriter.writeString("Street is invalidate");
            return;
        }

        ConsoleWriter.writeString("Address: ");

        Address[] address = addressService.getStreet(street);
        for (int i = 0; i < address.length; i++) {
            if (address[i] == null) break;
            ConsoleWriter.writeString((i + 1) + ". " + address[i].getStreet() + " " + address[i].getHome());
        }

        ConsoleWriter.writeString(" ");
    }

    @Override
    public void getHome() {

        ConsoleWriter.writeString("Enter the Home");
        int home = ConsoleReader.readInt();

        if (AddressValidator.invalidHome(home)) {
            ConsoleWriter.writeString("Home is invalidate");
            return;
        }

        ConsoleWriter.writeString("Address: ");

        Address[] address = addressService.getHome(home);
        for (int i = 0; i < address.length; i++) {
            if (address[i] == null) break;
            ConsoleWriter.writeString((i + 1) + ". " + address[i].getStreet() + " " + address[i].getHome());
        }
        ConsoleWriter.writeString(" ");
    }

    @Override
    public void getById() {

        ConsoleWriter.writeString("Enter the id");
        int id = ConsoleReader.readInt();

        if (AddressValidator.invalidHome(id)) {
            ConsoleWriter.writeString("id is invalidate");
            return;
        }

        Address address = addressService.getById(id);
        if (address == null) {
            ConsoleWriter.writeString("Address not found");
            return;
        }
        ConsoleWriter.writeString("Address: " + address.getStreet() + " " + address.getHome() + "\n");
    }
}
