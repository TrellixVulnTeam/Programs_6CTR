package ovh.devnote.ksiegarnia.dao;

import ovh.devnote.ksiegarnia.entity.User;

import java.util.List;

public interface UserDAO {

    public List<User> getUser(String username);
    public void saveUser(User user);

}
