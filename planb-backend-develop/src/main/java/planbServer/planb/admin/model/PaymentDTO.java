package planbServer.planb.admin.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class PaymentDTO {
    @ApiModelProperty(value = "결제 index", example = "1")
    private int payId;
    @ApiModelProperty(value = "결제 유형", example = "카카오페이")
    private String payType;
    @ApiModelProperty(value = "결제 금액", example = "240000")
    private int payPrice;
    @ApiModelProperty(value = "결제 일자", example = "yyyyMMdd HH:mm:ss")
    private Timestamp payDate;
    @ApiModelProperty(value = "결제 ID", example = "imp_012345678910")
    private String impUid;
    @ApiModelProperty(value = "주문 ID", example = "merchant_1675742534512")
    private String merchantUid;
    @ApiModelProperty(value = "결제 상태", example = "paid")
    private String status;

    @ApiModelProperty(value = "상품 ID", example = "20")
    private int proId;
    @ApiModelProperty(value = "상품명", example = "컨버스 척테일러 70")
    private String proName;
}
