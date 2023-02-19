package planbServer.planb.shop.repository;

import org.apache.ibatis.annotations.Mapper;
import planbServer.planb.shop.model.ProductSearchDTO;
import planbServer.planb.shop.model.ShopProductVO;

import java.util.List;

@Mapper
public interface ShopProductDAO {
    /* SHOP 페이지 , 전체 상품 출력 */
    public List<ShopProductVO> selectProductList(ProductSearchDTO productSearchDTO);
}
