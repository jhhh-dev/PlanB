package planbServer.planb.mypage.model;

import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class BookMarkListDTO {
    @ApiParam(value = "상품 번호", required = true)
    private int proId;

    @ApiParam(value = "상품이름", required = true)
    private String proName;

    @ApiParam(value = "상품가격", required = true)
    private String proPrice;
}
