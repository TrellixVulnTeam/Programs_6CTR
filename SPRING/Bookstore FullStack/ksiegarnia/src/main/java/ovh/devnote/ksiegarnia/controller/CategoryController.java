package ovh.devnote.ksiegarnia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ovh.devnote.ksiegarnia.entity.Kategoria;
import ovh.devnote.ksiegarnia.services.CategoryService;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public String listCategories(Model model)
    {
        List<Kategoria> categories = categoryService.getCategories();
        model.addAttribute("categories", categories);
        return "categorieslist";
    }

    @GetMapping("/formadd")
    public String addForm(Model model)
    {
        Kategoria category = new Kategoria();
        model.addAttribute("category", category);
        return "dodajKategorie";

    }

    @PostMapping("/saveCategory")
    public String saveCategory(@ModelAttribute("category") Kategoria kategoria)
    {
        categoryService.saveCategory(kategoria);
        return "redirect:/categories/list";
    }

    @GetMapping("/updateCategoryForm")
    public String updateCategoryForm(@RequestParam("categoryId") int categoryId, Model model)
    {
        model.addAttribute("category", categoryService.getCategory(categoryId));
        return "dodajKategorie";
    }

    @GetMapping("/deleteCategoryForm")
    public String deleteCategory(@RequestParam("categoryId") int id)
    {
        categoryService.deleteCategory(id);
        return "redirect:/categories/list";
    }


}
