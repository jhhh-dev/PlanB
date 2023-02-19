package planbServer.planb.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import planbServer.planb.admin.model.MemberSearchDTO;
import planbServer.planb.admin.model.MemberVO;
import planbServer.planb.admin.repository.MemberDAO;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService{

    @Autowired
    private MemberDAO memberDAO;

    @Override
    public List<MemberVO> getMemberList(MemberSearchDTO memberSearchDTO){

        return memberDAO.getMemberList(memberSearchDTO);
    };

}
