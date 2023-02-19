package planbServer.planb.security.login.repository;

import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class LoginDTO {
    //로그인 요청 정보를 담는 곳
    @ApiParam(value = "로그인 유저 아이디")
    private String userId;

    @ApiParam(value = "로그인 유저 비밀번호")
    private String userPw;


    public Members toEntity() {

        Members build = Members.builder()
                .userId(userId)
                .userPw(userPw)
                .build();

        return build;
    }
}
