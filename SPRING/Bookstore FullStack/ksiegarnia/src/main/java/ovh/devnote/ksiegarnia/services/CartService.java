package ovh.devnote.ksiegarnia.services;

import ovh.devnote.ksiegarnia.entity.Koszyk;
import ovh.devnote.ksiegarnia.entity.Ksiazka;

import java.util.List;

public interface CartService {

    public void saveCart(Koszyk koszyk);
    public List<Koszyk> getKoszyk(String username);
    public void addToCart(Ksiazka ksiazka);
    public void deleteCart(int prodId);
    public Koszyk getCartBook(int prodId);
}
