package planbServer.planb.admin.service;

import planbServer.planb.admin.model.PaymentDTO;

import java.util.List;

public interface PaymentService {

    public List<PaymentDTO> getPaymentList();
}
