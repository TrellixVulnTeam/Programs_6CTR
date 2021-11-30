package ovh.devnote.ksiegarnia.dao;

import ovh.devnote.ksiegarnia.entity.Autor;

import java.util.List;

public interface AuthorDAO {

    public List<Autor> getAuthors();
    public Autor getAuthor(int id);
    public void saveAuthor(Autor autor);
    public void deleteAuthor(int id);
}
