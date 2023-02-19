package planbServer.planb.mypage.repository;

import org.apache.ibatis.annotations.Mapper;
import planbServer.planb.mypage.model.*;

import java.util.List;

@Mapper
public interface UserInfoDAO {
    MemberDTO selectUserInfo(MemberDTO memberDTO);

    MemberDTO selectUserName(MemberDTO memberDTO);

    int updateUserName(MemberDTO memberDTO);

    List<String> selectNameList();

    int updateUserPw(MemberDTO memberDTO);

    MemberDTO selectPw(MemberDTO memberDTO);

    void updateUserAddr(MemberDTO memberDTO);

    List<BookMarkListDTO> selectBMList(MemberDTO memberDTO);

    List<ReviewListDTO> selectReviewList(ListDTO listDTO);

    List<OrderDetailDTO> selectOrderList(ListDTO listDTO);

    OrderDetailDTO selectOrderDetail(OrderDetailDTO orderDetailDTO);
}
