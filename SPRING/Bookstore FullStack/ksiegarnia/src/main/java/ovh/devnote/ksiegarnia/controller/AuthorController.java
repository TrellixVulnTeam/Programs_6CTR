package ovh.devnote.ksiegarnia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ovh.devnote.ksiegarnia.entity.Autor;
import ovh.devnote.ksiegarnia.services.AuthorService;

import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping("/list")
    public String listAuthors(Model model)
    {
        List<Autor>authors = authorService.getAuthors();
        model.addAttribute("authors", authors);
        return "authorslist";
    }

    @GetMapping("/formadd")
    public String addForm(Model model)
    {
        Autor autor = new Autor();
        model.addAttribute("author", autor);
        return "addauthorform";
    }

    @PostMapping("/saveAuthor")
    public String saveAuthor(@ModelAttribute("author") Autor autor)
    {
        authorService.saveAuthor(autor);
        return "redirect:/authors/list";
    }

    @PutMapping("/updateAuthorForm")
    public String updateAuthorForm(@RequestParam("authorId") int authorId, Model model)
    {
        model.addAttribute("author", authorService.getAuthor(authorId) );
        return "addauthorform";
    }
    @DeleteMapping("deleteAuthorForm")
    public String deleteAuthorForm(@RequestParam("authorId") int id)
    {
        authorService.deleteAuthor(id);
        return "redirect:/authors/list";
    }

}
