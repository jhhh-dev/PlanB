package planbServer.planb.detail.model;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class BookmarkVO {

    @ApiModelProperty(value = "사용자 ID", example = "test@planb.com")
    private String userId;
    @ApiModelProperty(value = "상품 ID", example = "5")
    private String proId;
}
