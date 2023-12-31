package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    void add(User user);

    List<User> usersList();

    void update(User user);

    User delete(int id);

    User get(int id);
}
