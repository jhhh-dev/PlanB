package planbServer.planb.review.model;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
public class ReviewDTO {
    @ApiParam(value = "리뷰 번호")
    private int reviewId;
    @ApiParam(value = "제품 번호")
    private int proId;
    @ApiParam(value = "주문 번호")
    private int orderId;
    @ApiParam(value = "유저 아이디")
    private String userId;
    @ApiParam(value = "리뷰 내용")
    private String reviewContent;
    @ApiParam(value = "리뷰 이미지")
    private String reviewImg;
    @ApiParam(value = "리뷰 작성일")
    private String reviewDate;
    @ApiParam(value = "좋아요 수")
    private int likeCount;
    @ApiParam(value = "유저 이름")
    private String userName;
    @ApiParam(value = "제품 이름")
    private String proName;
}
