package planbServer.planb.detail.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class PaymentDTO {

    @ApiModelProperty(value = "결제 index", example = "1")
    private int payId;

    @ApiModelProperty(value = "결제 유형", example = "kakaopay")
    private String payType;

    @ApiModelProperty(value = "결제 금액", example = "149000")
    private int payPrice;

    @ApiModelProperty(value = "결제 일자", example = "yyyyMMdd HH:mm:ss")
    private Timestamp payDate;



    @ApiModelProperty(value = "아임포트 결제 ID", example = "imp_061534872056")
    private String impUid;

    @ApiModelProperty(value = "아임포트 주문 ID", example = "merchant_1675742534512")
    private String merchantUid;

    @ApiModelProperty(value = "아임포트 결제 결과", example = "paid")
    private String status;

}
