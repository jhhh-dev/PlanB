package planbServer.planb.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import planbServer.planb.admin.model.PaymentDTO;
import planbServer.planb.admin.repository.PaymentDAO;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDAO paymentDAO;

    @Override
    public List<PaymentDTO> getPaymentList(){

        return paymentDAO.getPaymentList();
    }
}
