package ovh.devnote.PlayGameStore.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ovh.devnote.PlayGameStore.DAO.GameDAO;
import ovh.devnote.PlayGameStore.Entity.Game;

import java.util.List;

@Service
public class GameService {


    @Autowired
    GameDAO gameDAO;

    public void addGame(Game game)
    {
        gameDAO.save(game);
    }
    public Iterable<Game> gamesList()
    {
        Iterable<Game> games = gameDAO.findAll();

        return games;
    }
    public void deleteGame(Integer id)
    {
        gameDAO.deleteById(id);

    }
    public List<Game> findGameByTitle()
    {
       List<Game> games = gameDAO.findAll();
        return games;
    }


}
