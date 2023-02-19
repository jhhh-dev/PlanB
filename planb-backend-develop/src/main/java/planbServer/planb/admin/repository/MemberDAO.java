package planbServer.planb.admin.repository;

import org.apache.ibatis.annotations.Mapper;
import planbServer.planb.admin.model.MemberSearchDTO;
import planbServer.planb.admin.model.MemberVO;

import java.util.List;

@Mapper
public interface MemberDAO {

    List<MemberVO> getMemberList(MemberSearchDTO memberSearchDTO);
}
