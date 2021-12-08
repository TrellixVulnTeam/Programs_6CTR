package ovh.devnote.PlayGameStore.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ovh.devnote.PlayGameStore.Entity.Game;
import ovh.devnote.PlayGameStore.Service.GameService;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @RequestMapping("/addGameForm")
    public String addGameForm() {
        return "gameAddForm";
    }

    @PostMapping("/saveGame")
    public String saveGame(@RequestParam String title, @RequestParam int quantity, @RequestParam double price) {
        Game game = new Game();
        game.setTitle(title);
        game.setQuantity(quantity);
        game.setPrice(price);
        gameService.addGame(game);

        return "redirect:/games/list";
    }

    @GetMapping("/list")
    public String listGame(Model model) {
        Iterable<Game> games = gameService.gamesList();

        model.addAttribute("games", games);

        return "listGame";
    }

    @PostMapping("/deleteGame")
    public String deleteGame(@RequestParam Integer deleteId) {
        gameService.deleteGame(deleteId);
        return "redirect:/games/list";
    }

    @GetMapping("/findGame")

    public String findGameById(@RequestParam String title, @RequestParam double lowPrice, @RequestParam double maxPrice, Model model) {
        List<Game> games = gameService.findGameByTitle();
        List<Game> foundGame = new ArrayList<>();

//        Predicate<Game> findGame = c-> games.stream().anyMatch(u->u.getTitle().equals(title));
//
//        foundGame = games.stream().filter(findGame).collect(Collectors.toList());

//        System.out.println(title + " szukaj w " + games + " i znalazłeś " + foundGame );
//
        for (Game game : games) {



                if(lowPrice <= 0 && maxPrice > 0 && title == "")
                {
                    if(game.getPrice()<=maxPrice)
                    {
                        foundGame.add(game);
                    }
                }else if(lowPrice > 0 && maxPrice <= lowPrice && title == "")
                {
                    if(game.getPrice()>lowPrice)
                    {
                        foundGame.add(game);
                    }
                }else if(lowPrice > 0 && maxPrice > lowPrice && title == "")
                {
                    if(game.getPrice()>lowPrice && game.getPrice()<maxPrice)
                    {
                        foundGame.add(game);
                    }
                }else if(lowPrice == maxPrice && title == "")
                {
                    if(game.getPrice()==lowPrice)
                    {
                        foundGame.add(game);
                    }

                 }else if(lowPrice <= 0 && maxPrice > 0 && title != "" )
                {
                    if(game.getPrice()<=maxPrice && game.getTitle().contains(title))
                    {
                        foundGame.add(game);
                    }

                }else if(lowPrice > 0 && maxPrice <= lowPrice && title != "")
                {
                    if(game.getPrice()>lowPrice && game.getTitle().contains(title))
                    {
                        foundGame.add(game);
                    }
                }else if(lowPrice > 0 && maxPrice > lowPrice && title != "")
                {
                    if(game.getPrice()>lowPrice && game.getPrice()<maxPrice && game.getTitle().contains(title))
                    {
                        foundGame.add(game);
                    }
                }else if(lowPrice == maxPrice && title != "")
                {
                    if(game.getPrice()==lowPrice && game.getTitle().contains(title) )
                    {
                        foundGame.add(game);
                    }

                }

        }



        model.addAttribute("foundGame", foundGame);
        model.addAttribute("foundGameSize", foundGame.size());

        return "foundGame";
    }




}
