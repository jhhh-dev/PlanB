package planbServer.planb.admin.repository;

import org.apache.ibatis.annotations.Mapper;
import planbServer.planb.admin.model.OrderDTO;

import java.util.List;

@Mapper
public interface OrderDAO {

    public List<OrderDTO> getOrderList();
}
