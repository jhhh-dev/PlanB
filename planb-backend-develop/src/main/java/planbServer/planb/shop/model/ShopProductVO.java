package planbServer.planb.shop.model;

import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ShopProductVO {

    @ApiParam(value = "상품 번호", example = "2")
    private int proId;

    @ApiParam(value = "상품 이름", example = "나이키 조던 화이트")
    private String proName;

    @ApiParam(value = "상품 등록 일자", example = "2023-01-18 19:49:38")
    private Timestamp proDate;

    @ApiParam(value = "상품 브랜드", example = "나이키")
    private String proBrand;

    @ApiParam(value = "상품 사이즈", example = "250")
    private String proSize;

    @ApiParam(value = "상품 가격", example = "185000")
    private int proPrice;

    @ApiParam(value = "상품 이미지", example = "2023013105445876171.png")
    private String proImg;

    @ApiParam(value = "상품 카테고리", example = "20")
    private int proCate;

    @ApiParam(value = "상품 조회수", example = "25")
    private int proCnt;

    @ApiParam(value = "상품 숨김 여부", example = "0")
    private boolean proHide;

}
