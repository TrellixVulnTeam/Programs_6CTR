package ovh.devnote.ksiegarnia.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ovh.devnote.ksiegarnia.dao.OrderDAO;
import ovh.devnote.ksiegarnia.entity.Zamowione;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDAO orderDAO;

    @Override
    @Transactional
    public void addToOrder(Zamowione zamowione) {
        orderDAO.addToOrder(zamowione);
    }

    @Override
    @Transactional
    public List<Zamowione> getOrder(String surname) {
        return orderDAO.getOrder(surname);
    }

    @Override
    @Transactional
    public List<Zamowione> getOrders() {
        return orderDAO.getOrders();
    }

    @Override
    @Transactional
    public Zamowione getOrderId(int orderId) {
        return orderDAO.getOrderId(orderId);
    }
    @Override
    @Transactional
    public void changeStatus(Zamowione zamowione) {
        orderDAO.changeStatus(zamowione);
    }




}
