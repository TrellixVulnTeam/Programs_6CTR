package ovh.devnote.ksiegarnia.services;

import org.springframework.transaction.annotation.Transactional;
import ovh.devnote.ksiegarnia.entity.Zamowione;

import java.util.List;

public interface OrderService {


    public void addToOrder(Zamowione zamowione);


    @Transactional
    List<Zamowione> getOrder(String surname);

    @Transactional
    List<Zamowione> getOrders();

    @Transactional
    Zamowione getOrderId(int orderId);

    @Transactional
    void changeStatus(Zamowione zamowione);
}
