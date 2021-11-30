package ovh.devnote.ksiegarnia.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ovh.devnote.ksiegarnia.Entity.Book;

@Repository
public interface BookDAO extends CrudRepository <Book,Integer>{



}
