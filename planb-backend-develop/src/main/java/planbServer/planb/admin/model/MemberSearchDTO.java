package planbServer.planb.admin.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MemberSearchDTO {

    @ApiModelProperty(value = "검색 단어", example = "홍길동")
    private String searchWord;

    @ApiModelProperty(value = "검색 옵션", example = "최신순")
    private String searchOption;

}
