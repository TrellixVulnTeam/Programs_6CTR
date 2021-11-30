package ovh.devnote.ksiegarnia.dao;

import ovh.devnote.ksiegarnia.entity.Kategoria;
import ovh.devnote.ksiegarnia.entity.Ksiazka;

import java.util.List;

public interface CategoryDAO {
    public Kategoria getCategory(int id);
    public void saveCategory(Kategoria kategoria);
    public void deleteCategory(int id);
    public List<Kategoria> getCategories();

}
