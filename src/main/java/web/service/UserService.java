package web.service;

import web.model.User;

public interface UserService {

    void save(User user);

    User getUserByName(String username);
}
