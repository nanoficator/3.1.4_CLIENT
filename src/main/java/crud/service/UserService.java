package crud.service;

import crud.model.User;

import java.util.Collection;

public interface UserService {

    Collection<User> getAllUsers();

    String addUser(User user);

    String deleteUser(User user);

    String editUser(User user);

}
