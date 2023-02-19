package planbServer.planb.mypage.model;

import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReviewListDTO {
    @ApiParam(value = "리뷰 아이디")
    private int reviewId;

    @ApiParam(value = "상품 아이디")
    private int proId;

    @ApiParam(value = "상품 이름")
    private String proName;

    @ApiParam(value = "리뷰 내용")
    private String reviewContent;

    @ApiParam(value = "리뷰 이미지")
    private String reviewImg;

    @ApiParam(value = "리뷰 작성 날짜")
    private String reviewDate;
}
