package by.bookstore.console.action;

import by.bookstore.console.util.ConsoleReader;
import by.bookstore.console.util.ConsoleWriter;
import by.bookstore.console.validator.StoreValidator;
import by.bookstore.entity.Address;
import by.bookstore.entity.Store;
import by.bookstore.service.AddressService;
import by.bookstore.service.AddressServiceImpl;
import by.bookstore.service.StoreService;
import by.bookstore.service.StoreServiceImpl;

public class StoreActionImpl implements StoreAction {
    private StoreService storeService = new StoreServiceImpl();
    private AddressService addressService = new AddressServiceImpl();


    @Override
    public void save() {
        ConsoleWriter.writeString("Enter the title");
        String title = ConsoleReader.readString();

        if (StoreValidator.invalidTitle(title)) {
            ConsoleWriter.writeString("Title is invalidate");
            return;
        }


        ConsoleWriter.writeString("Address: ");
        Address[] all = addressService.getAll();
        for (int i = 0; i < all.length; i++) {
            ConsoleWriter.writeString((i + 1) + ". " + all[i].getStreet());
        }

        ConsoleWriter.writeString("Choose the address:");
        int i = ConsoleReader.readInt() - 1;
        Address address = all[i];

        storeService.save(new Store(title, address));
        ConsoleWriter.writeString("The Store was save. \n");
    }

    @Override
    public void deleteById() {
        ConsoleWriter.writeString("Enter the id");
        int id = ConsoleReader.readInt();

        if (StoreValidator.invalidId(id)) {
            ConsoleWriter.writeString("id is invalidate");
        }
        storeService.delete(id);
        ConsoleWriter.writeString("The Store was delete. \n");
    }

    @Override
    public void deleteByTitle() {
        ConsoleWriter.writeString("Enter the Title");
        String title = ConsoleReader.readString();

        if (StoreValidator.invalidTitle(title)) {
            ConsoleWriter.writeString("title is invalidate");
        }

        storeService.delete(title);
        ConsoleWriter.writeString("The Store was delete. \n");
    }

    @Override
    public void updateTitle() {
        ConsoleWriter.writeString("Enter the new Title");
        String title = ConsoleReader.readString();

        if (StoreValidator.invalidTitle(title)) {
            ConsoleWriter.writeString("Title is invalidate");
        }

        ConsoleWriter.writeString("Enter the id");
        int id = ConsoleReader.readInt();

        if (StoreValidator.invalidId(id)) {
            ConsoleWriter.writeString("id is invalidate");
        }

        storeService.updateTitle(title, id);
        ConsoleWriter.writeString("The Title was update. \n");

    }


    @Override
    public void updateAddress() {

        Address[] all = addressService.getAll();
        for (int i = 0; i < all.length; i++) {
            ConsoleWriter.writeString((i + 1) + " Address " + all[i].getStreet());
        }

        ConsoleWriter.writeString("Choose the address");
        int i = ConsoleReader.readInt() - 1;
        Address address = all[i];


        ConsoleWriter.writeString("Enter the id");
        int id = ConsoleReader.readInt();

        if (StoreValidator.invalidId(id)) {
            ConsoleWriter.writeString("id is invalidate");
        }

        storeService.updateAddress(address, id);
        ConsoleWriter.writeString("The Address was update. \n");

    }

    @Override
    public void getAll() {
        int count = 1;
        Store[] all = storeService.getAll();
        ConsoleWriter.writeString("Store:");
        for (Store store : all) {
            ConsoleWriter.writeString(count + ". " + store.getTitle());
            count++;
        }
        ConsoleWriter.writeString(" ");
    }

    @Override
    public void getByTitle() {
        ConsoleWriter.writeString("Enter the Title");
        String title = ConsoleReader.readString();

        if (StoreValidator.invalidTitle(title)) {
            ConsoleWriter.writeString("title is invalidate");
        }

        Store store = storeService.getByTitle(title);
        if (store == null) {
            ConsoleWriter.writeString("Store not found");
            return;
        }
        ConsoleWriter.writeString("Store title: " + store.getTitle() + "\n" + "Address: " + store.getAddress().getStreet() +
                " " + store.getAddress().getHome());
    }

    @Override
    public void getById() {
        ConsoleWriter.writeString("Enter the id");
        int id = ConsoleReader.readInt();

        if (StoreValidator.invalidId(id)) {
            ConsoleWriter.writeString("id is invalidate");
        }

        Store store = storeService.getById(id);
        if (store == null) {
            ConsoleWriter.writeString("Store not found");
            return;
        }
        ConsoleWriter.writeString("Store title: " + store.getTitle() + "\n" + "Address: " + store.getAddress().getStreet() +
                " " + store.getAddress().getHome());
    }
}
