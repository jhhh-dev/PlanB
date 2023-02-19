package planbServer.planb.admin.service;

import planbServer.planb.admin.model.OrderDTO;

import java.util.List;

public interface OrderService {

    public List<OrderDTO> getOrderList();
}
