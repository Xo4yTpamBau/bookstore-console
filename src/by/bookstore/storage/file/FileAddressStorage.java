package by.bookstore.storage.file;

import by.bookstore.entity.Address;
import by.bookstore.entity.Author;
import by.bookstore.entity.Book;
import by.bookstore.storage.AddressStorage;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileAddressStorage extends AbstractFileStorage implements AddressStorage {


    @Override
    public void save(Address address) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(getLastId(PATH_ADDRESS) + 1).append(" ")
                .append(address.getStreet()).append(" ")
                .append(address.getHome()).append(" ");
        String s = stringBuilder.toString();

        try (FileWriter fileWriter = new FileWriter(PATH_ADDRESS, true)) {
            fileWriter.write(s);
            fileWriter.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        List<Address> all = new ArrayList<>(Arrays.asList(getAll()));
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getId() == id) {
                all.remove(i);
                break;
            }
        }
            writeFileAddress(all);
    }

    @Override
    public void delete(Address address) {
        List<Address> all = new ArrayList<>(Arrays.asList(getAll()));
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).equals(address)) {
                all.remove(i);
                break;
            }
        }
            writeFileAddress(all);
    }

    @Override
    public String updateStreet(String street, int id) {
        List<Address> all = Arrays.asList(getAll());
        String old = null;
        for (Address address : all) {
            if (address.getId() == id) {
                old = address.getStreet();
                address.setStreet(street);
                break;
            }
        }
        writeFileAddress(all);
        return old;
    }

    private void writeFileAddress(List<Address> all) {
        try (FileWriter fileWriter = new FileWriter(PATH_ADDRESS)) {
            for (Address address : all) {
                fileWriter.write(String.format("%d %s %d",
                        address.getId(),
                        address.getStreet(),
                        address.getHome()));
                fileWriter.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int updateHome(int home, int id) {
        List<Address> all = Arrays.asList(getAll());
        int old = 0;
        for (Address address : all) {
            if (address.getId() == id) {
                old = address.getHome();
                address.setHome(home);
                break;
            }
        }
        writeFileAddress(all);
        return old;
    }

    @Override
    public Address[] getAll() {
        List<Address> all = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH_ADDRESS))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] s = line.split(" ");
                all.add(new Address(Integer.parseInt(s[ADDRESS_ID]),
                        s[ADDRESS_STREET],
                        Integer.parseInt(s[ADDRESS_HOME])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return all.toArray(new Address[0]);
    }

    @Override
    public Address[] getStreet(String street) {
        Address[] all = getAll();
        List<Address> allStreet = new ArrayList<>();
        for (Address address : all) {
            if (address.getStreet().equals(street)) {
                allStreet.add(address);
            }
        }
        return allStreet.toArray(new Address[0]);
    }


    @Override
    public Address[] getHome(int home) {
        Address[] all = getAll();
        List<Address> allHome = new ArrayList<>();
        for (Address address : all) {
            if (address.getHome() == home) {
                allHome.add(address);
            }
        }
        return allHome.toArray(new Address[0]);
    }

    @Override
    public Address getById(int id) {
        return getAddressById(String.valueOf(id));
    }

    @Override
    public boolean contains(int id) {
        Address[] all = getAll();
        for (Address address : all) {
            if (address.getId() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(Address address) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH_ADDRESS))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] s = line.split(" ");
                if (s[ADDRESS_STREET].equals(address.getStreet()) && s[ADDRESS_HOME].equals(String.valueOf(address.getHome()))) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
