package ovh.devnote.PlayGameStore.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ovh.devnote.PlayGameStore.Entity.Category;

@Repository
public interface CategoryDAO extends JpaRepository<Category,Integer> {
}
