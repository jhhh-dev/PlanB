package planbServer.planb.shop.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import planbServer.planb.shop.model.ProductSearchDTO;
import planbServer.planb.shop.model.ShopProductVO;
import planbServer.planb.shop.service.ShopProductService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    ShopProductService shopProductService;
    @ApiOperation(value = "상품 목록 출력", notes = "검색 키워드, 필터(카테고리, 가격, 사이즈) 조건이 존재하면 적용하여 상품 목록을 출력한다.")
    @GetMapping("/productList")
    public List<ShopProductVO> selectProductList(ProductSearchDTO productSearchDTO ) {
        log.info("---------------------상품 목록 출력 시작---------------------");

        log.info("---------------------DTO 값 출력: "+productSearchDTO);

        log.info(productSearchDTO.getPage()+"페이지");

        return shopProductService.selectProductList(productSearchDTO);
    }


}
