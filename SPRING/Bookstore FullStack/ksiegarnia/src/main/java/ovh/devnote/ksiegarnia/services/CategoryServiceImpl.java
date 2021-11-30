package ovh.devnote.ksiegarnia.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ovh.devnote.ksiegarnia.dao.CategoryDAO;
import ovh.devnote.ksiegarnia.entity.Kategoria;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryDAO categoryDAO;
    @Autowired
    public CategoryServiceImpl(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Override
    @Transactional
    public List<Kategoria> getCategories() {
        return categoryDAO.getCategories();
    }

    @Override
    @Transactional
    public void saveCategory(Kategoria kategoria) {
        categoryDAO.saveCategory(kategoria);
    }

    @Override
    @Transactional
    public Kategoria getCategory(int id) {
        return categoryDAO.getCategory(id);
    }

    @Override
    @Transactional
    public void deleteCategory(int id) {
        categoryDAO.deleteCategory(id);
    }


}