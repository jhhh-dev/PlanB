package planbServer.planb.admin.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ProductHideDTO {

    @ApiModelProperty(value = "상품 번호", example = "20")
    private int proId;
    @ApiModelProperty(value = "상품 숨김 옵션", example = "0")
    private boolean proHide;

}
