package ovh.devnote.ksiegarnia.dao;

import ovh.devnote.ksiegarnia.entity.Zamowione;

import java.util.List;

public interface OrderDAO {

    void changeStatus(Zamowione zamowione);

    public void addToOrder(Zamowione zamowione) ;



    List<Zamowione> getOrder(String username);

    List<Zamowione> getOrders();

    Zamowione getOrderId(int orderId);
}
