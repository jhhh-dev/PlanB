package planbServer.planb.shop.model;

import lombok.Data;
import io.swagger.annotations.ApiParam;
import java.util.List;


@Data
public class ProductSearchDTO {
    @ApiParam(value = "검색 키워드", required = false, example = "나이키")
    private String keyword;

    @ApiParam(value = "정렬 옵션", required = true, example = "recent")
    private String sortOption;

    @ApiParam(value = "카테고리 옵션", required = false, example = "10,20")
    private String cateOption;

    @ApiParam(value = "가격 옵션 - 최소 가격", required = true, example = "0")
    private String minPrice;

    @ApiParam(value = "가격 옵션 - 최대 가격", required = true, example = "2000000")
    private String maxPrice;

    @ApiParam(value = "사이즈 옵션 - 최소 사이즈", required = true, example = "210")
    private String minSize;

    @ApiParam(value = "사이즈 옵션 - 최대 사이즈", required = true, example = "290")
    private String maxSize;

    @ApiParam(value = "페이징 - 현재 페이지 번호", required = true, example = "1")
    private int page;

    @ApiParam(value = "페이징 - 오프셋 설정")
    private int getOffset(){
        int result = (page) * 12;
        return result;
    }


}
