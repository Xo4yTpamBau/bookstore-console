package by.bookstore.console.action;

import by.bookstore.entity.Address;
import by.bookstore.entity.User;

public interface UserAction {
    void save();

    void registration();

    void authorization();

    void updateName();

    void updatePassword();

    void updateAge();

    void updateAddress();

    void updateNameForUser();

    void updatePasswordForUser();

    void updateAgeForUser();

    void updateAddressForUser();

    void deleteById();

    void deleteByLogin();

    void findAll();

    void findAllByAge();

    void findAllByName();

    void findByAddress();

    void findById();

    void findByLogin();


    void logout();
}
