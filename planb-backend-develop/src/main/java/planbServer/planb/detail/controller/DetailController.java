package planbServer.planb.detail.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import planbServer.planb.detail.model.BookmarkVO;
import planbServer.planb.detail.model.DetailProductDTO;
import planbServer.planb.detail.model.OrderDTO;
import planbServer.planb.detail.model.PaymentDTO;
import planbServer.planb.detail.service.DetailService;
import planbServer.planb.detail.model.ReviewDTO;
import planbServer.planb.review.model.LikeDTO;

import java.util.List;

@Slf4j
@RestController
public class DetailController {

    @Autowired
    DetailService detailService;

    @ApiOperation(value = "특정 상품의 상세정보 가져오기", notes = "상품 ID에 해당하는 상품의 정보를 가져온다")
    @GetMapping("/detailInfo")
    public DetailProductDTO getDetailInfo(
            @ApiParam(value="상품 ID", required=true)
            @RequestParam int proId){

        log.info("상품 정보 가져오기" + proId);

        return detailService.getDetailInfo(proId);
    }

    @ApiOperation(value = "특정 상품의 리뷰 가져오기", notes = "상품 ID에 해당하는 리뷰를 가져온다")
    @GetMapping("/getStyle")
    public List<ReviewDTO> getStyle(
            @ApiParam(value="상품 ID", required=false)
            @RequestParam int proId){

        log.info("스타일 리뷰 가져오기" + proId);

        return detailService.getStyle(proId);
    }

    @ApiOperation(value = "브랜드 상품 가져오기", notes = "보고있는 상품과 동일한 브랜드의 상품을 가져온다")
    @GetMapping("/getBrandProduct")
    public List<DetailProductDTO> getBrandProduct(
            @ApiParam(value="상품 ID", required=false)
            @RequestParam int proId){

        log.info("동일 브랜드 상품가져오기 // 번호는? : " + proId);

        return detailService.getBrandProduct(proId);
    }

    @ApiOperation(value = "상품 결제하기", notes = "아임포트 API를 활용하여 결제하기")
    @PostMapping ("/payment")
    public void insertPayment(
            @ApiParam(value="결제 정보", required=true)
            @RequestBody PaymentDTO paymentDTO){

        log.info("결제 정보 확인 : " + paymentDTO);

        //payId = AI, payDate = now(),

        //서비스로 데이터 전달
        detailService.insertPayment(paymentDTO);

        
        log.info("결제 관련 서비스 전달 완료");


    }

    @ApiOperation(value = "주문정보 입력", notes = "결제 완료 후 주문정보 입력")
    @PostMapping ("/order")
    public void insertOrder(
            @ApiParam(value="주문 정보", required=true)
            @RequestBody OrderDTO orderDTO){

        log.info("주문 정보 확인 : " + orderDTO);

        //서비스로 데이터 전달
        detailService.insertOrder(orderDTO);

        log.info("주문 관련 서비스 전달 완료");
    }

    @ApiOperation(value = "조회수 증가", notes = "상품 상세페이지 조회수 증가")
    @GetMapping ("/plusReadCount")
    public void plusReadCount(
            @ApiParam(value="상품 ID", required=true)
            @RequestParam int proId){


        //서비스로 데이터 전달
        detailService.plusReadCount(proId);

        log.info("조회수 증가 완료");
    }

    @ApiOperation(value = "관심상품 등록/제거", notes = "관심상품으로 등록 또는 제거")
    @PostMapping ("/bookmarkChange")
    public void changeBookmark(
            @ApiParam(value="사용자ID 및 상품ID", required=true)
            @RequestBody BookmarkVO bookmarkVO){

        // 관심상품으로 등록되어있는 경우 제거
        if (detailService.getBookmarkCount(bookmarkVO) >= 1){

            detailService.deleteBookmark(bookmarkVO);
            log.info("관심상품 제거 완료" + bookmarkVO);
        } else {

            // 없는 경우 관심상품 등록
            detailService.insertBookmark(bookmarkVO);
            log.info("관심상품 등록 완료" + bookmarkVO);
        }
    }

    @ApiOperation(value = "관심상품 등록 여부", notes = "등록이 되어있을 시 1")
    @GetMapping ("/bookmarkCount")
    public int bookmarkCount(
            @ApiParam(value="사용자ID 및 상품ID", required=true) BookmarkVO bookmarkVO){

        // 유저정보, 상품정보
        log.info("해당 상품에 대한 북마크 여부 : " + bookmarkVO);

        return detailService.getBookmarkCount(bookmarkVO);
    }

    @ApiOperation(value = "특정 사용자와 상품 후기 좋아요 여부", notes = "등록이 되어있을 시 1")
    @GetMapping ("/likeCount")
    public int getReviewLike(
            @ApiParam(value="리뷰ID 및 사용자ID", required=true) LikeDTO likeDTO){

        // 유저정보, 상품정보
        log.info("해당 리뷰와 유저에 대한 좋아요 여부 : " + likeDTO);

        return detailService.getReviewLike(likeDTO);
    }


    @ApiOperation(value = "해당 후기의 좋아요 전체 Count", notes = "좋아요 갯수")
    @GetMapping ("/likeAllCount")
    public int getLikeCount(@ApiParam(value="리뷰ID", required=true) int reviewId){

        int count = detailService.getLikeCount(reviewId);

        log.info("해당 상품의 전체 Count 갯수 = " + count );

        return count;
    }

    @ApiOperation(value = "해당 후기의 좋아요 등록/해제", notes = "좋아요 등록 또는 삭제")
    @PostMapping ("/changeLike")
    public void changeLike(@ApiParam(value="리뷰ID 와 사용자ID", required=true)
                               @RequestBody LikeDTO likeDTO){

        //DTO 의 사용하지않는 컬럼에 0 값 넣기
//        likeDTO.setLikeCount(0);

        // 관심상품으로 등록되어있는 경우 제거
        if (detailService.getReviewLike(likeDTO) != 0){

            detailService.deleteLike(likeDTO);
            log.info("좋아요 제거 완료");
        } else {

            // 없는 경우 관심상품 등록
            detailService.insertLike(likeDTO);
            log.info("좋아요 등록 완료");
        }
    }


}
