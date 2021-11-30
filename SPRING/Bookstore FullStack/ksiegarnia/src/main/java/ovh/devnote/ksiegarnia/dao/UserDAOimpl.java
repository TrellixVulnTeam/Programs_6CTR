package ovh.devnote.ksiegarnia.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;
import ovh.devnote.ksiegarnia.entity.User;

import java.util.List;

@Repository
public class UserDAOimpl implements UserDAO{
    @Autowired
    SessionFactory sessionFactory;
    public UserDAOimpl(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> getUser(String username) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<User> query = currentSession.createQuery("from User where username=:username", User.class).setParameter("username", username);
        return query.getResultList();
    }

    @Override
    public void saveUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        user.setPassword(BCrypt.hashpw(user.getPassword(),BCrypt.gensalt()));
        session.save(user);
    }


}
