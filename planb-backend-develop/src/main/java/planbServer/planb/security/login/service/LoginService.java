package planbServer.planb.security.login.service;

import planbServer.planb.security.login.repository.AuthenticationDTO;
import planbServer.planb.security.login.repository.LoginDTO;

public interface LoginService {
    public AuthenticationDTO selectLogin(LoginDTO loginDTO);
//    public LoginDTO findId(LoginDTO loginDTO);
}
