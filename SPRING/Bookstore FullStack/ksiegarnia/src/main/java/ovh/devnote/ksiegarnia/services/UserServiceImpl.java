package ovh.devnote.ksiegarnia.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ovh.devnote.ksiegarnia.dao.UserDAO;
import ovh.devnote.ksiegarnia.entity.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional
    public void saveUser(User user) {
        userDAO.saveUser(user);
    }

    @Override
    @Transactional
    public List<User> getUser(String username) {
        if(userDAO.getUser(username).isEmpty())
            return null;
        else
            return userDAO.getUser(username);
    }

}
