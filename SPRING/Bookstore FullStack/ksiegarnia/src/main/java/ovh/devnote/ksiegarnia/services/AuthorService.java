package ovh.devnote.ksiegarnia.services;

import ovh.devnote.ksiegarnia.entity.Autor;

import java.util.List;

public interface AuthorService {

    public List<Autor> getAuthors();
    public void saveAuthor(Autor autor);
    public Autor getAuthor(int id);
    public void deleteAuthor(int id);

}
