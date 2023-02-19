package planbServer.planb.mainpage.model;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class MainpageProductDTO {

    @ApiModelProperty(value = "상품 ID", example = "5")
    private int proId;
    @ApiModelProperty(value = "상품명", example = "컨버스 척테일러 70")
    private String proName;

    @ApiModelProperty(value = "브랜드", example = "나이키")
    private String proBrand;

    @ApiModelProperty(value = "상품 금액", example = "240000")
    private int proPrice;

    @ApiModelProperty(value = "상품 이미지", example = "23021301234567.png")
    private String proImg;

    @ApiModelProperty(value = "상품 조회수", example = "4")
    private int proCnt;

    @ApiModelProperty(value = "해당 상품의 북마크 갯수", example = "4")
    private int bookmarkCount;

}
