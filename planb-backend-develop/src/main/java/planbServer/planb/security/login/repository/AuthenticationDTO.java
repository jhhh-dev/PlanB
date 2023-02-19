package planbServer.planb.security.login.repository;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationDTO {
    //로그인 후 정보를 담아 전달
    @ApiParam(value = "유저 아이디")
    private String userId;
    @ApiParam(value = "유저 비밀번호")
    private String userPw;
    @ApiParam(value = "유저 이름")
    private String userName;
    @ApiParam(value = "유저 핸드폰 번호")
    private String userPhone;
    @ApiParam(value = "유저 역할")
    private String userRole;
    
}
