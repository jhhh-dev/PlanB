package planbServer.planb.joins.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import planbServer.planb.joins.model.JoinDTO;
import planbServer.planb.joins.service.JoinService;

@Api("로그인기능API Controller")
@Slf4j
@RestController
//@RequestMapping("")
public class JoinController {
    @Autowired
    JoinService loginsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @ApiOperation(value = "이메일 찾기")
    @GetMapping("/join/findEmail")
    public String findEmail(JoinDTO joinDTO){
      //  log.info(loginsDTO.getUserPhone() + "##@W$#@$%@#%@#%#@");
        JoinDTO dto = loginsService.findEmail(joinDTO);

        if(dto == null){
            return "NO";
        }else{
            return dto.getUserId();
        }

    }

    @ApiOperation(value = "회원가입하기")
    @PostMapping("/joins")
    public int joinTest(@RequestBody JoinDTO joinDTO){
        log.info("joinController //// joinDTO " + joinDTO);

        JoinDTO emailDto = loginsService.checkEmail(joinDTO);
        log.info("joinController //// emailDto " + emailDto);
        if(emailDto == null){
            JoinDTO phoneDto = loginsService.checkPhone(joinDTO);
            if(phoneDto == null){
                joinDTO.setUserPw(passwordEncoder.encode(joinDTO.getUserPw()));
                int result = loginsService.joinUser(joinDTO);
                log.info("joinController //// 회원가입 //// " + result);
                return result;
            }else {
                log.info("joinController //// 핸드폰 번호 중복 //// ");
                return -1;
            }
        }else{
            log.info("joinController //// 이메일 중복 //// ");
            return 0;
        }
    }


}
