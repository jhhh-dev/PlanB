package planbServer.planb.mypage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import planbServer.planb.mypage.model.*;
import planbServer.planb.mypage.repository.UserInfoDAO;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDAO userInfoDAO;


    @Override
    public MemberDTO selectUserInfo(MemberDTO memberDTO) {
        return userInfoDAO.selectUserInfo(memberDTO);
    }

    @Override
    public MemberDTO selectUserName(MemberDTO memberDTO) {
        return userInfoDAO.selectUserName(memberDTO);
    }

    @Override
    public int updateUserName(MemberDTO memberDTO) {
        return userInfoDAO.updateUserName(memberDTO);
    }

    @Override
    public List<String> selectNameList() {
        return userInfoDAO.selectNameList();
    }

    @Override
    public int updateUserPw(MemberDTO memberDTO) {
        //memberDTO.setUserPw(passwordEncoder.encode(memberDTO.getUserPw()));
        return userInfoDAO.updateUserPw(memberDTO);
    }

    @Override
    public MemberDTO selectPw(MemberDTO memberDTO) {
        return userInfoDAO.selectPw(memberDTO);
    }

    @Override
    public void updateUserAddr(MemberDTO memberDTO) {
        userInfoDAO.updateUserAddr(memberDTO);
    }

    @Override
    public List<BookMarkListDTO> selectBMList(MemberDTO memberDTO) {
        return userInfoDAO.selectBMList(memberDTO);
    }

    @Override
    public List<ReviewListDTO> selectReviewList(ListDTO listDTO) {
        return userInfoDAO.selectReviewList(listDTO);
    }

    @Override
    public List<OrderDetailDTO> selectOrderList(ListDTO listDTO) {
        return userInfoDAO.selectOrderList(listDTO);
    }

    @Override
    public OrderDetailDTO selectOrderDetail(OrderDetailDTO orderDetailDTO) {
        return userInfoDAO.selectOrderDetail(orderDetailDTO);
    }
}
