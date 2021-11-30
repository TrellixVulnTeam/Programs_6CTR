package ovh.devnote.ksiegarnia.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ovh.devnote.ksiegarnia.dao.AuthorDAO;
import ovh.devnote.ksiegarnia.entity.Autor;

import java.util.List;
@Service
public class AuthorServiceImpl implements AuthorService {

    private  AuthorDAO authorDAO;
    @Autowired
    public AuthorServiceImpl(AuthorDAO authorDAO)
    {
        this.authorDAO = authorDAO;
    }

    @Override
    @Transactional
    public List<Autor> getAuthors() {
        List<Autor>authors= authorDAO.getAuthors();
        return authors;
    }

    @Override
    @Transactional
    public void saveAuthor(Autor autor) {
        authorDAO.saveAuthor(autor);
    }

    @Override
    @Transactional
    public Autor getAuthor(int id) {
        return authorDAO.getAuthor(id);
    }

    @Override
    @Transactional
    public void deleteAuthor(int id) {
        authorDAO.deleteAuthor(id);
    }


}
