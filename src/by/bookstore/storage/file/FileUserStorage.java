package by.bookstore.storage.file;

import by.bookstore.entity.Address;
import by.bookstore.entity.Role;
import by.bookstore.entity.User;
import by.bookstore.storage.UserStorage;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileUserStorage extends AbstractFileStorage implements UserStorage {

    @Override
    public void save(User user) {
        try (FileWriter fileWriter = new FileWriter(PATH_USERS, true)) {
            FileAddressStorage fileAddressStorage = new FileAddressStorage();
            fileAddressStorage.save(user.getAddress());
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder
                    .append(getLastId(PATH_USERS) + 1).append(" ")
                    .append(user.getName()).append(" ")
                    .append(user.getLogin()).append(" ")
                    .append(user.getPassword()).append(" ")
                    .append(user.getAge()).append(" ")
                    .append(getIdByAddress(user.getAddress().getStreet(), String.valueOf(user.getAddress().getHome()))).append(" ")
                    .append(user.getRole()).append(" ");
            String s = stringBuilder.toString();
            fileWriter.write(s);
            fileWriter.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateName(String name, int id) {
        List<User> all = Arrays.asList(findAll());
        for (User user : all) {
            if (user.getId() == id) {
                user.setName(name);
            }
        }
        writeFileUser(all);
    }

    @Override
    public void updatePassword(String password, int id) {
        List<User> all = Arrays.asList(findAll());
        for (User user : all) {
            if (user.getId() == id) {
                user.setPassword(password);
            }
        }
        writeFileUser(all);
    }

    @Override
    public void updateAge(int age, int id) {
        List<User> all = Arrays.asList(findAll());
        for (User user : all) {
            if (user.getId() == id) {
                user.setAge(age);
            }
        }
        writeFileUser(all);
    }

    @Override
    public void updateAddress(Address address, int id) {
        FileAddressStorage fileAddressStorage = new FileAddressStorage();
        fileAddressStorage.save(address);
        List<User> all = Arrays.asList(findAll());
        for (User user : all) {
            if (user.getId() == id) {
                user.setAddress(address);
            }
        }
        writeFileUser(all);
    }

    @Override
    public void deleteById(int id) {
        List<User> all = new ArrayList<>(Arrays.asList(findAll()));
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getId() == id) {
                all.remove(i);
                break;
            }
        }
        writeFileUser(all);
    }

    @Override
    public void deleteByLogin(String login) {
        List<User> all = new ArrayList<>(Arrays.asList(findAll()));
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getLogin().equals(login)) {
                all.remove(i);
                break;
            }
        }
        writeFileUser(all);
    }

    private void writeFileUser(List<User> all) {
        try (FileWriter fileWriter = new FileWriter(PATH_USERS)) {
            for (User user : all) {
                fileWriter.write(String.format("%d %s %s %s %d %d %s",
                        user.getId(),
                        user.getName(),
                        user.getLogin(),
                        user.getPassword(),
                        user.getAge(),
                        user.getAddress().getId(),
                        user.getRole()));
                fileWriter.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public User[] findAll() {
        List<User> all = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH_USERS))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] s = line.split(" ");
                all.add(new User(Integer.parseInt(s[USER_ID]),
                        s[USER_NAME],
                        s[USER_LOGIN],
                        s[USER_PASSWORD],
                        Integer.parseInt(s[USER_AGE]),
                        getAddressById(s[USER_ADDRESS_ID]),
                        Role.valueOf(s[USER_ROLE])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return all.toArray(new User[0]);
    }

    @Override
    public User[] findAllByAge(int age) {
        User[] all = findAll();
        List<User> allAge = new ArrayList<>();
        for (User user : all) {
            if (user.getAge() == age) {
                allAge.add(user);
            }
        }
        return allAge.toArray(new User[0]);
    }

    @Override
    public User[] findAllByName(String name) {
        User[] all = findAll();
        List<User> allName = new ArrayList<>();
        for (User user : all) {
            if (user.getName().equals(name)) {
                allName.add(user);
            }
        }
        return allName.toArray(new User[0]);
    }

    @Override
    public User findByAddress(Address address) {
        User[] all = findAll();
        for (User user : all) {
            if (user.getAddress().equals(address)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User findById(int id) {
        return getUserById(String.valueOf(id));
    }

    @Override
    public User findByLogin(String login) {
        User[] all = findAll();
        for (User user : all) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean contains(int id) {
        User[] all = findAll();
        for (User user : all) {
            if (user.getId() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(String login) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH_USERS))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] s = line.split(" ");
                if (s[USER_LOGIN].equals(login)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}

