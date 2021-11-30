package ovh.devnote.ksiegarnia.services;

import org.springframework.transaction.annotation.Transactional;
import ovh.devnote.ksiegarnia.entity.Ksiazka;

import java.util.List;

public interface BookService {
    public List<Ksiazka> getBooks();
    public void saveBook(Ksiazka ksiazka);
    public Ksiazka getBook(int bookId);
    public void deleteBook(int bookId);
    public void updateBook(Ksiazka ksiazka);

    @Transactional
    List<Ksiazka> getBooksFiltered(int categoryId);
}
