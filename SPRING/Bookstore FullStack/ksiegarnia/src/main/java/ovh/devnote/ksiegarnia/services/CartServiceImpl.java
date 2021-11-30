package ovh.devnote.ksiegarnia.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ovh.devnote.ksiegarnia.dao.CartDAO;
import ovh.devnote.ksiegarnia.entity.Koszyk;
import ovh.devnote.ksiegarnia.entity.Ksiazka;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartDAO cartDAO;

    @Override
    @Transactional
    public void saveCart(Koszyk koszyk) {
        cartDAO.saveCart(koszyk);
    }

    @Override
    @Transactional
    public List<Koszyk> getKoszyk(String surname) {
        return cartDAO.getCart(surname);
    }

    @Override
    @Transactional
    public void addToCart(Ksiazka ksiazka) {
        cartDAO.addToCart(ksiazka);
    }

    @Override
    @Transactional
    public void deleteCart(int prodId) {
        cartDAO.deleteCart(prodId);
    }

    @Override
    @Transactional
    public Koszyk getCartBook(int prodId) {
        return cartDAO.getCartBook(prodId);
    }

}
