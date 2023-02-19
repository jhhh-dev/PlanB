package planbServer.planb.mypage.model;

import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class MemberDTO {
    @ApiParam(value = "사용자 아이디")
    private String userId;

    @ApiParam(value = "사용자 비밀번호")
    private String userPw;

    @ApiParam(value = "사용자 이름")
    private String userName;

    @ApiParam(value = "사용자 핸드폰번호")
    private String userPhone;

    @ApiParam(value = "사용자 주소")
    private String userAddr;

    @ApiParam(value = "사용자 가입일")
    private String userDate;

}
