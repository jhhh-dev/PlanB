package planbServer.planb.security.login.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import planbServer.planb.security.exception.ForbiddenException;
import planbServer.planb.security.exception.UserNotFoundException;
import planbServer.planb.security.login.model.LoginMapper;
import planbServer.planb.security.login.repository.AuthenticationDTO;
import planbServer.planb.security.login.repository.LoginDTO;
import planbServer.planb.security.login.repository.Members;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {
    @Autowired
    LoginMapper loginMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public AuthenticationDTO selectLogin(LoginDTO loginDTO) {
        Members loginEntity = loginDTO.toEntity();
        log.info("여기왔음"+ loginEntity);

        Members member = loginMapper.findId(loginDTO);

        if(member.getUserId() == null){
            throw new UserNotFoundException("아이디 불일치");
        }

        if(!passwordEncoder.matches(loginEntity.getUserPw(), member.getUserPw())){
            throw new ForbiddenException("비밀버호 불일치");
        }
        //log.info("여기왔음@@@@@@@@@@@@@@@@@@");
        return loginMapper.selectLogin(loginDTO);
    }

}
