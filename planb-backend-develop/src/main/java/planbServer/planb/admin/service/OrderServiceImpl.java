package planbServer.planb.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import planbServer.planb.admin.model.OrderDTO;
import planbServer.planb.admin.repository.OrderDAO;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderDAO orderDAO;

    @Override
    public List<OrderDTO> getOrderList() {

        return orderDAO.getOrderList();
    }
}
