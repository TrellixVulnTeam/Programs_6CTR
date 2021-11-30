package ovh.devnote.ksiegarnia.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ovh.devnote.ksiegarnia.entity.Zamowione;

import java.util.List;

@Repository
public class OrderDAOImpl implements OrderDAO {

    @Autowired
    SessionFactory sessionFactory;


    @Override
    public void changeStatus(Zamowione zamowione) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(zamowione);
    }

    @Override
    public void addToOrder(Zamowione zamowione) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(zamowione);
    }

    @Override
    public List<Zamowione> getOrder(String username) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Zamowione> query = currentSession.createQuery("from Zamowione where username=:username", Zamowione.class).setParameter("username", username);
        return query.getResultList();
    }

    @Override
    public List<Zamowione> getOrders() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Zamowione> query = currentSession.createQuery("from Zamowione", Zamowione.class);
        return query.getResultList();
    }

    @Override
    public Zamowione getOrderId(int orderId) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Zamowione> query = currentSession.createQuery("from Zamowione where id=:id", Zamowione.class).setParameter("id", orderId);
        return query.getSingleResult();
    }
}
