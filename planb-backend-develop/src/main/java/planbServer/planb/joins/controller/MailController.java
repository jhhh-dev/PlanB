package planbServer.planb.joins.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import planbServer.planb.joins.model.JoinDTO;
import planbServer.planb.joins.service.MailService;

@Api("메일발송API Controller")
@Slf4j
@RestController
public class MailController {

    @Autowired
    private MailService mailService;

    @ApiOperation(value = "임시 비밀번호 이메일 발송")
    @PostMapping("/logins/sendMail")
    public int sendEmail(@RequestBody JoinDTO loginsDTO){
        //log.info(loginsDTO+"32324324");
        if(mailService.findPw(loginsDTO) == null){
            return 0;
        }else{
            mailService.mailSend(mailService.getMail(loginsDTO));
            return 1;
        }

    }
}
