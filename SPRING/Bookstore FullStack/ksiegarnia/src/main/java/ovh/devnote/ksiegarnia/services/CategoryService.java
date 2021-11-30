package ovh.devnote.ksiegarnia.services;

import ovh.devnote.ksiegarnia.entity.Kategoria;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Locale;

public interface CategoryService {

    List<Kategoria> getCategories();
    void saveCategory(Kategoria kategoria);
    Kategoria getCategory(int id);

    void deleteCategory(int id);



}
