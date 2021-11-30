package ovh.devnote.ksiegarnia.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ovh.devnote.ksiegarnia.entity.Ksiazka;
import java.util.List;
@Repository
public class BookDAOImpl implements BookDAO{
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Ksiazka> getBooks() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Ksiazka> query = currentSession.createQuery("from Ksiazka",Ksiazka.class);
        List<Ksiazka> books = query.getResultList();

        return books;
    }
    @Override
    public void saveBook(Ksiazka ksiazka) {
        Session session = sessionFactory.getCurrentSession();
        session.flush();
        session.saveOrUpdate(ksiazka);
    }




    @Override
    public Ksiazka getBook(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Ksiazka>query =  currentSession.createQuery("from Ksiazka where id=:id", Ksiazka.class)
                .setParameter("id", id);
        return query.getSingleResult();


    }




    @Override
    public void deleteBook(int bookId) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.createQuery("delete from Ksiazka where id=:id")
                .setParameter("id", bookId).executeUpdate();
    }

    @Override
    public void updateBook(Ksiazka ksiazka) {
        Session session = sessionFactory.getCurrentSession();
        session.update(ksiazka);
    }

    @Override
    public List<Ksiazka> getBooksFiltered(int categoryId) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Ksiazka> query = currentSession.createQuery("from Ksiazka where kategoria.id=:id",Ksiazka.class).setParameter("id", categoryId);
        List<Ksiazka> books = query.getResultList();

        return books;
    }


}