package ovh.devnote.ksiegarnia.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ovh.devnote.ksiegarnia.dao.BookDAO;
import ovh.devnote.ksiegarnia.entity.Ksiazka;

import java.util.List;
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDAO bookDAO;
    @Override
    @Transactional
    public List<Ksiazka> getBooks() {
        List<Ksiazka> books = bookDAO.getBooks();
        return books;
    }
    @Override
    @Transactional
    public void saveBook(Ksiazka ksiazka) {
        bookDAO.saveBook(ksiazka);
    }

    @Override
    @Transactional
    public void updateBook(Ksiazka ksiazka)
    {
        bookDAO.updateBook(ksiazka);
    }

    @Override
    @Transactional
    public Ksiazka getBook(int bookId) {
        return bookDAO.getBook(bookId);
    }

    @Override
    @Transactional
    public void deleteBook(int bookId) {
        bookDAO.deleteBook(bookId);

    }

    @Override
    @Transactional
    public List<Ksiazka> getBooksFiltered(int categoryId) {
        return  bookDAO.getBooksFiltered(categoryId);

    }





}