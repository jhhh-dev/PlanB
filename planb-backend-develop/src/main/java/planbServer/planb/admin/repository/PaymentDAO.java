package planbServer.planb.admin.repository;

import org.apache.ibatis.annotations.Mapper;
import planbServer.planb.admin.model.PaymentDTO;

import java.util.List;

@Mapper
public interface PaymentDAO {

    public List<PaymentDTO> getPaymentList();
}
