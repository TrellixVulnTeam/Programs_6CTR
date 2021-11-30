package ovh.devnote.ksiegarnia.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ovh.devnote.ksiegarnia.entity.Kategoria;

import java.util.List;
@Repository
public class CategoryDAOImpl implements CategoryDAO {



    @Autowired
    SessionFactory sessionFactory;
    public CategoryDAOImpl(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<Kategoria> getCategories() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Kategoria> query = currentSession.createQuery("from Kategoria", Kategoria.class);
        return query.getResultList();
    }




    @Override
    public Kategoria getCategory(int id)
    {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Kategoria>query = currentSession.createQuery("from Kategoria where id=:id", Kategoria.class)
                .setParameter("id", id);
        return query.getSingleResult();

    }

    @Override
    public void saveCategory(Kategoria category) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(category);
    }


    @Override
    public void deleteCategory(int categoryId){
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.createQuery("update Ksiazka set kategoria=null where kategoria.id=:id")
                .setParameter("id",categoryId).executeUpdate();
        currentSession.createQuery("delete from Kategoria where id=:id")
                .setParameter("id",categoryId).executeUpdate();
    }

}
