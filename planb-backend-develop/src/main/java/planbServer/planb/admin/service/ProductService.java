package planbServer.planb.admin.service;

import planbServer.planb.admin.model.ProductHideDTO;
import planbServer.planb.admin.model.ProductVO;

import java.util.List;

public interface ProductService {

    public List<ProductVO> selectProductList();

    public void updateStatus(ProductHideDTO productHideDTO);

    public void insertProduct(ProductVO productVO);

    public ProductVO getProductInfo(int proId);

    public void updateProduct(ProductVO productVO);

    public void deleteProduct(int proId);
}

