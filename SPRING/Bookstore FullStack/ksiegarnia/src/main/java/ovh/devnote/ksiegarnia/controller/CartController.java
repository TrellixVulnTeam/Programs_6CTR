package ovh.devnote.ksiegarnia.controller;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ovh.devnote.ksiegarnia.entity.Koszyk;
import ovh.devnote.ksiegarnia.entity.Ksiazka;
import ovh.devnote.ksiegarnia.services.BookService;
import ovh.devnote.ksiegarnia.services.CartService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/cart", method = { RequestMethod.GET, RequestMethod.POST })
public class CartController {

    @Autowired
    CartService cartService;
    @Autowired
    BookService bookService;
    @Autowired
    private SessionFactory sessionFactory;

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/addToCart")
    public String  addToCart(@RequestParam("bookId") int bookId)
    {
        Koszyk koszyk = new Koszyk();
        Ksiazka ksiazka = new Ksiazka();
        int ilosc;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        ksiazka = bookService.getBook(bookId);
        ilosc = ksiazka.getIlosc();
        ilosc=ilosc-1;
        ksiazka.setIlosc(ilosc);

        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String ObecnaData;
        ObecnaData=formatter.format(date).toString();
        koszyk.setData(ObecnaData);
        koszyk.setKsiazka_id(bookId);

        koszyk.setUsername(username);

        koszyk.setNazwa(bookService.getBook(bookId).getNazwa());
        koszyk.setKategoria(bookService.getBook(bookId).getKategoria().getNazwa());
        koszyk.setCena(bookService.getBook(bookId).getCena());
        cartService.saveCart(koszyk);

        bookService.saveBook(ksiazka);
        return "redirect:/user";
    }

    @GetMapping("/cart")
    public String listCart(Model model)
    {
        double cenaRazem=0;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        List<Koszyk> products = cartService.getKoszyk(username);
        model.addAttribute("products", products);
        for (Koszyk kosz: products) {
            cenaRazem = cenaRazem + kosz.getCena();
        }

        return "cart";
    }


    @GetMapping("/deleteProduct")
    public String deleteProduct(@RequestParam("prodId") int prodId) {

        int ilosc;
        int bookId = cartService.getCartBook(prodId).getKsiazka_id();
        Ksiazka ksiazka = new Ksiazka();
        ksiazka = bookService.getBook(bookId);
        ilosc =  ksiazka.getIlosc();
        ilosc = ilosc + 1;
        ksiazka.setIlosc(ilosc);
        cartService.deleteCart(prodId);
        bookService.saveBook(ksiazka);
        return "redirect:/cart/cart";
    }



}
