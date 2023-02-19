package planbServer.planb.joins.repository;

import org.apache.ibatis.annotations.Mapper;
import planbServer.planb.joins.model.JoinDTO;

@Mapper
public interface JoinDAO {
    JoinDTO findEmail(JoinDTO joinDTO);
    void updateTempPw(JoinDTO joinDTO);
    JoinDTO findPw(JoinDTO joinDTO);
    Integer joinUser(JoinDTO joinDTO);
    JoinDTO checkEmail(JoinDTO joinDTO);
    JoinDTO checkPhone(JoinDTO joinDTO);

}
