package planbServer.planb.mainpage.repository;

import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Mapper;
import planbServer.planb.mainpage.model.MainpageProductDTO;
import planbServer.planb.mainpage.model.MainpageReviewDTO;

import java.util.List;

@Mapper
public interface MainpageDAO {

    @ApiParam("메인페이지 스타일 후기")
    public List<MainpageReviewDTO> getMainReview();

    @ApiParam("메인페이지 인기상품 리스트")
    public List<MainpageProductDTO> getMainPopular();

    @ApiParam("메인페이지 신규상품 리스트")
    public List<MainpageProductDTO> getNewProduct();
}
