package planbServer.planb.review.model;

import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class LikeDTO {
    @ApiParam(value = "리뷰 번호", required = true)
    private int reviewId;
    @ApiParam(value = "유저 아이디", required = true)
    private String userId;

}
