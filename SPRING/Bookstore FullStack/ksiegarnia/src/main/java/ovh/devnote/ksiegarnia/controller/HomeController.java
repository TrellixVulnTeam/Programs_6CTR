package ovh.devnote.ksiegarnia.controller;

import com.itextpdf.text.DocumentException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ovh.devnote.ksiegarnia.document.PdfCreator;
import ovh.devnote.ksiegarnia.entity.Kategoria;
import ovh.devnote.ksiegarnia.entity.Ksiazka;
import ovh.devnote.ksiegarnia.services.BookService;
import ovh.devnote.ksiegarnia.services.CategoryService;

import java.io.IOException;
import java.util.List;

@Controller
public class HomeController {
    private final PdfCreator pdfCreator;
    private final BookService bookService;
    private final CategoryService categoryService;
    public HomeController(PdfCreator pdf, PdfCreator pdfCreator, BookService bookService, CategoryService categoryService) {
        this.pdfCreator = pdfCreator;

        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexPage(ModelMap model) throws IOException, DocumentException {
        List<Kategoria> categories = categoryService.getCategories();
        List<Ksiazka> books = bookService.getBooks();
        model.addAttribute("books", books);
        model.addAttribute("message", "Access for everybody");
        model.addAttribute("categories", categories );

        return "index";

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(ModelMap model)
    {
        List<Ksiazka> books = bookService.getBooks();
        model.addAttribute("books", books);
        model.addAttribute("message", "Admin Page...");
        return "admin";
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String userPage(ModelMap model)
    {
        List<Ksiazka> books = bookService.getBooks();
        model.addAttribute("books", books);
        model.addAttribute("message", "User page ... ");
        return "user";
    }


    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String contactPage( )
    {

        return "contact";
    }


    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String aboutPage( )
    {

        return "about";
    }

}
