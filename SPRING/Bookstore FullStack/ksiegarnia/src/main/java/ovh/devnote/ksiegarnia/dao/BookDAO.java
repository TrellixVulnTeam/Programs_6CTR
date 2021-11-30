package ovh.devnote.ksiegarnia.dao;

import ovh.devnote.ksiegarnia.entity.Ksiazka;
import java.util.List;

public interface BookDAO
{
    public List<Ksiazka> getBooks();
    public void saveBook(Ksiazka ksiazka);
    public Ksiazka getBook(int id);
    public void deleteBook(int id);
    public void updateBook(Ksiazka ksiazka);

    List<Ksiazka> getBooksFiltered(int categoryId);
}