package planbServer.planb.admin.repository;

import org.apache.ibatis.annotations.Mapper;
import planbServer.planb.admin.model.ProductHideDTO;
import planbServer.planb.admin.model.ProductVO;

import java.util.List;

@Mapper
public interface ProductDAO {

    /* 관리자페이지 > 상품관리 - 전체 조회 */
    public List<ProductVO> selectProductList();

    /* 관리자페이지 > 상품관리 - 숨김상태 변경 */
    public void updateStatus(ProductHideDTO productHideDTO);

    /* 관리자페이지 > 상품등록 */
    public void insertProduct(ProductVO productVO);

    /* 관리자페이지 > 상품등록 */
    public ProductVO getProductInfo(int proId);

    /* 관리자페이지 > 상품관리 - 숨김상태 변경 */
    public void updateProduct(ProductVO productVO);

    /* 관리자페이지 > 상품관리 - 상품 삭제 */
    public void deleteProduct(int proId);


}
