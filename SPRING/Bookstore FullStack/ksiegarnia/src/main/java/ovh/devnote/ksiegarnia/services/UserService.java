package ovh.devnote.ksiegarnia.services;

import ovh.devnote.ksiegarnia.entity.User;

import java.util.List;

public interface UserService {
    public void saveUser(User user);
    public List<User> getUser(String username);
}
