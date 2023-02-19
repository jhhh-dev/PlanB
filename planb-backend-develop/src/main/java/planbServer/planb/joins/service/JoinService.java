package planbServer.planb.joins.service;

import planbServer.planb.joins.model.JoinDTO;

public interface JoinService {
    public JoinDTO findEmail(JoinDTO joinDTO);
    Integer joinUser(JoinDTO joinDTO);
    JoinDTO checkEmail(JoinDTO joinDTO);
    JoinDTO checkPhone(JoinDTO joinDTO);
}
