package planbServer.planb.mainpage.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import planbServer.planb.mainpage.model.MainpageProductDTO;
import planbServer.planb.mainpage.model.MainpageReviewDTO;
import planbServer.planb.mainpage.service.MainpageService;


import java.util.List;


@Slf4j
@RestController
public class MainpageController {

    @Autowired
    MainpageService mainpageService;


    @ApiOperation(value = "메인페이지 스타일 리뷰 가져오기", notes = "최근 등록된 순서대로 스타일 리뷰 출력")
    @GetMapping("/mainReview")
    public List<MainpageReviewDTO> getMainReview(){

        return mainpageService.getMainReview();
    }

    @ApiOperation(value = "메인페이지 인기상품 가져오기", notes = "조회수가 높은 상품순으로 출력")
    @GetMapping("/mainPopular")
    public List<MainpageProductDTO> getMainPopular(){

        return mainpageService.getMainPopular();
    }

    @ApiOperation(value = "메인페이지 신규상품 리스트 가져오기", notes = "최근 등록된 순서대로 상품 출력")
    @GetMapping("/mainNewProduct")
    public List<MainpageProductDTO> getNewProduct(){

        return mainpageService.getNewProduct();
    }

}
