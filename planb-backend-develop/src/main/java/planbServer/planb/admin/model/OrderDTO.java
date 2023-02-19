package planbServer.planb.admin.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class OrderDTO {
    @ApiModelProperty(value = "주문 ID(idx)", example = "1")
    private int orderId;
    @ApiModelProperty(value = "사용자 ID", example = "test@example.com")
    private String userId;
    @ApiModelProperty(value = "주문 일자", example = "yyyyMMdd HH:mm:ss")
    private Timestamp orderDate;
    @ApiModelProperty(value = "상품 ID", example = "20")
    private int proId;
    @ApiModelProperty(value = "배송비", example = "5000")
    private int orderFee;
    @ApiModelProperty(value = "배송받는 사람", example = "홍길동")
    private String orderReciver;
    @ApiModelProperty(value = "배송지 주소", example = "서울시 영등포구 영등포로150 생각공장 당산 B동 1590호")
    private String orderAddr;
    @ApiModelProperty(value = "배송받는 사람 연락처", example = "01012345678")
    private String orderPhone;
    @ApiModelProperty(value = "결제 ID", example = "imp_012345678910")
    private String payId;
    @ApiModelProperty(value = "상품명", example = "컨버스 척테일러 70")
    private String proName;
}