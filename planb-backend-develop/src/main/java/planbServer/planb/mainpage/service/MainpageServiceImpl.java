package planbServer.planb.mainpage.service;

import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import planbServer.planb.mainpage.model.MainpageProductDTO;
import planbServer.planb.mainpage.model.MainpageReviewDTO;
import planbServer.planb.mainpage.repository.MainpageDAO;

import java.util.List;

@Service
public class MainpageServiceImpl implements MainpageService {

    @Autowired
    MainpageDAO mainpageDAO;

    public List<MainpageReviewDTO> getMainReview() {
        return mainpageDAO.getMainReview();
    }

    public List<MainpageProductDTO> getMainPopular() {
        return mainpageDAO.getMainPopular();
    }

    public List<MainpageProductDTO> getNewProduct() {
        return mainpageDAO.getNewProduct();
    }
}