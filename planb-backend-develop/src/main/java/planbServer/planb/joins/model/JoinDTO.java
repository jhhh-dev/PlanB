package planbServer.planb.joins.model;

import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class JoinDTO {
    @ApiParam(value = "유저 아이디", example = "asdf@m2mglobal.com")
    private String userId;
    @ApiParam(value = "유저 핸드폰 번호", example = "01022222222")
    private String userPhone;
    @ApiParam(value = "유저 비밀번호")
    private String userPw;
}
