package planbServer.planb.security.login.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import planbServer.planb.security.login.repository.AuthenticationDTO;
import planbServer.planb.security.login.repository.LoginDTO;
import planbServer.planb.security.login.service.LoginService;

@RestController
@Slf4j
@Api("로그인API Controller")
//@RequestMapping("/test")
public class LoginController {

    @Autowired
    LoginService loginService;



    //로그인 처리
    @ApiOperation(value = "로그인")
    @PostMapping("/login")
    public ResponseEntity<AuthenticationDTO> selectLogin(@RequestBody LoginDTO loginDTO){

        //로그인 아이디, 비밀번호를 받음
        log.info("controller.UserId ::: " + loginDTO.getUserId());
        log.info("controller.UserPw ::: " + loginDTO.getUserPw());

        AuthenticationDTO authenticationDTO = loginService.selectLogin(loginDTO);

        return ResponseEntity.ok().body(authenticationDTO);
    }
}
