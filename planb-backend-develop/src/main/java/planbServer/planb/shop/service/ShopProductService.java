package planbServer.planb.shop.service;

import planbServer.planb.shop.model.ProductSearchDTO;
import planbServer.planb.shop.model.ShopProductVO;

import java.util.List;


public interface ShopProductService {
    public List<ShopProductVO> selectProductList(ProductSearchDTO productSearchDTO);
}
