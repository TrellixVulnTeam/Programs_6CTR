package ovh.devnote.ksiegarnia.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ovh.devnote.ksiegarnia.DAO.BookDAO;
import ovh.devnote.ksiegarnia.Entity.Book;
import ovh.devnote.ksiegarnia.Service.BookService;

import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    public Book bookAdd(@RequestParam String title, @RequestParam String author)
    {

        Book book = new Book();
        book.setBookTitle(title);
        book.setBookAuthor(author);



        return  bookService.bookAdd(book);
    }

    @GetMapping("/list")
    public Iterable<Book> booksGet(){

       return bookService.booksGet();

    }

    @PostMapping("/find")
    public Book findBookById(@RequestParam Integer id)
    {
        System.out.println("To jest ID !!!!!!!!!! " + id);
        Book book = bookService.findBookById(id);

        return  book;
    }

    @PostMapping("/delete")
    public String deleteBookById(@RequestParam Integer id)
    {
        Book book = bookService.findBookById(id);
                bookService.deleteBookById(id);

                return "Ta książka została usunięta " + book;
    }

//    @GetMapping("/findTitle/{title}")
//    public Book findBookByTitle(@PathVariable String title)
//    {
//        Book book = bookService.findBookByTitle(title);
//
//        return book;
//
//    }

    @PostMapping("/deleteAll")
    public String deleteBooksAll()
    {
        bookService.deleteBooksAll();

        return "Usunięto wszystkie pozycje książek";
    }

    @PostMapping("/edit")
    public String editBook(@RequestParam Integer id, @RequestParam String title, @RequestParam String author)
    {
        Book book = bookService.findBookById(id);
        if(title.isEmpty() )
        {
            book.setBookTitle(book.getBookTitle());

        }else {
            book.setBookTitle(title);
        }
        if(author.isEmpty())
        {
            book.setBookAuthor(book.getBookAuthor());
        }else
        {
            book.setBookAuthor(author);

        }


        bookService.bookAdd(book);
        return "Book updated";
    }



}
