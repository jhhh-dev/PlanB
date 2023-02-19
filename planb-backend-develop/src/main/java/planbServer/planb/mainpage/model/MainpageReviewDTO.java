package planbServer.planb.mainpage.model;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class MainpageReviewDTO {

    @ApiModelProperty(value = "리뷰 index", example = "1")
    private int reviewId;

    @ApiModelProperty(value = "상품 ID", example = "5")
    private int proId;

    @ApiModelProperty(value = "주문 ID", example = "5")
    private int orderId;

    @ApiModelProperty(value = "사용자 ID", example = "test@example.com")
    private String userId;

    @ApiModelProperty(value = "리뷰 내용", example = "너무 이뻐요")
    private String reviewContent;

    @ApiModelProperty(value = "리뷰 이미지", example = "202302100310277060.jpg")
    private String reviewImg;

    @ApiModelProperty(value = "리뷰 작성일자", example = "yyyyMMdd HH:mm:ss")
    private String reviewDate;
}
