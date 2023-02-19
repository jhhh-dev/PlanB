package planbServer.planb.detail.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class OrderDTO {

    @ApiModelProperty(value = "주문 index", example = "1")
    private int orderId;

    @ApiModelProperty(value = "사용자 ID", example = "test@example.com")
    private String userId;

    @ApiModelProperty(value = "주문 일자", example = "yyyyMMdd HH:mm:ss")
    private Timestamp orderDate;

    @ApiModelProperty(value = "상품 ID", example = "5")
    private String proId;

    @ApiModelProperty(value = "배송비", example = "5000")
    private String orderFee;

    @ApiModelProperty(value = "받는 사람", example = "홍길동")
    private String orderReciver;

    @ApiModelProperty(value = "받을 주소", example = "서울시 영등포구 영등포로150 생각공장 당산 B동 1509호")
    private String orderAddr;

    @ApiModelProperty(value = "연락처", example = "01012345678")
    private String orderPhone;

    @ApiModelProperty(value = "결제 ID", example = "imp_061534872056")
    private String payId;


}
