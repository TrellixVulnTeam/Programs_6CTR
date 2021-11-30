package ovh.devnote.ksiegarnia.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ovh.devnote.ksiegarnia.DAO.BookDAO;
import ovh.devnote.ksiegarnia.Entity.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookDAO bookDAO;


    public Book bookAdd(Book book)
    {

        bookDAO.save(book);
        return book;
    }


    public List<Book> booksGet()
    {

        List<Book> books=new ArrayList<>();
        bookDAO.findAll().forEach(book -> books.add(book));
        return books;

    }

    public Book findBookById(Integer id)
    {


            return bookDAO.findById(id).get();
    }

    public String deleteBookById(Integer id)
    {
        Book book = bookDAO.findById(id).get();
        if(book == null)
        {
            return "Nie ma ksiązki o podanym ID";
        }else{
            bookDAO.deleteById(id);
            return "Ksiązka o id = " + id + " została pomyślnie usunięta";
        }
    }

    public void deleteBooksAll()
    {
        bookDAO.deleteAll();
    }



}
