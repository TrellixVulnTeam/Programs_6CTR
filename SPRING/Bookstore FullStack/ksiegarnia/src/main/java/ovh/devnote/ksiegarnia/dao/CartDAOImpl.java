package ovh.devnote.ksiegarnia.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ovh.devnote.ksiegarnia.entity.Koszyk;
import ovh.devnote.ksiegarnia.entity.Ksiazka;

import java.util.List;

@Repository
public class CartDAOImpl implements CartDAO {

    @Autowired
    SessionFactory sessionFactory;


    @Override
    public void saveCart(Koszyk koszyk) {
        Session session = sessionFactory.getCurrentSession();
        session.save(koszyk);



    }

    @Override
    public List<Koszyk> getCart(String username) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Koszyk> query = currentSession.createQuery("from Koszyk where username=:username", Koszyk.class).setParameter("username", username);

        return query.getResultList();
    }

    @Override
    public void addToCart(Ksiazka ksiazka) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(ksiazka);
    }

    @Override
    public void deleteCart(int prodId) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.createQuery("delete from Koszyk where id=:id")
                .setParameter("id",prodId).executeUpdate();
    }


    @Override
    public Koszyk getCartBook(int prodId) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Koszyk> query = currentSession.createQuery("from Koszyk where id=:id", Koszyk.class).setParameter("id", prodId);

        return query.getSingleResult();
    }

}
