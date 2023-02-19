package planbServer.planb.detail.model;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReviewDTO {
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
    private Timestamp reviewDate;

    @ApiModelProperty(value = "상품명", example = "뉴발란스 993 메이드 인 USA 그레이 - D 스탠다드")
    private String proName;
}
