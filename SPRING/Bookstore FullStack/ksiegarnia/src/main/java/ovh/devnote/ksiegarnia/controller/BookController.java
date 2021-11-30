package ovh.devnote.ksiegarnia.controller;

import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ovh.devnote.ksiegarnia.document.PdfCreator;
import ovh.devnote.ksiegarnia.entity.Autor;
import ovh.devnote.ksiegarnia.entity.Kategoria;
import ovh.devnote.ksiegarnia.entity.Ksiazka;
import ovh.devnote.ksiegarnia.services.AuthorService;
import ovh.devnote.ksiegarnia.services.BookService;
import ovh.devnote.ksiegarnia.services.CategoryService;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/books")
public class BookController {
    Authentication authentication;
    private final BookService bookService;
    private final CategoryService categoryService;
    private final AuthorService authorService;

    @Autowired
    public BookController(BookService bookService, CategoryService categoryService, AuthorService authorService, PdfCreator pdfCreator) {
        this.bookService = bookService;
        this.categoryService = categoryService;
        this.authorService = authorService;

    }

    @GetMapping("/list")
    public String listBooks(Model model){
        List<Ksiazka> books = bookService.getBooks();
        model.addAttribute("books",books);
        model.addAttribute("authors", authorService.getAuthors());
        return "bookslist";
    }

    @GetMapping("/formadd")
    public String addForm(Model model) throws IOException, DocumentException {
        Ksiazka book = new Ksiazka();
        model.addAttribute("book", book);
        model.addAttribute("authors", authorService.getAuthors());
        model.addAttribute("categories", categoryService.getCategories());



        return "addbookform";
    }

    @PostMapping("/saveBook")
    public String saveBook(@ModelAttribute("book") Ksiazka ksiazka, @RequestParam("kategoria") int id, @RequestParam("ids") List<String> ids) {
        Kategoria kategoria = categoryService.getCategory(id);
        Set<Autor> autorzy= new HashSet<>();
        int x;
        String s;
        for(int i=0; i<ids.size(); i++)
        {
            x=Integer.parseInt(ids.get(i));
            Autor autor = authorService.getAuthor(x);
            autorzy.add(autor);
        }
        ksiazka.setAutorzy(autorzy);
        ksiazka.setKategoria(kategoria);
        bookService.saveBook(ksiazka);
        return "redirect:/admin";
    }

    @GetMapping("/updateBookForm")
    public String updateBookForm(@RequestParam("bookId") int bookId, Model model) {
        model.addAttribute("book", bookService.getBook(bookId));
        model.addAttribute("authors", authorService.getAuthors());
        model.addAttribute("categories", categoryService.getCategories());
        return "addbookform";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/deleteBookForm")
    public String deleteBook(@RequestParam("bookId") int bookId) {
        bookService.deleteBook(bookId);
        return "redirect:/admin";
    }

    @GetMapping("/filterList")
    public String filterList(@RequestParam("categoryId") int categoryId, Model model ) {
        List<Ksiazka> books = bookService.getBooksFiltered(categoryId);
        model.addAttribute("books", books);
        model.addAttribute("authors", authorService.getAuthors());
        return "user";
    }
}