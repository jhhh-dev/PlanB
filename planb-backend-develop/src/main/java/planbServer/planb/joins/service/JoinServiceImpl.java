package planbServer.planb.joins.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import planbServer.planb.joins.model.JoinDTO;
import planbServer.planb.joins.repository.JoinDAO;

@Slf4j
@Service
public class JoinServiceImpl implements JoinService {
    @Autowired
    JoinDAO loginsDAO;



    @Override
    public JoinDTO findEmail(JoinDTO loginsDTO) {
      //  log.info(loginsDTO.getUserPhone()+"32452523532");
        JoinDTO dto = loginsDAO.findEmail(loginsDTO);
       // log.info(dto.getUserId()+"3413245efs");
        return dto;
    }

    @Override
    public Integer joinUser(JoinDTO joinDTO) {
        //loginsDTO.setUserPw(passwordEncoder.encode(loginsDTO.getUserPw()));
        return loginsDAO.joinUser(joinDTO);
    }

    @Override
    public JoinDTO checkEmail(JoinDTO loginsDTO) {
        return loginsDAO.checkEmail(loginsDTO);
    }

    @Override
    public JoinDTO checkPhone(JoinDTO loginsDTO) {
        return loginsDAO.checkPhone(loginsDTO);
    }
}
