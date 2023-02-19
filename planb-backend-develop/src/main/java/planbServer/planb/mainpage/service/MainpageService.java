package planbServer.planb.mainpage.service;

import io.swagger.annotations.ApiParam;
import planbServer.planb.mainpage.model.MainpageProductDTO;
import planbServer.planb.mainpage.model.MainpageReviewDTO;
import java.util.List;

public interface MainpageService {

    public List<MainpageReviewDTO> getMainReview();
    @ApiParam("메인페이지 인기상품 리스트")
    public List<MainpageProductDTO> getMainPopular();
    @ApiParam("메인페이지 신규상품 리스트")
    public List<MainpageProductDTO> getNewProduct();
}
