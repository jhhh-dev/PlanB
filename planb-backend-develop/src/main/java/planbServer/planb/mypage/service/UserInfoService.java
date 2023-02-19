package planbServer.planb.mypage.service;

import planbServer.planb.mypage.model.*;

import java.util.List;

public interface UserInfoService {

    public MemberDTO selectUserInfo(MemberDTO memberDTO);

    public MemberDTO selectUserName(MemberDTO memberDTO);

    public int updateUserName(MemberDTO memberDTO);

    public List<String> selectNameList();

    public int updateUserPw(MemberDTO memberDTO);

    public MemberDTO selectPw(MemberDTO memberDTO);

    public void updateUserAddr(MemberDTO memberDTO);

    public List<BookMarkListDTO> selectBMList(MemberDTO memberDTO);

    public List<ReviewListDTO> selectReviewList(ListDTO listDTO);

    public List<OrderDetailDTO> selectOrderList(ListDTO listDTO);

    public OrderDetailDTO selectOrderDetail(OrderDetailDTO orderDetailDTO);

}
