package planbServer.planb.security.login.model;

import org.apache.ibatis.annotations.Mapper;
import planbServer.planb.security.login.repository.AuthenticationDTO;
import planbServer.planb.security.login.repository.LoginDTO;
import planbServer.planb.security.login.repository.Members;

@Mapper
public interface LoginMapper {
    AuthenticationDTO selectLogin(LoginDTO loginDTO);

    Members findId(LoginDTO loginDTO);

    //Members getUser(String username);

}
