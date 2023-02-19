package planbServer.planb.admin.service;

import planbServer.planb.admin.model.MemberSearchDTO;
import planbServer.planb.admin.model.MemberVO;

import java.util.List;

public interface MemberService {

//    public List<MemberVO> getMemberList();
    public List<MemberVO> getMemberList(MemberSearchDTO memberSearchDTO);
}
