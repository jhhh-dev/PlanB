package planbServer.planb.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import planbServer.planb.admin.model.ProductHideDTO;
import planbServer.planb.admin.model.ProductVO;
import planbServer.planb.admin.repository.ProductDAO;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO productDAO;

    @Override
    public List<ProductVO> selectProductList() {

        return productDAO.selectProductList();
    }

    @Override
    public void updateStatus(ProductHideDTO productHideDTO){

        productDAO.updateStatus(productHideDTO);
    }

    @Override
    public void insertProduct(ProductVO productVO){

        productDAO.insertProduct(productVO);
    }

    @Override
    public ProductVO getProductInfo(int proId){

        return productDAO.getProductInfo(proId);
    }

    @Override
    public void updateProduct(ProductVO productVO){

        productDAO.updateProduct(productVO);
    }

    @Override
    public void deleteProduct(int proId){

        productDAO.deleteProduct(proId);
    }


}
