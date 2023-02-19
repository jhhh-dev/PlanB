package planbServer.planb.admin.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class MemberVO {

    @ApiModelProperty(value = "사용자 ID(이메일)", example = "test@example.com")
    private String userId;

    @ApiModelProperty(value = "비밀번호")
    private String userPw;

    @ApiModelProperty(value = "사용자 이름", example = "홍길동")
    private String userName;

    @ApiModelProperty(value = "사용자 연락처", example = "01012345678")
    private String userPhone;

    @ApiModelProperty(value = "사용자 주소", example = "서울시 영등포구 영등포로 150 생각공장 당산 B동 1509호")
    private String userAddr;

    @ApiModelProperty(value = "가입 일자", example = "yyyy-MM-dd HH:mm:ss")
    private Timestamp userDate;
}
