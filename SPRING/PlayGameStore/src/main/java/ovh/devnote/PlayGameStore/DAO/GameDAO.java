package ovh.devnote.PlayGameStore.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ovh.devnote.PlayGameStore.Entity.Game;

@Repository
public interface GameDAO extends JpaRepository<Game,Integer> {

}
