package ovh.devnote.ksiegarnia.dao;

import ovh.devnote.ksiegarnia.entity.Koszyk;
import ovh.devnote.ksiegarnia.entity.Ksiazka;

import java.util.List;

public interface CartDAO {
    public void saveCart(Koszyk koszyk);

    List<Koszyk> getCart(String username);
    public void addToCart(Ksiazka ksiazka);
    public void deleteCart(int prodId);
    public Koszyk getCartBook(int prodId);
}
