package planbServer.planb.mypage.model;

import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class OrderDetailDTO {
    @ApiParam(value = "주문 번호")
    private int orderId;
    @ApiParam(value = "주문 날짜")
    private String orderDate;
    @ApiParam(value = "제품 아이디")
    private int proId;
    @ApiParam(value = "배달 요금")
    private String orderFee;
    @ApiParam(value = "배송 받는이 이름")
    private String orderReciver;
    @ApiParam(value = "배송 주소")
    private String orderAddr;
    @ApiParam(value = "배송 핸드폰 번호")
    private String orderPhone;
    @ApiParam(value = "결제 총 가격")
    private String payPrice;
    @ApiParam(value = "결제 가격")
    private String payType;
    @ApiParam(value = "제품 가격")
    private String proPrice;
    @ApiParam(value = "제품 이름")
    private String proName;
}
