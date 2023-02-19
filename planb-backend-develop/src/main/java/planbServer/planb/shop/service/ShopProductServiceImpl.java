package planbServer.planb.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import planbServer.planb.shop.model.ProductSearchDTO;
import planbServer.planb.shop.model.ShopProductVO;
import planbServer.planb.shop.repository.ShopProductDAO;

import java.util.List;
@Slf4j
@Service
public class ShopProductServiceImpl implements ShopProductService {
    @Autowired
    private ShopProductDAO shopProductDAO;

    @Override
    public List<ShopProductVO> selectProductList(ProductSearchDTO productSearchDTO) {
        
        return shopProductDAO.selectProductList(productSearchDTO);
    }
}
