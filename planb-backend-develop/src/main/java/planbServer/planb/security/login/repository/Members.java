package planbServer.planb.security.login.repository;

import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Data;

@Data
public class Members {
    //유저정보를 담는 곳
    @ApiParam(value = "유저 아이디")
    private String userId;

    @ApiParam(value = "유저 비밀번호")
    private String userPw;

    @ApiParam(value = "유저 이름")
    private String userName;

    @ApiParam(value = "유저 역할")
    private String userRole;

    @Builder
    public Members(String userId,
                   String userPw,
                   String userName,
                   String userRole
                   ) {
        this.userId = userId;
        this.userPw = userPw;
        this.userName = userName;
        this.userRole = userRole;
    }
}
