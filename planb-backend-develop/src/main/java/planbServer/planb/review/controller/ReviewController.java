package planbServer.planb.review.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import planbServer.planb.admin.model.ProductVO;
import planbServer.planb.review.model.*;
import planbServer.planb.review.service.ReviewService;
import planbServer.planb.util.ChangeName;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Api("리뷰(STYLE)API Controller")
@RestController
@Slf4j
@RequestMapping("/review")
public class ReviewController {
    //파일 첨부를 위한 경로
    private final static String reviewImageDirectory = Paths.get("").toAbsolutePath() + "/src/main/resources/images/";
   // private final static String imageDirectory = Paths.get("").toAbsolutePath() + "/src/main/resources/images/";

    @Autowired
    ReviewService reviewService;

    @ApiOperation(value = "리뷰 등록")
    @PostMapping("/reviewInsert")
    public void reviewService(
            MultipartFile uploadFile,
            ReviewDTO reviewDTO){
//        //VO + 업로드파일 확인
//        log.info("VO 확인 = "+ reviewDTO);
//        log.info("upload 확인 = "+ uploadFile);
//        log.info("리뷰 등록 진행");

        // 업로드할 폴더의 경로(path) 구하기
        String path = reviewImageDirectory;

        //업로드한 파일의 파일명을 변수에 담기
        String uploadFileName = uploadFile.getOriginalFilename();

        //파일명을 날짜타입으로 변경
        uploadFileName = ChangeName.getChangeFileName(uploadFile.getOriginalFilename());

        try {
            //path 경로에 파일 업로드 진행
            uploadFile.transferTo(new File(path + "/" + uploadFileName));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //성공 시 콘솔에 찍기
      //  log.info("파일 업로드 성공 -> " + path + uploadFileName );

        //DTO 에 uploadFile 담기
        reviewDTO.setReviewImg(uploadFileName);


       // log.info("최종 VO확인 : " + reviewDTO);

        reviewService.reviewInsert(reviewDTO);

    }

    @ApiOperation(value = "작성 리뷰 체크")
    @GetMapping("/reviewCheck")
    public Integer reviewCheck(ReviewDTO reviewDTO){
        return reviewService.reviewCheck(reviewDTO);
    }

    @ApiOperation(value = "좋아요 수 카운트")
    @GetMapping("/likeCount")
    public Integer likeCouont(int reviewId){
        return reviewService.likeCount(reviewId);
    }

    @ApiOperation(value = "리뷰 상세 내용 호출")
    @GetMapping("/reviewDetail")
    public ReviewDTO reviewDetail(Integer reviewId){
        ReviewDTO review = new ReviewDTO();
        review = reviewService.reviewDetail(reviewId);
        review.setLikeCount(reviewService.likeCount(reviewId));
        return review;
    }

    @ApiOperation(value = "좋아요 누르기")
    @GetMapping("/insertLike")
    public void insertLike(LikeDTO likeDTO){
        reviewService.insertLike(likeDTO);
    }

    @ApiOperation(value = "좋아요 취소")
    @GetMapping("/deleteLike")
    public void deleteLike(LikeDTO likeDTO){
        reviewService.deleteLike(likeDTO);
    }

    @ApiOperation(value = "좋아요 여부 체크")
    @GetMapping("/checkLike")
    public String checkLike(LikeDTO likeDTO){
        LikeDTO like = reviewService.checkLike(likeDTO);
        if(like != null){
            return "OK";
        }else{
            return "NO";
        }
    }

    @ApiOperation(value = "리뷰 목록 페이징 처리 - 최신순")
    @GetMapping("/reviewList")
    public List<ReviewDTO> reviewListPageRecentfilter(PageDTO pageDTO){
        log.info(pageDTO.getPage()+"페이지");
        log.info(pageDTO.getOption()+"option");
        return reviewService.reviewListPageRecentfilter(pageDTO);
    }

    @ApiOperation(value = "리뷰 삭제")
    @GetMapping("/reviewDelete")
    public void reviewDelete(ReviewDTO reviewDTO){
        reviewService.reviewDelete(reviewDTO);
    }

}
